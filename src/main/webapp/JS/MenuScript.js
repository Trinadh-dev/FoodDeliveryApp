document.querySelectorAll('.add-to-cart').forEach(button => {
    button.addEventListener('click', function () {
        this.classList.add('d-none'); // Hide "Add to Cart" button
        const quantityControls = this.nextElementSibling;
        quantityControls.classList.remove('d-none'); // Show quantity controls
        const confirmButton = quantityControls.nextElementSibling;
        confirmButton.classList.remove('d-none'); // Show confirm button
    });
});

document.querySelectorAll('.increase').forEach(button => {
    button.addEventListener('click', function () {
        let input = this.previousElementSibling;
        let currentValue = parseInt(input.value);
        if (!isNaN(currentValue)) {
            input.value = currentValue + 1; // Increment quantity by 1
        }
    });
});

document.querySelectorAll('.decrease').forEach(button => {
    button.addEventListener('click', function () {
        let input = this.nextElementSibling;
        let currentValue = parseInt(input.value);
        if (currentValue > 1 && !isNaN(currentValue)) {
            input.value = currentValue - 1; // Decrement quantity by 1, but not below 1
        }
    });
});
document.addEventListener("DOMContentLoaded", function () {
    const addToCartButtons = document.querySelectorAll(".add-to-cart");
    const confirmButtons = document.querySelectorAll(".confirm-add-to-cart");
    const quantityControls = document.querySelectorAll(".quantity-controls");
    const popup = document.getElementById("popup");

    addToCartButtons.forEach((button, index) => {
        button.addEventListener("click", function () {
            // Show quantity controls and confirm button
            addToCartButtons[index].classList.add("d-none");
            quantityControls[index].classList.remove("d-none");
            confirmButtons[index].classList.remove("d-none");
        });
    });

    confirmButtons.forEach((button, index) => {
        button.addEventListener("click", function (e) {
            e.preventDefault(); // Prevent form submission

            // Show the popup
            popup.classList.remove("d-none");
            popup.style.bottom = "20px"; // Slide in

            // Hide popup after 3 seconds
            setTimeout(function () {
                popup.style.bottom = "-100px"; // Slide out
            }, 3000);

            // Delay form submission by 3.5 seconds to allow popup to display
            setTimeout(function () {
                button.closest("form").submit(); // Now submit the form
            }, 500);
        });
    });
});





