const supplierButton = document.getElementById("supplierButton");
const createSupplierForm = document.getElementById("createSupplierForm");
const createSupplierButton = document.getElementById("createSupplierButton");
const cancelCreateSupplier = document.getElementById("cancelCreateSupplier");

supplierButton.addEventListener('click',function () {
    createSupplierForm.style.display='block';
})
createSupplierButton.addEventListener('click',function () {
    window.location.href='suppliers';
})
cancelCreateSupplier.addEventListener('click',function (event) {
    event.preventDefault();
    createSupplierForm.style.display='none';
})