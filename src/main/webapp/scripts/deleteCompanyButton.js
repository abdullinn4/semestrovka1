document.getElementById("deleteCompanyButton").addEventListener("click",function () {
    document.getElementById("deleteForm").style.display="block"
});
document.getElementById("confirmDeleteCompanyButton").addEventListener("click",function (){
    window.location.href = "profile"
});
document.getElementById("cancelDeleteCompanyButton").addEventListener("click",function (event) {
    event.preventDefault();
    document.getElementById("deleteForm").style.display="none"
});
