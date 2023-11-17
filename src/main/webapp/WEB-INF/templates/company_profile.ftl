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
        <form method="post" action="delete_company">
            <div id="deleteForm" class="deleteForm" style="display: none">
                <div class="deleteFormInfo">
                    <p>Вы уверены, что хотите удалить компанию?</p>
                    <p>Удаление компании приведет к удалению всех связанных с ней данных из сайта</p>
                    <input type="hidden" name="company_name" value="${company.name!}">
                    <button id="confirmDeleteCompanyButton" name="confirmDeleteCompanyButton" class="confirmDeleteUserButton">Да</button>
                    <button id="cancelDeleteCompanyButton" name="cancelDeleteCompanyButton" class="cancelDeleteUserButton" type="button">Нет</button>
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
            <div class="add_company_info">
                <div>
                    <h3>Компания</h3>
                </div>
                <div>
                    <label for="name">
                        <p>Имя комапнии</p>
                        <input type="text" name="name" id="name"  value="${company.name!}"placeholder="Введите имя компании" class="profile_inputs" readonly>
                    </label>
                    <label for="business_sector">
                        <p>Отрасль бизнеса</p>
                        <input type="text" name="business_sector" value="${company.businessSector!}" id="business_sector" placeholder="Введите отрасль бизнеса" class="profile_inputs" readonly>
                    </label>
                    <label for="country">
                        <p>Страна</p>
                        <input type="text" name="country" id="country" value="${company.country!}" placeholder="Введите страну" class="profile_inputs" readonly>
                    </label>
                    <label for="title">
                        <p>Описание</p>
                        <textarea name="title" id="title" placeholder="Описание" class="description_input" readonly>"${company.title!}"</textarea>
                    </label>
                </div>
                <div>
                    <button id="deleteCompanyButton" class="deleteUserButton">Удалить компанию</button>
                </div>
            </div>

        </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/deleteCompanyButton.js"></script>
</body>
</html>