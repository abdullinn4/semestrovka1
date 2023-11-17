const filterButton = document.getElementById("filterButton");
const cancelFilterButton = document.getElementById("cancelFilterButton");
const filterPanel = document.getElementById("filterPanel");
const applyFilterButton = document.getElementById("applyFilterButton");
const industryCheckboxes = document.querySelectorAll("input[type=checkbox][id^='industry']");
const countryCheckboxes = document.querySelectorAll("input[type=checkbox][id^='country']");
const companyList = document.getElementById("companyList");

filterButton.addEventListener("click",function () {
    filterPanel.style.display="block";
});
cancelFilterButton.addEventListener("click",function (){
    filterPanel.style.display="none";
});
applyFilterButton.addEventListener("click",function (){
    const selectedIndustry = Array.from(industryCheckboxes)
        .filter(checkbox => checkbox.checked)
        .map(checkbox => checkbox.value);

    const selectedCountry = Array.from(countryCheckboxes)
        .filter(checkbox=> checkbox.checked)
        .map(checkbox=> checkbox.value);

    console.log(selectedIndustry);
    console.log(selectedCountry);
    $.ajax({
        url: "/filtering",
        method: "POST",
        data: JSON.stringify({
            industries:selectedIndustry,
            countries:selectedCountry
        }),
        dataType:"text",
        success:function (data){
            let companies = JSON.parse(data);
            companyList.innerHTML="";

            companies.forEach(function (company) {
                const companyLink = document.createElement("a");
                companyLink.href = "company?name=" + company.name;
                companyLink.textContent = company.name;

                const companyLiItem = document.createElement("li");
                companyLiItem.appendChild(companyLink);
                companyList.appendChild(companyLiItem);
            });
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });


});