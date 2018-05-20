package pl.politechnika.customer;


import pl.config.jdbc.customer.CustomerQueryBuilder;
import pl.politechnika.beans.customer.CustomerBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddCustomerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            CustomerBean newCustomerBean = new CustomerBean();

            newCustomerBean.setDiscountCode(request.getParameter("discountCode"));
            newCustomerBean.setZip(request.getParameter("zip"));
            newCustomerBean.setName(request.getParameter("name"));
            newCustomerBean.setAddressLine1(request.getParameter("addressLine1"));
            newCustomerBean.setAddressLine2(request.getParameter("addressLine2"));
            newCustomerBean.setCity(request.getParameter("city"));
            newCustomerBean.setState(request.getParameter("state"));
            newCustomerBean.setPhone(request.getParameter("phone"));
            newCustomerBean.setFax(request.getParameter("fax"));
            newCustomerBean.setEmail(request.getParameter("email"));
            newCustomerBean.setCreditLimit(request.getParameter("creditLimit"));

            System.out.println(newCustomerBean);
            CustomerQueryBuilder.saveCustomer(newCustomerBean);

            response.sendRedirect("/customer/list");

        } catch (NullPointerException e) {
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
