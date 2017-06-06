
(function checkSession(){
    $.post("/session", function(data){
        if (data == "NotAutorized"){
            $("body").html("NotAutorized");
        }
    });
})();

$("#button").click(function (e) {

   var city = $("#selectCity").val();
   var date = $("#date").val();
   var price = $("#price").val();

    $.post("/main",{
        city : city,
        date : date,
        price : price
    }, function(data){

        if(data)
        {

            var result = data.split("|");

            var header = " <tr>" +
                "<th>Date</th>" +
                "<th>Price</th>" +
                "<th>City</th>" +
                "<th>Description</th>" +
                "</tr>";

            $("#table").html(header);

            for(var i=0; i<result.length - 1; i++)
            {
                printData(result[i]);
            }
        } else {
            alert("No results !");
            $("#table").html("");
        }

    });
});

function printData(row) {

    var t = $("#table");

    var elems = row.split(";");

    var row = "";

    row += "<tr>";

    for(var i=0; i<elems.length; i++)
    {
        row += "<td>";
        row += elems[i];
        row += "</td>";
    }

    row += "</tr>";

    t.append(row);
}