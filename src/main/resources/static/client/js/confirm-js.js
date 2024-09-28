// Hiển thị popup khi nhấn vào nút Confirmation
document.getElementById("confirmButton").addEventListener("click", function(){
    $('#confirmationModal').modal('show');
});

// Xử lý khi người dùng chọn Yes trong popup
document.getElementById("confirmButton").addEventListener("click", function(){
    document.getElementById("confirmationPopup").classList.add("is-visible");
});

document.getElementById("confirmNoBtn").addEventListener("click", function(){
    document.getElementById("confirmationPopup").classList.remove("is-visible");
});

document.getElementById("closePopup").addEventListener("click", function(){
    document.getElementById("confirmationPopup").classList.remove("is-visible");
});