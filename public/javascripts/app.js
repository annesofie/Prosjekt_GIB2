/**
 * Created by annesofiestranderichsen on 06.03.15.
 */
var init = function() {

    $('.addVareKnappHjem').click(function() {
        $(this).removeClass('addVareKnappHjem').addClass('removeVareKnappHjem');
        $('.liste').append($(this).parent().parent());
    });
    $('.addVareKnappElektro').click(function() {
        $(this).removeClass('addVareKnappElektro').addClass('removeVareKnappElektro');
        $('.liste').append($(this).parent().parent());
    });
    $('.addVareKnappFritid').click(function() {
        $(this).removeClass('addVareKnappFritid').addClass('removeVareKnappFritid');
        $('.liste').append($(this).parent().parent());
    });
    $('.addVareKnappJernvare').click(function() {
        $(this).removeClass('addVareKnappJernvare').addClass('removeVareKnappJernvare');
        $('.liste').append($(this).parent().parent());
    });
    $('.addVareKnappMultimedia').click(function() {
        $(this).removeClass('addVareKnappMultimedia').addClass('removeVareKnappMulimedia');
        $('.liste').append($(this).parent().parent());
    });

    $('.removeVareKnappHjem').click(function() {
        $(this).removeClass('removeVareKnappHjem').addClass('addVareKnappHjem');
        $('.hjem').append($(this).parent().parent());
    });

    $('.removeVareKnappElektro').click(function() {
        $(this).removeClass('removeVareKnappElektro').addClass('addVareKnappElektro');
        $('.hjem').append($(this).parent().parent());
    });

    $('.removeVareKnappFritid').click(function() {
        $(this).removeClass('removeVareKnappFritid').addClass('addVareKnappFritid');
        $('.hjem').append($(this).parent().parent());
    });

    $('.removeVareKnappJernvare').click(function() {
        $(this).removeClass('removeVareKnappJernvare').addClass('addVareKnappJernvare');
        $('.hjem').append($(this).parent().parent());
    });

    $('.removeVareKnappMultimedia').click(function() {
        $(this).removeClass('removeVareKnappMultimedia').addClass('addVareKnappMultimedia');
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