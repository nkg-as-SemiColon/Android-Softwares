package com.queue.ormlite.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.queue.ormlite.R;
import com.queue.ormlite.db.MySqlDBhelper;
import com.queue.ormlite.model.Author;
import com.queue.ormlite.model.Book;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MySqlDBhelper dbHelper;
    private Dao<Book, Integer> bookDao;
    private Dao<Author, Integer> authorDao;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        These two lines are to ensure that android os doesn't throw error due to network access in the
        main thread. Generally android recommends that all the interner access should be done
        in separate thread using AsyncTask.
        */
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        dbHelper = new MySqlDBhelper();

        bookDao = dbHelper.getBookDao();
        authorDao = dbHelper.getAuthorDao();

        textView = (TextView) findViewById(R.id.text_view);

        asyncTask(bookDao, authorDao);



    }

    public void asyncTask(final Dao<Book, Integer> bookDao, final Dao<Author, Integer> authorDao){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.progress_circle).setVisibility(View.VISIBLE);
                populateTable(bookDao, authorDao);
                doSomeTask(5000);
                final List<Book> books = fetchAndPrintData(bookDao, authorDao);

                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.progress_circle).setVisibility(View.GONE);
                        textView.setText(books.toString());
                    }
                });
            }
        };
        new Thread(runnable).start();
    }


    public void populateTable(Dao<Book, Integer> bookDao, Dao<Author, Integer> authorDao){

        Book book = new Book();
        book.setName("Physics Concept I");
        book.setAuthor(new Author("HC Verma"));

        try {
            bookDao.create(book);
        } catch (SQLException e){
            e.printStackTrace();
        }

        Book book1 = new Book();
        book1.setName("Physics Concept II");
        try {
            List<Author> authorList = authorDao.queryForEq("name", "HC Verma");
            book1.setAuthor((Author) ((authorList.size() == 0) ? new Author("HC Verma") : authorList.get(0)));
            bookDao.create(book1);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Book> fetchAndPrintData(Dao<Book, Integer> bookDao, Dao<Author, Integer> authorDao){
        List<Book> bookList;
        try{
            bookList = bookDao.queryForAll();
            return bookList;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void doSomeTask(int milis){
        //Thread.sleep(milis);
        SystemClock.sleep(milis);
    }
}
