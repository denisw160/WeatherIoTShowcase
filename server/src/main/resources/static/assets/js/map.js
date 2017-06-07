// Global variables
var map;
var markers = [];

var markerCluster;

var url;


/**
 * Deletes all markers in the array by removing references to them.
 */
function deleteMarkers() {

    // for (var i = 0; i < markers.length; i++) {
    //     markers[i].setMap(null);
    // }
    // markers = [];

    while (markers.length) {
        var pop = markers.pop();
        pop.setMap(null);
    }

    if (markerCluster) {
        markerCluster.clearMarkers();
    }

    console.debug("Markers cleared");
}

/**
 * Updates the map.
 */
function updateMarkers() {

    deleteMarkers();

    if (url) {
        $.get(url, function (data) {
            for (var i = 0; i < data.length; i++) {
                var t = data[i];
                var pos = {lat: t.latitude, lng: t.longitude};
                var marker = new google.maps.Marker({
                    position: pos,
                    map: map,
                    title: t.temperature + ' °C',
                    label: t.temperature + ' °C'
                });

                markers.push(marker);
                markerCluster.addMarker(marker, false);
            }

            markerCluster.redraw();
        });
    }

    console.debug("Google Map updated");
}

/**
 * Callback-Function for Google Maps API.
 */
function initMap() {

    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 52.376189, lng: 9.740565},
        zoom: 4
    });

    markerCluster = new MarkerClusterer(map, [],
        {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});

    google.maps.event.addListener(map, 'idle', updateMarkers);
    console.debug("Google Map ready");
}

/**
 * Initialize the map.
 *
 * @param options Options for the map
 * @param i18n Translation
 */
function init(options, i18n) {

    url = options.urlTemperature;

}

