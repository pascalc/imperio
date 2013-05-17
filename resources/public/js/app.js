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
    $("#keyboard").fadeOut(FADE_TIME);
    $("#trackpad").fadeIn(FADE_TIME);
  });

  $("a.keyboard").click(function(e) {
    e.preventDefault();
    $("#keyboard").fadeIn(FADE_TIME);
    $("#trackpad").fadeOut(FADE_TIME);
    $("#keyboard").focus();
  });

  $("#keyboard").hide();
});
