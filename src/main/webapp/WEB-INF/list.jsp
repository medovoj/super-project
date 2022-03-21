<%@ page import="app.entities.Book" %>
<%@ page import="app.dao.BookDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 07.12.2021
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>List Books</title>
</head>
<body>
    <jsp:include page="headers.jsp"/>
    <div>
        <h1>Books collection</h1>
    <table>
        <tr>
            <th></th>
            <th>name</th>
            <th>price</th>
        </tr>


        <c:forEach  var="book" items="${books}" >

        <form action="buy" method="post">
            <input type="hidden" name="book" value="${book.name}">

        <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>

                <td>
                    <input type = "submit"  value = "Buy" />
                </td>
                <td>
                    Count: <input type = "text" name = "number" /> <br />
                </td>
        </tr>
        </c:forEach>
    </table>
    <jsp:include page="footer.jsp" />
    </div>

</body>
</html>
