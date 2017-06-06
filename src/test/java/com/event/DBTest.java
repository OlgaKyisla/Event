package com.event;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBTest {
    @Test
    public void dbConnectionTest() {
        DBHelper dbHelper = new DBHelper();
        Connection connection = dbHelper.getConnection();
        Assert.assertNotNull(connection);
    }
    @Test
    public void dbLoginTest() throws SQLException {
        DBHelper dbHelper = new DBHelper();
        ResultSet test = dbHelper.login("test@gmail.com", "test");
        Assert.assertTrue(test.next());
        test = dbHelper.login("rere", "wew");
        Assert.assertFalse(test.next());
    }
}
