
function operation_alert(result, callback) {
	$("html, body").animate({ scrollTop: 0 }, "slow");
    $("#login-auto-close-alert").focus();
    
    var str = result.result;
    if ((str.search("SUCCESS")) != -1) {
        $("#login-alert-text").html("<strong>" + result.message + "</strong>");
        $("#login-auto-close-alert").removeClass("hidden").addClass("alert-success fade in");
        $("#login-auto-close-alert").fadeTo(1500, 750).slideUp(600, function() {
            $(this).removeClass("alert-success fade in");
//            callback();
        });
    } else {
        $("#login-alert-text").html("<strong>" + result.reason + "</strong>");
        $("#login-auto-close-alert").removeClass("hidden").addClass("alert-danger fade in");
        $("#login-auto-close-alert").fadeTo(1500, 750).slideUp(600, function() {
            $(this).removeClass("alert-danger fade in");
//            callback();
        });
    }  
}

function addProductOnCart(productName,q){
	var prodotto = {
			name : productName,
			quantity: q
		};
	
	
	$.ajax({
		url: "cart?action=addProductOnCart",
		type: "POST",
		datatype: "json",
		data: JSON.stringify(prodotto),
        success: function(result){
        	var data = JSON.parse(result);
        	var size = data.size;
        	console.log(data.size);
        	$("#countCart").text(size);
        	

        	
        },
        error: function(){	
        	alert("Errore di richiesta al server! Riprovare.");
        } 
	});
};



$(document).ready(function () {

	
	
	$(".delete-Product").on('click', function() {
	
	
	var productName = $(this).data('id');
	
	console.log(productName);
	
	$.ajax({
		url: "cart?action=deleteProduct",
		type: "POST",
		datatype: "json",
		data: JSON.stringify(productName),
        success: function(result){   	
        	window.location.reload();
        },
        error: function(){	
        	alert("Errore di richiesta al server! Riprovare.");
        } 
	});
  });
});


//$(function () {
//    $(".custom-close").on('click', function() {
//        $('#cartmodal').modal('hide');
//    });
//});


