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
        <div class="log_in">
            <div class="log_in_logo">
                <img src="images/signup_logo.svg" alt="signup_logo">
            </div>
            <div class="log_in_info">
                <form method="post" action="/authorization">
                    <div class="log_in_start_text">
                        <h3>Вход</h3>
                    </div>
                    <div>
                        <label>
                            <p>Email</p>
                            <input type="text" placeholder="Введите email" name="email" class="log_in_inputs" required>
                        </label>
                    </div>
                    <div>
                        <label>
                            <p>Пароль</p>
                            <input type="password" placeholder="Введите пароль" name="password" class="log_in_inputs" required>
                        </label>
                    </div>
                    <div class="rememberMe">
                        <label>
                            <input type="checkbox" checked name="remember_me">
                        </label>
                        <h5>Запомнить меня</h5>
                    </div>
                    <div>
                        <input type="submit" name="submit" value="Войти" class="log_in_submit">
                    </div>
                    <div class="log_in_finish_text">
                        <h5>У вас нет учетной записи? <a href="/registration" class="log_in_signupnow">Зарегистрироваться сейчас</a> </h5>
                    </div>
                    <#if req.getParameter("error")?default("") == "incorrect_password">
                        <div class="error_message">
                            <p>Неверный пароль. Пожалуйста, попробуйте снова.</p>
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
</body>
</html>