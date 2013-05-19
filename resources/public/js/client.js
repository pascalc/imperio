var TRACKPAD_MARGIN = 70;
function setTrackpadSize() {
  var height = $(document).height();
  var offset = $("#trackpad").offset().top;
  $("#trackpad").css('height', height - offset - TRACKPAD_MARGIN);
}

var FADE_TIME = 200;
$(document).ready(function() {
  setTrackpadSize();

  $("a.trackpad").click(function(e) {
    e.preventDefault();
    $("#keyboard-inputs").hide();
    $("#trackpad").show();
  });

  $("a.keyboard").click(function(e) {
    e.preventDefault();
    $("#keyboard-inputs").show();
    $("#trackpad").hide();
  });

  $("#keyboard-inputs").hide();
});

