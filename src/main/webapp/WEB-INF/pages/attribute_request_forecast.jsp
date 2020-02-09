<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vladimir
  Date: 22.05.2019
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Форма для атрибутов запроса</title>
</head>
<body>
<div>
    <spring:form method="get" action="/fast-water/water/main">
        <spring:input path="latitude"/>
        <spring:input path="longitude"/>
        <spring:input path="yearStart"/>
        <spring:input path="monthStart"/>
        <spring:input path="dayStart"/>
        <spring:input path="houreStart"/>
        <spring:input path="yearFinish"/>
        <spring:input path="monthFinish"/>
        <spring:input path="dayFinish"/>
        <spring:input path="houreFinish"/>
        <spring:input path="servisAPI"/>
        <spring:button>Обновить данные</spring:button>
    </spring:form>
</div>
</body>
</html>
