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
        <form method="post" action="apply_partnership_servlet">
            <div id="partnershipForm" class="partnershipForm" style="display: none">
                <div class="partnershipInfo">
                    <input type="hidden" name="company_name" value="${company.name!}">
                    <h3>Предложить партнерство</h3>
                    <label for="company_name">
                        <p>Введите имя своей компании</p>
                    </label>
                    <input type="text" id="company_name" name="initiatorName" placeholder="Имя компании" class="profile_inputs">

                    <label for="date_start">
                        <p>Дата начала</p>
                    </label>
                    <input type="text" id="date_start" name="startDate" placeholder="Дата: yyyy-mm-dd" class="profile_inputs">

                    <label for="date_end">
                        <p>Дата завершения</p>
                    </label>
                    <input type="text" id="date_end" name="endDate" placeholder="Дата : yyyy-mm-dd" class="profile_inputs">
                    <label for="partnership_title">
                        <p>Описание</p>
                        <textarea name="partnership_title" id="partnership_title" placeholder="Описание" class="partnership_title"></textarea>
                    </label>
                    <div class="company_buttons">
                        <button id="sendPartnership" name="sendPartnership" class="deleteUserButton">Отправить заявку</button>
                        <button id="cancelPartnership" name="cancelPartnership" class="deleteUserButton" type="button">Закрыть</button>
                    </div>
                </div>
            </div>
            <#if request.getParameter("error")?default("") == "existing_partnership">
                <div class="error_message">
                    <p>Партнерство с этой Компанией и такими же параметрами уже существует.</p>
                </div>
            </#if>
        </form>
        <form method="post" action="apply_investment_servlet">
            <div id="investmentForm" class="partnershipForm" style="display: none">
                <div class="partnershipInfo">
                    <input type="hidden" name="company_name" value="${company.name!}">
                    <h3>Предложить инвестицию</h3>
                    <label for="initiator_company">
                        <p>Введите имя своей компании</p>
                    </label>
                    <input type="text" id="initiator_company" name="initiator_company" placeholder="Имя компании" class="profile_inputs">

                    <label for="sum_investment">
                        <p>Введите сумму инвестиции</p>
                    </label>
                    <input type="text" id="sum_investment" name="sum_investment"  placeholder="Сумма инвестиции" class="profile_inputs">

                    <label for="invest_date">
                        <p>Дата инвестиции</p>
                    </label>
                    <input type="text" id="invest_date" name="invest_date" placeholder="Дата: yyyy-mm-dd" class="profile_inputs">
                    <label for="investment_title">
                        <p>Описание</p>
                        <textarea id="investment_title" name="investment_title" placeholder="Описание" class="partnership_title"></textarea>
                    </label>
                    <div class="company_buttons">
                        <button id="sendInvestment" name="sendInvestment" class="deleteUserButton">Отправить заявку</button>
                        <button id="cancelInvestment" name="cancelInvestment" class="deleteUserButton" type="button">Закрыть</button>
                    </div>
                </div>
            </div>
        </form>
        <div class="add_company_info">
            <div>
                <h3>Компания</h3>
            </div>
            <div>
                <label for="name">
                    <p>Имя компании</p>
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
            <div class="company_buttons">
                <button id="proposePartnershipButton" class="deleteUserButton">Предложить Партнерство</button>
                <button id="proposeInvestmentButton" class="deleteUserButton">Предложить Инвестирование</button>
            </div>
        </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/partnershipForm.js"></script>
<script src="./scripts/investmentForm.js"></script>
</body>
</html>