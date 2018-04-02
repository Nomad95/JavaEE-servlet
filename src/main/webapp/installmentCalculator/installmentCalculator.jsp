<!DOCTYPE html>

<html>
<head>
    <title>Kalkulator</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:useBean id="installmentCalculatorBean" class="pl.politechnika.installmentCalculator.InstallmentCalculatorBean" scope="session" />
<jsp:setProperty name="installmentCalculatorBean" property="*" />


<jsp:include page="../httpElements/header.jsp" />

<div>Kalkulator rat</div>
<form method="POST" action="">
    Kwota pozyczki<input type="text" name="loanAmount"/><br/>
    Oprocentowanie<input type="text" name="interest"/><br/>
    Liczba rat<input type="number" name="numberOfInstallments"/><br/>
    <input type="submit" value="Oblicz">
</form>

<h2><%= installmentCalculatorBean.calculateInstallment() %></h2>

<jsp:include page="../httpElements/footer.jsp" />

</body>
</html>
