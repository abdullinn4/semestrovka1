$(document).ready(function () {
    $.ajax({
        url: "/ajax_incoming_partnerships",
        type: "GET",
        dataType: "text",
        success: function (data) {
            let applications = JSON.parse(data);
            applications.forEach(function (application) {
                const li = document.createElement('li');
                const a = document.createElement('a');
                a.href = `application_partnership?id=${application.id}`;
                a.textContent = `Заявка ${application.id}`;
                li.appendChild(a);
                applicationsList.appendChild(li);
            });
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
    $.ajax({
        url: "/ajax_incoming_investments",
        type: "GET",
        dataType: "text",
        success: function (data) {
            let investments = JSON.parse(data);
            investments.forEach(function (investment) {
                const li = document.createElement('li');
                const a = document.createElement('a');
                a.href = `application_investment?id=${investment.id}`;
                a.textContent = `Заявка ${investment.id}`;
                li.appendChild(a);
                applicationsList.appendChild(li);
            });
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
});