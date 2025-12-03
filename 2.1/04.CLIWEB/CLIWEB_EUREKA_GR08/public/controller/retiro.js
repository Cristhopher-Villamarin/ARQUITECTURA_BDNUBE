/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import { showModal } from '/controller/modal.js';

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('retiroForm').addEventListener('submit', realizarRetiro);
});

async function realizarRetiro(e) {
    e.preventDefault();
    
    const cuenta = document.getElementById('cuenta').value.trim();
    const monto = parseFloat(document.getElementById('monto').value);
    const errorMessage = document.getElementById('error-message');
    const successMessage = document.getElementById('success-message');
    
    // Ocultar mensajes anteriores
    errorMessage.style.display = 'none';
    successMessage.style.display = 'none';

    if (!cuenta || !monto || isNaN(monto) || monto <= 0) {
        errorMessage.style.display = 'block';
        document.getElementById('error-text').textContent = 'Por favor complete todos los campos y asegúrese de que el monto sea un número positivo válido';
        return;
    }

    try {
        const response = await fetch('/deposit', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
                cuenta: cuenta, 
                monto: monto, 
                tipo: 'RET', 
                cd: null 
            })
        });

        const result = await response.json();

        if (result.success && result.result) {
            successMessage.style.display = 'block';
            document.getElementById('success-text').textContent = 'Retiro realizado con éxito';
            
            // Limpiar formulario
            document.getElementById('retiroForm').reset();
            
            // Opcional: redirigir después de unos segundos
            setTimeout(() => {
                window.location.href = '/home';
            }, 2000);
        } else {
            errorMessage.style.display = 'block';
            document.getElementById('error-text').textContent = result.message || 'Error al realizar el retiro. Verifique los datos de la cuenta y el saldo disponible';
        }
    } catch (error) {
        console.error('Error al realizar retiro:', error);
        errorMessage.style.display = 'block';
        document.getElementById('error-text').textContent = 'Error al conectar con el servidor: ' + error.message;
    }
}
