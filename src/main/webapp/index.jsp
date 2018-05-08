<%@ page import="pl.config.jdbc.JDBCConnection" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
    <title>TODO supply a title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="bootstrap/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<%! private String title = ""; %>
<%! private static int overallVisitCount = 0; %>
<%! private int sessionVisitCount = 0; %>
<%! public static final String VISITED_SITE_COOKIE = "visitedSite";
    public static final String TIMES_VISITED_ATTR = "timesVisited";
     %>


<span>Dzisiejsza data: <%=LocalDate.now()%></span> <br/>
<%
    List<Cookie> cookies = request.getCookies() != null ? Arrays.asList(request.getCookies()) : new ArrayList<Cookie>();
    Optional<Cookie> maybeCookie = Optional.empty();
    for (Cookie cookie : cookies) {
        if(VISITED_SITE_COOKIE.equals(cookie.getName())) {
            maybeCookie = Optional.of(cookie);
        }
    }

    if (maybeCookie.isPresent()) {
        title = "Witaj ponownie!";
    } else {
        title = "Witaj po raz pierwszy";
        Cookie cookie = new Cookie("visitedSite", "true");
        cookie.setMaxAge(60*60);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    JDBCConnection.testConnection();
%>
<%
    HttpSession userSession = request.getSession(true);
    if(Objects.nonNull(userSession.getAttribute(TIMES_VISITED_ATTR))) {
        int timesVisited = (int) userSession.getAttribute(TIMES_VISITED_ATTR);
        sessionVisitCount = ++timesVisited;
        userSession.setAttribute(TIMES_VISITED_ATTR, timesVisited);
    } else {
        sessionVisitCount = 1;
        userSession.setAttribute(TIMES_VISITED_ATTR, 1);
    }
    overallVisitCount++;
%>
<h2><%=title%></h2>
<p>Odwiedzin w sesji: <%=sessionVisitCount%></p><br />
<p>Odwiedzin w czasie dzialania aplikacji: <%=overallVisitCount%></p><br />

<a href="calc/calc.jsp"><button class="btn btn-default" role="button">Kalkulator</button></a>
<a href="poll/poll.jsp"><button class="btn btn-default" role="button">Sonda</button></a>
<a href="customer/list"><button class="btn btn-default" role="button">Klienci</button></a>


<script src="bootstrap/jquery-3.3.1.min.js"></script>
<script>window.jQuery || document.write('<script src="bootstrap/jquery-3.3.1.min.js"><\/script>')</script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
</body>
</html>
