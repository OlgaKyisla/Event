package com.event;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        try {
            String name = request.getParameter("name");
            String login = request.getParameter("login");
            String pass = request.getParameter("pass");

            String query = "INSERT  INTO  user (login, pass, name) VALUE (?, ?, ?)";

            DBHelper db = new DBHelper();

            Connection conn = db.getConnection();

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, login);
            statement.setString(2, pass);
            statement.setString(3, name);

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");


            try {
                statement.execute();
            }
            catch (MySQLIntegrityConstraintViolationException exception)
            {
               out.write("failed");
            }
     }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
