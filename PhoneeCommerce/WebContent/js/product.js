/**
 * 
 */

$(document).ready(function(){
    $('#qty_input').prop('disabled', true);
    $('#plus-btn').click(function(){
    	$('#qty_input').val(parseInt($('#qty_input').val()) + 1 );
    	    });
        $('#minus-btn').click(function(){
    	$('#qty_input').val(parseInt($('#qty_input').val()) - 1 );
    	if ($('#qty_input').val() == 0) {
			$('#qty_input').val(1);
		}

    	    });
 });



$( document ).ready(function() {
	


			
			
			$("#review-form").submit(function(e){
				
				e.preventDefault();
			    var frm = null;
			    frm = $(this).serializeFormJSON();
			    var button = $("#btn-add-review");
			    frm.id =  button.data('idproduct');
			    frm.user= button.data('iduser');
			    frm.feedback=$( "#feedback" ).val();
			    
			    
			    
			   
			    console.log(frm);
			    
			    $.ajax({
			        url: "product?action=newReview",
			        type: "POST",
			        dataType: "JSON",
			        data: JSON.stringify(frm),
			        success: function(data){
			        	$("#add-review-modal").modal('hide');
			        	operation_alert(data, function(){
			        		
			        	});
			        	window.location.reload();
			        },
			        error: function(){	
			        	$("#add-review-modal").modal('hide');
			        	alert("Errore di richiesta al server! Riprovare.");
			        }    	
			    });
			    
				
			});
});



$( document ).ready(function() {
	$("#close-modal").on('click',function(){
		var modal = $("#add-review-modal");
			modal.find('#title').val("");
			modal.find('#text').val("");
	});
});
