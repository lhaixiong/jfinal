<%@ page pageEncoding='UTF-8' contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <%--<link rel="stylesheet" type="text/css" href="abc.css"/>--%>
</head>
<body>
<c:forEach items="${userList}" var="user">
    name:${user.name}<br>
    age:${user.age}<br>
    birthday:${user.birthday}<br>
</c:forEach>
aaa:${aaa}

</body>
</html>
