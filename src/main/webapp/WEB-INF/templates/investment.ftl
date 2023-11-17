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
            <div class="add_company_info">
                <div>
                    <h3>Инвестиция</h3>
                </div>
                <div>
                    <input type="hidden" name="id" value="${investment.id!}">
                    <label for="companyI_name">
                        <p>Имя компании-инициатора</p>
                    </label>
                    <input type="text"  id="initiator_company" name="initiator_company" value="${investment.investorName!}" placeholder="Имя компании" class="profile_inputs" readonly>

                    <label for="companyP_name">
                        <p>Имя компании-партнера</p>
                    </label>
                    <input type="text" id="companyP_name" name="companyP_name" value="${investment.recipientName!}" placeholder="Имя компании" class="profile_inputs" readonly>

                    <label for="date_start">
                        <p>Сумма инвестиции</p>
                    </label>
                    <input type="text" id="sum_investment" name="sum_investment" value="${investment.amount!}" placeholder="Сумма" class="profile_inputs" readonly>

                    <label for="date_end">
                        <p>Статус</p>
                    </label>
                    <input type="text" id="investment_status" name="investment_status"  value="${investment.status!}"placeholder="Статус" class="profile_inputs" readonly>

                    <label for="partnership_status">
                        <p>Дата инвестиции</p>
                    </label>
                    <input type="text" id="invest_date" name="invest_date"  value="${investment.investmentDate!}"placeholder="Дата" class="profile_inputs" readonly>

                    <label for="partnership_title">
                        <p>Описание</p>
                        <textarea id="investment_title" name="investment_title"  value="${investment.title!}"placeholder="Описание" class="partnership_title" readonly>${investment.title!}</textarea>
                    </label>
                </div>
            </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/logoutButton.js"></script>
<script src="./scripts/application_partnership.js"></script>
</body>
</html>