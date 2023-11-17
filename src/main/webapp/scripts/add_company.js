function countCharacters(){
    let textArea = document.getElementById("title");
    let count = textArea.value.length;
    let counter = document.getElementById("counter");
    counter.innerHTML = count;
}
function limitCharacters(){
    let textArea = document.getElementById("title");
    let maxLength = 200;
    let counterLimit = document.getElementById("counter_limit");
    if (textArea.value.length > maxLength){
        textArea.value = textArea.value.slice(0,maxLength);
        counterLimit.innerHTML="Превышено количество допутимых символов"
    }
}