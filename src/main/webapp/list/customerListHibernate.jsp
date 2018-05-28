<%@ page import="pl.politechnika.beans.customer.CustomerEntity" %>
<%@ page import="pl.politechnika.repository.customer.CustomerRepository" %>
<%@ page import="pl.politechnika.repository.customer.impl.CustomerRepositoryImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<!DOCTYPE html>

<html>
<head>
    <title>Lista Konsumentow</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<%! private List<CustomerEntity> customers = new ArrayList<>(); %>
<%! private CustomerRepository customerRepository = new CustomerRepositoryImpl(); %>
<%
    HttpSession userSession = request.getSession(true);
    if(Objects.nonNull(userSession.getAttribute("customerList"))) {
        customers = customerRepository.getAll();
    }
%>
<div>
    <h2>Lista Customerow (Z pomoca Hibernate)</h2>
</div>
<div>
    <table>
        <%
            for (CustomerEntity customer : customers) {
        %>
        <tr>
            <td><%=customer.getId()%></td>
            <td><%=customer.getName()%></td>
            <td><%=customer.getPhone()%></td>
            <td><%=customer.getEmail()%></td>
            <td><%=customer.getCity()%></td>
            <td><a href="detailsHibernate.jsp?id=<%=customer.getId()%>">Details</a></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<hr />
<div>
    <jsp:useBean id="newCustomerBean" class="pl.politechnika.beans.customer.CustomerBean" scope="session" >
        <jsp:setProperty name="newCustomerBean" property="*" />
    </jsp:useBean>


    <div>Dodaj klienta</div>
    <form method="POST" action="/customer/addHibernate">
        Imie <input type="text" name="name"/><br/>
        Telefon <input type="text" name="phone"/><br/>
        Email <input type="text" name="email"/><br/>
        Miasto <input type="text" name="city"/><br/>
        Adres 1 <input type="text" name="addressLine1"/><br/>
        Adres 2 <input type="text" name="addressLine2"/><br/>
        Kod pocztowy <input type="text" name="zip"/><br/>
        Stan <input type="text" name="state"/><br/>
        Fax <input type="text" name="fax"/><br/>
        Kod znizki <input type="text" name="discountCode"/><br/>
        Limit kredytu<input type="text" name="creditLimit"/><br/>
        <input type="submit" value="Dodaj">
    </form>

</div>

</body>
</html>
