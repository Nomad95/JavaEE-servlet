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

            newCustomerBean.setDiscountCode((String) request.getAttribute("discountCode"));
            newCustomerBean.setZip((String) request.getAttribute("zip"));
            newCustomerBean.setName((String) request.getAttribute("name"));
            newCustomerBean.setAddressLine1((String) request.getAttribute("addressLine1"));
            newCustomerBean.setAddressLine2((String) request.getAttribute("addressLine2"));
            newCustomerBean.setCity((String) request.getAttribute("city"));
            newCustomerBean.setState((String) request.getAttribute("state"));
            newCustomerBean.setPhone((String) request.getAttribute("phone"));
            newCustomerBean.setFax((String) request.getAttribute("fax"));
            newCustomerBean.setEmail((String) request.getAttribute("email"));
            newCustomerBean.setCreditLimit((String) request.getAttribute("creditLimit"));

            System.out.println(newCustomerBean);//dlaczego null??
            CustomerQueryBuilder.saveCustomer(newCustomerBean);

            response.sendRedirect("/list/customerList.jsp");

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
