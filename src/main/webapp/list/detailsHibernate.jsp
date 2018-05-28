<%@ page import="pl.politechnika.beans.customer.CustomerEntity" %>
<%@ page import="pl.politechnika.repository.customer.impl.CustomerRepositoryImpl" %>
<%@ page import="java.util.Objects" %>
<!DOCTYPE html>

<html>
<head>
    <title>Lista Konsumentow</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<%
    if (Objects.nonNull(request.getParameter("id"))) {
        CustomerEntity customer = new CustomerRepositoryImpl().getOne(Long.parseLong(request.getParameter("id")));
%>
<table>
    <tr>
        <td><%=customer.getId()%></td>
        <td><%=customer.getName()%></td>
        <td><%=customer.getPhone()%></td>
        <td><%=customer.getEmail()%></td>
        <td><%=customer.getCity()%></td>
        <td><%=customer.getAddressLine1()%></td>
        <td><%=customer.getAddressLine2()%></td>
        <td><%=customer.getZip()%></td>
        <td><%=customer.getState()%></td>
        <td><%=customer.getFax()%></td>
        <td><%=customer.getDiscountCode()%></td>
        <td><%=customer.getCreditLimit()%></td>
    </tr>
</table>
<%
    }
%>
<div>
    <a href="/list/customerListHibernate.jsp">Powrot</a>
</div>

</body>
</html>
