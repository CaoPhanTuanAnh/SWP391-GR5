/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
let submit = document.querySelector("#form-submit");
let password = document.querySelector("#password");
let rePassword = document.querySelector("#re-password");
submit.disabled = true;
submit.style = "background-color: grey; border-color:black;";
let regexPassword1 = /^[A-Za-z\d@.#$!%*?&]{8,16}$/;
let regexPassword2 = /(?=.*[a-z])/;
let regexPassword3 = /(?=.*[A-Z])/;
let regexPassword4 = /(?=.*\d)/;
let regexPassword5 = /(?=.*[@.#$!%*?&])/;
let correctPassword = false;

function checkPassword() {
    let mess = password.nextElementSibling;
    correctPassword = false;
    if (!regexPassword1.test(password.value)) {
        mess.innerHTML = "Password must have 8-16 characters!";
    } else if (!regexPassword2.test(password.value)) {
        mess.innerHTML = "Password must have at least 1 lowercase charaters!";
    } else if (!regexPassword3.test(password.value)) {
        mess.innerHTML = "Password must have at least 1 uppercase charaters!";
    } else if (!regexPassword4.test(password.value)) {
        mess.innerHTML = "Password must have at least 1 digit!";
    } else if (!regexPassword5.test(password.value)) {
        mess.innerHTML = "Password must have at least 1 special charaters!";
    } else {
        mess.innerHTML = "";
        correctPassword = true;
    }
}

function checkRePassword() {
    let mess = rePassword.nextElementSibling;
    if (password.value === rePassword.value) {
        mess.innerHTML = "";
        allowSubmit();
    } else {
        mess.innerHTML = "Re-entered password is incorrect!";
    }
}

function allowSubmit(){
    if(correctPassword){
        submit.disabled = false;
        submit.style = "";
    }
}

