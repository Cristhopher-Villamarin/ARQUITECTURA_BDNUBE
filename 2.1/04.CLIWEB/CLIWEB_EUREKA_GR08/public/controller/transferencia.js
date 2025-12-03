/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import { showModal } from '/controller/modal.js';

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('transferenciaForm').addEventListener('submit', realizarTransferencia);
});

async function realizarTransferencia(e) {
    e.preventDefault();
    
    const cuentaOrigen = document.getElementById('cuentaOrigen').value.trim();
    const cuentaDestino = document.getElementById('cuentaDestino').value.trim();
    const monto = parseFloat(document.getElementById('monto').value);
    const errorMessage = document.getElementById('error-message');
    const successMessage = document.getElementById('success-message');
    
    // Ocultar mensajes anteriores
    errorMessage.style.display = 'none';
    successMessage.style.display = 'none';

    if (!cuentaOrigen || !cuentaDestino || !monto || isNaN(monto) || monto <= 0) {
        errorMessage.style.display = 'block';
        document.getElementById('error-text').textContent = 'Por favor complete todos los campos y asegúrese de que el monto sea un número positivo válido';
        return;
    }

    if (cuentaOrigen === cuentaDestino) {
        errorMessage.style.display = 'block';
        document.getElementById('error-text').textContent = 'La cuenta origen y destino no pueden ser la misma';
        return;
    }

    try {
        const response = await fetch('/deposit', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
                cuenta: cuentaOrigen, 
                monto: monto, 
                tipo: 'TRA', 
                cd: cuentaDestino 
            })
        });

        const result = await response.json();

        if (result.success && result.result) {
            successMessage.style.display = 'block';
            document.getElementById('success-text').textContent = 'Transferencia realizada con éxito';
            
            // Limpiar formulario
            document.getElementById('transferenciaForm').reset();
            
            // Opcional: redirigir después de unos segundos
            setTimeout(() => {
                window.location.href = '/home';
            }, 2000);
        } else {
            errorMessage.style.display = 'block';
            document.getElementById('error-text').textContent = result.message || 'Error al realizar la transferencia. Verifique los datos de las cuentas y el saldo disponible en la cuenta origen';
        }
    } catch (error) {
        console.error('Error al realizar transferencia:', error);
        errorMessage.style.display = 'block';
        document.getElementById('error-text').textContent = 'Error al conectar con el servidor: ' + error.message;
    }
}
