
var basket;
var sum = 0;
$(document).ready(function () {
    
    basket = JSON.parse(localStorage.getItem("mybasket"));
    var list = $('#tr');
    var main = $('#main');
    var gin = $('#gin');
    if (!basket) {
        main.append('<br><p>Brak produktów w koszyku</p>');
        $('#pay').hide();
        gin.hide();

        return;
    }
    else {
        list.show();
    }


    for (var i = 0; i < basket.length; i++) {
        console.log(JSON.stringify(basket[i]));
        sum = sum + parseInt(basket[i].price, 10);
        list.before(' <tr id="row' + i + '">'+
            '<td>'+
            '<div id="id" hidden>+basket[i].id+</div>'+
            '<h4><a href="#">'+basket[i].product+'</a></h4>'+ // product name
            ''+
            '</td>'+
            '<td class="text-center">'+ basket[i].price +'</td>'+
            '<td class="text-center">'+ basket[i].kat +'</td>'+
        '<td >'+
        '<button type="button" class="btn btn-default" id="remove_button'+i+'">'+
        '<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>'+
        ' </button></td>'+
        ' </tr>');

    }


    $('#total').text( sum );
    for (var i = 0; i < basket.length; i++) {
        var id = "#remove_button" + i;
        $(id).click({param1: i, param2: basket[i].product.toString()}, function(event) {
            var index = event.data.param1;
            var name = event.data.param2;
            var el;
            for (var j=0; j<basket.length; j++) {
                if (basket[j].product == name) {
                    el = basket.splice(j, 1)[0];
                    break;
                }
            }
            console.log(el)
            if (el) {
                sum -= (el.price);
                console.log(sum)
                $('#total').text( sum );
                $('#row' + index).remove();
            }
            if (basket.length === 0) {
                gin.hide();
                main.append('<br><p>Brak dodanych produktów.</p>');

                $('#pay').hide();
                localStorage.removeItem("mybasket");
                return;
            } else {
                localStorage.setItem("mybasket", JSON.stringify(basket));
            }
        });
    }
});