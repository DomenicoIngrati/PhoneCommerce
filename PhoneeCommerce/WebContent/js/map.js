
$( document ).ready(function() {

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
            zoom: 10,
            center: { lng: 13.4, lat: 52.51 }
        });


    var mapEvents = new H.mapevents.MapEvents(map);


    // Add event listeners:
    map.addEventListener('tap', function(evt) {
        // Log 'tap' and 'mouse' events:
        console.log(evt.type, evt.currentPointer.type);
    });

    // Instantiate the default behavior, providing the mapEvents object:
    var behavior = new H.mapevents.Behavior(mapEvents);
    // Create the default UI:
    var ui = H.ui.UI.createDefault(map, defaultLayers , 'it-IT');

    /*var mapSettings = ui.getControl('mapsettings');
    var zoom = ui.getControl('zoom');
    var scalebar = ui.getControl('scalebar');
    var panorama = ui.getControl('panorama');

    panorama.setAlignment('top-left');
    mapSettings.setAlignment('top-left');
    zoom.setAlignment('top-left');
    scalebar.setAlignment('top-left');*/

    var svgMarkup = '<svg width="24" height="24" ' +
        'xmlns="http://www.w3.org/2000/svg">' +
        '<rect stroke="white" fill="#1b468d" x="1" y="1" width="22" ' +
        'height="22" /><text x="12" y="18" font-size="12pt" ' +
        'font-family="Arial" font-weight="bold" text-anchor="middle" ' +
        'fill="white">H</text></svg>';

    // Create an icon, an object holding the latitude and longitude, and a marker:
    var icon = new H.map.Icon('https://img.icons8.com/ultraviolet/40/000000/marker.png'),
        coords = {lat: 52.53075, lng: 13.3851},
        marker = new H.map.Marker(coords, {icon: icon});

    // Add the marker to the map:
    map.addObject(marker);// Create the parameters for the geocoding request:
    var geocodingParams = {
        searchText: '200 S Mathilda Ave, Sunnyvale, CA'
    };

    $.ajax({
        url: "positionmap?action=RETRIEVEALL",
        type: "POST",
        dataType: "JSON",
        success: function(result){

        },
        error: function(){
            alert("Errore di richiesta al server! Riprovare.");
        }
    });

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