<%@ page pageEncoding='UTF-8' contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <c:if test="${!empty userList}">
        <c:forEach items="${userList}" var="bean">
            id:${bean.id}<br>
            groupId:${bean.groupId}<br>
            userName:${bean.userName}<br>
            nickName:${bean.nickName}<br>
            password:${bean.password}<br>
            createTime:${bean.createTime}<br>
        </c:forEach>
    </c:if>
</body>
</html>
