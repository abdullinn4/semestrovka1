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
        <form method="post" action="application_partnership">
            <div class="add_company_info">
                <div>
                    <h3>Запрос на партнерство</h3>
                </div>
                <div>
                    <input type="hidden" name="id" value="${partnership.id!}">
                    <label for="company_name">
                        <p>Имя компании</p>
                    </label>
                    <input type="text" id="company_name" name="company_name" value="${partnership.initiator!}" placeholder="Имя компании" class="profile_inputs" readonly>

                    <label for="date_start">
                        <p>Дата начала</p>
                    </label>
                    <input type="text" id="date_start" name="date_start" value="${partnership.startDate!}" placeholder="Дата начала" class="profile_inputs" readonly>

                    <label for="date_end">
                        <p>Дата завершения</p>
                    </label>
                    <input type="text" id="date_end" name="date_start"  value="${partnership.endDate!}"placeholder="Дата завершения" class="profile_inputs" readonly>

                    <label for="partnership_status">
                        <p>Статус</p>
                    </label>
                    <input type="text" id="partnership_status" name="partnership_status"  value="${partnership.status!}"placeholder="Статус" class="profile_inputs" readonly>

                    <label for="partnership_title">
                        <p>Описание</p>
                        <textarea name="title" id="partnership_title"  value="${partnership.title!}"placeholder="Описание" class="partnership_title" readonly>${partnership.title!}</textarea>
                    </label>
                </div>
                <div class="company_buttons">
                    <button id="acceptPartnershipButton" name="acceptInvestmentButton" class="deleteUserButton">Принять запрос</button>
                    <button id="rejectPartnershipButton" name="rejectInvestmentButton" class="deleteUserButton">Отклонить запрос</button>
                    <button id="cancelPartnership" name="cancelInvestment" class="deleteUserButton" type="button">Закрыть</button>
                </div>
            </div>
            <#if request.getParameter("error")?default("") == "exist_status">
                <div class="error_message">
                    <p>Данная заявка на партнерство уже проверена</p>
                </div>
            </#if>
        </form>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/application_partnership.js"></script>
</body>
</html>