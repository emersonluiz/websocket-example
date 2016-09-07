var websocket = null;

function connect() {
    var url = 'ws://' + window.location.host + '/websocket-example/example';
    websocket = new WebSocket(url);

    websocket.onopen = function() {
        writeMessage('<span class="green">Connected</span>');
        document.getElementById("BtnConnect").disabled = true;
        document.getElementById("BtnSendMessage").disabled = false;
        document.getElementById("BtnDisconnect").disabled = false;
    };
    websocket.onmessage = function(event) {
        writeMessage(event.data);
    };
    websocket.onerror = function(event) {
        writeMessage('Error! ' + event.data, 'error');
    };
    websocket.onclose = function() {
        writeMessage('<span class="red">Disconnected</span>');
        document.getElementById("BtnConnect").disabled = false;
        document.getElementById("BtnSendMessage").disabled = true;
        document.getElementById("BtnDisconnect").disabled = true;
    };
}

function disconnect() {
    if (websocket !== null) {
        websocket.close();
        websocket = null;
    }
}

function sendMessage(message) {
    if (websocket !== null) {
        websocket.send(message);
    }
}

function writeMessage(text) {
    document.getElementById("terminal").innerHTML += text + "<br />";
}
