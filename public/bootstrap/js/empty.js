
$(document).ready(function () {
    $("#make-payment").click(function() {
        localStorage.removeItem("mybasket");
        window.location.href = "/cart"
    });
});
