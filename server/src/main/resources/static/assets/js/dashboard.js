// Global variables
var gaugeCurrent;
var gaugeErrors;
var gaugeStates;

function init(options, i18n) {

    // Options for the gauges
    var solidgaugeOptions = {
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

    // Current order
    gaugeCurrent = Highcharts.chart('gauge-current', Highcharts.merge(solidgaugeOptions, {
        yAxis: {
            min: 0,
            max: 100,
            title: {
                text: i18n.get('dashboard.gauge-current')
            },
            stops: [
                [0.0, '#DF5353'], // red
                [0.7, '#DDDF0D'], // yellow
                [0.9, '#55BF3B']  // green
            ]
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'current',
            data: [0],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                '<span style="font-size:12px;color:silver">' + i18n.get('dashboard.gauge-current-processed') + '</span></div>'
            },
            tooltip: {
                enabled: false
            }
        }]
    }));

    // Errors in processing
    gaugeErrors = Highcharts.chart('gauge-errors', Highcharts.merge(solidgaugeOptions, {
        yAxis: {
            min: 0,
            max: 100,
            title: {
                text: i18n.get('dashboard.gauge-errors')
            },
            stops: [
                [0.0, '#DDDF0D'],  // yellow
                [0.1, '#DF5353']   // red
            ]
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'errors',
            data: [0],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                '<span style="font-size:12px;color:silver">' + i18n.get('dashboard.gauge-errors-occurred') + '</span></div>'
            },
            tooltip: {
                enabled: false
            }
        }]
    }));

    // States for the orders
    gaugeStates = Highcharts.chart('gauge-states', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: 0,
            plotShadow: false
        },
        title: {
            text: i18n.get('dashboard.gauge-states'),
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
            name: i18n.get('dashboard.gauge-states-name'),
            innerSize: '40%',
            data: [
                [i18n.get('dashboard.gauge-state-open'), 1.0],
                [i18n.get('dashboard.gauge-state-canceled'), 2.0],
                [i18n.get('dashboard.gauge-state-completed'), 3.0]
            ]
        }]
    });

    // Update the gauges
    setInterval(function () {
        // Update Current
        if (gaugeCurrent) {
            $.get(options.urlCurrent, function (data) {
                var axis = gaugeCurrent.yAxis[0];
                axis.setExtremes(data.min, data.max);

                var point = gaugeCurrent.series[0].points[0];
                point.update(data.value);
            });
        }

        // Update Errors
        if (gaugeErrors) {
            $.get(options.urlErrors, function (data) {
                var axis = gaugeErrors.yAxis[0];
                axis.setExtremes(data.min, data.max);

                var point = gaugeErrors.series[0].points[0];
                point.update(data.value);
            });
        }

        // Update States
        if (gaugeStates) {
            $.get(options.urlStates, function (data) {
                var open = gaugeStates.series[0].points[0];
                open.update(data.open);
                var canceled = gaugeStates.series[0].points[1];
                canceled.update(data.canceled);
                var completed = gaugeStates.series[0].points[2];
                completed.update(data.completed);
            });
        }

    }, 5000);
}