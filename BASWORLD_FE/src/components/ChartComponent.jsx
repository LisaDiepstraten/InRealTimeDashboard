import Chart from 'chart.js/auto';
import React, { useEffect, useRef } from 'react';
import './ChartComponent.css';

const ChartComponent = ({ chartType, data, options }) => {
    const chartRef = useRef(null);
    const chartInstanceRef = useRef(null);

    // Function to calculate a linear trendline
    const calculateTrendline = (dataPoints) => {
        const n = dataPoints.length;
        const xValues = dataPoints.map((_, index) => index);
        const yValues = dataPoints;

        const sumX = xValues.reduce((acc, x) => acc + x, 0);
        const sumY = yValues.reduce((acc, y) => acc + y, 0);
        const sumXY = xValues.reduce((acc, x, index) => acc + x * yValues[index], 0);
        const sumX2 = xValues.reduce((acc, x) => acc + x * x, 0);

        const slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        const intercept = (sumY - slope * sumX) / n;

        return xValues.map((x) => slope * x + intercept);
    };

    useEffect(() => {
        if (!chartRef.current) return; // Ensure the canvas exists

        const ctx = chartRef.current.getContext('2d');

        // Destroy existing chart instance if it exists
        if (chartInstanceRef.current) {
            chartInstanceRef.current.destroy();
        }

        // Prepare updated data
        let updatedData = { ...data };

        // Only add trendline if it's not a pie chart
        if (chartType !== 'pie' && data?.datasets?.length > 0) {
            const mainDataset = data.datasets[0];
            if (mainDataset?.data?.length > 0) {
                const trendlineData = calculateTrendline(mainDataset.data);
                updatedData = {
                    ...data,
                    datasets: [
                        ...data.datasets,
                        {
                            label: 'Trendline',
                            data: trendlineData,
                            borderColor: '#FF5733', // A bright, noticeable red-orange color
                            borderWidth: 3, // Thicker line for more visibility
                            fill: false, // No fill under the line
                            pointRadius: 3, // Small circles at data points
                            pointBackgroundColor: '#FF5733', // Color of the points
                            pointHoverRadius: 5, // Larger point size on hover
                            tension: 0.4, // Smooth the line
                            type: 'line', // Ensure it's treated as a line chart
                            cubicInterpolationMode: 'monotone', // Smooth interpolation
                            lineTension: 0.4, // Ensures the line is smooth
                        },
                    ],
                };
            }
        }

        // Create new chart instance
        chartInstanceRef.current = new Chart(ctx, {
            type: chartType,
            data: updatedData, // Use the updated data
            options: {
                ...options,
                responsive: true,
                maintainAspectRatio: false,
                scales: chartType !== 'pie' ? {
                    y: {
                        beginAtZero: true, // Ensure the y-axis starts from zero
                        ticks: {
                            stepSize: 10, // Adjusts the spacing between ticks
                            font: {
                                size: 12, // Size of the tick labels
                            },
                        },
                    },
                } : undefined, // No scales for pie chart
                elements: {
                    line: {
                        tension: 0.4, // Line smoothing
                    },
                    point: {
                        radius: 5, // Larger points for better visibility
                    },
                },
                tooltips: {
                    enabled: true,
                    mode: 'nearest',
                    intersect: false,
                    callbacks: {
                        title: (tooltipItems) => `Trendline at x: ${tooltipItems[0].xLabel}`,
                        label: (tooltipItem) => `y: ${tooltipItem.raw.toFixed(2)}`,
                    },
                },
                legend: {
                    labels: {
                        font: {
                            size: 14, // Font size for the legend
                        },
                    },
                },
            },
        });

        // Cleanup on unmount
        return () => {
            if (chartInstanceRef.current) {
                chartInstanceRef.current.destroy();
                chartInstanceRef.current = null; // Reset the reference
            }
        };
    }, [chartType, data, options]);

    return <canvas ref={chartRef} className="chart-canvas" />;
};

export default ChartComponent;
