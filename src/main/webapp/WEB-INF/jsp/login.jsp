<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\1\7 0007
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>小春论坛登录</title>
</head>
<body>
    <c:if test = "${!empty error}">
         <font color="red"><c:out value="${error}"/></font>
    </c:if>

    <form action="<c:url value="/loginCheck.html"/>" method ="post">
        用户名：
        <input type="text " name="username"/>
        <br>
        密码：
        <input type="password" name="password"/>
        <br>
        <input type="submit" name="登录"/>
        <input type="reset" name="重置"/>
    </form>
</body>
</html>
