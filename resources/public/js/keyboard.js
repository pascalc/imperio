$(document).ready(function() {
  // Text input
  var ENTER = 13;
  $("#keyboard").on('keypress', function(e) {
    if (e.which == ENTER) {
      e.preventDefault();
      var text = $("#keyboard").val();
      command("type", [text]);
      $("#keyboard").val("");
    }
  })

  // Special keys
  var special_keys = {
    "#up-key" : function() { 
      command("key", ["up"]); 
    },
    "#left-key" : function() { 
      command("key", ["left"]); 
    },
    "#right-key" : function() { 
      command("key", ["right"]); 
    },
    "#down-key" : function() { 
      command("key", ["down"]); 
    },
    "#enter-key" : function() { 
      command("key", ["enter"]); 
    },
    "#backspace-key" : function() { 
      command("key", ["backspace"]); 
    },
  }

  $.each(special_keys, function(key, val) {
    $(key).click(val);
  });
});
