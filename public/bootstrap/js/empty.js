/**
 * Created by beduin90 on 08.06.2016.
 */

$(document).ready(function () {
    $("#make-payment").click(function() {
        localStorage.removeItem("mybasket");
        window.location.href = "/cart"
    });
});
