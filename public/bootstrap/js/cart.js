
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
            '<td class="col-sm-10 col-md-6">'+
            '<div class="media">'+
            '<a class="thumbnail pull-left" href="#"> <img class="media-object" src="/assets/images/avatar.jpg"  > </a>'+
            '<div class="media-body text-center" style="vertical-align:bottom;">'+
                '<div id="id" hidden>+basket[i].id+</div>'+
            '<h4 class="media-heading"><a href="#">'+basket[i].product+'</a></h4>'+ // product name

            '</div>'+
            ' </div></td>'+
            '<td class="col-sm-1 col-md-1 text-center"><strong>'+ basket[i].price +'</strong></td>'+
            '<td class="col-sm-1 col-md-1 text-center"><strong>'+ basket[i].kat +'</strong></td>'+
        '<td class="col-sm-1 col-md-1">'+
        '<button type="button" class="btn btn-danger" id="remove_button'+i+'">'+
        '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>'+
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