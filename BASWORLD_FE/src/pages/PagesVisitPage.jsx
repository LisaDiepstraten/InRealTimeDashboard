import React, { useEffect, useState } from 'react';
import APICalls from '../services/APICalls';
import ChartComponent from '../components/ChartComponent';
import '../components/ChartComponent.css';

const VisitPage = () => {
    const [pieChartData, setPieChartData] = useState(null);

    useEffect(() => {
        const fetchMostVisitedCategories = async () => {
            try {
                const response = await APICalls.getMostVisitedCategories(); // Using new API
                setPieChartData(generatePieChartData(response));
            } catch (error) {
                console.error("Error fetching most visited categories", error);
            }
        };
        fetchMostVisitedCategories();
    }, []);

    const generatePieChartData = (eventData) => {
        const labels = Object.keys(eventData);
        const data = Object.values(eventData);

        // Define an expanded set of distinct colors for the categories
        const backgroundColors = [
            "#2E86C1", "#F4D03F", "#1ABC9C", "#E67E22", 
            "#8E44AD", "#3498DB", "#FF6384", "#2ECC71", "#C0392B"
        ];

        // Ensure we have enough colors by repeating the color array if needed
        const extendedColors = [...backgroundColors];
        while (extendedColors.length < labels.length) {
            extendedColors.push(...backgroundColors);
        }

        return {
            labels: labels,
            datasets: [
                {
                    label: 'Page Visits',
                    data: data,
                    backgroundColor: extendedColors.slice(0, labels.length),
                }
            ],
        };
    };

    if (!pieChartData) return <p>Loading...</p>;

    // Options for the pie chart
    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'right', // Place legends to the right of the chart
                labels: {
                    boxWidth: 20, // Customize legend box size
                    padding: 15,  // Add spacing between legend items
                    font: {
                        size: 30 // Adjust font size
                    }
                }
            },
            tooltip: {
                callbacks: {
                    label: function (tooltipItem) {
                        const category = tooltipItem.label;
                        const count = tooltipItem.raw;
                        return `${category}: ${count} visits`;
                    }
                }
            }
        },
        layout: {
            padding: {
                right: 20 // Add padding to ensure space for legends
            }
        }
    };

    

    return (
        <div className="VisitPage">
            <section className="hero-section text-center p-3 bg-light">
                <div className="container">
                    <h1>Event Type Dashboard</h1>
                </div>
            </section>

            <div className="content-container container">
                <section className="charts-section">
                    <h2>Most Visited Categories</h2>
                    <div className="chart-container" style={{ display: 'flex', justifyContent: 'center' }}>
                        {/* Chart and legend side-by-side */}
                        <ChartComponent
                            chartType="pie"
                            data={pieChartData}
                            options={options}
                        />
                    </div>
                </section>
            </div>
        </div>
    );
};

 export default VisitPage;


