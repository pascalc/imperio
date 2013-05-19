function getServerInfo() {
  return $.get("/info");
}

$(document).ready(function() {
  getServerInfo().then(function(data) {
    $("#instructions .client-url").text(
      "http://" + data.ip + ":" + data.port
    );
  });
});
