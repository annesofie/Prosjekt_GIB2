/**
 * Created by annesofiestranderichsen on 06.03.15.
 */
var init = function() {
    $('.søkknapp').click(function() {
        alert("Du kan ikke søke enda, jobber med saken!");
    });

    $('.loginknapp').click(function() {
        $('.login').fadeOut(800);
        $('.loggetinn').fadeIn(900);
    });

    var map = L.map('leaflet-kart', {
        maxZoom: 1,
        minZoom: 1,
        zoomControl: false,
        dragging: false,
        crs: L.CRS.Simple
    }).setView([0, 0], 1);

    map.setMaxBounds(new L.LatLngBounds([0,500], [500,0]));

    var imageUrl = 'http://i57.tinypic.com/2i8deac.png'
    var imageBounds = [[500,0], [0,370]];

    L.imageOverlay(imageUrl, imageBounds).addTo(map);
};

$(document).ready(init);