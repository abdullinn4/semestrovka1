const form = document.getElementById("form");
const email = document.getElementById("email");
const password = document.getElementById("password");
const repeatPassword = document.getElementById("repeat_password");

form.addEventListener("submit",e=>{
    validateInputs(e);
});
const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add("error");
    inputControl.classList.remove("success");
};
const isValidEmail = email =>{
    const regex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    return regex.test(String(email).toLowerCase())
};
const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = "";
    inputControl.classList.add("success");
    inputControl.classList.remove("error");
};
const validateInputs = (e) =>{
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const repeatPasswordValue = repeatPassword.value.trim();

    if (emailValue === ""){
        setError(email,"Email is required");
    }else if (!isValidEmail(emailValue)){
        setError(email,"Provide a valid email address");
    }else{
        setSuccess(email);
    }

    if (passwordValue === ""){
        setError(password, "Password is required");
    }else if (passwordValue.length < 8){
        setError(password, "Password length must be greater than 8");
    }else{
        setSuccess(password);
    }

    if (repeatPasswordValue === ""){
        setError(repeatPassword,"Please confirm your password");
    }else if (repeatPasswordValue !== passwordValue){
        setError(repeatPassword,"Passwords doesn't match");
    }else{
        setSuccess(repeatPassword);
    }
    if (isValidEmail(emailValue) && passwordValue.length >= 8 && repeatPasswordValue===passwordValue){

    }else{
        e.preventDefault();
    }
};
