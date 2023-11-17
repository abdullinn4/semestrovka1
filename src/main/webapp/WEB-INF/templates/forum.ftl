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
        <div class="add_company_info">
            <div>
                <h3>Форум</h3>
            </div>
            <div>
                <label for="forum_name">
                    <p>Название форума</p>
                </label>
                <input type="text" id="forum_name" name="forum_name" placeholder="Название форума" class="profile_inputs" value="${forum.name!}">

                <label for="forum_title">
                    <p>Описание</p>
                    <textarea name="title" id="forum_title" placeholder="Описание" class="partnership_title">${forum.title!}</textarea>
                </label>
            </div>
        </div>

        <div id="chat" class="forum_chat">
            <h3>Чат</h3>
            <#list messages as message>
                <div class="message">
                    <p>${message.timestamp!}  ${message.sender!}: ${message.text!}</p>
                </div>
            </#list>
        </div>
        <form method="post" action="send_message">
            <div class="add_company_info">
                <h3>Отправить сообщение</h3>
                <form id="messageForm">
                    <input type="hidden" name="forum_id" value="${forum.id!}">
                    <label for="text"></label><textarea id="text" name="text" class="partnership_title" placeholder="Введите сообщение"></textarea>
                    <button type="submit" class="confirmLogoutButton">Отправить</button>
                </form>
            </div>
        </form>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
</body>
</html>