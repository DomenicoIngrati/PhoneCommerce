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
	$('#modify-banner-modal').on('show.bs.modal', function (e) {

		var button = $(e.relatedTarget);

		var name = button.data('name');

		var modal = $(this);
		modal.find('#modify-banner-title').val(button.data('title'));
		modal.find('#modify-banner-description').val(button.data('description'));
		modal.find('#modify-banner-btn-add-banner').attr('data-idbanner',button.data('idbanner'));
	});
});

$( document ).ready(function() {
	$('#image-banner-modal').on('show.bs.modal', function (e) {

		var button = $(e.relatedTarget);

		var modal = $(this);

		modal.find('#image-banner-btn-add-banner').attr('data-idbanner',button.data('idbanner'));
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

	$("#add-banner-btn-add-banner").on('click',function(e) {
		e.preventDefault();
		var option = {
			url: "carousel?action=create",
			type: "POST",

			success: function(data){
				$("#add-banner-modal").modal('hide');
				var result = JSON.parse(data);
				operation_alert(result, function(){
					window.location.href = "home?action=modifycarousel";
				});
			},
			error: function(){
				alert("Errore di richiesta al server! Riprovare.");
			}
		}
		$("#add-banner-form").ajaxSubmit(option);
	});
});

$( document ).ready(function() {

	$("#modify-banner-btn-add-banner").on('click',function(e) {
		e.preventDefault();
		var form = new FormData();
		form.append("title",$("#modify-banner-title").val());
		form.append("description",$("#modify-banner-description").val());
		form.append("id",$("#modify-banner-btn-add-banner").data('idbanner'));
		$.ajax({

			url: "carousel?action=updatetexts",
			type: "POST",
			data: form,
			processData: false,
			contentType: false,
			success: function(data){
				$("#modify-banner-modal").modal('hide');
				var result = JSON.parse(data);
				operation_alert(result, function(){
					window.location.href = "home?action=modifycarousel";
				});
			},
			error: function(){
				alert("Errore di richiesta al server! Riprovare.");
			}

		});
	});
});

$( document ).ready(function() {

	$("#image-banner-btn-add-banner").on('click',function(e) {
		e.preventDefault();
		var form = new FormData();
		form.append("file",$("#image-banner-img")[0].files[0]);
		form.append("id",$("#image-banner-btn-add-banner").data('idbanner'));

		$.ajax({

			url: "carousel?action=updateimage",
			type: "POST",
			enctype: "multipart/form-data",
			data: form,
			processData: false,
			contentType: false,
			success: function(data){
				$("#image-banner-modal").modal('hide');
				var result = JSON.parse(data);
				operation_alert(result, function(){
					window.location.href = "home?action=modifycarousel";
				});
			},
			error: function(){
				alert("Errore di richiesta al server! Riprovare.");
			}

		});
	});
});

$( document ).ready(function() {

	$(".btn-delete-banner").on('click',function(e){

		var id = $(this).data('idbanner');
		var form = new FormData();
		form.append("id",id);
		$.ajax({

			url: "carousel?action=deletebanner",
			type: "POST",
			data: form,
			processData: false,
			contentType: false,
			success: function(data){
				$("#banner-" + id).fadeOut();
			},
			error: function(){
				alert("Errore di richiesta al server! Riprovare.");
			}

		});
	})

});