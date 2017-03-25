var
    connect = require('../index').connect,
    ProtoMessages = require('connect-protobuf-messages'),
    EncodeDecode = require('connect-js-encode-decode'),
    AdapterTLS = require('connect-js-adapter-tls'),
    protocol = new ProtoMessages([
        {
            file: 'node_modules/connect-protobuf-messages/src/main/protobuf/CommonMessages.proto',
            protoPayloadType: 'ProtoPayloadType'
        }
    ]),
    adapter = new AdapterTLS({
        host: 'sandbox-tradeapi.spotware.com',
        port: 5032
    }),
    encodeDecode = new EncodeDecode(),
    codec = require('connect-js-codec'),
    sendCommand;

protocol.load();
protocol.build();

sendCommand = connect(
  adapter,
  codec(adapter, encodeDecode, protocol)
);

adapter.onOpen(function () {
    sendCommand(52, {timestamp: Date.now()}, (p) => {console.dir(p)});
});
adapter.connect();
