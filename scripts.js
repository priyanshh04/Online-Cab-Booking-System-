document.getElementById('registerForm').addEventListener('submit', function(event) {
  event.preventDefault();

  const email = document.getElementById('email');
  const password = document.getElementById('password');

  if (!email.value.includes('@')) {
    alert('Please enter a valid email address.');
    return;
  }

  if (password.value.length < 6) {
    alert('Password must be at least 6 characters long.');
    return;
  }

  alert('Form submitted successfully!');
});

document.getElementById('loginForm')?.addEventListener('submit', function(event) {
  event.preventDefault();
  alert('Login successful!');
});
