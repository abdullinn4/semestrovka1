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
            <div class="profile_info">
                <div class="main_profile_info">
                    <div>
                        <h4>Мой профиль</h4>
                    </div>
                    <div class="general_info_photo">
                        <div class="profile_photo">
                            <img src="images/user_profile_icon.svg" alt="user_profile_icon" class="user_profile_icon">
                        </div>
                        <div class="general_info_names">
                            <label>
                                <p>Имя</p>
                                <input type="text" name="first_name" value="${user.firstName!}" class="profile_inputs" readonly>
                            </label>
                            <label>
                                <p>Фамилия</p>
                                <input type="text" name="second_name" value="${user.lastName!}"  class="profile_inputs" readonly>
                            </label>
                        </div>
                        <div class="general_info_names">
                            <label>
                                <p>Email</p>
                                <input type="email" name="Email" value="${user.email!}" class="profile_inputs" readonly>
                            </label>
                            <label>
                                <p>Страна</p>
                                <input type="text" name="country" value="${user.country!}"  class="profile_inputs" readonly>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="user_companies">
                    <h4>Мои компании</h4>
                    <ul>
                        <#list companies as company>
                            <li> <a href="company_profile?name=${company.name}">${company.name}</a> </li>
                        </#list>
                    </ul>
                </div>
                <div class="user_companies">
                    <h4>Партнерства</h4>
                    <ul>
                        <#list partnerships as partnership>
                            <li><a href="partnership?id=${partnership.id}">Партнерство ${partnership.initiator} - ${partnership.partner} </a> </li>
                        </#list>
                    </ul>
                </div>
                <div class="user_companies">
                    <h4>Инвестирования</h4>
                    <ul>
                        <#list investments as investment>
                            <li><a href="investment?id=${investment.id}">Инвестирование ${investment.investorName} - ${investment.recipientName} </a> </li>
                        </#list>
                    </ul>
                </div>
                <div class="user_companies">
                    <h4>Поставщики</h4>
                    <ul>
                        <#list cooperations as cooperation>
                            <li><a href="cooperation?id=${cooperation.id}">${cooperation.supplierName} - ${cooperation.partnerName} </a> </li>
                        </#list>
                    </ul>
                </div>
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