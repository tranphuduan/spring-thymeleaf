function showPopup(code, message) {
    const overlay = document.getElementById('overlay'); // Lớp nền overlay
    const popup = document.getElementById('alertPopup'); // Popup
    const popupMessage = popup.querySelector('.popup-message'); // Nội dung popup

    // Cập nhật nội dung popup
    popupMessage.textContent = message;

    // Xóa các class cũ và thêm class mới (success hoặc error)
    popup.classList.remove('success', 'error');
    if (code === "00") {
        // Hiển thị thành công nếu code là "00"
        popup.classList.add('success');
        overlay.style.display = 'none'; // Không hiện overlay cho success
        popup.style.display = 'block'; // Hiển thị popup

        // Tự động ẩn popup sau 1 giây
        setTimeout(() => {
            popup.style.display = 'none';
        }, 1000);
    } else {
        // Hiển thị lỗi nếu code khác "00"
        popup.classList.add('error');
        overlay.style.display = 'block'; // Hiện overlay cho error
        popup.style.display = 'block'; // Hiển thị popup
    }
}

function closePopup() {
    const overlay = document.getElementById('overlay');
    const popup = document.getElementById('alertPopup');

    // Ẩn popup và overlay
    overlay.style.display = 'none';
    popup.style.display = 'none';
}