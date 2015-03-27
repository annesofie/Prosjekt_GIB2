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

    L.imageOverlay(imageUrl, imageBounds).addTo(map);

    $("#addVareKnapp").click(function() {
            var latlngs = Array();

            appRoutes.controllers.Application.getTargetVertices().ajax({
                success: function(data) {
                    $(data).each(function(index,vertex) {
                        latlngs.push(L.latLng(vertex.xPos,vertex.yPos));
                        window.alert(latlngs);
                    });
                    var polyline = L.polyline(latlngs, {color: 'red'}).addTo(map);
                }
            });

            //appRoutes.controllers.Application.sortShoppingList();
        }
    )

   function newUserOk() {
       alert("Bruker er opprettet, logg inn");
   }


};

$(document).ready(init);
