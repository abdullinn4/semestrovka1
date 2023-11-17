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
        <div class="main_profile">
            <div class="main_profile_nav">
                <a href="profile" >Мой профиль</a>
                <a href="outgoing_applications" >Исходящие заявки</a>
                <a href="incoming_applications" >Входящие заявки</a>
                <a href="settings" >Настройки</a>
                <a href="add_company" >Добавить компанию</a>
            </div>
            <div class="add_company_info">
                <div>
                    <h3>Добавить компанию</h3>
                </div>
                <form method="post" action="add_company">
                    <div>
                        <label for="name">
                            <p>Имя комапнии</p>
                        </label>
                        <input type="text" name="name" id="name" placeholder="Введите имя команпнии" class="profile_inputs">
                        <label for="business_sector">
                            <p>Отрасль бизнеса</p>
                        </label>
                        <input type="text" name="business_sector" id="business_sector" placeholder="Введите отрасль бизнеса" class="profile_inputs">
                        <label for="country">
                            <p>Страна</p>
                        </label>
                        <input type="text" name="country" id="country" placeholder="Введите страну" class="profile_inputs">
                        <label for="title">
                            <p>Описание</p>
                        </label>
                        <textarea oninput="countCharacters(); limitCharacters()" name="title" id="title" placeholder="Описание" class="description_input"></textarea>
                        <p>Кол-во символов: <span id="counter">0</span></p>
                        <p><span id="counter_limit"></span></p>
                    </div>
                    <div>
                        <input type="submit" value="Сохранить" class="add_company_button">
                    </div>
                </form>
            </div>
        </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/add_company.js"></script>
</body>
</html>