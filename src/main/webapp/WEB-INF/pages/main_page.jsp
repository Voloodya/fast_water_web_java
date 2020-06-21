<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Главная страница</title>
    <style>
        .layer1 {
            float: left;
            display: block;
            width: 50%;
        }
        .layer2 {
            float: left;
            display: block;
            width: 50%;
        }
        .layer3{
            clear: left;
        }
    </style>
    <style>
        .thead-table-fixed thead {
            width: calc(100% - 1px) !important;
        }

        .thead-table-fixed tbody {
            display: block;
            height: 200px;
            overflow: auto;
            overflow-y:scroll;
        }

        .thead-table-fixed thead, .thead-table-fixed tbody tr {
            display: table;
            width: 100%;
            table-layout: fixed;
            text-align: center; vertical-align: center; horiz-align: center;
        }
    </style>
    <style>
        /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
        #map {
            height: 50%;
        }
        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
        }
        table {table-layout: fixed;width:98%; height: 250px; margin-top: 16px; border: black; }
        .tr{background: #730d73; color: white; text-align: center; vertical-align: center; horiz-align: center;}
        .tr_tbody{text-align: center; vertical-align: center; horiz-align: center;}
        td {word-wrap:break-word; text-align-all: center; border: black; align: center;}
        .container {
            height: 250px;
            overflow: scroll;
        }
    </style>
    <style>
        .div-right{margin-left: auto; margin-right: 0;
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
    </script>
    <!-- -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWH__g2XMzhes7PIk-8v2MbBSZTgMnBSg&callback=initMap"
            async defer></script>

</head>
<body>
<div>
<div class="div-right">
    <form name="form_button" method="GET" action='/water/water/administration'>
        <button type="submit">Страница администрирования</button>
    </form>
</div>
</div>
<H2>Исторические данные</H2>
<div>
    <table class="thead-table-fixed">
        <thead>
        <tr bgcolor="#730d73"; style="color: white">
            <th>Координаты</th>
            <th>Населённый пункт</th>
            <th>Дата</th>
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
            <th>Толщина льда</th>
            <th>Уровень воды</th>
            <th>Риск затопления</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listFlood}" var="flood">
            <tr class="tr_tbody">
                <td class="geo">${flood.geographKoordsPost}</td>
                <td class="city">${flood.nameLocality}</td>
                <td>${flood.date}</td>
                <td>${flood.snow}</td>
                <td>${flood.rain}</td>
                <td>${flood.snowRain}</td>
                <td>${flood.relativeHumidity}</td>
                <td>${flood.temperatureDay}</td>
                <td>${flood.temperatureNight}</td>
                <td class="temp">${flood.temperatureMidle}</td>
                <td>${flood.temperatureMax}</td>
                <td>${flood.temperatureMin}</td>
                <td>${flood.levelSnow}</td>
                <td>${flood.solaractivity}</td>
                <td>${flood.heightIceOnWater}</td>
                <td>${flood.levelWater}</td>
                <td>${flood.warningFlood}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        //document.querySelector('table.flood')
        document.querySelector('table.thead-table-fixed').addEventListener("click", e => {
            var tr = e.target.closest("tbody > tr");
            if (!tr) return;
            var position = tr.querySelector("td.geo").textContent;//Берём нужную ячейку
            position = position.split(/\s+/).map(Number); //Разбиваем содержимое ячейки
            var temperatura = tr.querySelector("td.temp").textContent;//Берём нужную ячейку
            UpdateGoogleMap(position[0],position[1],temperatura);
            var city = tr.querySelector("td.city").textContent;//Берём нужную ячейку
            document.form1.location.value=city;
        });
    </script>
</div>

<div class="layer1">
    <form name="form1" method="GET" action="/water/water/main">
    Населённый пункт:    <input type="text" name="location"/>
        <button type="submit">Поиск</button>
    </form>
</div>
<div class="layer2">
    <form name="form_button" method="GET" action='/water/water/main?location=""'>
        <button type="submit">Отобразить всё</button>
    </form>
</div>
<br class="layer3">
<%--<br>--%>
<%--<br>--%>
<%--<div>--%>
<%--    <form name="form2" method="GET" action="/water/water/main">--%>
<%--    Долгота <input type="text" name="latitude2"/>--%>
<%--    Широта <input type="text" name="longitude2"/>--%>
<%--    Год с:<input type="text" name="yearStart2"/>--%>
<%--    Месяц с:<input type="text" name="monthStart2"/>--%>
<%--    День с:<input type="text" name="dayStart2"/>--%>
<%--    Год по:<input type="text" name="yearFinish2"/>--%>
<%--    Месяц по:<input type="text" name="monthFinish2"/>--%>
<%--    День по: <input type="text" name="dayFinish2"/>--%>
<%--        <button tybe="submit">Загрузить</button>--%>
<%--    </form>--%>
<%--</div>--%>
<div class="layer1">
<table class="thead-table-fixed">
    <caption><H2 align="left">Прогноз с ресурса DarkSky</H2></caption>
    <thead>
    <tr bgcolor="#730d73"; style="color: white">
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
        <tr class="tr_tbody">
            <td class="geo">${forecastDarkSky.koords}</td>
            <td>${forecastDarkSky.localDateStr}</td>
            <td>${forecastDarkSky.localTimeStr}</td>
            <td>${forecastDarkSky.cloudCover}</td>
            <td>${forecastDarkSky.precipIntensity}</td>
            <td class="temp">${forecastDarkSky.temperatureHigh}</td>
            <td>${forecastDarkSky.temperatureLow}</td>
            <td>${forecastDarkSky.humidity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<div class="layer2">
    <table class="thead-table-fixed">
        <caption><H2 align="left">Прогноз с ресурса WeatherOpenMap</H2></caption>
        <thead>
        <tr bgcolor="#730d73"; style="color: white">
            <th>Координаты</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Облачность</th>
            <th>Осадки</th>
            <th>Максимальная t</th>
            <th>Минимальная t</th>
            <th>Влажность</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listForecastOpenMap}" var="forecastOpenMap">
            <tr class="tr_tbody">
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
<div id="map" class="layer3"></div>
<div>
    <table class="thead-table-fixed">
        <caption><H2 align="left">Прогноз изменения уровня воды</H2></caption>
        <thead>
        <tr bgcolor="#730d73"; style="color: white">
            <th>Населённый пункт</th>
            <th>Координаты</th>
            <th>Дата</th>
            <th>Прогноз изменение уровня воды</th>
            <th>Прогноз уровня воды</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listPredictLevelWater}" var="listPredictWater">
            <tr class="tr_tbody">
                <td class="geo">${listPredictWater.postId.geographkoordsId.koordinate}</td>
                <td class="city">${listPredictWater.postId.localityId.nameLocality}</td>
                <td>${listPredictWater.date}</td>
                <td>${listPredictWater.changeLevelWater}</td>
                <td>${listPredictWater.levelWater}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        //document.querySelector('table.flood')
        addEventListener("click", ({target}) => {
            var tr = target.closest("tbody > tr");
            if(!tr) return;
            var position = tr.querySelector("td.geo").textContent;//Берём нужную ячейку
            position = position.split(/\s+/).map(Number); //Разбиваем содержимое ячейки
            UpdateGoogleMap(position[0],position[1]);
        });
    </script>
</div>
<%--<div id="map_forecast"></div>--%>
<script>
    // var map_forecast, marker2;
    // function initMap() {
    //     map_forecast = new google.maps.Map(document.getElementById('map_forecast'), {
    //         center: new google.maps.LatLng(51.754207,55.106578),
    //         zoom: 10
    //     });
    //     map_forecast.setCenter({
    //         lat: 51.754207,
    //         lng: 55.106578
    //     });
    //     marker && marker2.setMap(null);
    //     marker=new google.maps.Marker({
    //         position: {
    //             lat: 51.754207,
    //             lng: 55.106578
    //         },
    //         map: map_forecast,
    //         title: 'Оренбург'
    //     });
    // }
    // function UpdateGoogleMap_2(latitude, longitude,temperatura) {
    //     latitude = (latitude && +latitude)||51.754207;
    //     longitude = (longitude && +longitude)||55.106578;
    //     map_forecast.setCenter({
    //         lat: latitude,
    //         lng: longitude,
    //     });
    //     map_forecast.zoom=15;
    //     marker && marker2.setMap(null);
    //     marker2=new google.maps.Marker({
    //         position: {
    //             lat: latitude,
    //             lng: longitude,
    //         },
    //         map: map_forecast,
    //         title: temperatura
    //     });
    // };
</script>
<%--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWH__g2XMzhes7PIk-8v2MbBSZTgMnBSg&callback=initMap"--%>
<%--        async defer></script>--%>
<div class="layer1">
     <table class="thead-table-fixed">
            <caption><H2 align="left">Динамика изменения уровня воды</H2></caption>
            <thead>
            <tr bgcolor="#730d73"; style="color: white">
                <th>Координаты</th>
                <th>Населённый пункт</th>
                <th>Дата</th>
                <th>Время</th>
                <th>Уровень воды</th>
                <th>Изменение ур-ня воды</th>
                <th>Скороть изм-ия ур-ня воды (см/ч)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listChangeFlood}" var="ChangeFlood">
                <tr class="tr_tbody">
                    <td class="geo">${ChangeFlood.geographKoordsPost}</td>
                    <td class="city">${ChangeFlood.nameLocality}</td>
                    <td>${ChangeFlood.date}</td>
                    <td>${ChangeFlood.time}</td>
                    <td>${ChangeFlood.levelWater}</td>
                    <td>${ChangeFlood.changeLevelWater}</td>
                    <td>${ChangeFlood.speedChangeLevelWater}</td>
                </tr>
            </c:forEach>
            </tbody>
     </table>
    <script>
        //document.querySelector('table.flood')
        addEventListener("click", ({target}) => {
            var tr = target.closest("tbody > tr");
            if(!tr) return;
            var position = tr.querySelector("td.geo").textContent;//Берём нужную ячейку
            position = position.split(/\s+/).map(Number); //Разбиваем содержимое ячейки
            UpdateGoogleMap(position[0],position[1]);
        });
    </script>
</div>
<div class="layer2">
     <table class="thead-table-fixed">
            <caption><H2 align="left">Динамика изменения уровня снежного покрова</H2></caption>
            <thead>
            <tr bgcolor="#730d73"; style="color: white">
                <th>Координаты</th>
                <th>Населённый пункт</th>
                <th>Дата</th>
                <th>Время</th>
                <th>Уровень снега</th>
                <th>Изменение ур-ня снега</th>
                <th>Скороть изм-ия ур-ня снега (см/ч)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listChangeFlood}" var="ChangeFlood">
                <tr class="tr_tbody">
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
    <script>
        //document.querySelector('table.flood')
        addEventListener("click", ({target}) => {
            var tr = target.closest("tbody > tr");
            if(!tr) return;
            var position = tr.querySelector("td.geo").textContent;//Берём нужную ячейку
            position = position.split(/\s+/).map(Number); //Разбиваем содержимое ячейки
            UpdateGoogleMap(position[0],position[1]);
        });
    </script>
</div>
</body>
</html>
