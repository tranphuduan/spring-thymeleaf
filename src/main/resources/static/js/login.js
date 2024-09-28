document.getElementById("login-form").addEventListener("submit", function(e) {
    e.preventDefault(); // Ngăn không reload lại trang

    const username = document.querySelector('input[name="username"]').value;
    const password = document.querySelector('input[name="password"]').value;

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === "01") {
                // Xử lý logic cho code 01
                document.getElementById('confirm-group').style.display = 'block';
                document.getElementById('sendButton').style.display = 'none';
                document.getElementById('confirmButton').style.display = 'inline-block';
            }else {
                showPopup(data.code, data.mess);
            }
        })
        .catch(error => console.error('Error:', error));
});

document.getElementById("confirmYesBtn").addEventListener("click", function(){
    const confirmCode = document.querySelector('input[name="confirmCode"]').value;

    if (confirmCode.trim() === "") {
        alert("Please enter the confirmation code.");
        return;
    }

    const username = document.querySelector('input[name="username"]').value;
    const password = document.querySelector('input[name="password"]').value;

    fetch('/auth/confirm', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: username,
            password: password,
            confirmCode: confirmCode
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === "00") {
                window.location.href = "/home";
            } else {
                alert(data.mess);
            }
        })
        .catch(error => console.error('Error:', error));

    // Đóng popup sau khi gửi mã confirm
    document.getElementById("confirmationPopup").classList.remove("is-visible");
});