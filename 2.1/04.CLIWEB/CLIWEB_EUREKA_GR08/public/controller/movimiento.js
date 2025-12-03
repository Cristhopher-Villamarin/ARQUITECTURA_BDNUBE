/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import { showModal } from '/controller/modal.js';

// Códigos de tipo de movimiento
const COD_EGRESO = new Set(['004']); // Retiro
const COD_INGRESO = new Set(['001', '003', '009']); // Apertura, Depósito, Transferencia

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('movimientoForm').addEventListener('submit', consultarMovimientos);
});

async function consultarMovimientos(e) {
    e.preventDefault();
    
    const cuenta = document.getElementById('cuenta').value.trim();
    const errorMessage = document.getElementById('error-message');
    const statsContainer = document.getElementById('stats-container');
    const movementsContainer = document.getElementById('movements-container');
    const noMovements = document.getElementById('no-movements');
    
    // Ocultar elementos anteriores
    errorMessage.style.display = 'none';
    statsContainer.style.display = 'none';
    movementsContainer.style.display = 'none';
    noMovements.style.display = 'none';

    if (!cuenta) {
        errorMessage.style.display = 'block';
        document.getElementById('error-text').textContent = 'Por favor ingrese el número de cuenta';
        return;
    }

    try {
        // Obtener movimientos
        const movementsResponse = await fetch('/movementsR', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ numcuenta: cuenta })
        });

        const movementsResult = await movementsResponse.json();

        // Obtener datos de la cuenta
        const accountResponse = await fetch('/accountDetails', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ numcuenta: cuenta })
        });

        const accountResult = await accountResponse.json();

        if (movementsResult.success && accountResult.success && accountResult.account) {
            const movements = movementsResult.movements || [];
            const account = accountResult.account;
            
            const saldoActual = account.decCuenSaldo || account.saldo || 0;
            let totalIngresos = 0;
            let totalEgresos = 0;

            // Calcular totales
            movements.forEach(mov => {
                if (!mov) return;

                // El servicio ya procesa los movimientos, así que usamos los campos ya procesados
                const importe = parseFloat(mov.importe || mov.importeMovimiento || 0);
                const codigo = mov.codigoTipoMovimiento || mov.tipo || '';
                const descripcion = (mov.tipoDescripcion || mov.tipo || mov.accion || '').toLowerCase().trim();
                const accion = (mov.accion || '').toLowerCase();

                // Si el importe es negativo, es un egreso; si es positivo, es un ingreso
                if (importe < 0 || COD_EGRESO.has(codigo) || descripcion === 'retiro' || descripcion === 'débito' || accion === 'débito') {
                    totalEgresos += Math.abs(importe);
                } else if (importe > 0 || COD_INGRESO.has(codigo) || descripcion === 'deposito' || descripcion === 'depósito' 
                         || descripcion === 'transferencia' || descripcion === 'apertura de cuenta' || descripcion === 'crédito' || accion === 'crédito') {
                    totalIngresos += Math.abs(importe);
                }
            });

            // Mostrar estadísticas
            document.getElementById('saldo-actual').textContent = `$${saldoActual.toFixed(2)}`;
            document.getElementById('total-ingresos').textContent = `$${totalIngresos.toFixed(2)}`;
            document.getElementById('total-egresos').textContent = `$${totalEgresos.toFixed(2)}`;
            document.getElementById('saldo-neto').textContent = `$${(totalIngresos - totalEgresos).toFixed(2)}`;
            statsContainer.style.display = 'block';

            // Mostrar movimientos
            if (movements.length > 0) {
                const tableBody = document.getElementById('movements-table-body');
                tableBody.innerHTML = '';

                movements.forEach(mov => {
                    const row = document.createElement('tr');
                    // El servicio ya procesa los movimientos, así que usamos los campos ya procesados
                    const importe = parseFloat(mov.importe || mov.importeMovimiento || 0);
                    const saldo = parseFloat(mov.saldo || 0);
                    const fecha = mov.fecha || mov.fechaMovimiento || mov.fechaMovimientoDt || 'N/A';
                    const tipo = mov.codigoTipoMovimiento || mov.tipo || 'N/A';
                    const descripcion = mov.tipoDescripcion || mov.tipo || mov.accion || 'N/A';
                    const numero = mov.numero || mov.numeroMovimiento || 0;
                    const cuentaRef = mov.cuentaReferencia || '-';
                    
                    // Formatear fecha si es posible
                    let fechaFormateada = fecha;
                    try {
                        if (fecha !== 'N/A' && fecha) {
                            // Si es un objeto Date o string de fecha
                            const fechaObj = fecha instanceof Date ? fecha : new Date(fecha);
                            if (!isNaN(fechaObj.getTime())) {
                                fechaFormateada = fechaObj.toLocaleDateString('es-ES', { 
                                    day: '2-digit', 
                                    month: '2-digit', 
                                    year: 'numeric' 
                                }) + ' ' + fechaObj.toLocaleTimeString('es-ES', { 
                                    hour: '2-digit', 
                                    minute: '2-digit' 
                                });
                            }
                        }
                    } catch (e) {
                        // Mantener fecha original si hay error
                        console.log('Error al formatear fecha:', e);
                    }

                    const importeColor = importe >= 0 ? '#11998e' : '#f5576c';
                    const importeFormateado = importe >= 0 ? `+$${Math.abs(importe).toFixed(2)}` : `-$${Math.abs(importe).toFixed(2)}`;
                    
                    row.innerHTML = `
                        <td>${numero}</td>
                        <td>${fechaFormateada}</td>
                        <td>${tipo}</td>
                        <td>${descripcion}</td>
                        <td style="font-weight: 600; color: ${importeColor};">${importeFormateado}</td>
                        <td>$${saldo.toFixed(2)}</td>
                        <td>${cuentaRef}</td>
                    `;
                    tableBody.appendChild(row);
                });

                movementsContainer.style.display = 'block';
            } else {
                noMovements.style.display = 'block';
            }
        } else {
            errorMessage.style.display = 'block';
            document.getElementById('error-text').textContent = movementsResult.message || accountResult.message || 'Error al consultar movimientos';
        }
    } catch (error) {
        console.error('Error al consultar movimientos:', error);
        errorMessage.style.display = 'block';
        document.getElementById('error-text').textContent = 'Error al conectar con el servidor: ' + error.message;
    }
}
