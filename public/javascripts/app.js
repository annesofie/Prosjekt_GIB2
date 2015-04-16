/**
 * Created by annesofiestranderichsen on 06.03.15.
 */
var init = function() {

    /* metode for aa disable elementer i varelisten som allerede ligger i handlelisten*/
    disable = new Boolean();

    function highlight(a) {
        if(disable==false)a.className='highlight'
    }

    function normal(a) {
        a.className='normal'
    }


    $("td > a").on("click", function(event){
        if ($(this).is("[disabled]")) {
            event.preventDefault();
        }
    });

    $("td > a").attr("disabled", "disabled");

    $("td > a").removeAttr("disabled");


    var map = L.map('leaflet-kart', {
        maxZoom: 0.45,
        minZoom: 0.45,
        zoomControl: false,
        dragging: false,
        crs: L.CRS.Simple
    }).setView([0, 0], 1);

    map.setMaxBounds(new L.LatLngBounds([0,500], [500,0]));

    var imageUrl = 'http://i61.tinypic.com/30ucvfc.jpg';
    var imageBounds = [[500,0], [0,370]];

    L.imageOverlay(imageUrl, imageBounds).addTo(map);

    $('.søk-knapp').click(function() {
        var inngang = L.marker([60, 317]).bindPopup("Inngang").addTo(map);
        var vertex2 = L.marker([60, 260]).bindPopup("2").addTo(map);
        var vertex3 = L.marker([115, 260]).bindPopup("3").addTo(map);
        var vertex4 = L.marker([170, 260]).bindPopup("4").addTo(map);
        var vertex5 = L.marker([225, 260]).bindPopup("5").addTo(map);
        var vertex6 = L.marker([288, 260]).bindPopup("6").addTo(map);
        var vertex7 = L.marker([351, 260]).bindPopup("7").addTo(map);
        var vertex8 = L.marker([415, 260]).bindPopup("8").addTo(map);
        var vertex9 = L.marker([415, 204]).bindPopup("9").addTo(map);
        var vertex10 = L.marker([415, 147]).bindPopup("10").addTo(map);
        var vertex11 = L.marker([415, 90]).bindPopup("11").addTo(map);
        var vertex12 = L.marker([351, 90]).bindPopup("12").addTo(map);
        var vertex13 = L.marker([288, 90]).bindPopup("13").addTo(map);
        var vertex14 = L.marker([225, 90]).bindPopup("14").addTo(map);
        var vertex15 = L.marker([170, 90]).bindPopup("15").addTo(map);
        var vertex16 = L.marker([115, 90]).bindPopup("16").addTo(map);
        var vertex17 = L.marker([60, 147]).bindPopup("17").addTo(map);
        var vertex18 = L.marker([60, 204]).bindPopup("18").addTo(map);
        var vertex19 = L.marker([225, 147]).bindPopup("19").addTo(map);
        var vertex20 = L.marker([225, 204]).bindPopup("20").addTo(map);
    });

    /*// Creates a red marker with the coffee icon
    var redMarker = L.AwesomeMarkers.icon({
        icon: 'coffee',
        markerColor: 'red'
    });

    L.marker([100,100], {icon: redMarker}).addTo(map);*/

    var inngangIcon = L.icon({
        iconUrl: 'http://goo.gl/TKKxdJ',

        iconSize:     [50, 50], // size of the icon
        iconAnchor:   [25, 50], // point of the icon which will correspond to marker's location
        popupAnchor:  [0, -50] // point from which the popup should open relative to the iconAnchor
    });

    var kasseIcon = L.icon({
        iconUrl: 'http://goo.gl/eRJ0jt',

        iconSize:     [50, 50], // size of the icon
        iconAnchor:   [25, 50], // point of the icon which will correspond to marker's location
        popupAnchor:  [0, -50] // point from which the popup should open relative to the iconAnchor
    });


    if (window.location.pathname == '/shoppingPath') {
        var latlngs = Array();
        var varenr = 1;
        appRoutes.controllers.Application.getTargetVertices().ajax({
            success: function (data) {
                $(data).each(function (index, vertex) {
                    latlngs.push(L.latLng(vertex.xPos, vertex.yPos));
                    if (vertex.beskrivelse != null) {
                        L.marker([vertex.xPos, vertex.yPos]).bindPopup('<p align="center">' + varenr + ": " + vertex.beskrivelse + "</p>" + "Klikk for å se plassering").on('mouseover', function(e){
                            this.openPopup();
                        }).on('mouseout', function(e) {
                            this.closePopup();
                        }).on('click', function(e) {
                            window.location.replace("/closeUpVare");
                        }).addTo(map);
                            varenr++;
                    }
                });
                var polyline = L.polyline(latlngs, {color: 'red'}).addTo(map);
            }
        });
    } else if (window.location.pathname == '/closeUpVare') {
        map.setMaxBounds(new L.LatLngBounds([1000,0], [0,400]));
        var imageUrl = 'http://i57.tinypic.com/2yjqw5c.jpg';
        var imageBounds = [[300, 0], [0, 100]];
        L.imageOverlay(imageUrl, imageBounds).addTo(map);
    }

    var inngang = L.marker([60, 317],{icon: inngangIcon}).bindPopup("Inngang").addTo(map);
    var kasse = L.marker([60, 204],{icon: kasseIcon}).bindPopup("Kasse").addTo(map);


};

$(document).ready(init);
