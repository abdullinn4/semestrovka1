<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="./styles/style.css">
    <#import "baseForAnon.ftl" as base>

</head>
<body>
<header>
    <@base.header/>
</header>
<main class="main">
    <@base.mainContent>
        <div class="sign_up">
            <div class="sign_up_logo">
                <img src="images/signup_logo.svg" alt="signup_logo">
            </div>
            <div class="sign_up_info">
                <form method="post" id="form" action="/registration">
                    <div class="sign_up_start_text">
                        <h3>Создайте аккаунт</h3>
                    </div>
                    <div class="input-control">
                        <label for="email">
                            <p>Email</p>
                        </label>
                        <input type="text" id="email" placeholder="Введите email" name="email" class="sign_up_inputs" >
                        <div class="error"></div>
                    </div>
                    <div class="input-control">
                        <label for="password">
                            <p>Password</p>
                        </label>
                        <input type="password" id="password" placeholder="Введите пароль" name="password" class="sign_up_inputs" >
                        <div class="error"></div>
                    </div>
                    <div class="input-control">
                        <label for="repeat_password">
                            <p>Repeat password</p>
                        </label>
                        <input type="password" id="repeat_password" placeholder="Повторите пароль" name="repeat_password" class="sign_up_inputs" >
                        <div class="error"></div>
                    </div>
                    <div class="policy">
                        <input type="checkbox" required>
                        <h5>Я принимаю все условия</h5>
                    </div>
                    <div class="sign_up_button">
                        <input type="submit" id="submit" name="submit" value="Зарегистрироваться" class="sign_up_submit">
                    </div>
                    <div class="sign_up_finish_text">
                        <h5>Уже есть аккаунт? <a href="/authorization" class="sign_up_loginnow">Войти сейчас</a> </h5>
                    </div>
                    <#if req.getParameter("error")?default("") == "username_exists">
                        <div class="error">
                            <p>Пользователь с таким email уже существует.</p>
                        </div>
                    </#if>
                </form>
            </div>
        </div>
    </@base.mainContent>
</main>
<footer>
    <@base.footer/>
</footer>
<script src="./scripts/registration.js"></script>
</body>
</html>

