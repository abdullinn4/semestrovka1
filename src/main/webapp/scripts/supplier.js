const applySupplierButton = document.getElementById("applySupplierButton");
const supplierForm = document.getElementById("supplierForm");
const sendSupplier = document.getElementById("sendSupplier");
const cancel = document.getElementById("cancelSupplier");

applySupplierButton.addEventListener("click",function () {
    supplierForm.style.display='block'
});
sendSupplier.addEventListener('click',function () {
    window.location.href="suppliers"
});
cancel.addEventListener("click",function (event) {
    event.preventDefault();
    supplierForm.style.display='none'
})
