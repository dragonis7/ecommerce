/**
 * Created by beduin90 on 08.06.2016.
 */


var price,name, img;
jQuery(document).ready(function () {

    $("#clean-basket").click(function() {
        localStorage.removeItem("mybasket");
        return;
        
    });


    $("#clean").click(function() {
        localStorage.removeItem("mybasket");
        return;

    });



    $(".button-add-to-cart").click(function() {

        var basket = JSON.parse(localStorage.getItem("mybasket"));
        var $tr = $(this).closest('tr');
        var $cols = $tr.find('td');
        var id = $(this).attr("data-id");
        var name = $cols.eq(1).text()
        var price = $cols.eq(3).text()

        var kat = $cols.eq(4).text()
        if (!basket) {
            basket = new Array();
        }
        basket.push({
            id: id,
            product: name,
            price: price,
            kat:kat
        });





        $("#passwordsNoMatchRegister").slideDown('slow').delay(1500).slideUp('slow');



        localStorage.setItem("mybasket", JSON.stringify(basket));

    });
});