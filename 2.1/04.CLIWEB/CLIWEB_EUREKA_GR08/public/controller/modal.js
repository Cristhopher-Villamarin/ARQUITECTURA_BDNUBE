/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

export function showModal(title, content, callback = null) {
    const modal = document.getElementById('custom-modal');
    const modalTitle = document.getElementById('modal-title');
    const modalContent = document.getElementById('modal-content');
    const modalOkButton = document.getElementById('modal-ok-button');

    modalTitle.textContent = title;
    modalContent.textContent = content;
    modal.classList.remove('hidden');

    const newButton = modalOkButton.cloneNode(true);
    modalOkButton.parentNode.replaceChild(newButton, modalOkButton);

    newButton.addEventListener('click', () => {
        closeModal();
        if (callback && typeof callback === 'function') {
            callback();
        }
    });
}

export function closeModal() {
    const modal = document.getElementById('custom-modal');
    modal.classList.add('hidden');
}

window.showModal = showModal;
window.closeModal = closeModal;

