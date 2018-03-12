<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
    <title>Kalkulator</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div>Podaj dane do operacji</div>
<form method="POST" action="/calc">
    <input type="text" name="leftParam"/>
    <select name="operation">
        <option selected="selected">+</option>
        <option>-</option>
        <option>*</option>
        <option>/</option>
    </select>
    <input type="text" name="rightParam"/>
    <input type="submit" value="=">
</form>
</body>
</html>
