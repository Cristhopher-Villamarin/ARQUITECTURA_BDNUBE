/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import { showModal } from '/controller/modal.js';

document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Limpiar mensajes anteriores
    const errorMessage = document.getElementById('error-message');
    errorMessage.style.display = 'none';

    try {
        const response = await fetch('/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });

        const result = await response.json();

        if (result.success) {
            // Guardar username en sessionStorage
            sessionStorage.setItem('username', username);
            window.location.href = '/home';
        } else {
            errorMessage.style.display = 'block';
            document.getElementById('error-text').textContent = 'Por favor, intente de nuevo.';
        }
    } catch (error) {
        console.error('Login error:', error);
        errorMessage.style.display = 'block';
        document.getElementById('error-text').textContent = 'Ocurrió un error durante el login. Por favor, revise la conexión.';
    }
});
