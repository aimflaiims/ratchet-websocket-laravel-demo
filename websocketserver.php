<?php
use Ratchet\Server\IoServer;
use Ratchet\Http\HttpServer;
use Ratchet\WebSocket\WsServer;
use App\Http\Controllers\WebSocketController;

require dirname(__FILE__) . '/vendor/autoload.php';

$server = IoServer::factory(
     new HttpServer(
         new WsServer(
             new WebSocketController()
         )
     ),
     8090
);
$server->run();
