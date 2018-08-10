package com.queue.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.queue.ormlite.dao.BookDao;

/**
 * Created by semicolon on 9/8/18.
 */

@DatabaseTable(tableName = "books", daoClass = BookDao.class)
public class Book {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "author", canBeNull = false, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private Author author;

    public Book(){
    }

    public Book(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                '}';
    }
}
