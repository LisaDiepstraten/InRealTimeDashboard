import React, { useState, useEffect } from "react";
import PageOne from "./Devicepage";
import PageTwo from "./MarketingPage";
import PageThree from "./PagesVisitPage";
import PageFour from "./WorldMap";
import PageFive from "./Sessionspage";
import PageSix from "./UnifiedDashboard";
import './SlideShow.css';

const SlideShowpage = () => {
  // Array of real page components
  const pages = [<PageOne />, <PageTwo />, <PageThree />, <PageFour />, <PageFive />, <PageSix />];

  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1 === pages.length ? 0 : prevIndex + 1));
 }, 5000);

    return () => clearInterval(timer); // Cleanup the timer when the component unmounts
  }, [pages.length]);


  return (
    <div className="backgroundcolorslideshow">
    <div style={{ position: "relative", overflow: "hidden", height: "92vh", width: "95vw" }}>
      {pages.map((page, index) => (
        <div
          key={index}
          style={{
            position: "absolute",
            top: 0,
            left: 0,
            transform: `translateX(${(index - currentIndex) * 100}%)`, // Slide pages horizontally
            width: "100%", 
            height: "100%", 
            transition: "transform 1s ease-in-out", 
            display: "flex",
            justifyContent: "center", 
            alignItems: "center", 
            background: "linear-gradient(to right, white, #1c8a34)", 
          }}
        >
          <div
            style={{
              width: "80%", 
              height: "90%", 
          
            }}
          >
            {page}
          </div>
        </div>
      ))}
    </div>
    </div>
  );
};


export default SlideShowpage;
