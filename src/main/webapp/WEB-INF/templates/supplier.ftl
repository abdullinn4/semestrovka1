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
        <form method="post" action="apply_cooperation">
            <div id="supplierForm" class="partnershipForm" style="display: none">
                <div class="partnershipInfo">
                    <input type="hidden" name="supplier_id" value="${supplier.id!}">
                    <h3>Предложить сотрудничество</h3>
                    <label for="company_name">
                        <p>Введите имя своей компании</p>
                    </label>
                    <input type="text" id="company_name" name="company_name" placeholder="Имя компании" class="profile_inputs">

                    <div class="company_buttons">
                        <button id="sendSupplier" name="sendPartnership" class="deleteUserButton">Отправить заявку</button>
                        <button id="cancelSupplier" name="cancelSupplier" class="deleteUserButton">Закрыть</button>
                    </div>
                </div>
            </div>
        </form>
        <div class="add_company_info">
            <div>
                <h3>Поставщик</h3>
                <label for="supplier_name">
                    <p>Компания</p>
                </label>
                <input type="text" id="supplier_name" name="supplier_name" placeholder="Название" class="profile_inputs" value="${supplier.companyName!}">

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

                <div class="company_buttons">
                    <button id="applySupplierButton" name="applySupplierButton" class="deleteUserButton">Сотрудничать</button>
                </div>
            </div>
        </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/searching.js"></script>
<script src="./scripts/forums.js"></script>
<script src="./scripts/supplier.js"></script>
</body>
</html>