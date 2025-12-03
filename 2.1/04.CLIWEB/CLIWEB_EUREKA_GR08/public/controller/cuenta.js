/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import { showModal } from '/controller/modal.js';

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('cuentaForm').addEventListener('submit', consultarCuenta);
});

async function consultarCuenta(e) {
    e.preventDefault();
    
    const cuenta = document.getElementById('cuenta').value.trim();
    const errorMessage = document.getElementById('error-message');
    const accountDetails = document.getElementById('account-details');
    
    // Ocultar mensajes anteriores
    errorMessage.style.display = 'none';
    accountDetails.style.display = 'none';

    if (!cuenta) {
        errorMessage.style.display = 'block';
        document.getElementById('error-text').textContent = 'Por favor ingrese el número de cuenta';
        return;
    }

    try {
        const response = await fetch('/accountDetails', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ numcuenta: cuenta })
        });

        const result = await response.json();

        if (result.success && result.account) {
            const account = result.account;
            
            // Mostrar detalles de la cuenta
            document.getElementById('account-code').textContent = account.chrCuenCodigo || account.codigo || cuenta;
            document.getElementById('account-balance').textContent = `$${(account.decCuenSaldo || account.saldo || 0).toFixed(2)}`;
            document.getElementById('account-movements').textContent = account.intCuenContMov || account.contadorMovimientos || 0;
            
            // Formatear fecha
            let fechaCreacion = 'N/A';
            if (account.dttCuenFechaCreacion) {
                const fecha = new Date(account.dttCuenFechaCreacion);
                fechaCreacion = fecha.toLocaleDateString('es-ES');
            } else if (account.fechaCreacion) {
                const fecha = new Date(account.fechaCreacion);
                fechaCreacion = fecha.toLocaleDateString('es-ES');
            }
            document.getElementById('account-date').textContent = fechaCreacion;
            
            document.getElementById('account-status').textContent = account.vchCuenEstado || account.estado || 'N/A';
            document.getElementById('account-branch').textContent = account.chrSucucodigo || account.sucursal || 'N/A';
            
            accountDetails.style.display = 'block';
        } else {
            errorMessage.style.display = 'block';
            document.getElementById('error-text').textContent = result.message || 'No se encontraron datos para la cuenta especificada';
        }
    } catch (error) {
        console.error('Error al consultar cuenta:', error);
        errorMessage.style.display = 'block';
        document.getElementById('error-text').textContent = 'Error al conectar con el servidor: ' + error.message;
    }
}

