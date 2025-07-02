import React, { useEffect } from 'react';
import Navbar from '../Components/LandingPage/Navbar';
import HeroSection from '../Components/LandingPage/Herosection';
import FeatureCards from '../Components/LandingPage/Features';
import DSARoadmap from '../Components/LandingPage/DSARoadmap';
import Footer from '../Components/LandingPage/Footer';
import TeamSection from '../Components/LandingPage/cards';


function LandingPage() {
  useEffect(() => {
 
    document.title = 'CodeJudge - Practice. Solve. Grow.';
    
   
    const defaultTitle = document.querySelector('[data-default]');
    if (defaultTitle) {
      defaultTitle.textContent = 'CodeJudge - Practice. Solve. Grow.';
    }
  }, []);

  return (
    <div className="min-h-screen flex flex-col">
      <Navbar />
      <main className="flex-grow">
        <HeroSection/>
        <FeatureCards/>
        <DSARoadmap/>
        <TeamSection/>
        <Footer/>

        
      </main>
      
    </div>
  );
}

export default LandingPage;
