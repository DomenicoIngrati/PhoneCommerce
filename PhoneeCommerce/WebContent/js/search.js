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




$( document ).ready(function() {

	$("#search").submit(function(e) {
	    e.preventDefault();
	    var nameValue = document.getElementById("uniqueID").value;
	    
	    $.ajax({
	        url: "home?action=search",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(nameValue),
	        success: function(data){

	        	window.location.href="home?action=searchCompleted";
	        },
	        error: function(){	
	        	window.location.href="home?action=searchCompleted";
	        }
	    });
	});
});