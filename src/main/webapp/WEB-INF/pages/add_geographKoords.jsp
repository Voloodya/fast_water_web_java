<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vladimir
  Date: 29.03.2019
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--Модель "koordsFromServer" будет передана в контроллер "/fast-water/water/add_koords"--%>
<spring:form modelAttribute="koordsFromServer" method="put" action="/fast-water/water/add_koords">
    <spring:input path="typeKoordinate" title="Тип координат"/>
    <spring:input path="srid"/>
    <spring:input path="radiusAction"/>
    <spring:input path="heighSeaLevel"/>
    <spring:input path="koordinateTxt"/>
    <spring:input path="description"/>
    <spring:button>Write koords</spring:button>
</spring:form>
</body>
</html>
