$(document).ready(function () {
    $.ajax({
        url: "/ajax_companies",
        type: "GET",
        dataType: "text",
        success: function (data) {
            let companies = JSON.parse(data);
            companies.forEach(function (company) {
                let companyLink = $("<a>").attr("href","company?name="+company.name).text(company.name);
                let companyLiItem = $("<li>").append(companyLink);
                $("#companyList").append(companyLiItem);
            });
        },
        error:function (xhr,status,error){
            console.error(error);
        }
    });
});
