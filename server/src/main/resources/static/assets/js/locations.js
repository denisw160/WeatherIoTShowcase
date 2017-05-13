// Global variables
var mapLocations;

function init(options, i18n) {

    var data = [
        ['de', 0]
    ];

    // Creating map
    mapLocations = Highcharts.mapChart('map-locations', {
        title: {
            text: null
        },
        chart: {
            backgroundColor: 'rgb(39, 43, 48)',
            map: 'custom/world-highres2'
        },
        credits: {
            enabled: false
        },
        mapNavigation: {
            enabled: true,
            buttonOptions: {
                verticalAlign: 'bottom'
            }
        },
        colorAxis: {
            min: 0
        },
        series: [{
            data: data,
            name: i18n.get('locations.map-names'),
            states: {
                hover: {
                    color: '#BADA55'
                }
            },
            dataLabels: {
                enabled: true,
                format: '{point.name}'
            }
        }]
    });

    // Updates
    update(options);
    setInterval(function () {
        update(options);
    }, 5000);

}

/**
 * Update the map.
 */
function update(options) {

    if (mapLocations) {
        $.get(options.urlLocationMap, function (data) {
            mapLocations.series[0].setData(data.value);
        });
    }

}
