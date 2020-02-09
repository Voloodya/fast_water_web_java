<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vladimir
  Date: 14.05.2019
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Главная страница</title>
    <style>
        /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
        #map {
            height: 65%;
        }
        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
        }
        table {table-layout: fixed;width:98%; height: 200px; margin-top: 16px; border: black; }
        .tr{background: cornflowerblue; color: white; text-align: center; vertical-align: center; horiz-align: center;}
        td {word-wrap:break-word; text-align-all: center; border: black; align: center;}
        .container {
            height: 200px;
            overflow: scroll;
        }
    </style>
    <script>
        var map, marker;
        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: new google.maps.LatLng(51.754207,55.106578),
                zoom: 10
            });
            map.setCenter({
                lat: 51.754207,
                lng: 55.106578
            });
            marker && marker.setMap(null);
            marker=new google.maps.Marker({
                position: {
                    lat: 51.754207,
                    lng: 55.106578
                },
                map: map,
                title: 'Оренбург'
            });
        }

        function UpdateGoogleMap(latitude, longitude,temperatura) {
            latitude = (latitude && +latitude)||51.754207;
            longitude = (longitude && +longitude)||55.106578;
            map.setCenter({
                lat: latitude,
                lng: longitude,
            });
            map.zoom=15;
            marker && marker.setMap(null);
            marker=new google.maps.Marker({
                position: {
                    lat: latitude,
                    lng: longitude,
                },
                map: map,
                title: temperatura
            });
        };
        //document.querySelector('table.flood')
        addEventListener("click", ({target}) => {
        var tr = target.closest("tbody > tr");
        if(!tr) return;
        var position = tr.querySelector("td.geo").textContent;//Берём нужную ячейку
        position = position.split(/\s+/).map(Number); //Разбиваем содержимое ячейки
        var temperatura = tr.querySelector("td.temp").textContent;//Берём нужную ячейку
        UpdateGoogleMap(position[0],position[1],temperatura);
        var city = tr.querySelector("td.city").textContent;//Берём нужную ячейку

        document.form2.latitude2.value=position[0];
        document.form2.longitude2.value=position[1];
        document.form1.location.value=city;
        });
    </script>
    <!-- -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWH__g2XMzhes7PIk-8v2MbBSZTgMnBSg&callback=initMap"
            async defer></script>
</head>

<body>
</div>
<br>
<H2>Исторические данные</H2>
<div class="container">
    <table>
        <thead>
        <tr class="tr">
            <th>Координаты</th>
            <th>Населённый пункт</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Снег</th>
            <th>Дождь</th>
            <th>Снег с дождём</th>
            <th>Влажность</th>
            <th>Дневная t</th>
            <th>Ночная t</th>
            <th>Средняя t</th>
            <th>Макс. t</th>
            <th>Мин. t</th>
            <th>Высота снежного покрова</th>
            <th>Облачность</th>
            <th>t воды</th>
            <th>Толщина льда</th>
            <th>Уровень воды</th>
            <th>Риск затопления</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listFlood}" var="flood">
            <tr>
                <td class="geo">${flood.geographKoordsPost}</td>
                <td class="city">${flood.nameLocality}</td>
                <td>${flood.date}</td>
                <td>${flood.time}</td>
                <td>${flood.snow}</td>
                <td>${flood.rain}</td>
                <td>${flood.snowRain}</td>
                <td>${flood.airHumidity}</td>
                <td>${flood.temperatureDay}</td>
                <td>${flood.temperatureNight}</td>
                <td class="temp">${flood.temperature}</td>
                <td>${flood.temperatureMax}</td>
                <td>${flood.temperatureMin}</td>
                <td>${flood.levelSnow}</td>
                <td>${flood.solaractivity}</td>
                <td>${flood.temperatureWater}</td>
                <td>${flood.heightIceOnWater}</td>
                <td>${flood.levelWater}</td>
                <td>${flood.warningFlood}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<div>
    <form name="form1" method="GET" action="/fast-water/water/main">
    Населённый пункт:    <input type="text" name="location"/>
        <button type="submit">Поиск</button>
    </form>
    <form name="form_button" method="GET" action='/fast-water/water/main?location=""'>
        <button type="submit">Отобразить всё</button>
    </form>
</div>
<br>
<div id="map"></div>
<br>
<div>
    <form name="form2" method="GET" action="/fast-water/water/main">
    Долгота <input type="text" name="latitude2"/>
    Широта <input type="text" name="longitude2"/>
    Год с:<input type="text" name="yearStart2"/>
    Месяц с:<input type="text" name="monthStart2"/>
    День с:<input type="text" name="dayStart2"/>
    Год по:<input type="text" name="yearFinish2"/>
    Месяц по:<input type="text" name="monthFinish2"/>
    День по: <input type="text" name="dayFinish2"/>
        <button tybe="submit">Загрузить</button>
    </form>
</div>
<br>
<!-- -->
<H2>Динамика изменения уровня воды</H2>
<div class="container">
        <table class="container">
            <thead>
            <tr class="tr">
                <th>Координаты</th>
                <th>Населённый пункт</th>
                <th>Дата</th>
                <th>Время</th>
                <th>Уровень воды</th>
                <th>Изменение ур-ня воды</th>
                <th>Скороть изм-ия ур-ня воды (м/ч)</th>
                <th>Риск затопления</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listChangeFlood}" var="ChangeFlood">
                <tr>
                    <td class="geo">${ChangeFlood.geographKoordsPost}</td>
                    <td class="city">${ChangeFlood.nameLocality}</td>
                    <td>${ChangeFlood.date}</td>
                    <td>${ChangeFlood.time}</td>
                    <td>${ChangeFlood.levelWater}</td>
                    <td>${ChangeFlood.changeLevelWater}</td>
                    <td>${ChangeFlood.speedChangeLevelWater}</td>
                    <td>${ChangeFlood.warningFlood}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
<H2>Динамика изменения уровня снежного покрова</H2>
    <div class="container">
        <table>
            <thead>
            <tr class="tr">
                <th>Координаты</th>
                <th>Населённый пункт</th>
                <th>Дата</th>
                <th>Время</th>
                <th>Уровень снега</th>
                <th>Изменение ур-ня снега</th>
                <th>Скороть изм-ия ур-ня снега (м/ч)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listChangeFlood}" var="ChangeFlood">
                <tr>
                    <td class="geo">${ChangeFlood.geographKoordsPost}</td>
                    <td class="city">${ChangeFlood.nameLocality}</td>
                    <td>${ChangeFlood.date}</td>
                    <td>${ChangeFlood.time}</td>
                    <td>${ChangeFlood.levelSnow}</td>
                    <td>${ChangeFlood.changeLevelSnow}</td>
                    <td>${ChangeFlood.speedChangeLevelSnow}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
<H2>Прогноз с ресурса DarkSky</H2>
<div class="container">
<table>
    <thead>
    <tr class="tr">
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
            <td class="temp">${forecastDarkSky.temperature}</td>
            <td>${forecastDarkSky.temperatureHigh}</td>
            <td>${forecastDarkSky.temperatureLow}</td>
            <td>${forecastDarkSky.humidity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<H2>Прогноз с ресурса WeatherOpenMap</H2>
<div class="container">
    <table>
        <thead>
        <tr class="tr">
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
                <td class="temp">${forecastOpenMap.temperature}</td>
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
