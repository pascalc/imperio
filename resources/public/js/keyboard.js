var ENTER = 13;
$("#keyboard").on('keypress', function(e) {
  if (e.which == ENTER) {
    e.preventDefault();
    var text = $("#keyboard").val();
    command("type", [text]);
    $("#keyboard").val("");
  }
})
