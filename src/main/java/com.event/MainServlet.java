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
import java.sql.Statement;

public class MainServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        try {

            String date = request.getParameter("date");
            String price = request.getParameter("price");
            String city = request.getParameter("city");

            String query = "SELECT * FROM  event WHERE  %1 AND %2 AND %3";

            DBHelper db = new DBHelper();

            Connection conn = db.getConnection();

            if (city.equals("")) {
                query = query.replace("%1", "1");
            } else {
                query = query.replace("%1", "city = " + "'" + city + "'");
            }

            if (price.equals("0")) {
                query = query.replace("%2", "1");
            } else {
                query = query.replace("%2", "price =" +"'" +  price + "'");
            }

            if (date.equals("")) {
                query = query.replace("%3", "1");
            } else {
                query = query.replace("%3", "date = '" + date + "'");
            }

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            String result = "";

            while (resultSet.next()) {

                String dateResult           = resultSet.getString("date");
                String priceResult          = resultSet.getString("price");
                String cityResult           = resultSet.getString("city");
                String descriptionResult    = resultSet.getString("description");

                result += dateResult + ";";
                result += priceResult + ";";
                result += cityResult + ";";
                result += descriptionResult;

                result += "|";

            }

            out.write(result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
