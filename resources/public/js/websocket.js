function getServerInfo() {
  return $.get("/info");
}

var ws, send, command;
function setUpWebSocket(address) {
  ws = new WebSocket("ws://" + address + "/socket");
  console.log("readyState:", ws.readyState);

  ws.onopen = function()
  {
    // Web Socket is connected, send data using send()
    console.log("Socket opened!");
    console.log("readyState:", ws.readyState);
  };

  ws.onclose = function()
  { 
    // websocket is closed.
    console.log("Socket closed!");
    console.log("readyState:", ws.readyState);
  };

  send = function(msg) {
    ws.send(msg);
  }

  command = function(cmd, args) {
    console.log("command", cmd, args);
    send(JSON.stringify({
      'command': cmd,
      'args' : args,
    }));
  }
}

$(document).ready(function() {
  getServerInfo().then(function(data) {
    setUpWebSocket(data.ip + ":" + data.port);
  });
});
