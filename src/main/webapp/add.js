$("#SendAdd").click(function (e) {
    if($("#date").val() && $("#description").val()) {
    $.post("/add", $("#form").serialize(), function (data) {
        if(data){
            alert(data);
        } else {
            alert("Event added successfully!");
        }
    });
    }
    else {
        alert(" ERROR! Fill in ALL fields!");
    }
});
$("#LogButton").click(function (e) {
    $.post("/exitSession", function (data) {
        window.location.href = "/index.html";
    });
});