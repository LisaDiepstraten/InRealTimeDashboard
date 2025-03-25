import React, { useState, useEffect, useRef } from "react";
import { CheckboxSVGMap } from "react-svg-map";
import "react-svg-map/lib/index.css";
import world from "@svg-maps/world";
import "./Map.css";

// Import the API call function
import APICalls from '../services/APICalls';

// Function to determine the color based on the value
const getCountryColor = (value) => {
  if (value >= 640) return "#660000";    
  if (value >= 320) return "#A50000";    
  if (value >= 160) return "#E01C0E";   
  if (value >= 80) return "#FF4F34";  
  if (value >= 1) return "#FF7F5C";  
  return "#F1F1F1"; // Default for countries with 0
};

function WorldMap() {
  const [selectedCountry, setSelectedCountry] = useState(null); // For storing the name of the selected country
  const [countryValues, setCountryValues] = useState({}); // To store the country counts fetched from API
  const [scale, setScale] = useState(1); // Zoom level
  const [position, setPosition] = useState({ x: 0, y: 0 }); // Pan position
  const mapRef = useRef(null); // Reference to the SVG map container

  // Fetch the country count data from the API
  useEffect(() => {
    const fetchData = async () => {
      const data = await APICalls.getCountryCount();
      setCountryValues(data); // Store the fetched country count data
    };

    fetchData();
  }, []); // Only run this once when the component mounts

  // Handle mouse wheel for zooming
  const handleWheel = (event) => {
    event.preventDefault(); // Prevent default scroll behavior
    setScale((prevScale) => {
      const zoomFactor = event.deltaY > 0 ? 0.95 : 1.05;
      const newScale = Math.min(Math.max(prevScale * zoomFactor, 0.8), 3); // Limit zoom
      return newScale;
    });
  };

  useEffect(() => {
    // Manually add event listener to handle passive scroll
    const mapContainer = mapRef.current;
    if (mapContainer) {
      mapContainer.addEventListener("wheel", handleWheel, { passive: false });
    }

    // Cleanup event listener when component unmounts or on changes
    return () => {
      if (mapContainer) {
        mapContainer.removeEventListener("wheel", handleWheel);
      }
    };
  }, []);

  // Handle mouse drag for panning
  const isPanning = useRef(false);
  const panStart = useRef({ x: 0, y: 0 });

  const handleMouseDown = (event) => {
    isPanning.current = true;
    panStart.current = { x: event.clientX, y: event.clientY };
  };

  const handleMouseMove = (event) => {
    if (!isPanning.current) return;

    // Calculate the difference in mouse position (dx, dy)
    let dx = event.clientX - panStart.current.x;
    let dy = event.clientY - panStart.current.y;

    // Divide the movement by the zoom factor to adjust the speed based on zoom level
    dx /= scale;
    dy /= scale;

    // Apply the calculated movement (dx, dy) to the current position
    setPosition({ x: position.x + dx, y: position.y + dy });

    // Update the pan start position for next move calculation
    panStart.current = { x: event.clientX, y: event.clientY };
  };

  const handleMouseUp = () => {
    isPanning.current = false;
  };

  const onLocationHover = (event) => {
    const countryName = event.target.getAttribute("name"); // Get the country name
    const value = countryValues[countryName] || 0; // Get the country value from the country name
    setSelectedCountry({ name: countryName, value }); // Set the country name and value
  };

  const onLocationMouseOut = () => {
    setSelectedCountry(null); // Reset on mouse out
  };

  const applyCountryStyles = () => {
    // Apply colors based on event counts for all countries
    const mapPaths = document.querySelectorAll('.map-class path');
    mapPaths.forEach(path => {
      const countryName = path.getAttribute("name"); // Get the country name
      const value = countryValues[countryName] || 0;
      path.style.fill = getCountryColor(value); // Apply the color based on event count
    });
  };

  useEffect(() => {
    // Apply styles to countries when countryValues is updated
    applyCountryStyles();
  }, [countryValues]); // Reapply styles when countryValues change

  return (
    <div
      className="map-container"
      onMouseDown={handleMouseDown}
      onMouseMove={handleMouseMove}
      onMouseUp={handleMouseUp}
      onMouseLeave={handleMouseUp}
      ref={mapRef}
      style={{
        overflow: "hidden",
        cursor: isPanning.current ? "grabbing" : "grab",
        width: "100%",
        height: "600px",
        position: "relative",
      }}
    >
      <div
        style={{
          transform: `scale(${scale}) translate(${position.x}px, ${position.y}px)`,
          transformOrigin: "center",
          width: "100%",
          height: "100%",
        }}
      >
        <CheckboxSVGMap
          map={world}
          className="map-class"
          onLocationMouseMove={onLocationHover} // Trigger on hover
          onLocationMouseOut={onLocationMouseOut} // Reset on mouse out
        />
      </div>

      {/* Display the hovered country's name and value under the map */}
      {selectedCountry && (
        <div
          className="country-text-div"
          style={{
            position: "absolute",
            bottom: "10px", // Position the text just below the map
            left: "50%", // Center the text horizontally
            transform: "translateX(-50%)", // Adjust for exact centering
            fontSize: "40px",
            fontWeight: "bold",
            backgroundColor: "rgba(0, 0, 0, 0.5)",
            color: "white",
            padding: "5px 10px",
            borderRadius: "5px",
          }}
        >
          <p className="country-text">{selectedCountry.name}: {selectedCountry.value}</p>
        </div>
      )}

      {/* Legend */}
      <div className="legend" style={{
        position: "absolute",
        bottom: "10px", // Position the legend slightly above the text
        right: "10px",
        fontSize: "14px",
        backgroundColor: "rgba(0, 0, 0, 0.5)",
        color: "white",
        padding: "5px 10px",
        borderRadius: "5px",
        display: "flex",
        justifyContent: "space-around",
        flexDirection: "column",
        width: "100px",
      }}>
        <div style={{ color: "#660000", fontWeight: "bold" }}>640+ </div>
        <div style={{ color: "#A50000", fontWeight: "bold" }}>320+ </div>
        <div style={{ color: "#E01C0E", fontWeight: "bold" }}>160+ </div>
        <div style={{ color: "#FF4F34", fontWeight: "bold" }}>80+  </div>
        <div style={{ color: "#FF7F5C", fontWeight: "bold" }}>1+   </div>
        <div style={{ color: "", fontWeight: "bold" }}>0     </div>
      </div>
    </div>
  );
}

export default WorldMap;
