/**
 * 
 */




function addProductOnCart(productName,q){
	var prodotto = {
			name : productName,
			quantity: q
		};
	
	console.log(prodotto.name);
	console.log(prodotto.quantity);
	alert('Oggetto aggiunto');
	$.ajax({
		url: "cart?action=addProductOnCart",
		type: "POST",
		datatype: "json",
		data: JSON.stringify(prodotto),
        success: function(data){
//        	operation_alert(data, function(){
//        		window.location.href = "?action=index";
//        	});
        	
    		window.location.reload();
        },
        error: function(){	
        	alert("Errore di richiesta al server! Riprovare.");
        } 
	});
};

$(function () {
    $(".custom-close").on('click', function() {
        $('#myModal').modal('hide');
    });
});


