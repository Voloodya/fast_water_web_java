<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vladimir
  Date: 20.05.2019
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<H1>Прогноз с ресурса DarkSky</H1>
<div>
    <table class="flood">
        <thead>
        <tr>
            <th>Координаты</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Облачность</th>
            <th>Осадки</th>
            <th>t</th>
            <th>Ночная t</th>
            <th>Дневная t</th>
            <th>Влажность</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listForecastDarkSky}" var="forecastDarkSky">
            <tr>
                <td class="geo">${forecastDarkSky.koords}</td>
                <td>${forecastDarkSky.localDateStr}</td>
                <td>${forecastDarkSky.localTimeStr}</td>
                <td>${forecastDarkSky.cloudCover}</td>
                <td>${forecastDarkSky.precipIntensity}</td>
                <td>${forecastDarkSky.temperature}</td>
                <td>${forecastDarkSky.temperatureHigh}</td>
                <td>${forecastDarkSky.temperatureLow}</td>
                <td>${forecastDarkSky.humidity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<H1>Прогноз с ресурса WeatherOpenMap</H1>
<div>
    <table class="flood">
        <thead>
        <tr>
            <th>Координаты</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Облачность</th>
            <th>Осадки</th>
            <th>Средняя t</th>
            <th>Максимальная t</th>
            <th>Минимальная t</th>
            <th>Влажность</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listForecastOpenMap}" var="forecastOpenMap">
            <tr>
                <td class="geo">${forecastOpenMap.koords}</td>
                <td>${forecastOpenMap.localDateStr}</td>
                <td>${forecastOpenMap.localTimeStr}</td>
                <td>${forecastOpenMap.clouds}</td>
                <td>${forecastOpenMap.rain3h}</td>
                <td>${forecastOpenMap.temperature}</td>
                <td>${forecastOpenMap.temperatureMax}</td>
                <td>${forecastOpenMap.temperatureMin}</td>
                <td>${forecastOpenMap.humidity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
