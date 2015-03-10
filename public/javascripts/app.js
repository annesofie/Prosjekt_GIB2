/**
 * Created by annesofiestranderichsen on 06.03.15.
 */
var init = function() {

    $('.addVareKnapp').click(function() {
        $(this).removeClass('addVareKnapp').addClass('removeVareKnapp');
        $('.liste').append($(this).parent().parent());
    });

    $('.removeVareKnapp').click(function() {
        $(this).removeClass('removeVareKnapp').addClass('addVareKnapp');
        $('.hjem').append($(this).parent().parent());
    });

    var map = L.map('leaflet-kart', {
        maxZoom: 0.45,
        minZoom: 0.45,
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