var canvas = document.getElementById("canvas");

var lastX, lastY;
var move = false;
var fingers = 0;

canvas.addEventListener('touchstart', function(event) {
  lastX = event.targetTouches[0].pageX;
  lastY = event.targetTouches[0].pageY;
  fingers = event.targetTouches.length;
});

canvas.addEventListener('touchmove', function(event) {
  event.preventDefault();
  move = true;

  var curX = event.targetTouches[0].pageX;
  var curY = event.targetTouches[0].pageY;

  if (fingers == 1) {
    var mX = (curX - lastX) * 2;
    var mY = (curY - lastY) * 2;
    command("move_mouse", [mX, mY]);
  } else if (fingers == 2) {
    var scrollY = (curY - lastY) * 0.5;
    command("scroll", [scrollY]);
  }
  
  lastX = curX;
  lastY = curY;
  fingers = event.targetTouches.length;
});

canvas.addEventListener('touchend', function(event) {
  if (!move) {
    if (fingers == 1) {
      command("left_click", []);  
    } else if (fingers == 2) {
      command("right_click", []);
    }
  }
  fingers = 0;
  move = false;
});
