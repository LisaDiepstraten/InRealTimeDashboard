import React, { useState, useEffect } from 'react';
import { MapContainer, TileLayer, GeoJSON } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import APICalls from '../services/APICalls';
import '../components/LocationPage.css';


function LocationPage() {
    const [geoJsonData, setGeoJsonData] = useState(null);

    // Fetch GeoJSON data and API call for country data if needed
    useEffect(() => {
        const fetchGeoData = async () => {
            try {
                const response = await fetch('/countries.geo.json'); // Path to your GeoJSON file
                const data = await response.json();
                setGeoJsonData(data);
            } catch (error) {
                console.error('Error fetching GeoJSON data:', error);
            }
        };

        fetchGeoData();
    }, []);

    const style = () => ({
        fillColor: '#123123', // Default color for countries
        weight: 1,
        opacity: 1,
        color: '#888',
        dashArray: '3',
        fillOpacity: 0.7,
    });

    return (
        <div className="LocationPage">
            <section className="hero-section text-center p-3 bg-light">
                <div className="container">
                    <h1>Event Locations</h1>
                </div>
            </section>

            <div className="content-container container">
                <section className="map-section">
                    <MapContainer
                        center={[51.505, -0.09]} // Initial center of the map
                        zoom={2}
                        style={{ height: '620px', width: '100%' }}
                        minZoom={2}  // Minimum zoom level
                        maxZoom={10} // Maximum zoom level
                        maxBounds={[
                            [ -90, -180 ],  // South-West corner
                            [ 90, 180 ]     // North-East corner
                        ]}
                        maxBoundsViscosity={1.0}  // Allow full map movement within bounds
                        scrollWheelZoom={true}    // Enable zooming via mouse scroll
                        dragging={true}           // Enable dragging
                        zoomControl={true}        // Show zoom controls
                    >
                        {/* TileLayer URL for OpenStreetMap */}
                        <TileLayer
                            url="https://cartodb-basemaps-a.global.ssl.fastly.net/light_all/{z}/{x}/{y}.png"
    attribution='&copy; <a href="https://www.carto.com/">CARTO</a>'
                        />
                        {/* Display GeoJSON data for country boundaries */}
                        {geoJsonData && (
                            <GeoJSON data={geoJsonData} style={style} />
                        )}
                    </MapContainer>
                </section>
            </div>
        </div>
    );
}

export default LocationPage;
