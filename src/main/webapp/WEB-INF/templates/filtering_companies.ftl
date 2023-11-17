<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="./styles/style.css">
    <#import "baseForUser.ftl" as base>
</head>
<body>
<header>
    <@base.header/>
</header>
<main>
    <@base.mainContent>
        <div class="all_companies">
            <h3>Компании</h3>
            <div class="searching_filtering">
                <label for="searchInput"></label>
                <input type="text" id="searchInput" onkeyup="searching()" placeholder="Найти компанию...">
                <button id="filterButton" class="filterButton">
                    <img src="images/filter.png" alt="filter" class="filter_img">
                </button>
            </div>
            <form method="post" action="filtering">
                <div id="filterPanel" class="filterPanel" style="display: none" >
                    <div class="filterPanel_form">
                        <h3>Фильтр</h3>
                        <label for="industry" class="filterPanel_name">Отрасль бизнеса:</label>
                        <div class="filter_checkbox">
                            <#list businessSectors as industry>
                                <label for="${industry}">
                                    <input type="checkbox" name="industry" id="${industry}" value="${industry}">
                                    ${industry}
                                </label>
                            </#list>
                        </div>
                        <label for="country" class="filterPanel_name">Страны</label>
                        <div class="filter_checkbox">
                            <#list countries as country>
                                <label for="${country}">
                                    <input type="checkbox" name="country" id="${country}" value="${country}">
                                    ${country}
                                </label>
                            </#list>
                        </div>
                        <button id="applyFilterButton" class="applyFilterButton">Применить фильтр</button>
                        <button id="cancelFilterButton" class="applyFilterButton">Закрыть</button>
                    </div>
                </div>

                <div class="company_name">
                    <ul id="companyList"></ul>
                </div>
            </form>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/searching.js"></script>
<script src="./scripts/filter.js"></script>
</body>
</html>