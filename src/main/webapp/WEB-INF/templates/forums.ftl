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
            <h3>Форумы</h3>
            <div class="searching_filtering">
                <label for="searchInput"></label>
                <input type="text" id="searchInput" onkeyup="searching()" placeholder="Найти компанию...">
                <button id="createForum" name="createForum" class="confirmLogoutButton">
                    Создать формум
                </button>
            </div>
            <form method="post" action="create_forum">
                <div id="forumForm" class="partnershipForm" style="display: none">
                    <div class="partnershipInfo">
                        <h3>Создать форум</h3>
                        <label for="forum_name">
                            <p>Введите название форума</p>
                        </label>
                        <input type="text" id="forum_name" name="forum_name" placeholder="Название форума" class="profile_inputs">

                        <label for="forum_title">
                            <p>Описание</p>
                            <textarea name="forum_title" id="forum_title" placeholder="Описание" class="partnership_title"></textarea>
                        </label>

                        <label for="forum_topic">
                            <p>Тематика форума</p>
                        </label>
                        <input type="text" id="forum_topic" name="forum_topic" placeholder="Тематика" class="profile_inputs">

                        <label for="create_company">
                            <p>Компания создатель</p>
                        </label>
                        <input type="text" id="create_company" name="create_company" placeholder="Компания" class="profile_inputs">

                        <label for="forum_date">
                            <p>Дата создания</p>
                        </label>
                        <input type="text" id="forum_date" name="forum_date" placeholder="Дата: yyyy-mm-dd" class="profile_inputs">

                        <div class="company_buttons">
                            <button id="createForumButton" name="createForumButton" class="deleteUserButton">Создать</button>
                            <button id="cancelForum" name="cancelForum" class="deleteUserButton" type="button">Закрыть</button>
                        </div>
                    </div>
                </div>
            </form>
            <div class="company_name">
                <ul id="forumList">
                    <#list forums as forum>
                        <li><a href="forum?id=${forum.id}">${forum.name}</a> </li>
                    </#list>
                </ul>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/searching.js"></script>
<script src="./scripts/forums.js"></script>
</body>
</html>