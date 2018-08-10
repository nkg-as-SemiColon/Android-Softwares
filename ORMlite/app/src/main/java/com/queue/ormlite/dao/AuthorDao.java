package com.queue.ormlite.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.queue.ormlite.model.Author;

import java.sql.SQLException;

/**
 * Created by semicolon on 10/8/18.
 */

public class AuthorDao extends BaseDaoImpl<Author, Integer> implements AuthorDaoInterface {

    public AuthorDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Author.class);
    }
}
