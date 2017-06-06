/**
 * Created by Ola on 04.06.2017.
 */
$("#backButton").click(function (e) {
    $.post("/exitSession", function (data) {
        window.location.href = "/index.html";
    });
});

$("#LogButton").click(function (e) {
    $.post("/exitSession", function (data) {
        window.location.href = "/index.html";
    });
});

$("#SendReg").click(function (e) {
    if($("#name").val()&& $("#login").val() && $("#pass").val()) {

        $.post("/reg", $("#form").serialize(), function (data) {
            if (data == "failed") {
                alert("User with such email already exists!");
            } else {
                window.location.href = "/index.html";
            }
        });
    }
    else {
        alert(" ERROR! Fill in ALL fields!");
    }

});

