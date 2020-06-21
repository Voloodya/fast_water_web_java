<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Страница администратора</title>
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
        .dataBD thead {
            width: calc(100% - 1px) !important;
        }

        .dataBD tbody {
            display: block;
            height: 200px;
            overflow: auto;
            overflow-y:scroll;
        }

        .dataBD thead, .dataBD tbody tr {
            display: table;
            width: 100%;
            table-layout: fixed;
            text-align: center; vertical-align: center; horiz-align: center;
        }
    </style>

    <style>
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
        /* Optional: Makes the sample page fill the window. */
    </style>
    <style>
        .div-right{margin-left: auto; margin-right: 0;
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
</head>
<body>
<div>
<div class="div-right">
    <form name="form_button" method="GET" action='/water/water/main'>
        <button type="submit">На главную</button>
    </form>
</div>
</div>
<table class="thead-table-fixed">
    <caption><H2 align="left">Посты наблюдения</H2></caption>
    <thead>
    <tr bgcolor="#730d73"; style="color: white">
<%--        <th>ID поста</th>--%>
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
<%--            <td>${post.idPost}</td>--%>
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
        document.querySelector('table.thead-table-fixed').addEventListener("click", e => {
            var tr = e.target.closest("tbody > tr");
            if (!tr) return;
            var position = tr.querySelector("td.geo").textContent;//Берём нужную ячейку
            position = position.split(/\s+/).map(Number); //Разбиваем содержимое ячейки
            var post = tr.querySelector("td.post").textContent;//Берём нужную ячейку
            document.FormDownload.latitude.value=position[0];
            document.FormDownload.longitude.value = position[1];
            document.FormDownload.post.value = post;
        });
    </script>
</div>
<br>
<div>
    <form name="FormDownload" method="GET" action="/water/water/administration">
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
<div>
    <table class="thead-table-fixed">
        <caption><H2 align="left">Данные с ресурса DarkSky</H2></caption>
        <thead>
        <tr bgcolor="#730d73"; style="color: white">
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
                <td class="temp">${weatherdarkSky.temperature}</td>
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
<div>
    <table class="thead-table-fixed">
        <caption><H2 align="left">Данные из файла</H2></caption>
        <thead>
        <tr bgcolor="#730d73"; style="color: white">
            <th>Пост</th>
            <th>Дата</th>
            <th>Кол. дн. с нач.года</th>
<%--            <th>Время</th>--%>
            <th>Осадки</th>
            <th>Уровень снега</th>
            <th>Водозапас в снегу</th>
            <th>Промерзание почвы</th>
            <th>Минимальная t</th>
            <th>Максимальная t</th>
            <th>Дефицит влажности воздуха</th>
            <th>Солнечное сияние</th>
            <th>Толщина льда на водоёме</th>
            <th>Уровень воды</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${hydrologyListFile}" var="hydrologyListFile">
            <tr>
                <td>${hydrologyListFile.namePost}</td>
                <td>${hydrologyListFile.localDate}</td>
                <td>${hydrologyListFile.countDay}</td>
<%--                <td>${hydrologyListFile.localTime}</td>--%>
                <td>${hydrologyListFile.downfall}</td>
                <td>${hydrologyListFile.levelSnow}</td>
                <td>${hydrologyListFile.waterReserveInSnow}</td>
                <td>${hydrologyListFile.levelFreezingGround}</td>
                <td>${hydrologyListFile.temperatureMin}</td>
                <td>${hydrologyListFile.temperatureMax}</td>
                <td>${hydrologyListFile.deficitHumidityAir}</td>
                <td>${hydrologyListFile.sunShine}</td>
                <td>${hydrologyListFile.heightIceOnWater}</td>
                <td>${hydrologyListFile.lewelWater}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<div>
    <form name="formDowloadFile" method="POST">
        <input type="text" name="downloadFile"/>
        <button type="button" onclick="getAjax('/water/water/downloadFile',printGet,'downloadFile')">Загрузить из файла</button>
    </form>
</div>
<br>
<div>
    <table class="thead-table-fixed">
        <caption><H2 align="left">Объединённые данные</H2></caption>
        <thead>
        <tr bgcolor="#730d73"; style="color: white">
            <th>Индификатор поста</th>
            <th>Координаты</th>
            <th>Дата</th>
            <th>Количество дней</th>
            <th>Осадки</th>
            <th>Влажность</th>
            <th>Дневная t</th>
            <th>Ночная t</th>
            <th>Средняя t</th>
            <th>Максимальная t</th>
            <th>Минимальная t</th>
            <th>Высота снежного покрова</th>
            <th>Плотность снежного покрова</th>
            <th>Промерзание почвы</th>
            <th>Толщина льда</th>
            <th>Уровень воды</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listFlood}" var="flood">
            <tr>
                <td>${flood.postId.namePost}</td>
                <td>${flood.postId.geographkoordsId.koordinate}</td>
                <td>${flood.date}</td>
                <td>${flood.snowRain}</td>
                <td>${flood.relativeHumidity}</td>
                <td>${flood.humidityDeficit}</td>
                <td>${flood.temperatureDay}</td>
                <td>${flood.temperatureNight}</td>
                <td>${flood.temperatureMax}</td>
                <td>${flood.temperatureMin}</td>
                <td>${flood.levelSnow}</td>
                <td>${flood.waterReserveInSnow}</td>
                <td>${flood.levelFreezingGround}</td>
                <td>${flood.heightIceOnWater}</td>
                <td>${flood.levelWater}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<div>
    <form name="formDowload" method="POST">
        <input type="text" name="downloadAgregate"/>
        <button type="button" onclick="getAjax('/water/water/downloadAgregate',printGet,'downloadAgregate')">Загрузить объединенные данные</button>
    </form>
</div>
<div>
    <table class="dataBD">
        <caption><H2 align="left">Записанные данные</H2></caption>
        <thead>
        <tr bgcolor="#730d73"; style="color: white">
            <th>Имя поста</th>
            <th>Номер поста</th>
            <th>Дата</th>
            <th>Кол. дн. с нач. г.</th>
            <th>Время</th>
            <th>Осадки</th>
            <th>Высота снежного покрова</th>
            <th>Водозапас снега</th>
            <th>Промерзание почвы</th>
            <th>Высота льда</th>
            <th>Температура max</th>
            <th>Температура min</th>
            <th>Температура midle</th>
            <th>Дифицит влажности</th>
            <th>Солнечное сияние</th>
            <th>Уровень воды</th>
<%--            <th>Изменение уровня воды</th>--%>
            <th>Измен. снеж. покр.</th>
            <th>Измен. снеж. покр. 10дн.</th>
            <th>Средн. измен. ур. воды 3дн.</th>
            <th>Осадки 3дн.</th>
            <th>Средн. t за 3 дн.</th>
            <th>Прогноз изменения ур-на воды</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listDataBaseFlood}" var="DataBaseFlood">
            <tr>
                <td class="post">${DataBaseFlood.namePost}</td>
                <td>${DataBaseFlood.numberPostForNrlNtwrk}</td>
                <td class="date-cell">${DataBaseFlood.date}</td>
                <td>${DataBaseFlood.numberDaysYear}</td>
                <td>${DataBaseFlood.time}</td>
                <td>${DataBaseFlood.snowRain}</td>
                <td>${DataBaseFlood.levelSnow}</td>
                <td>${DataBaseFlood.waterReserveInSnow}</td>
                <td>${DataBaseFlood.levelFreezingGround}</td>
                <td>${DataBaseFlood.heightIceOnWater}</td>
                <td>${DataBaseFlood.temperatureMax}</td>
                <td>${DataBaseFlood.temperatureMin}</td>
                <td>${DataBaseFlood.temperatureMidle}</td>
                <td>${DataBaseFlood.humidityDeficit}</td>
                <td>${DataBaseFlood.solaractivity}</td>
                <td>${DataBaseFlood.levelWater}</td>
<%--                <td>${DataBaseFlood.changeLevelWater}</td>--%>
                <td>${DataBaseFlood.changeLevelSnow}</td>
                <td>${DataBaseFlood.changeSnowBefore10days}</td>
                <td>${DataBaseFlood.changeWaterBefore3dayMiddle}</td>
                <td>${DataBaseFlood.downfallBefore3days}</td>
                <td>${DataBaseFlood.temperatureMiddleBefore3days}</td>
                <td>${DataBaseFlood.forecastChangeLevelWater}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        document.querySelector('table.dataBD').addEventListener("click", e => {
            var tr = e.target.closest("tbody > tr");
            if (!tr) return;
            var post = tr.querySelector("td.post").textContent;//Берём нужную ячейку
            var date=tr.querySelector("td.date-cell").textContent;//Берём нужную ячейку
            date = date.split("-").map(Number); //Разбиваем содержимое ячейки
            document.FormDell.post.value = post;
            document.FormPrognoz.post.value=post;
            document.FormDell.yearStart.value = date[0];
            document.FormPrognoz.yearStart.value=date[0];
            document.FormDell.monthStart.value = date[1];
            document.FormPrognoz.monthStart.value=date[1];
            document.FormDell.dayStart.value = date[2];
            document.FormPrognoz.dayStart.value=date[2];
        });
    </script>
</div>
<br>
<div>
    <form name="FormDell" method="GET" action="/water/water/administrationDell">
        Пост: <input type="text" name="post"/>
        Год с:<input type="text" name="yearStart"/>
        Месяц с:<input type="text" name="monthStart"/>
        День с:<input type="text" name="dayStart"/>
        Год по:<input type="text" name="yearFinish"/>
        Месяц по:<input type="text" name="monthFinish"/>
        День по:<input type="text" name="dayFinish"/>
        <button tybe="submit">Удалить</button>
    </form>
</div>
<div>
    <form name="FormPrognoz" method="GET" action="/water/water/administrationPrognoz">
        Пост: <input type="text" name="post"/>
        Год с:<input type="text" name="yearStart"/>
        Месяц с:<input type="text" name="monthStart"/>
        День с:<input type="text" name="dayStart"/>
        Год по:<input type="text" name="yearFinish"/>
        Месяц по:<input type="text" name="monthFinish"/>
        День по:<input type="text" name="dayFinish"/>
        <button tybe="submit">Прогноз</button>
    </form>
</div>
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
    function getAjax(url, callback, body) { // функция Ajax GET
        // создаем Объект
        var oXmlHttp = createXMLHttp();
        var body=body+'='+encodeURIComponent('value');
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
    function getAjaxPrognoz(url, callback, value1,value2,value3,value4,value5,value6,value7) { // функция Ajax GET
        // создаем Объект
        var oXmlHttp = createXMLHttp();
        var body="body";
        var body=body+'='+encodeURIComponent(value1)+encodeURIComponent(value2);
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
</body>
</html>
