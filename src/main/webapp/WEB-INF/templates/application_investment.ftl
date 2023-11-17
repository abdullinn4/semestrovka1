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
        <form method="post" action="application_investment">
            <div class="add_company_info">
                <div>
                    <h3>Запрос на инвестицию</h3>
                </div>
                <div>
                    <input type="hidden" name="id" value="${investment.id!}">
                    <label for="company_name">
                        <p>Имя компании</p>
                    </label>
                    <input type="text" id="company_name" name="company_name" value="${investment.investorName!}" placeholder="Имя компании" class="profile_inputs" readonly>

                    <label for="date_start">
                        <p>Дата</p>
                    </label>
                    <input type="text" id="invest_date" name="invest_date" value="${investment.investmentDate!}" placeholder="Дата" class="profile_inputs" readonly>

                    <label for="date_end">
                        <p>Сумма инвестиции</p>
                    </label>
                    <input type="text" id="sum_investment" name="sum_investment"  value="${investment.amount!}"placeholder="Сумма инвестиции" class="profile_inputs" readonly>

                    <label for="partnership_status">
                        <p>Статус</p>
                    </label>
                    <input type="text" id="investment_status" name="investment_status"  value="${investment.status!}"placeholder="Статус" class="profile_inputs" readonly>

                    <label for="partnership_title">
                        <p>Описание</p>
                        <textarea name="title" id="investment_title" name="investment_title"  value="${investment.title!}"placeholder="Описание" class="partnership_title" readonly>${investment.title!}</textarea>
                    </label>
                </div>
                <div class="company_buttons">
                    <button id="acceptInvestmentButton" name="acceptInvestmentButton" class="deleteUserButton">Принять запрос</button>
                    <button id="rejectInvestmentButton" name="rejectInvestmentButton" class="deleteUserButton">Отклонить запрос</button>
                    <button id="cancelInvestment" name="cancelInvestment" class="deleteUserButton" type="button">Закрыть</button>
                </div>
            </div>
            <#if request.getParameter("error")?default("") == "exist_status">
                <div class="error_message">
                    <p>Данная заявка на инвестирование уже проверена</p>
                </div>
            </#if>
        </form>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/application_investment.js"></script>
</body>
</html>