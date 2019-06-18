
$( document ).ready(function() {
	
    // Create an icon, an object holding the latitude and longitude, and a marker:
    var icon = new H.map.Icon('https://img.icons8.com/doodle/48/000000/marker.png');

    
	
    $.ajax({
        url: "positionmap?action=RETRIEVEALL",
        type: "POST",
        dataType: "JSON",
        success: function(result){
        	
        	var array=result;
        	
        	
        	var arrayLength = array.length;
        	for (var i = 0; i < arrayLength; i++) {
        
        	    
                var coords = {lat: array[i].latitude, lng: array[i].longitude},
                marker = new H.map.Marker(coords, {icon: icon});
                
                map.addObject(marker);
        	}
        },
        error: function(){
            alert("Errore di richiesta al server! Riprovare.");
        }
    });

    // Initialize the platform object:
    var platform = new H.service.Platform({
        'app_id': '6jhY5ek8sBnK5WuWCz4y',
        'app_code': 'bQBf9567l28hLiuw8cZNpg'
    });

    // Get the default map types from the Platform object:
    var defaultLayers = platform.createDefaultLayers();

    // Instantiate the map:
    var map = new H.Map(
        document.getElementById('mapContainer'),
        defaultLayers.normal.map,
        {
            zoom: 5,
            center: { lng:12.216 , lat:41.883 }
        });


    var mapEvents = new H.mapevents.MapEvents(map);


    // Add event listeners:
    map.addEventListener('tap', function(evt) {
        // Log 'tap' and 'mouse' events:
    });

    // Instantiate the default behavior, providing the mapEvents object:
    var behavior = new H.mapevents.Behavior(mapEvents);
    // Create the default UI:
    var ui = H.ui.UI.createDefault(map, defaultLayers , 'it-IT');



    var svgMarkup = '<svg width="24" height="24" ' +
        'xmlns="http://www.w3.org/2000/svg">' +
        '<rect stroke="white" fill="#1b468d" x="1" y="1" width="22" ' +
        'height="22" /><text x="12" y="18" font-size="12pt" ' +
        'font-family="Arial" font-weight="bold" text-anchor="middle" ' +
        'fill="white">H</text></svg>';



    // Add the marker to the map:

    
    


    // Define a callback function to process the geocoding response:
    var onResult = function(result) {
    	
        var locations = result.Response.View[0].Result,
            position,
            marker;
        // Add a marker for each location found
        for (i = 0;  i < locations.length; i++) {
            position = {
                lat: locations[i].Location.DisplayPosition.Latitude,
                lng: locations[i].Location.DisplayPosition.Longitude
            };
            
            marker = new H.map.Marker(position);
            map.addObject(marker);
        }
    	
    	
    };

    // Get an instance of the geocoding service:
    var geocoder = platform.getGeocodingService();

    // Call the geocode method with the geocoding parameters,
    // the callback and an error callback function (called if a
    // communication error occurs):
    geocoder.geocode(geocodingParams, onResult, function(e) {
        alert(e);
    });

});