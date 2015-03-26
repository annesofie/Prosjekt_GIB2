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

    var vertex1 = L.marker([60, 317]).bindPopup("1");
    var vertex2 = L.marker([60, 260]).bindPopup("2");
    var vertex3 = L.marker([115, 260]).bindPopup("3");
    var vertex4 = L.marker([170, 260]).bindPopup("4");
    var vertex5 = L.marker([225, 260]).bindPopup("5");
    var vertex6 = L.marker([288, 260]).bindPopup("6");
    var vertex7 = L.marker([351, 260]).bindPopup("7");
    var vertex8 = L.marker([415, 260]).bindPopup("8");
    var vertex9 = L.marker([415, 204]).bindPopup("9");
    var vertex10 = L.marker([415, 147]).bindPopup("10");
    var vertex11 = L.marker([415, 90]).bindPopup("11");
    var vertex12 = L.marker([351, 90]).bindPopup("12");
    var vertex13 = L.marker([288, 90]).bindPopup("13");
    var vertex14 = L.marker([225, 90]).bindPopup("14");
    var vertex15 = L.marker([170, 90]).bindPopup("15");
    var vertex16 = L.marker([115, 90]).bindPopup("16");
    var vertex17 = L.marker([60, 147]).bindPopup("17");
    var vertex18 = L.marker([60, 204]).bindPopup("18");
    var vertex19 = L.marker([225, 147]).bindPopup("19");
    var vertex20 = L.marker([225, 204]).bindPopup("20");

    L.imageOverlay(imageUrl, imageBounds).addTo(map);

    $("#addVareKnapp").click(function() {
            var latlngs = Array();

            appRoutes.controllers.Application.getTargetVertices().ajax({
                success: function(data) {
                    $(data).each(function(index,vertex) {
                        latlngs.push([vertex.getPosX(),vertex.getPosY()]);
                    });
                }
            });

            var polyline = L.polyline(latlngs, {color: 'red'}).addTo(map);

            appRoutes.controllers.Application.sortShoppingList();
        }
    )


};

$(document).ready(init);
