<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vladimir
  Date: 28.04.2019
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<spring:form modelAttribute="idPostFromServer" method="post" action="/fast-water/water/post">
    <spring:input path="idPost" title="ID Поста"/>
    <spring:button>Write koords</spring:button>
</spring:form>
</body>
</html>
