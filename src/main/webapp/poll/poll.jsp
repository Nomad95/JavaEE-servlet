<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
    <title>Sonda</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div>Ankietka</div>
<form method="POST" action="/poll">
    <input type="checkbox" name="ASP.NET" value="ASP.NET"/>ASP.NET<br/>
    <input type="checkbox" name="JavaScript" value="JavaScript"/>JavaScript<br/>
    <input type="checkbox" name="PHP" value="PHP"/>PHP<br/>
    <input type="checkbox" name="Python" value="Python"/>Python<br/>
    <input type="checkbox" name="RubyOnRails" value="RubyOnRails"/>Ruby On Rails<br/>
    <input type="checkbox" name="Servlets & JSP" value="Servlets & JSP"/>Servlets & JSP<br/>

    <input type="submit" value="Glosuj">
</form>
</body>
</html>
