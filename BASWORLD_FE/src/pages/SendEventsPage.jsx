import { countries } from 'countries-list';
import React, { useEffect, useState } from 'react';
import { v4 as uuidv4 } from 'uuid';
import Navbar from '../components/NavBar';
import createWebSocketClient from '../services/WebSocketClient'; // Ensure this is imported correctly

const devices = ["Desktop", "Mobile", "Tablet"];
const marketingSources = ["google", "facebook", "instagram", "others"];
const productCategories = [
  { name: "Tractorhead", id: "tractorhead" },
  { name: "Truck", id: "truck" },
  { name: "Light commercial vehicle", id: "light_commercial_vehicle" },
  { name: "Construction equipment", id: "construction_equipment" },
  { name: "Semi-trailer", id: "semi_trailer" },
  { name: "Trailer", id: "trailer" },
  { name: "Farm equipment", id: "farm_equipment" },
  { name: "Various", id: "various" },
];
const countryArray = Object.values(countries).map(country => ({
  country: country.name,
  region: country.region || 'Unknown',
  city: 'Unknown City',
  latitude: (Math.random() * 180 - 90).toFixed(4),
  longitude: (Math.random() * 360 - 180).toFixed(4)
}));

function SendEventsPage() {
  const [amount, setAmount] = useState(1);
  const [logs, setLogs] = useState([]);
  const [isSending, setIsSending] = useState(false);

  useEffect(() => {
    const stompClient = createWebSocketClient();
    stompClient.activate();
    return () => stompClient.deactivate();
  }, []);
  const handleClearLogs = () => {
    setLogs([]);
  };
  const generateRandomEvent = () => {
    const randomCountry = countryArray[Math.floor(Math.random() * countryArray.length)];
    const randomCategory = productCategories[Math.floor(Math.random() * productCategories.length)];

  const randomThreshold = 0.2 + Math.random() * (0.5 - 0.2); 
  const eventType = Math.random() < randomThreshold ? 'invalid' : 'valid';


    const eventTimeStamp = new Date().toISOString();
    console.log("Generated Timestamp:", eventTimeStamp);  // Check the timestamp in the console

    const event = {
      event: "bas-user-event",
      eventHeaders: {
        entity: "userEvent",
        entityKey: eventType === 'valid' ? uuidv4() : "invalid-event",
        eventMainType: "CREATE",
        eventSubType: "select_item",
        eventTimeStamp: new Date().toISOString(),
        publishedBy: "buttPackage",
        policyVersion: "1.0.0",
      },
      eventPayload: {
        application: {
          name: "SampleApp",
          version: "2.5.3",
          environment: "production",
        },
        context: {
          page: {
            title: "Product Page",
            designSize: "designSize-lg",
            url: "https://www.example.com/product/123",
            canonicalUrl: "https://www.example.com/product/123",
          },
          session: {
            buttSessionId: "sess-" + Math.random().toString(36).substring(2, 15),
            vendorClickIds: {
              google: "GA-" + Math.floor(Math.random() * 1000000),
              bing: "BING-" + Math.floor(Math.random() * 1000000),
              facebook: "FB-" + Math.floor(Math.random() * 1000000),
              linkedIn: "LI-" + Math.floor(Math.random() * 1000000),
            },
            referer: "https://www.google.com",
            utmParameters: {
              source: marketingSources[Math.floor(Math.random() * marketingSources.length)],
              medium: "cpc",
              campaign: "summer-sale",
              term: "running shoes",
              content: "ad-variant-1",
            },
          },
          client: {
            buttClientId: "client-" + Math.random().toString(36).substring(2, 15),
            vendorClientIds: {
              googleAnalytics: "GA-" + Math.floor(Math.random() * 1000000),
              bing: "BING-" + Math.floor(Math.random() * 1000000),
              facebook: "FB-" + Math.floor(Math.random() * 1000000),
            },
            rawUserAgent: "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36",
            device: {
              type: devices[Math.floor(Math.random() * devices.length)],
              vendor: "Dell",
              model: "XPS 13",
              screenWidth: 1920,
              screenHeight: 1080,
            },
            operatingSystem: {
              name: "Windows",
              version: "10",
            },
            browser: {
              name: "Chrome",
              version: "90.0.4430.212",
              viewportWidth: 1920,
              viewportHeight: 1040,
              language: "en-US",
              cookiesEnabled: true,
              javaScriptEnabled: true,
              doNotTrackEnabled: false,
            },
            engine: {
              name: "WebKit",
              version: "537.36",
            },
            geolocation: {
              latitude: randomCountry.latitude,
              longitude: randomCountry.longitude,
              country: randomCountry.country,
              region: randomCountry.region,
              city: randomCountry.city,
            },
          },
          user: {
            userId: "user-" + Math.random().toString(36).substring(2, 10),
            userEmailAddress: "user@example.com",
            personId: "person-" + Math.random().toString(36).substring(2, 10),
            personEmailAddress: "person@example.com",
            companyId: "company-abc123",
            companyName: "Example Corp",
          },
        },
        event: {
          eventName: "select_item",
          data: {
            item_list_id: "list-" + Math.random().toString(36).substring(2, 10),
            item_list_name: "Featured Items",
            items: {
              item_id: Math.floor(Math.random() * 100000),
              item_name: `${randomCategory.name} ` + Math.floor(Math.random() * 10000),
              affiliation: "Sample Affiliation",
              item_brand: `${randomCategory.name} Brand`,
              item_category: randomCategory.name,
              item_category2: "Default SubCategory",
              item_list_id: randomCategory.id,
              item_list_name: "Featured Items",
              index: 1,
              item_variant: "Black",
              price: parseFloat((Math.random() * 500).toFixed(2)),
              quantity: Math.floor(Math.random() * 10) + 1,
            },
          },
        },
      },
    };

    return event;
  };
  const isEventValid = (event) => {
    return event.eventHeaders.entityKey !== "invalid-event" && event.eventPayload.event.data.items.price > 0;
  };
  // const handleSendEvents = () => {
  //   if (isSending) return;  // Prevent starting the request if it's already sending
  
  //   setIsSending(true);  // Set to true when starting the send process
  
  //   // Create WebSocket client only once
  //   const stompClient = createWebSocketClient();
  
  //   stompClient.onConnect = () => {
  //     console.log("WebSocket connected.");
  
  //     // Batch events into an array
  //     const eventsBatch = [];
  //     for (let i = 0; i < amount; i++) {
  //       const newEvent = generateRandomEvent();
  //       if (isEventValid(newEvent)) {
  //         eventsBatch.push(newEvent); // Add valid event to the batch
  //       }
  //     }
  
  //     // Send all events in one batch if there are any
  //     if (eventsBatch.length > 0) {
  //       stompClient.publish({
  //         destination: "/app/sendEvent",
  //         body: JSON.stringify(eventsBatch), // Send all events at once
  //       });
  //       console.log("Batch of events sent");
  
  //       // Update logs once after all events are sent
  //       setLogs((prevLogs) => [
  //         ...eventsBatch.map((event) => ({
  //           status: "Sent",
  //           time: new Date().toLocaleString(),
  //           event: event.eventHeaders.entityKey,
  //         })),
  //         ...prevLogs, // Prepend the new logs to the existing ones
  //       ]);
  //     }
  
  //     setIsSending(false);  // Reset after finishing the send process
  //   };
  
  //   stompClient.activate();
  // };
  const handleSendEvents = () => {
    if (isSending) return; // Prevent sending multiple requests simultaneously

    setIsSending(true); // Mark as sending

    const stompClient = createWebSocketClient();
    stompClient.onConnect = () => {
        console.log("WebSocket connected.");

        for (let i = 0; i < amount; i++) {
            const newEvent = generateRandomEvent();

            // Validate and send the event
            if (isEventValid(newEvent)) {
                stompClient.publish({
                    destination: "/app/sendEvent",
                    body: JSON.stringify(newEvent),
                });
                console.log("Event sent:", newEvent.eventHeaders.entityKey);

                // Update logs immediately
                setLogs((prevLogs) => [
                    { status: "Sent", time: new Date().toLocaleString(), event: newEvent.eventHeaders.entityKey },
                    ...prevLogs,
                ]);
            } else {
              console.warn("Invalid event skipped:", {
                entityKey: newEvent.eventHeaders.entityKey,
                price: newEvent.eventPayload.event.data.items.price,
            });

                // Log invalid events
                setLogs((prevLogs) => [
                    { status: "Invalid", time: new Date().toLocaleString(), event: newEvent.eventHeaders.entityKey },
                    ...prevLogs,
                ]);
            }
        }

        setIsSending(false); // Reset sending state
    };

    stompClient.activate();
};

  

  const pageStyles = {
    padding: '20px',
    fontFamily: 'Arial, sans-serif',
  };

  const containerStyles = {
    maxWidth: '600px',
    margin: '0 auto',
  };

  const inputStyles = {
    marginBottom: '20px',
  };

  const buttonStyles = {
    padding: '10px 20px',
    fontSize: '16px',
    backgroundColor: '#4CAF50',
    color: 'white',
    border: 'none',
    cursor: 'pointer',
  };

  const buttonDisabledStyles = {
    backgroundColor: '#ccc',
    cursor: 'not-allowed',
  };

  const logsContainerStyles = {
    marginTop: '20px',
  };

  const logItemStyles = {
    padding: '8px',
    margin: '4px 0',
    border: '1px solid #ddd',
    borderRadius: '4px',
  };

  const invalidEventStyles = {
    color: 'red',
    fontWeight: 'bold',
  };

  return (
    <div style={pageStyles}>
      <Navbar />
      <div style={containerStyles}>
        <h2>Send Event Data</h2>
        <div>
          <label htmlFor="amount">Number of Events to Send:</label>
          <input
            id="amount"
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            style={inputStyles}
          />
        </div>
        <button
          onClick={handleSendEvents}
          style={{ ...buttonStyles, ...(isSending ? buttonDisabledStyles : {}) }}
          disabled={isSending}
        >
          {isSending ? 'Sending...' : 'Send Events'}
        </button>
        <button
          onClick={handleClearLogs}
          style={{ ...buttonStyles, marginLeft: '10px' }}
        >
          Clear Logs
        </button>
        <div style={logsContainerStyles}>
          <h3>Logs:</h3>
          <ul>
            {logs.map((log, index) => (
              <li
                key={index}
                style={{
                  ...logItemStyles,
                  ...(log.status === "Invalid" ? invalidEventStyles : {}),
                }}
              >
                {log.time} - {log.event} - {log.status}
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default SendEventsPage;
