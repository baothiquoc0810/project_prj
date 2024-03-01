// create a vanilla js function to increase the value of the input field "#quantity" by 1
// when click the button "#increase_btn"

window.addEventListener("DOMContentLoaded", (event) => {
    var quantity = document.querySelector('#quantity');
    var increase_btn = document.querySelector('#increase_btn');
    var decrease_btn = document.querySelector('#decrease_btn');

    increase_btn.addEventListener('click', function() {
        if (!!quantity.value) {
            quantity.value = parseInt(quantity.value) + 1;
        }
        else {
            quantity.value = 1;
        }
    });

    // create valiation for password field
    var password = document.querySelector('#password');
        password.addEventListener('keyup', function() {
        var password_error = document.querySelector('#password_error');
        var password_value = password.value;
        var password_length = password_value.length;
        if (password_length < 8) {
            password_error.innerHTML = 'Password must be at least 8 characters';
        }
        else {
            password_error.innerHTML = '';
        }

    });

    // create validation for confirm password field
    var confirm_password = document.querySelector('#confirm_password');
    confirm_password.addEventListener('keyup', function() {
        var confirm_password_error = document.querySelector('#confirm_password_error');
        var confirm_password_value = confirm_password.value;
        var password_value = password.value;
        if (confirm_password_value != password_value) {
            confirm_password_error.innerHTML = 'Password does not match';
        }
        else {
            confirm_password_error.innerHTML = '';
        }
    });

    // add validation for email field that makes sure the email is in valid email format using regex
    var email = document.querySelector('#email');
    email.addEventListener('keyup', function() {
        var email_error = document.querySelector('#email_error');
        var email_value = email.value;
        var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (!email_regex.test(email_value)) {
            email_error.innerHTML = 'Invalid email format';
        }
        else {
            email_error.innerHTML = '';
        }
    });


//    }
//
//    decrease_btn.addEventListener('click', function() {
//        if (parseInt(quantity.value) > 1) {
//            quantity.value = parseInt(quantity.value) - 1;
//        }
//    });
});