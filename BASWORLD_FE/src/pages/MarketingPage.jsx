import React, { useState, useEffect } from 'react';
import APICalls from '../services/APICalls';
import ChartComponent from '../components/ChartComponent';
import '../components/ChartComponent.css';

function MarketingPage() {
    const [chartData, setChartData] = useState(null);

    useEffect(() => {
        const fetchMarketingData = async () => {
            try {
                const response = await APICalls.getMarketingCount();
                setChartData(generateChartData(response));
            } catch (error) {
                console.error("Error fetching marketing data", error);
            }
        };
        fetchMarketingData();
    }, []);

    const generateChartData = (marketingData) => {
        const currentDate = new Date();

        // Generate last 12 months in "M/YYYY" format
        const last12Months = Array.from({ length: 12 }, (_, i) => {
            const date = new Date(currentDate.getFullYear(), currentDate.getMonth() - i);
            return `${date.getMonth() + 1}/${date.getFullYear()}`;  // Format as M/YYYY
        }).reverse();


        // Static marketing sources
        const marketingSources = ["google", "facebook", "instagram", "others"];
        
        // Initialize sessions count for each marketing source for every month
        const sessionsPerMarketingSource = marketingSources.map(() => Array(12).fill(0));

        // Loop through marketing data to populate sessions count
        Object.keys(marketingData).forEach(source => {
            // Convert the source to lowercase to ensure we match the source correctly
            const sourceData = marketingData[source];

            last12Months.forEach((month, monthIndex) => {
                // Check if the month exists in the source's data
                if (sourceData[month]) {
                    const sourceIndex = marketingSources.indexOf(source);
                    if (sourceIndex !== -1) {
                        sessionsPerMarketingSource[sourceIndex][monthIndex] = sourceData[month];
                    }
                }
            });
        });

        // Prepare chart data
        const stackedBarDataMarketing = {
            labels: last12Months,
            datasets: marketingSources.map((source, i) => ({
                label: source.charAt(0).toUpperCase() + source.slice(1),  // Capitalize first letter for the label
                backgroundColor: ["#2b5797", "#FF5733", "#28A745", "#FFD700"][i],
                data: sessionsPerMarketingSource[i]
            }))
        };


        return {
            stackedBarDataMarketing
        };
    };

    if (!chartData) return <p>Loading...</p>;

    return (
        <div className="MarketingPage">
            <section className="hero-section text-center p-3 bg-light">
                <div className="container">
                    <h1>Marketing Dashboard</h1>
                </div>
            </section>

            <div className="content-container container">
                <section className="charts-section">
                    <h2>Marketing Sessions per Source</h2>
                    <div className="chart-container">
                        <ChartComponent
                            chartType="bar"
                            data={chartData.stackedBarDataMarketing}
                            options={{
                                responsive: true,
                                maintainAspectRatio: false,
                                title: { display: true, text: "Sessions per Marketing Source per Month (Last 12 Months)" },
                                legend: {
                                    display: true,
                                    onClick: (e, legendItem) => {
                                        const index = legendItem.datasetIndex;
                                        const chart = e.chart;

                                        // Toggle visibility of dataset
                                        chart.data.datasets[index].hidden = !chart.data.datasets[index].hidden;
                                        chart.update();
                                    }
                                },
                                tooltips: {
                                    mode: 'index',
                                    intersect: false
                                },
                                hover: {
                                    mode: 'nearest',
                                    intersect: true
                                },
                                scales: {
                                    yAxes: [
                                        {
                                            ticks: {
                                                beginAtZero: true
                                            },
                                            stacked: true
                                        }
                                    ],
                                    xAxes: [
                                        {
                                            ticks: {
                                                fontSize: 12
                                            },
                                            stacked: true,
                                            barPercentage: 0.6,
                                            categoryPercentage: 0.7
                                        }
                                    ]
                                }
                            }}
                        />
                    </div>
                </section>
            </div>
        </div>
    );
}

export default MarketingPage;
