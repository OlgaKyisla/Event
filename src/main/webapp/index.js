$("#exitButton").click(function (e) {
    $.post("/exitSession", function (data) {
        window.location.href = "/registration.html";
    });
});

$("#SendSubmit").click(function (e) {
   $.post("/login", $("#form1").serialize(), function (data) {
       if (data == "failed") {
           alert("Incorrectly entered data, try again!");
       } else {
           window.location.href = "/home.html";
       }
   });
});

