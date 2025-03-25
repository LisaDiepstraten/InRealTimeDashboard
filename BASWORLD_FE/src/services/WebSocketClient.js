import { Client } from "@stomp/stompjs";

const createWebSocketClient = () => {
    const client = new Client({
        brokerURL: "ws://localhost:8080/event-websocket", // Use ws:// for native WebSocket
        reconnectDelay: 5000, // Reconnect after 5 seconds if the connection is lost
        debug: (msg) => console.log("[STOMP DEBUG]:", msg), // Enable debug logging
    });

    return client;
};

export default createWebSocketClient;
