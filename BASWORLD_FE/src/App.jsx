import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import './App.css';
import Navbar from './components/NavBar';
import Dashboardpage from './pages/Dashboardpage';
import Devicepage from './pages/Devicepage';
import LocationPage from './pages/LocationPage';
import MarketingPage from './pages/MarketingPage';
import PagesVisitPage from './pages/PagesVisitPage';
import SendEventsPage from './pages/SendEventsPage';
import Sessionspage from './pages/Sessionspage';
import WorldMap from './pages/WorldMap';
import UnifiedDashboard from './pages/UnifiedDashboard';
import SlideShowpage from './pages/SlideShowpage';

const App = () => {
  return (
    <div className="App">
      <Router>
        <Navbar />  {/* Navbar appears on all pages */}
        <main className="main-content">
          <Routes>
            <Route path="/" element={<Dashboardpage />} />
            <Route path="/dashboard" element={<Dashboardpage />} />
            <Route path="/sessions" element={<Sessionspage />} />
            <Route path="/devices" element={<Devicepage />} />
            <Route path="/marketing" element={<MarketingPage />} />
            <Route path="/pages" element={<PagesVisitPage />} />
            <Route path="/send-event" element={<SendEventsPage />} />
            <Route path="/locations" element={<LocationPage />} />
            <Route path="/world" element={<WorldMap />} />
            <Route path="/unified" element={<UnifiedDashboard />} />
            <Route path="/slideshow" element={<SlideShowpage />} />
          </Routes>
        </main>
      </Router>
    </div>
  );
};

export default App;
