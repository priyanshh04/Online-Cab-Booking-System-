// validation.js
document.addEventListener("DOMContentLoaded", function () {
    const forms = document.querySelectorAll("form");

    forms.forEach((form) => {
        form.addEventListener("submit", function (e) {
            e.preventDefault();
            let isValid = true;

            form.querySelectorAll("input").forEach((input) => {
                if (input.hasAttribute("required") && input.value.trim() === "") {
                    displayError(input, `${input.placeholder} is required.`);
                    isValid = false;
                } else if (input.type === "email" && !validateEmail(input.value)) {
                    displayError(input, "Enter a valid email address.");
                    isValid = false;
                } else if (input.id === "password" && input.value.length < 6) {
                    displayError(input, "Password must be at least 6 characters.");
                    isValid = false;
                } else if (input.id === "confirmPassword" && input.value !== document.getElementById("password").value) {
                    displayError(input, "Passwords do not match.");
                    isValid = false;
                } else {
                    clearError(input);
                }
            });

            if (isValid) {
                alert("Form submitted successfully!");
            }
        });
    });

    function displayError(input, message) {
        const errorElement = input.nextElementSibling;
        errorElement.textContent = message;
        errorElement.style.display = "block";
    }

    function clearError(input) {
        const errorElement = input.nextElementSibling;
        errorElement.textContent = "";
        errorElement.style.display = "none";
    }

    function validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }
});
