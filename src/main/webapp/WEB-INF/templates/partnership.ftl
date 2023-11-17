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
                    <h3>Партнерство</h3>
                </div>
                <div>
                    <input type="hidden" name="id" value="${partnership.id!}">
                    <label for="companyI_name">
                        <p>Имя компании-инициатора</p>
                    </label>
                    <input type="text" id="companyI_name" name="company_name" value="${partnership.initiator!}" placeholder="Имя компании" class="profile_inputs" readonly>

                    <label for="companyP_name">
                        <p>Имя компании-партнера</p>
                    </label>
                    <input type="text" id="companyP_name" name="companyP_name" value="${partnership.partner!}" placeholder="Имя компании" class="profile_inputs" readonly>

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
                    <input type="text" id="partnership_status" name="partnership_status"  value="${partnership.status!}"placeholder="Дата завершения" class="profile_inputs" readonly>

                    <label for="partnership_title">
                        <p>Описание</p>
                        <textarea name="title" id="partnership_title"  value="${partnership.title!}"placeholder="Описание" class="partnership_title" readonly>${partnership.title!}</textarea>
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