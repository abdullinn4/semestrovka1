<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="./styles/style.css">
    <#import "baseForAnon.ftl" as base>
</head>
<body>
<header>
    <@base.header/>
</header>
<main>
    <@base.mainContent>
        <div class="main_info">
            <div class="main_info_text">
                <br>BizConnectors - это онлайн-платформа, которая соединяет предпринимателей и бизнесменов из разных отраслей и стран.
                <br>Наша основная цель - помочь пользователям найти партнеров, инвесторов, поставщиков или клиентов для их бизнеса.
            </div>
            <div class="main_info_image">
                <img src="./images/main_info_image.jpg" alt="main image" class="main_info_image">
            </div>
        </div>
        <div class="main_ideas">
            <div class="ideas">
                <h4>Поиск партнеров</h4>
                <p>Пользователи могут использовать поиск на сайте для нахождения подходящих партнеров, которые могут помочь в развитии и расширении их бизнеса.</p>
            </div>
            <div class="ideas">
                <h4>Поиск инвесторов</h4>
                <p>Если пользователь ищет финансирование для своего проекта или бизнеса, на сайте есть возможность найти потенциальных инвесторов, которые заинтересованы в инвестициях в различные секторы и страны. </p>
            </div>
            <div class="ideas">
                <h4>Поиск поставщиков</h4>
                <p>Для бизнеса важно иметь надежных поставщиков, которые предлагают качественные товары и услуги. Мы предлагает возможность поиска поставщиков из разных отраслей и стран, чтобы удовлетворить потребности бизнеса. </p>
            </div>
            <div class="ideas">
                <h4>Поиск клиентов</h4>
                <p>Для предпринимателей и бизнесменов важно найти новых клиентов и расширить свою клиентскую базу. На сайте можно найти потенциальных клиентов, которые заинтересованы в услугах или продуктах, предлагаемых вашим бизнесом. </p>
            </div>
        </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
</body>
</html>