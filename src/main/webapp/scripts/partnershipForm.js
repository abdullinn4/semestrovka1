const partnershipForm = document.getElementById("partnershipForm");
const proposePartnershipButton = document.getElementById("proposePartnershipButton");
const cancelPartnership = document.getElementById("cancelPartnership");
const sendPartnership = document.getElementById("sendPartnership");

proposePartnershipButton.addEventListener("click",function () {
    partnershipForm.style.display="block";
});
cancelPartnership.addEventListener("click",function (event) {
    event.preventDefault();
    partnershipForm.style.display="none";
});
sendPartnership.addEventListener("click",function (){
    window.location.href="company";
});
