/**
 * Created by annesofiestranderichsen on 06.03.15.
 */
var init = function() {

    /*/
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
    */


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

    L.marker([60, 317]).bindPopup("1").addTo(map);
    L.marker([60, 260]).bindPopup("2").addTo(map);
    L.marker([60, 204]).bindPopup("18").addTo(map);
    L.marker([60, 147]).bindPopup("17").addTo(map);
    L.marker([115, 260]).bindPopup("3").addTo(map);
    L.marker([170, 260]).bindPopup("4").addTo(map);
    L.marker([225, 260]).bindPopup("5").addTo(map);
    L.marker([288, 260]).bindPopup("6").addTo(map);
    L.marker([351, 260]).bindPopup("7").addTo(map);
    L.marker([415, 260]).bindPopup("8").addTo(map);
    L.marker([115, 90]).bindPopup("16").addTo(map);
    L.marker([170, 90]).bindPopup("15").addTo(map);
    L.marker([225, 90]).bindPopup("14").addTo(map);
    L.marker([288, 90]).bindPopup("13").addTo(map);
    L.marker([351, 90]).bindPopup("12").addTo(map);
    L.marker([415, 90]).bindPopup("11").addTo(map);
    L.marker([225, 147]).bindPopup("19").addTo(map);
    L.marker([225, 204]).bindPopup("20").addTo(map);
    L.marker([415, 147]).bindPopup("10").addTo(map);
    L.marker([415, 204]).bindPopup("9").addTo(map);

    L.imageOverlay(imageUrl, imageBounds).addTo(map);
};

$(document).ready(init);