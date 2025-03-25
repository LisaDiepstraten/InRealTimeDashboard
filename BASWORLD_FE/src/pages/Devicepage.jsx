import React, { useState, useEffect } from 'react';
import APICalls from '../services/APICalls';
import ChartComponent from '../components/ChartComponent';
import '../components/ChartComponent.css';

function Devicepage() {
    const [chartData, setChartData] = useState(null);

    useEffect(() => {
        const fetchDeviceData = async () => {
            try {
                const response = await APICalls.getDevicesCount();
                setChartData(generateChartData(response));
            } catch (error) {
                console.error("Error fetching device data", error);
            }
        };
        fetchDeviceData();
    }, []);

    const generateChartData = (devicesCount) => {
        const currentDate = new Date();
    
        // Generate the last 12 months as "M/YYYY" (no leading zero on month)
        const last12Months = Array.from({ length: 12 }, (_, i) => {
            const date = new Date(currentDate.getFullYear(), currentDate.getMonth() - i);
            return `${date.getMonth() + 1}/${date.getFullYear()}`;
        }).reverse();
    
    
        const deviceTypes = ["Desktop", "Mobile", "Tablet"];
        const deviceTypeMap = {
            desktop: "Desktop",
            mobile: "Mobile",
            tablet: "Tablet"
        };
    
        // Initialize sessions per device type for each month in last12Months
        const sessionsPerDeviceType = deviceTypes.map((deviceType) => {
            const lowerCaseDeviceType = deviceType.toLowerCase();
            return last12Months.map((month) => {
                const count = devicesCount[lowerCaseDeviceType]?.[month] || 0;
                return count;
            });
        });
    
        // Construct the dataset for the chart
        const stackedBarDataDevice = {
            labels: last12Months,
            datasets: deviceTypes.map((type, i) => ({
                label: type,
                backgroundColor: ["#4CAF50", "#FF5722", "#3F51B5"][i % 3],
                data: sessionsPerDeviceType[i]
            }))
        };
    
    
        return {
            stackedBarDataDevice
        };
    };
    
    
        
    

    if (!chartData) return <p>Loading...</p>;

    return (
        <div className="Devicepage">
            <section className="hero-section text-center p-3 bg-light">
                <div className="container">
                    <h1>Device Dashboard</h1>
                </div>
            </section>

            <div className="content-container container">
                <section className="charts-section">
                    <h2>Sessions per Device Type</h2>
                    <div className="chart-container">
                        <ChartComponent
                            chartType="bar"
                            data={chartData.stackedBarDataDevice}
                            options={{
                                responsive: true,
                                maintainAspectRatio: false,
                                title: { display: true, text: "Sessions per Device Type per Month (Last 12 Months)" },
                                legend: {
                                    display: true,
                                    onClick: (e, legendItem) => {
                                        const index = legendItem.datasetIndex;
                                        const ci = e.chart;
                                        const meta = ci.getDatasetMeta(index);
                                        meta.hidden = meta.hidden === null ? !ci.data.datasets[index].hidden : null;
                                        ci.update();
                                    }
                                },
                                scales: {
                                    yAxes: [{ ticks: { beginAtZero: true }, stacked: true }],
                                    xAxes: [{ ticks: { fontSize: 12 }, stacked: true, barPercentage: 0.6, categoryPercentage: 0.7 }]
                                }
                            }}
                        />
                    </div>
                </section>
            </div>
        </div>
    );
}

export default Devicepage;
