package com.queue.ormlite.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.queue.ormlite.model.Book;

import java.sql.SQLException;

/**
 * Created by semicolon on 10/8/18.
 */

public class BookDao extends BaseDaoImpl<Book, Integer> implements BookDaoInterface {

    public BookDao(final ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Book.class);
    }
}
