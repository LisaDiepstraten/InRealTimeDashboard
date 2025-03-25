import React, { useState, useEffect } from 'react';
import APICalls from '../services/APICalls';
import ChartComponent from '../components/ChartComponent';
import '../components/ChartComponent.css';

function Sessionspage() {
    const [chartData, setChartData] = useState(null);

    useEffect(() => {
        const fetchEvents = async () => {
            try {
                const response = await APICalls.getEventsCount(); // Updated API call
                setChartData(generateChartData(response));
            } catch (error) {
                console.error("Error fetching events", error);
            }
        };
        fetchEvents();
    }, []);

    const generateChartData = (eventData) => {
        const currentDate = new Date();

        // Generate the last 12 months as "M/YYYY" (no leading zero on month)
        const last12Months = Array.from({ length: 12 }, (_, i) => {
            const date = new Date(currentDate.getFullYear(), currentDate.getMonth() - i);
            return `${date.getMonth() + 1}/${date.getFullYear()}`;
        }).reverse();

        // Initialize an array to hold the event counts per month
        const sessionsPerMonth = Array(12).fill(0);

        // Iterate through the Map and aggregate counts per month
        Object.keys(eventData).forEach(eventMonth => {
            const count = eventData[eventMonth]; // Get the count for this month
            const monthIndex = last12Months.indexOf(eventMonth);

            if (monthIndex !== -1) {
                sessionsPerMonth[monthIndex] += count; // Aggregate count into the correct month
            }
        });

        // Construct the dataset for the bar chart
        const barDataSessionsPerMonth = {
            labels: last12Months,
            datasets: [{
                label: "Sessions per Month",
                backgroundColor: "#2b5797",
                data: sessionsPerMonth
            }]
        };

        return {
            barDataSessionsPerMonth,
        };
    };

    if (!chartData) return <p>Loading...</p>;

    return (
        <div className="Sessionspage">
            <section className="hero-section text-center p-3 bg-light">
                <div className="container">
                    <h1>Sessions Dashboard</h1>
                </div>
            </section>

            <div className="content-container container">
                <section className="charts-section">
                    <h2>Sessions Overview</h2>
                    <div className="chart-container">
                        <ChartComponent
                            chartType="bar"
                            data={chartData.barDataSessionsPerMonth}
                            options={{
                                responsive: true,
                                maintainAspectRatio: false,
                                title: { display: true, text: "Sessions per Month (Last 12 Months)" },
                                scales: {
                                    y: {
                                        ticks: {
                                            beginAtZero: true
                                        }
                                    },
                                    x: {
                                        ticks: {
                                            fontSize: 12
                                        },
                                        barPercentage: 0.6,
                                        categoryPercentage: 0.7
                                    }
                                }
                            }}
                        />
                    </div>
                </section>
            </div>
        </div>
    );
}

export default Sessionspage;
