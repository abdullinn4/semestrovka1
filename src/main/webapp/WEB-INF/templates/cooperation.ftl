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
                <h3>Сотрудничество</h3>
                <label for="supplier_name">
                    <p>Компания-поставщик</p>
                </label>
                <input type="text" id="supplier_name" name="supplier_name" placeholder="Название" class="profile_inputs" value="${supplier.companyName!}">

                <label for="supplier_name">
                    <p>Компания-партнер</p>
                </label>
                <input type="text" id="supplier_name" name="supplier_name" placeholder="Название" class="profile_inputs" value="${company.name!}">

                <label for="supplier_title">
                    <p>Описание</p>
                    <textarea name="supplier_title" id="supplier_title" placeholder="Описание" class="partnership_title">${supplier.title!}</textarea>
                </label>

                <label for="supplier_category">
                    <p>Категория</p>
                </label>
                <input type="text" id="supplier_category" name="supplier_category" placeholder="Категория" class="profile_inputs" value="${supplier.category!}">

                <label for="supplier_contacts">
                    <p>Контактная информация</p>
                </label>
                <input type="text" id="supplier_contacts" name="supplier_contacts" placeholder="Контактная информация" class="profile_inputs" value="${supplier.contactInfo!}">

                <label for="country">
                    <p>Страна</p>
                </label>
                <input type="text" id="country" name="country" placeholder="Страна" class="profile_inputs" value="${supplier.country!}">
            </div>
        </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
</body>
</html>