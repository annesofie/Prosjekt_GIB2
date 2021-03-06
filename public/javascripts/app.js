﻿/**
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

    //Inngangsikon
    var inngangIcon = L.icon({
        iconUrl: 'http://goo.gl/eib74S',

        iconSize:     [65, 65], // size of the icon
        iconAnchor:   [10, 45], // point of the icon which will correspond to marker's location
        popupAnchor:  [0, -50] // point from which the popup should open relative to the iconAnchor
    });

    //Kasseikon
    var kasseIcon = L.icon({
        iconUrl: 'http://i62.tinypic.com/2zel3sg.png',

        iconSize:     [50, 50], // size of the icon
        iconAnchor:   [25, 50], // point of the icon which will correspond to marker's location
        popupAnchor:  [0, -50] // point from which the popup should open relative to the iconAnchor
    });

    //Pil-ikon
    var pilIcon = L.icon({
        iconUrl: 'http://i61.tinypic.com/2w7gtxt.png',

        iconSize:     [50, 30], // size of the icon
        iconAnchor:   [0, 25], // point of the icon which will correspond to marker's location
        popupAnchor:  [0, -50] // point from which the popup should open relative to the iconAnchor
    });

    var map;
    var map2;

    //Konfigurasjon hovedkart
    map = L.map('leaflet-kart', {
        maxZoom: 0.45,
        minZoom: 0.45,
        zoomControl: false,
        dragging: false,
        crs: L.CRS.Simple
    }).setView([0, 0], 1);

    map.setMaxBounds(new L.LatLngBounds([0,500], [500,0]));
    var imageUrl = 'http://i60.tinypic.com/ad17hd.jpg';
    var imageBounds = [[500,0], [0,366]];
    L.imageOverlay(imageUrl, imageBounds).addTo(map);

    if (window.location.pathname == '/shoppingPath') {
        var inngang = L.marker([60, 317],{icon: inngangIcon}).bindPopup("Inngang").addTo(map);
        var kasse = L.marker([60, 204],{icon: kasseIcon}).bindPopup("Kasse").addTo(map);

        //Konfigurasjon hyllebilde
        map2 = L.map('leaflet-kart_close', {
            maxZoom: 0.2,
            minZoom: 0.2,
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

        //Route til Application.java for uthenting av korteste sti
        appRoutes.controllers.Application.getTargetVertices().ajax({
            success: function (data) {
                $(data).each(function (index, vertex) {
                    latlngs.push(L.latLng(vertex.xPos, vertex.yPos));
                    if (vertex.beskrivelse != null) {
                        var marker = L.marker([vertex.hylleX,vertex.hylleY]).bindPopup(vertex.beskrivelse).on('click', function(e) {
                            map2.panTo(new L.LatLng(280, 0));
                        });

                        L.marker([vertex.xPos, vertex.yPos]).bindPopup(varenr + ": " + vertex.beskrivelse
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

};

$(document).ready(init);
