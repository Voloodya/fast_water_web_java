<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vladimir
  Date: 25.05.2019
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Страница администратора</title>
    <style>
        table {table-layout: fixed;width:98%; height: 200px; margin-top: 16px; border: black; }
        .tr{background: cornflowerblue; color: white; text-align: center; vertical-align: center; horiz-align: center;}
        td {word-wrap:break-word; text-align-all: center; border: black; align: center;}
        .container {
            height: 200px;
            overflow: scroll;
        }
        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
        }
    </style>
    <script>
        // DOMContentLoaded – означает, что все DOM-элементы разметки уже созданы, можно их искать, вешать обработчики,
        // создавать интерфейс, но при этом, возможно, ещё не догрузились какие-то картинки или стили.
        //Событие DOMContentLoaded происходит на document
        // document.addEventListener('DOMContentLoaded', function() {
        //     document.querySelector('table.tablePost').addEventListener("click", e => {
        //         var tr = e.target.closest("tbody > tr");
        //         if (!tr) return;
        //         var position = tr.querySelector("td.geo").textContent;//Берём нужную ячейку
        //         position = position.split(/\s+/).map(Number); //Разбиваем содержимое ячейки
        //         var post = tr.querySelector("td.post").textContent;//Берём нужную ячейку
        //         document.form.latitude.value=position[0];
        //         document.form.longitude.value = position[1];
        //         document.form.post.value = post;
        //     });
        // });
    </script>
    <script>
        function createXMLHttp() {
            var Request = false;

            if (window.XMLHttpRequest)
            {
                //Gecko-совместимые браузеры, Safari, Konqueror
                Request = new XMLHttpRequest();
            }
            else if (window.ActiveXObject)
            {
                //Internet explorer
                try
                {
                    Request = new ActiveXObject("Microsoft.XMLHTTP");
                }
                catch (CatchException)
                {
                    Request = new ActiveXObject("Msxml2.XMLHTTP");
                }
            }

            if (!Request)
            {
                alert("Невозможно создать XMLHttpRequest");
            }
            return Request;
        }
        function getAjax(url, callback) { // функция Ajax GET

            // создаем Объект
            var oXmlHttp = createXMLHttp();
            var body='aggregate='+encodeURIComponent('value');
            // подготовка, объявление заголовков
            oXmlHttp.open("POST", url, true);
            oXmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            // описание функции, которая будет вызвана, когда придет ответ от сервера
            oXmlHttp.onreadystatechange = function() {
                if (oXmlHttp.readyState == 4) {
                    if (oXmlHttp.status == 200) {
                        callback(oXmlHttp.responseText);
                    } else {
                        callback(oXmlHttp.statusText);
                    }
                }
            }
            oXmlHttp.send(body);
        }
        function printGet(text) {
            alert(text);
        }
    </script>
</head>
<body>
<H2>Посты наблюдения</H2>
<div class="container">
<table class="tablePost">
    <thead>
    <tr class="tr">
        <th>ID поста</th>
        <th>Имя поста</th>
        <th>Координаты</th>
        <th>Высота над уровнем моря</th>
        <th>Участок водоёма</th>
        <th>Тип грунта</th>
        <th>Населённый пункт</th>
        <th>Описание</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listPosts}" var="post">
        <tr>
            <td>${post.idPost}</td>
            <td class="post">${post.namePost}</td>
            <td class="geo">${post.geographkoords}</td>
            <td>${post.hightSeaLevel}</td>
            <td>${post.basinLocality}</td>
            <td>${post.groundByGroundId}</td>
            <td class="city">${post.localityByLocalityId}</td>
            <td>${post.description}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <script>
        document.querySelector('table.tablePost').addEventListener("click", e => {
            var tr = e.target.closest("tbody > tr");
            if (!tr) return;
            var position = tr.querySelector("td.geo").textContent;//Берём нужную ячейку
            position = position.split(/\s+/).map(Number); //Разбиваем содержимое ячейки
            var post = tr.querySelector("td.post").textContent;//Берём нужную ячейку
            document.nameForm.latitude.value=position[0];
            document.nameForm.longitude.value = position[1];
            document.nameForm.post.value = post;
        });
    </script>
</div>
<br>
<div>
    <form name="nameForm" method="GET" action="/fast-water/water/administration">
    Широта: <input type="text" name="latitude"/>
    Долгота:<input type="text" name="longitude"/>
    Пост: <input type="text" name="post"/>
    Год с:<input type="text" name="yearStart"/>
    Месяц с:<input type="text" name="monthStart"/>
    День с:<input type="text" name="dayStart"/>
    Год по:<input type="text" name="yearFinish"/>
    Месяц по:<input type="text" name="monthFinish"/>
    День по:<input type="text" name="dayFinish"/>
        <button tybe="submit">Загрузить</button>
    </form>
</div>
<br>
<H2>Данные с ресурса DarkSky</H2>
<div class="container">
    <table>
        <thead>
        <tr class="tr">
            <th>Координаты</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Долгота дня</th>
            <th>Облачность</th>
            <th>min t</th>
            <th>max t</th>
            <th>Ночная t</th>
            <th>Дневная t</th>
            <th>t</th>
            <th>Влажность</th>
            <th>Осадки</th>
            <th>Снег</th>
            <th>Дождь</th>
            <th>Дождь/снег</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listWeatherDarkSky}" var="weatherdarkSky">
            <tr>
                <td>${weatherdarkSky.koords}</td>
                <td>${weatherdarkSky.localDateStr}</td>
                <td>${weatherdarkSky.localTimeStr}</td>
                <td>${weatherdarkSky.lonitudeDay_seconds}</td>
                <td>${weatherdarkSky.cloudCover}</td>
                <td>${weatherdarkSky.temperatureMin}</td>
                <td>${weatherdarkSky.temperatureMax}</td>
                <td>${weatherdarkSky.temperatureHigh}</td>
                <td>${weatherdarkSky.temperatureLow}</td>
                <td class>${weatherdarkSky.temperature}</td>
                <td>${weatherdarkSky.humidity}</td>
                <td>${weatherdarkSky.precipIntensity}</td>
                <td>${weatherdarkSky.snow}</td>
                <td>${weatherdarkSky.rain}</td>
                <td>${weatherdarkSky.sleet}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<H2>Данные c файла</H2>
<div class="container">
    <table>
        <thead>
        <tr class="tr">
            <th>ID Поста</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Уровень снега</th>
            <th>Плотность снега</th>
            <th>Промерзание почвы</th>
            <th>Температура воды</th>
            <th>Толщина льда на водоёме</th>
            <th>Уровень воды</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${hydrologyList}" var="hydrology">
            <tr>
                <td>${hydrology.namePost}</td>
                <td>${hydrology.localDate}</td>
                <td>${hydrology.localTime}</td>
                <td>${hydrology.levelSnow}</td>
                <td>${hydrology.reserveWater}</td>
                <td>${hydrology.levelFreezingGround}</td>
                <td>${hydrology.temperatureWater}</td>
                <td>${hydrology.heightIceOnWater}</td>
                <td>${hydrology.lewelWater}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<H2>Объединённые данные</H2>
<div class="container">
    <table class="flood">
        <thead>
        <tr class="tr">
            <th>Индификатор поста</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Долгота дня</th>
            <th>Снег</th>
            <th>Дождь</th>
            <th>Снег с дождём</th>
            <th>Влажность</th>
            <th>Дневная t</th>
            <th>Ночная t</th>
            <th>Средняя t</th>
            <th>Максимальная t</th>
            <th>Минимальная t</th>
<%--            <th>Облачность</th>--%>
            <th>Высота снежного покрова</th>
            <th>Плотность снежного покрова</th>
            <th>Промерзание почвы</th>
            <th>t воды</th>
            <th>Толщина льда</th>
            <th>Уровень воды</th>
            <th>Затопления</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listFlood}" var="flood">
            <tr>
                <td>${flood.postByPostId.namePost}</td>
                <td>${flood.date}</td>
                <td>${flood.time}</td>
                <td>${flood.longitudeDay}</td>
                <td>${flood.snow}</td>
                <td>${flood.rain}</td>
                <td>${flood.snowRain}</td>
                <td>${flood.relativeHumidity}</td>
                <td>${flood.humidityDeficit}</td>
                <td>${flood.temperatureDay}</td>
                <td>${flood.temperatureNight}</td>
                <td>${flood.temperatureMax}</td>
                <td>${flood.temperatureMin}</td>
<%--                <td>${flood.solaractivity}</td>--%>
                <td>${flood.levelSnow}</td>
                <td>${flood.waterReserveInSnow}</td>
                <td>${flood.levelFreezingGround}</td>
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
    <form name="form" method="POST">
        <input type="text" name="aggregate"/>
        <button type="button" onclick="getAjax('/fast-water/water/download',printGet)">Загрузить</button>
    </form>
</div>
</body>
</html>
