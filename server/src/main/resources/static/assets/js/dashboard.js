// Global variables
var gaugeStations;
var gaugeIncoming;
var gaugeLocations;

function init(options, i18n) {

    // Options for the gauges
    var gaugeOptions = {
        chart: {
            type: 'solidgauge'
        },
        title: null,
        pane: {
            center: ['50%', '60%'],
            startAngle: -90,
            endAngle: 90,
            background: {
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
                innerRadius: '60%',
                outerRadius: '100%',
                shape: 'arc'
            }
        },
        tooltip: {
            enabled: false
        },
        // the value axis
        yAxis: {
            // lineWidth: 0,
            // minorTickInterval: null,
            // tickAmount: 2,
            title: {
                y: -90
            },
            labels: {
                y: 16
            }
        },
        plotOptions: {
            solidgauge: {
                dataLabels: {
                    y: 5,
                    borderWidth: 0,
                    useHTML: true
                }
            }
        }
    };

    // Stations on server
    gaugeStations = Highcharts.chart('gauge-stations', Highcharts.merge(gaugeOptions, {
        yAxis: {
            min: 0,
            max: 100,
            title: null
        },
        chart: {
            backgroundColor: "rgb(46, 51, 56)"
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'stations',
            data: [0],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                '<span style="font-size:12px;color:silver">' + i18n.get('dashboard.gauge-stations') + '</span></div>'
            },
            tooltip: {
                enabled: false
            }
        }]
    }));

    // Incoming messages on server
    gaugeIncoming = Highcharts.chart('gauge-incoming', Highcharts.merge(gaugeOptions, {
        yAxis: {
            min: 0,
            max: 100,
            title: null,
            stops: [
                [0.0, '#55BF3B'], // green
                [0.8, '#DDDF0D'], // yellow
                [0.9, '#DF5353']  // red,
            ]
        },
        chart: {
            backgroundColor: "rgb(46, 51, 56)"
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'incoming',
            data: [0],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                '<span style="font-size:12px;color:silver">' + i18n.get('dashboard.gauge-incoming') + '</span></div>'
            },
            tooltip: {
                enabled: false
            }
        }]
    }));

    // States for the orders
    gaugeLocations = Highcharts.chart('gauge-locations', {
        chart: {
            backgroundColor: "rgb(46, 51, 56)",
            plotBackgroundColor: null,
            plotBorderWidth: 0,
            plotShadow: false
        },
        title: {
            text: i18n.get('dashboard.gauge-locations'),
            align: 'center',
            verticalAlign: 'top',
            y: 30
        },
        credits: {
            enabled: false
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.y}</b>'
        },
        plotOptions: {
            pie: {
                dataLabels: {
                    enabled: true,
                    distance: -50,
                    style: {
                        fontWeight: 'bold',
                        color: 'white'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: i18n.get('dashboard.gauge-locations-names'),
            innerSize: '40%',
            data: [
                ['unknown', 0.0]
            ]
        }]
    });

    // Updates
    update(options);
    setInterval(function () {
        update(options);
    }, 5000);

}

/**
 * Update the diagrams.
 */
function update(options) {

    // Update Stations
    if (gaugeStations) {
        $.get(options.urlStations, function (data) {
            var axis = gaugeStations.yAxis[0];
            axis.setExtremes(data.min, data.max);

            var point = gaugeStations.series[0].points[0];
            point.update(data.value);
        });
    }

    // Update Incoming
    if (gaugeIncoming) {
        $.get(options.urlIncoming, function (data) {
            var axis = gaugeIncoming.yAxis[0];
            axis.setExtremes(data.min, data.max);

            var point = gaugeIncoming.series[0].points[0];
            point.update(data.value);
        });
    }

    // Update Locations
    if (gaugeLocations) {
        $.get(options.urlLocations, function (data) {
            gaugeLocations.series[0].setData(data.value);
        });
    }

}
