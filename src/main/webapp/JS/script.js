const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add('right-panel-active');
    // Ensure the character faces right and moves down
    document.getElementById('character').style.top = '0';
    document.getElementById('character').style.transform = 'rotateY(180deg)';
});

signInButton.addEventListener('click', () => {
    container.classList.remove('right-panel-active');
    // Ensure the character faces left and moves up
    document.getElementById('character').style.top = '-100px';
    document.getElementById('character').style.transform = 'rotateY(0deg)';
});


