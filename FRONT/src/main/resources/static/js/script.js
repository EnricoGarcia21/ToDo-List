// Alternância de tema
const themeBtn = document.getElementById('themeBtn');
const htmlTag = document.documentElement;

themeBtn.addEventListener('click', () => {
  const isLight = htmlTag.getAttribute('data-bs-theme') === 'light';
  htmlTag.setAttribute('data-bs-theme', isLight ? 'dark' : 'light');
  themeBtn.textContent = isLight ? '☀️ Modo Claro' : '🌙 Modo Escuro';
  localStorage.setItem('theme', isLight ? 'dark' : 'light');
});

// Carregar tema salvo
if (localStorage.getItem('theme') === 'dark') {
  htmlTag.setAttribute('data-bs-theme', 'dark');
  themeBtn.textContent = '☀️ Modo Claro';
}

// Controle de telas (apenas visuais)
const loginScreen = document.getElementById('loginScreen');
const registerScreen = document.getElementById('registerScreen');
const todoScreen = document.getElementById('todoScreen');

document.getElementById('showRegister').onclick = () => {
  loginScreen.style.display = 'none';
  registerScreen.style.display = 'block';
};

document.getElementById('backToLogin').onclick = () => {
  registerScreen.style.display = 'none';
  loginScreen.style.display = 'block';
};

// Apenas navegação simulada
document.getElementById('loginForm').addEventListener('submit', e => {
  e.preventDefault();
  loginScreen.style.display = 'none';
  todoScreen.style.display = 'block';
});

document.getElementById('logoutBtn').addEventListener('click', () => {
  todoScreen.style.display = 'none';
  loginScreen.style.display = 'block';
});
