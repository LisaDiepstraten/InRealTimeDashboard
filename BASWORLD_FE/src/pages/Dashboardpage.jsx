import React, { useState, useEffect } from 'react';
import APICalls from '../services/APICalls';
import createWebSocketClient from '../services/WebSocketClient';
import Navbar from '../components/NavBar';
import { DateTime, Settings } from 'luxon';
import '../styles/dashboard.css';

// Set default locale for luxon
Settings.defaultLocale = 'en';

function Dashboard() {
    const [eventLogs, setEventLogsProducts] = useState([]);

    const fetchLogsFromAPI = async () => {
        try {
            const response = await APICalls.getEventLogsProducts();
            const formattedLogs = formatLogs(response);
            setEventLogsProducts(formattedLogs);
        } catch (error) {
            console.error("Error fetching event logs:", error);
        }
    };

    const formatLogs = (logs) => {
        return logs.map((log) => {
            const logParts = log.split(" at ");
            const logDetails = logParts[0];
            const timePart = logParts[1];

            try {
                console.log("Original timePart:", timePart); // Debugging
                const cleanedTimePart = timePart.replace(/\./g, '').trim(); // Remove periods
                const formattedTime = DateTime.fromFormat(cleanedTimePart, 'HH:mm, dd MMM yyyy')
                    .plus({ hours: 1 }) // Add one hour
                    .toFormat('HH:mm, dd MMM yyyy');
                return `${logDetails} at ${formattedTime}`;
            } catch (error) {
                console.error("Error parsing time:", timePart, error);
                return `${logDetails} at Invalid DateTime`;
            }
        });
    };

    useEffect(() => {
        // Fetch initial logs
        fetchLogsFromAPI();

        // Set up WebSocket connection
        const stompClient = createWebSocketClient();
        stompClient.onConnect = () => {
            console.log("WebSocket connected.");
            stompClient.subscribe("/topic/event-logs-products", (message) => {
                try {
                    const event = JSON.parse(message.body);

                    const userId = event?.eventPayload?.user?.userId || "Unknown user";
                    const location = event?.eventPayload?.client?.geolocation?.country || "Unknown location";
                    const item = event?.eventPayload?.event?.data?.items?.item_name || "Unknown item";
                    const timestamp = event?.eventHeaders?.eventTimeStamp;

                    const time = timestamp
                        ? DateTime.fromISO(timestamp)
                              .plus({ hours: 1 }) // Add one hour
                              .toFormat("HH:mm, dd MMM yyyy")
                        : "Invalid DateTime";

                    const formattedLog = `${userId} from location ${location} viewed ${item} at ${time}`;

                    // Add new log to the state
                    setEventLogsProducts((prevLogs) => [formattedLog, ...prevLogs].slice(0, 100)); // Keep max 100 logs
                } catch (error) {
                    console.error("Error processing WebSocket message:", error);
                }
            });
        };
        stompClient.activate();

        // Set up periodic fallback for accurate logs
        const intervalId = setInterval(fetchLogsFromAPI, 1000); // Fetch logs every 10 seconds

        // Cleanup WebSocket and interval
        return () => {
            stompClient.deactivate();
            clearInterval(intervalId);
        };
    }, []);

    return (
        <div className="dashboard">
            <Navbar />
            <div className="dashboard-container">
                <h1>Welcome to the Dashboard</h1>
                <h2>BAS WORLD</h2>
                <div className="events-container">
                    <h3>Latest Event Logs</h3>
                    <table className="event-table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Event Log</th>
                            </tr>
                        </thead>
                        <tbody>
                            {eventLogs.map((log, index) => (
                                <tr
                                    key={index}
                                    className={
                                        index % 3 === 0
                                            ? 'red-background'
                                            : index % 3 === 1
                                            ? 'green-background'
                                            : 'blue-background'
                                    }
                                >
                                    <td>{index + 1}</td>
                                    <td>{log}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;
