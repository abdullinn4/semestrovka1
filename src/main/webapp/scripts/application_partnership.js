const acceptPartnershipButton = document.getElementById("acceptPartnershipButton");
const rejectPartnershipButton = document.getElementById("rejectPartnershipButton")
const cancelPartnership = document.getElementById("cancelPartnership");

acceptPartnershipButton.addEventListener("click",function () {
    window.location.href='application_partnership'
});
rejectPartnershipButton.addEventListener("click",function () {
    window.location.href='application'
});
cancelPartnership.addEventListener("click",function (event) {
    event.preventDefault();
    window.location.href='incoming_applications'
});
