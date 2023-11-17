function searching(){
    let a;
    let textValue;
    const searchInput = document.getElementById("searchInput");
    const filter = searchInput.value.toUpperCase();
    const ul = document.getElementById("myCompanies");
    const li = document.getElementsByTagName("li");

    for (let i = 0; i < li.length; i++){
        a = li[i].getElementsByTagName("a")[0];
        textValue = a.textContent || a.innerText;
        if (textValue.toUpperCase().indexOf(filter) > -1){
            li[i].style.display = "";
        }else{
            li[i].style.display = "none"
        }
    }
}
