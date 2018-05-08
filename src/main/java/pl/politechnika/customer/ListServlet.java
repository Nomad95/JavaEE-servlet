package pl.politechnika.customer;


import pl.config.jdbc.JdbcManager;
import pl.politechnika.beans.customer.CustomerBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            ArrayList<CustomerBean> customers = new ArrayList<>();

            ResultSet rs = JdbcManager.query("SELECT * FROM CUSTOMERS");
            while (rs.next()) {
                CustomerBean customerBean = new CustomerBean();
                customerBean.setId(rs.getLong("id"));
                customerBean.setName(rs.getString("name"));
                customerBean.setPhone(rs.getString("phone"));
                customerBean.setEmail(rs.getString("email"));
                customerBean.setCity(rs.getString("city"));
                customers.add(customerBean);
            }

            request.getSession().setAttribute("customerList", customers);
            response.sendRedirect("/list/customerList.jsp");

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }


}
