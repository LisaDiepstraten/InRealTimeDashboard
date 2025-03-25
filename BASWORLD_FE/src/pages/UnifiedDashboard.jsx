import React, { useEffect, useState, useMemo } from "react";
import ChartComponent from "../components/ChartComponent";
import WorldMap from "../pages/WorldMap"; // Updated to use WorldMap component
import APICalls from "../services/APICalls";
import { useNavigate } from "react-router-dom";
import "./UnifiedDashboard.css";

const UnifiedDashboard = () => {
  const [deviceData, setDeviceData] = useState(null);
  const [marketingData, setMarketingData] = useState(null);
  const [visitData, setVisitData] = useState(null);
  const [sessionData, setSessionData] = useState(null);
  const [countryValues, setCountryValues] = useState({});
  const [isLoading, setIsLoading] = useState(true);

  const navigate = useNavigate(); // For navigation to detail pages

  // Parallel fetch data for all charts
  useEffect(() => {
    const fetchData = async () => {
      try {
        const [
          deviceResponse,
          marketingResponse,
          visitResponse,
          sessionResponse,
          countryResponse,
        ] = await Promise.all([
          APICalls.getDevicesCount(),
          APICalls.getMarketingCount(),
          APICalls.getMostVisitedCategories(),
          APICalls.getEventsCount(),
          APICalls.getCountryCount(),
        ]);

        setDeviceData(generateDeviceChartData(deviceResponse));
        setMarketingData(generateMarketingChartData(marketingResponse));
        setVisitData(generateVisitChartData(visitResponse));
        setSessionData(generateSessionChartData(sessionResponse));
        setCountryValues(countryResponse);
      } catch (error) {
        console.error("Error fetching data:", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);

  // Generates chart data for devices
  const generateDeviceChartData = useMemo(() => (devicesCount) => {
    const labels = generateLast12Months();
    const deviceTypes = ["Desktop", "Mobile", "Tablet"];

    const datasets = deviceTypes.map((type, i) => {
      const data = labels.map(
        (month) => devicesCount[type.toLowerCase()]?.[month] || 0
      );
      return {
        label: type,
        backgroundColor: ["#4CAF50", "#FF5722", "#3F51B5"][i % 3],
        data,
      };
    });

    return { labels, datasets };
  }, []);

  // Generates chart data for marketing sources
  const generateMarketingChartData = (marketingData) => {
    const labels = generateLast12Months();
    const sources = ["google", "facebook", "instagram", "others"];

    const datasets = sources.map((source, i) => {
      const data = labels.map((month) => marketingData[source]?.[month] || 0);
      return {
        label: source.charAt(0).toUpperCase() + source.slice(1),
        backgroundColor: ["#2b5797", "#FF5733", "#28A745", "#FFD700"][i],
        data,
      };
    });

    return { labels, datasets };
  };

  // Generates chart data for most visited categories
  const generateVisitChartData = (visitData) => {
    const labels = Object.keys(visitData);
    const data = Object.values(visitData);
    const colors = [
      "#2E86C1",
      "#F4D03F",
      "#1ABC9C",
      "#E67E22",
      "#8E44AD",
      "#3498DB",
      "#FF6384",
      "#2ECC71",
      "#C0392B",
    ];

    return {
      labels,
      datasets: [
        {
          label: "Page Visits",
          data,
          backgroundColor: colors,
        },
      ],
    };
  };

  // Generates chart data for session counts
  const generateSessionChartData = (sessionData) => {
    const labels = generateLast12Months();
    const data = labels.map((month) => sessionData[month] || 0);

    return {
      labels,
      datasets: [
        {
          label: "Sessions per Month",
          backgroundColor: "#2b5797",
          data,
        },
      ],
    };
  };

  // Generates an array of the last 12 months
  const generateLast12Months = () => {
    const currentDate = new Date();
    return Array.from({ length: 12 }, (_, i) => {
      const date = new Date(
        currentDate.getFullYear(),
        currentDate.getMonth() - i
      );
      return `${date.getMonth() + 1}/${date.getFullYear()}`;
    }).reverse();
  };

  return (
    <div className="unified-dashboard">
      {isLoading ? (
        <div className="loading-placeholder">Loading data...</div>
      ) : (
        <div className="dashboard-grid">
          <div className="dashboard-item" onClick={() => navigate("/devices")}>
            <p>Devices</p>
            {deviceData ? (
              <ChartComponent
                chartType="bar"
                data={deviceData}
                options={{
                  responsive: true,
                  maintainAspectRatio: false,
                  animation: false, // Disable animation
                  scales: {
                    x: { ticks: { autoSkip: false } },
                  },
                }}
              />
            ) : (
              <div className="skeleton-chart"></div>
            )}
          </div>

          <div
            className="dashboard-item"
            onClick={() => navigate("/marketing")}
          >
            <p>Marketing Source</p>
            {marketingData ? (
              <ChartComponent
                chartType="bar"
                data={marketingData}
                options={{
                  responsive: true,
                  maintainAspectRatio: false,
                  animation: false, // Disable animation
                  scales: {
                    x: { ticks: { autoSkip: false } },
                  },
                }}
              />
            ) : (
              <div className="skeleton-chart"></div>
            )}
          </div>

          <div className="dashboard-item" onClick={() => navigate("/pages")}>
            <p>Categories</p>
            {visitData ? (
              <ChartComponent
                chartType="pie"
                data={visitData}
                options={{
                  responsive: true,
                  maintainAspectRatio: false,
                  animation: false, // Disable animation
                }}
              />
            ) : (
              <div className="skeleton-chart"></div>
            )}
          </div>

          <div className="dashboard-item" onClick={() => navigate("/sessions")}>
            <p>Amount of Sessions</p>
            {sessionData ? (
              <ChartComponent
                chartType="bar"
                data={sessionData}
                options={{
                  responsive: true,
                  maintainAspectRatio: false,
                  animation: false, // Disable animation
                  scales: {
                    x: { ticks: { autoSkip: false } },
                  },
                }}
              />
            ) : (
              <div className="skeleton-chart"></div>
            )}
          </div>

          <div className="dashboard-item map-container">
            <p>Location Visits</p>
            <WorldMap countryValues={countryValues} />
          </div>
        </div>
      )}
    </div>
  );
};

export default UnifiedDashboard;