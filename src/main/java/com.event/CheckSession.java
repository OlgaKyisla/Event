package com.event;

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

public class CheckSession extends HttpServlet {

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        try {

            HttpSession session = request.getSession(false);

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            if (session == null) {
                out.print("NotAutorized");

            } else {
                out.print(session.getAttribute("login"));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
