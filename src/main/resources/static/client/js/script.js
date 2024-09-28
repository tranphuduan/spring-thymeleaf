$(document).ready(function() {
    // Lấy phần tử khung chat và nút thu gọn/mở rộng
    const chatBox = document.getElementById('chatBox');
    const toggleButton = document.getElementById('toggleButton');

    // Sự kiện click để thu gọn/mở rộng khung chat
    toggleButton.addEventListener('click', function () {
        if (chatBox.classList.contains('minimized')) {
            chatBox.classList.remove('minimized');
            toggleButton.textContent = '-'; // Hiển thị nút thu gọn
        } else {
            chatBox.classList.add('minimized');
            toggleButton.textContent = '+'; // Hiển thị nút mở rộng
        }
    });

});