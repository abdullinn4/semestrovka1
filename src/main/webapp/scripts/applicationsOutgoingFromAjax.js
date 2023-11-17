$(document).ready(function (){
    $.ajax({
        url:"/ajax_outgoing_applications",
        type: "GET",
        dataType: "text",
        success: function (data){
            let applications = JSON.parse(data);
            applications.forEach(function (application) {
                const li = document.createElement('li');
                const a = document.createElement('a');
                a.href = `partnership?id=${application.id}`;
                a.textContent = `Заявка на партнерство с ${application.partner}`;
                li.appendChild(a);
                $("#applicationsList").append(li);
            });
        },
        error:function (xhr,status,error){
            console.error(error);
        }
    });
    $.ajax({
        url: "/ajax_outgoing_investments",
        type: "GET",
        dataType: "text",
        success: function(data) {
            let investments = JSON.parse(data);
            investments.forEach(function(investment) {
                const li = document.createElement('li');
                const a = document.createElement('a');
                a.href = `investment?id=${investment.id}`;
                a.textContent = `Заявка на инвестирование ${investment.recipientName}`;
                li.appendChild(a);
                $("#applicationsList").append(li);
            });
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
});