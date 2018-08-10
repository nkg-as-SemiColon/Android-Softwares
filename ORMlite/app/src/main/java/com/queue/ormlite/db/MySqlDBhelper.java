package com.queue.ormlite.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.queue.ormlite.model.Author;
import com.queue.ormlite.model.Book;
import com.queue.ormlite.utils.Configuration;

import java.sql.SQLException;

/**
 * Created by semicolon on 9/8/18.
 */

public class MySqlDBhelper {

    /*
    JdbcPooledConnectionSource is used for pooling the connections. While JdbcConnectionSource
    makes simple connections.
    */
    private JdbcPooledConnectionSource pooledConnectionSource;
    //private ConnectionSource connectionSource;

    private Dao<Book, Integer> bookDao = null;
    private Dao<Author, Integer> authorDao = null;

    public MySqlDBhelper(){
        try {
            //The Configuration class is intentionally gitignored. It contains database credentials.
            //IP ADDRESS : 10.0.2.2:3306 is for the mysql running on localhost machine i.e. laptop.
            pooledConnectionSource = new JdbcPooledConnectionSource("jdbc:mysql://10.0.2.2:3306/"+ Configuration.DATABASE_NAME,Configuration.DATABASE_USER,Configuration.DATABASE_PASSWORD);
            //connectionSource = new JdbcConnectionSource("jdbc:mysql://10.0.2.2:3306/TEST","root","@mysql12");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public Dao<Book, Integer> getBookDao() {
        if (bookDao == null) {
            try{
                bookDao = DaoManager.createDao(pooledConnectionSource, Book.class);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return bookDao;
    }

    public Dao<Author, Integer> getAuthorDao() {
        if (authorDao == null) {
            try{
                authorDao = DaoManager.createDao(pooledConnectionSource, Author.class);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return authorDao;
    }
}
