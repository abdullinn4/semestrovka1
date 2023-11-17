document.getElementById("logoutButton").addEventListener("click",function () {
    document.getElementById("logoutForm").style.display = "block";
});
document.getElementById("confirmLogoutButton").addEventListener("click",function (){
    window.location.href = "index"
});
document.getElementById("cancelLogoutButton").addEventListener("click",function (event){
    event.preventDefault();
    document.getElementById("logoutForm").style.display = "none";
})