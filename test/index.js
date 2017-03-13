var
    connect = require('../index'),
    ProtoMessages = require('connect-protobuf-messages'),
    EncodeDecode = require('connect-js-encode-decode'),
    AdapterTLS = require('connect-js-adapter-tls');
    protocol = new ProtoMessages([
        {
            file: 'node_modules/connect-protobuf-messages/src/main/protobuf/CommonMessages.proto'
        },
        {
            file: 'node_modules/connect-protobuf-messages/src/main/protobuf/OpenApiMessages.proto'
        }
    ]),
    adapter = new AdapterTLS({
        host: 'sandbox-tradeapi.spotware.com',
        port: 5032
    }),
    encodeDecode = new EncodeDecode(),
    sendCommand;
    coder = require('./coder')(encodeDecode, adapter)
    this.encodeDecode.registerDecodeHandler(
        this.onMessage.bind(this)
    );

adapter.onOpen(function () {
    sendCommand = connect(adapter, coder);
    sendCommand(1086, {})
});
adapter.connect();
