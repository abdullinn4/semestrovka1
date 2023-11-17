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
            <h3>Поставщики</h3>
            <div class="searching_filtering">
                <label for="searchInput"></label>
                <input type="text" id="searchInput" onkeyup="searching()" placeholder="Найти компанию...">
                <button id="supplierButton" name="createForum" class="confirmLogoutButton">
                    Стать поставщиком
                </button>
            </div>
            <form method="post" action="create_supplier">
                <div id="createSupplierForm" class="partnershipForm" style="display: none">
                    <div class="partnershipInfo">
                        <h3>Стать поставщиком</h3>
                        <label for="supplier_name">
                            <p>Введите название компании</p>
                        </label>
                        <input type="text" id="supplier_name" name="supplier_name" placeholder="Название" class="profile_inputs">

                        <label for="supplier_title">
                            <p>Описание</p>
                            <textarea name="supplier_title" id="supplier_title" placeholder="Описание" class="partnership_title"></textarea>
                        </label>

                        <label for="supplier_category">
                            <p>Категория</p>
                        </label>
                        <input type="text" id="supplier_category" name="supplier_category" placeholder="Категория" class="profile_inputs">

                        <label for="supplier_contacts">
                            <p>Контактная информация</p>
                        </label>
                        <input type="text" id="supplier_contacts" name="supplier_contacts" placeholder="Контактная информация" class="profile_inputs">

                        <label for="country">
                            <p>Страна</p>
                        </label>
                        <input type="text" id="country" name="country" placeholder="Страна" class="profile_inputs">

                        <div class="company_buttons">
                            <button id="createSupplierButton" name="createForumButton" class="deleteUserButton">Создать</button>
                            <button id="cancelCreateSupplier" name="cancelForum" class="deleteUserButton">Закрыть</button>
                        </div>
                    </div>
                </div>
            </form>
            <div class="company_name">
                <ul id="supplierList">
                    <#list suppliers as supplier>
                        <li><a href="supplier?id=${supplier.id}">${supplier.companyName}</a> </li>
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
<script src="./scripts/createSupplier.js"></script>
</body>
</html>