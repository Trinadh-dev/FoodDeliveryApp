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

