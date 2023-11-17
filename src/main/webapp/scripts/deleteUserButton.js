document.getElementById("deleteUserButton").addEventListener("click" ,function () {
    document.getElementById("deleteForm").style.display = "block";
});
document.getElementById("confirmDeleteUserButton").addEventListener("click",function () {
    window.location.href = "index"
});
document.getElementById("cancelDeleteUserButton").addEventListener("click",function (event){
    event.preventDefault();
    document.getElementById("deleteForm").style.display = "none"
});
