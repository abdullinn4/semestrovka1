const acceptInvestmentButton = document.getElementById("acceptInvestmentButton");
const rejectInvestmentButton = document.getElementById("rejectInvestmentButton");
const cancelInvestment = document.getElementById("cancelInvestment");

acceptInvestmentButton.addEventListener("click",function () {
    window.location.href='application_investment'
});
rejectInvestmentButton.addEventListener("click",function () {
    window.location.href='application'
});
cancelInvestment.addEventListener("click",function (event) {
    event.preventDefault();
    window.location.href='incoming_applications'
});
