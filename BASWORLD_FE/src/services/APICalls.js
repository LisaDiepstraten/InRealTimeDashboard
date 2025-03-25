
import axios from 'axios';

const APICalls = {
    getallevents: async () => {
        try {
            const response = await axios.get('http://localhost:8080/events');
            return response.data;
        } catch (error) {
            console.error("Error fetching events", error);
            throw error;
        }
    },

    postEvent: async (event) => {
        try {
            const response = await axios.post('http://localhost:8080/events', event);
            return response.data;
        } catch (error) {
            console.error("Error posting event", error);
            throw error;
        }
    },


    getEventLogsProducts: async () => {
        try {
            const response = await axios.get('http://localhost:8080/events/logs/products');
            return response.data;
        } catch (error) {
            console.error("Error fetching events", error);
            throw error;
        }
    },

    getMostVisitedCategories: async () => {
        try {
            const response = await axios.get('http://localhost:8080/events/count/products');
            return response.data;
        } catch (error) {
            console.error("Error fetching events", error);
            throw error;
        }
    },

    getEventsCount: async () => {
        try {
            const response = await axios.get('http://localhost:8080/events/count');
            return response.data;
        } catch (error) {
            console.error("Error fetching events", error);
            throw error;
        }
    },

    getEventTypeCount: async () => {
        try {
            const response = await axios.get('http://localhost:8080/events/count/event-type');
            return response.data;
        } catch (error) {
            console.error("Error fetching events", error);
            throw error;
        }
    },

    getCountryCount: async () => {
        try {
            const response = await axios.get('http://localhost:8080/events/count/country');
            return response.data;
        } catch (error) {
            console.error("Error fetching events", error);
            throw error;
        }
    },

    getDevicesCount: async () => {
        try {
            const response = await axios.get('http://localhost:8080/events/count/device');
            return response.data;
        } catch (error) {
            console.error("Error fetching events", error);
            throw error;
        }
    },

    getMarketingCount: async () => {
        try {
            const response = await axios.get('http://localhost:8080/events/count/marketing');
            return response.data;
        } catch (error) {
            console.error("Error fetching events", error);
            throw error;
        }
    },

};

export default APICalls; 
