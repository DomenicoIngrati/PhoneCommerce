(function($) {

    $.fn.serializeFormJSON = function() {
        var disabled = this.find(':input:disabled').removeAttr('disabled');
        var o = {};
        var a = this.serializeArray();
        disabled.attr('disabled', 'disabled');
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

function operation_alert(result, callback) {
	$("html, body").animate({ scrollTop: 0 }, "slow");
    $("#login-auto-close-alert").focus();
    
    var str = result.result;
    if ((str.search("SUCCESS")) != -1) {
        $("#login-alert-text").html("<strong>" + result.message + "</strong>");
        $("#login-auto-close-alert").removeClass("hidden").addClass("alert-success fade in");
        $("#login-auto-close-alert").fadeTo(1500, 750).slideUp(600, function() {
            $(this).removeClass("alert-success fade in");
            callback();
        });
    } else {
        $("#login-alert-text").html("<strong>" + result.reason + "</strong>");
        $("#login-auto-close-alert").removeClass("hidden").addClass("alert-danger fade in");
        $("#login-auto-close-alert").fadeTo(1500, 750).slideUp(600, function() {
            $(this).removeClass("alert-danger fade in");
            callback();
        });
    }  
}

$( document ).ready(function() {
	
	$('#modifyModal').on('show.bs.modal', function (event) {
		
		var button = $(event.relatedTarget); // Button that triggered the modal
		var name = button.data('name'); // Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		var modal = $(this);
		modal.find('#name').val(button.data('name'));
		modal.find('#category').val(button.data('category'));
		modal.find('#price').val(button.data('price'));
		modal.find('#description').val(button.data('description'));
		modal.find('#label').html(button.data('image'));
		modal.find('#submitModify').attr('data-idproduct',button.data('id'));
		modal.find('#submitModify').attr('data-oldnamecategory',button.data('category'));
		
	});
	
});

$( document ).ready(function() {
	
	$(".delete").click(function(){
		var id = $(this).data('id');

		$.ajax({
			url: "product?action=DELETE",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(id),
	        success: function(result){
	        	 var str = result.result;
	        	    if ((str.search("SUCCESS")) != -1) {
	        	    	$("#"+id).slideUp();
	        	    	var deletedCategory = result.category;
	        	    	console.log(deletedCategory);
	        	    	if(deletedCategory != null){
	        	    		$("#"+deletedCategory).slideUp();
	        	    	}
	        	    }
	        	    else{
	        	    	alert("Errore: non e' stato possibile eliminare il prodotto!")
	        	    }
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
		});		
	});
});

$( document ).ready(function() {
	
	$("#modifyProduct").submit(function(e) {
	    e.preventDefault();
	    
	    var frm = $(this).serializeFormJSON();
	    
	    var filename = $('input[type=file]').val().replace(/C:\\fakepath\\/i, '')
	    frm.image = filename;
	    
	    var idproduct = $('#submitModify').data('idproduct');
	    var oldNameCategory = $('#submitModify').data('oldnamecategory');
	    
	    frm.id = idproduct;
	    frm.oldnamecategory = oldNameCategory;
	    $('#modifyModal').modal('hide');
//	    console.log(frm);

	    $.ajax({
	        url: "product?action=UPDATE",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(frm),
	        success: function(result){
	        	
	        	var category = result.oldnamecategory;
    	    	if(category != null){
    	    		$("#"+category).slideUp();
    	    	}
	        	operation_alert(result, function(){
	        	    	window.location.href = "home?action=modifydelete";
	        	    	//lo protrei fare dinamico modificando i campi su javascript
	        	});
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
	    });
	});
});

$( document ).ready(function() {
	
	// mette il nome del file nel label quando selezioniamo il file
	$(".custom-file-input").on("change", function() {
	  var fileName = $(this).val().split("\\").pop();
	  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
	});
	
});

$( document ).ready(function() {
	
	$("#add-product").submit(function(e) {
	    e.preventDefault();
	    
	    var frm = $(this).serializeFormJSON();
	    
	    var filename = $('input[type=file]').val().replace(/C:\\fakepath\\/i, '')
	    frm.image = filename;
	    console.log(frm);
	    
	    $.ajax({
	        url: "product?action=CREATE",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(frm),
	        success: function(result){
	        	operation_alert(result, function(){
	        	    	window.location.href = "home?action=aggiungiProdotto";
	        	});
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
	    });
	});
});
