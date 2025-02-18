/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
let submit = document.querySelector("#form-submit");
submit.disabled = true;
submit.style = "background-color: grey; border-color:black;";
let regexPassword1 = /^[A-Za-z\d@.#$!%*?&]{8,16}$/;
let regexPassword2 = /(?=.*[a-z])/;
let regexPassword3 = /(?=.*[A-Z])/;
let regexPassword4 = /(?=.*\d)/;
let regexPassword5 = /(?=.*[@.#$!%*?&])/;
let correctPassword = false;
let correctRePassword = false;

function checkPassword(passwordInput) {
    let password = passwordInput.value;
    let mess = passwordInput.nextElementSibling;
    correctPassword = false;
    if (!regexPassword1.test(password)) {
        mess.innerHTML = "Password must have 8-16 characters!";
    } else if (!regexPassword2.test(password)) {
        mess.innerHTML = "Password must have at least 1 lowercase charaters!";
    } else if (!regexPassword3.test(password)) {
        mess.innerHTML = "Password must have at least 1 uppercase charaters!";
    } else if (!regexPassword4.test(password)) {
        mess.innerHTML = "Password must have at least 1 digit!";
    } else if (!regexPassword5.test(password)) {
        mess.innerHTML = "Password must have at least 1 special charaters!";
    } else {
        mess.innerHTML = "";
        correctPassword = true;
        allowSubmit();
    }
}

function checkRePassword(rePasswordInput) {
    let password = document.querySelector("#password").value;
    let rePassword = rePasswordInput.value;
    let mess = rePasswordInput.nextElementSibling;
    correctRePassword = false;
    if (password === rePassword) {
        mess.innerHTML = "";
        correctRePassword = true;
        allowSubmit();
    } else {
        mess.innerHTML = "Re-entered password is incorrect!";
    }
}

function allowSubmit(){
    if(correctPassword&&correctRePassword){
        submit.disabled = false;
        submit.style = "";
    }
}

