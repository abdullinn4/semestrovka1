<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<#macro header>
    <header>
        <div class="header_wrapper">
            <div class="header_logo">
                <img src="images/logo.png" alt="logo" class="logo_image">
            </div>
            <nav class="header_nav">
                <ul class="header_list">
                    <ol class="header_item">
                        <a href="/index" class="header_link">Главная</a>
                    </ol>
                    <ol class="header_item">
                        <a href="/registration" class="header_link">Регистрация</a>
                    </ol>
                    <ol class="header_item">
                        <a href="/authorization" class="header_link">Вход</a>
                    </ol>
                </ul>
            </nav>
        </div>
    </header>
</#macro>
<main>
    <#macro mainContent>
        <#nested/>
    </#macro>
</main>
<#macro footer>
    <footer>
        <div class="footer_wrapper">
            <div class="footer_left">
                <div><img src="images/logo.png" alt="logo" class="logo_image"></div>
                <p>Развивайтесь и расширяйтесь!</p>
            </div>
            <div class="footer_right">
                <div class="footer_right_columns">
                    <h5>Информация</h5>
                    <div><a href="" class="header_link">Политика конфиденциальности</a></div>
                    <div><a href="" class="header_link">Использование Cookie</a></div>
                    <div><a href="" class="header_link">Пользовательское соглашение</a></div>
                </div>
                <div class="footer_right_columns">
                    <h5>Поддержка</h5>
                    <div>Телефон: 8 495 123-45-67 </div>
                    <div>Почта: example@yandex.ru </div>
                </div>
            </div>
        </div>
    </footer>
</#macro>
</body>
</html>