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

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String login = request.getParameter("login");
            String pass = request.getParameter("pass");




            DBHelper dbHelper = new DBHelper();
            ResultSet resultSet = dbHelper.login(login,pass);

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            if (resultSet.next()) {

                HttpSession session = request.getSession(true);

                session.setAttribute("login", login);

                out.write("success");
            } else {
                out.write("failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
