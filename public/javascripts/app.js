/**
 * Created by annesofiestranderichsen on 06.03.15.
 */


var init = function() {
    /*window.alert(closeUpX);*/
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

    var inngangIcon = L.icon({
        iconUrl: 'http://goo.gl/eib74S',

        iconSize:     [73, 73], // size of the icon
        iconAnchor:   [20, 52], // point of the icon which will correspond to marker's location
        popupAnchor:  [0, -50] // point from which the popup should open relative to the iconAnchor
    });

    var kasseIcon = L.icon({
        iconUrl: 'http://i62.tinypic.com/2zel3sg.png',

        iconSize:     [50, 50], // size of the icon
        iconAnchor:   [25, 50], // point of the icon which will correspond to marker's location
        popupAnchor:  [0, -50] // point from which the popup should open relative to the iconAnchor
    });

    var pilIcon = L.icon({
        iconUrl: 'http://i61.tinypic.com/2w7gtxt.png',

        iconSize:     [50, 30], // size of the icon
        iconAnchor:   [0, 25], // point of the icon which will correspond to marker's location
        popupAnchor:  [0, -50] // point from which the popup should open relative to the iconAnchor
    });

    var map;
    var map2;

    map = L.map('leaflet-kart', {
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

    var inngang = L.marker([60, 317],{icon: inngangIcon}).bindPopup("Inngang").addTo(map);
    var kasse = L.marker([60, 204],{icon: kasseIcon}).bindPopup("Kasse").addTo(map);

    if (window.location.pathname == '/shoppingPath') {
        map2 = L.map('leaflet-kart_close', {
            maxZoom: 0.45,
            minZoom: 0.45,
            zoomControl: false,
            dragging: false,
            crs: L.CRS.Simple
        }).setView([0, 0], 1);

        map2.setMaxBounds(new L.LatLngBounds([400,0],[0,650]));
        var imageUrl = 'http://i57.tinypic.com/2yjqw5c.jpg';
        var imageBounds = [[300, 0], [0, 480]];
        L.imageOverlay(imageUrl, imageBounds).addTo(map2);

        var latlngs = Array();
        var varenr = 1;
        var inngang = L.marker([-20,-20],{icon: pilIcon}).addTo(map);
        var markerLayer = L.layerGroup();
        appRoutes.controllers.Application.getTargetVertices().ajax({
            success: function (data) {
                $(data).each(function (index, vertex) {
                    latlngs.push(L.latLng(vertex.xPos, vertex.yPos));
                    if (vertex.beskrivelse != null) {
                        var marker = L.marker([vertex.x,vertex.y]).bindPopup(vertex.beskrivelse).on('click', function(e) {
                          map2.panTo(new L.LatLng(280, 0));
                        });
                        L.marker([vertex.xPos, vertex.yPos]).bindPopup('<p align="center">' + varenr + ": " + vertex.beskrivelse + "</p>" + "Klikk for å se plassering"
                        ).on('mouseover', function(e){
                            this.openPopup();
                        }).on('mouseout', function(e) {
                            this.closePopup();
                        }).on('click', function(e) {
                                if(vertex.id == 3 || vertex.id == 4) {
                                    $("#leaflet-kart_close").fadeIn();
                                    markerLayer.clearLayers();
                                    inngang.setLatLng(this.getLatLng());
                                    markerLayer.addLayer(marker).addTo(map2);
                                    marker.openPopup();
                                    map2.panTo(new L.LatLng(280, 0));
                                } else {
                                    window.alert("Hyllekart finnes dessverre ikke for denne delen av butikken.")
                                }
                        }).addTo(map);
                        varenr++;
                    }
                });
                var polyline = L.polyline(latlngs, {color: 'red'}).addTo(map);
            }
        });
    }

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
};

$(document).ready(init);
