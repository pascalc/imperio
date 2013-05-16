var canvas = document.getElementById("canvas");

var lastX, lastY;
var move = false;

canvas.addEventListener('touchstart', function(event) {
  console.log('touchstart', event);
  lastX = event.targetTouches[0].pageX;
  lastY = event.targetTouches[0].pageY;
});

canvas.addEventListener('touchmove', function(event) {
  event.preventDefault();
  move = true;

  var curX = event.targetTouches[0].pageX;
  var curY = event.targetTouches[0].pageY;

  var dX = (curX - lastX) * 2;
  var dY = (curY - lastY) * 2;
  command("move_mouse", [dX, dY]);

  lastX = curX;
  lastY = curY;
});

canvas.addEventListener('touchend', function(event) {
  console.log('touchend', event);
  if (move) {
    console.log("You dragged!");
  } else {
     console.log("You clicked!");
     command("left_click", []);
  }
  move = false;
});
