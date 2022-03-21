<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 07.12.2021
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<jsp:include page="headers.jsp"/>

<h1>${error}</h1>
       <form method="post" action="login">
           <label>Name:
               <input type="text" name="login"><br />
           </label>

           <label>Password:
               <input type="password" name="password"><br />
           </label>
           <button type="submit">Submit</button>
       </form>
</body>
</html>
