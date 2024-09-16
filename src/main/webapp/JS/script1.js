/**
 * 
 */

 
document.getElementById('scroll-to-restaurants').addEventListener('click', function() {
    document.getElementById('restaurants').scrollIntoView({ behavior: 'smooth' });
    console.log("hello world");
});