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
        <form method="post" action="delete_user">
            <div id="deleteForm" class="deleteForm" style="display: none">
                <div class="deleteFormInfo">
                    <p>Вы уверены, что хотите удалить аккаунт?</p>
                    <p>Удаление аккаунта приведет к удалению всех связанных с ним данных из сайта</p>
                    <button id="confirmDeleteUserButton" name="confirmDeleteUserButton" class="confirmDeleteUserButton">Да</button>
                    <button id="cancelDeleteUserButton" name="cancelDeleteUserButton" class="cancelDeleteUserButton" type="button">Нет</button>
                </div>
            </div>
        </form>
        <div class="main_profile">
            <div class="main_profile_nav">
                <a href="profile" >Мой профиль</a>
                <a href="outgoing_applications" >Исходящие заявки</a>
                <a href="incoming_applications" >Входящие заявки</a>
                <a href="settings" >Настройки</a>
                <a href="add_company" >Добавить компанию</a>
            </div>
            <div class="profile_info">
                <div class="main_profile_setting">
                    <div>
                        <h4>Настройки</h4>
                    </div>
                    <form method="post" action="settings">
                        <div class="general_info_photo">
                            <div class="profile_photo">
                                <img src="images/user_profile_icon.svg" alt="user_profile_icon" class="user_profile_icon">
                            </div>
                            <div class="general_info_names">
                                <label for="first_name">
                                    <p>Имя</p>
                                    <input type="text" name="first_name" id="first_name" placeholder="Имя" class="profile_inputs" value="${user.firstName!}">
                                </label>
                                <label for="last_name">
                                    <p>Фамилия</p>
                                    <input type="text" name="last_name" id="last_name" placeholder="Фамилия" class="profile_inputs" value="${user.lastName!}">
                                </label>
                            </div>
                            <div class="general_info_names">
                                <label for="email">
                                    <p>Email</p>
                                    <input type="email" name="email" id="email" class="profile_inputs" value="${user.email!}" readonly>
                                </label>
                                <label for="country">
                                    <p>Страна</p>
                                    <input type="text" name="country" id="country" placeholder="Страна" class="profile_inputs" value="${user.country!}">
                                </label>
                            </div>
                        </div>
                        <div class="settings_submit">
                            <input type="submit" value="Сохранить" class="setting_button">
                        </div>
                    </form>
                    <div>
                        <button id="deleteUserButton" class="deleteUserButton">Удалить аккаунт</button>
                    </div>
                </div>
            </div>
        </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/deleteUserButton.js"></script>
</body>
</html>