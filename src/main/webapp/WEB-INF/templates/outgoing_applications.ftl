<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="./styles/style.css">
    <#import "baseForUser.ftl" as base>
</head>
<body><header>
    <@base.header/>
</header>
<main>
    <@base.mainContent>
        <div class="main_profile">
            <div class="main_profile_nav">
                <a href="profile" >Мой профиль</a>
                <a href="outgoing_applications" >Исходящие заявки</a>
                <a href="incoming_applications" >Входящие заявки</a>
                <a href="settings" >Настройки</a>
                <a href="add_company" >Добавить компанию</a>
            </div>
            <div class="applications_form">
                <h3>Мои исходящие заявки</h3>
                <ul id="applicationsList">

                </ul>
            </div>
        </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/applicationsOutgoingFromAjax.js"></script>
</body>
</html>