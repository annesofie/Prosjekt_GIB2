/**
 * Created by annesofiestranderichsen on 06.03.15.
 */
var init = function() {

    $('#addVareKnapp').click(function() {
        $('<li>').text().appendTo('.varer');
    });

    var map = L.map('leaflet-kart', {
        maxZoom: 0.4,
        minZoom: 0.4,
        zoomControl: false,
        dragging: false,
        crs: L.CRS.Simple
    }).setView([0, 0], 1);

    map.setMaxBounds(new L.LatLngBounds([0,500], [500,0]));

    var imageUrl = 'http://i61.tinypic.com/30ucvfc.jpg'
    var imageBounds = [[500,0], [0,370]];

    L.imageOverlay(imageUrl, imageBounds).addTo(map);
};

$(document).ready(init);