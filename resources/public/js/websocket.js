ws = new WebSocket("ws://10.0.0.11:4000/socket");
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
  send(JSON.stringify({
    'command': cmd,
    'args' : args,
  }));
}  

