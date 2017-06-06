package com.event;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddEventServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        try {
            String city = request.getParameter("city");
            String price = request.getParameter("price");

            String date = request.getParameter("date");
            String description = request.getParameter("description");

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            try {
                if( Integer.parseInt(price) < 0 ){
                    throw new MyException();
                }
            }
            catch (MyException e){
                out.print(e.toString());
                return;
            }

            String query = "INSERT  INTO  event (city, price, date, description) VALUE (?, ?, ?, ?)";

            DBHelper db = new DBHelper();

            Connection conn = db.getConnection();

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, city);
            statement.setString(2, price);
            statement.setString(3, date);
            statement.setString(4, description);

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
