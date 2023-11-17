const investmentForm = document.getElementById("investmentForm");
const proposeInvestmentButton = document.getElementById("proposeInvestmentButton");
const sendInvestment = document.getElementById("sendInvestment");
const cancelInvestment = document.getElementById("cancelInvestment");

proposeInvestmentButton.addEventListener("click", function () {
    investmentForm.style.display="block";
});
cancelInvestment.addEventListener("click",function (event) {
    event.preventDefault();
    investmentForm.style.display="none"
});
sendInvestment.addEventListener('click',function () {
    window.location.href='company'
});