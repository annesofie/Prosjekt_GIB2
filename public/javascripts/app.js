/**
 * Created by annesofiestranderichsen on 06.03.15.
 */
var init = function() {

    function cloneRow() {

        var row = document.getElementsByName("vareTabell");
        var table = document.getElementsByName("liste");
        var clone = row.cloneNode(true);
        clone.name = "handleTabell";
        table.appendChild(clone);
    }
    function createRow() {

        var row = document.createElement('tr'); //create row node
        var col = document.createElement('td'); //create column node
        var col2 = document.createElement('td'); //create second column node
        //var col3 = document.createElement('td'); //create second column node
        row.appendChild(col);
        row.appendChild(col2);
        //row.appendChild(col3);
        col.innerHTML = this.navn;
        col2.innerHTML = this.pris
        //col3.innerHTML = button??

        var table = document.getElementsByName("liste");
        table.append(row);
    }
    $('.addVareKnappHjem').click(function() { 
        //cloneRow();
        createRow(); 

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