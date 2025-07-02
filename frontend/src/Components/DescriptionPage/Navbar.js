import React from 'react';
import { Code } from 'lucide-react';
import { Link } from 'react-router-dom'; 

const Navbar = () => {
  return (
    <nav className="bg-white shadow-sm border-b border-gray-200">
      <div className="max-w-6xl mx-auto px-4">
        <div className="flex justify-between h-16">
          <div className="flex items-center">
            <div className="flex-shrink-0 flex items-center">
              <Code className="h-8 w-8 text-orange-500" />
              <span className="ml-2 text-xl font-bold text-gray-800">CodeJudge</span>
            </div>
            <div className="hidden sm:ml-6 sm:flex sm:space-x-8">
              <Link 
                to="/" 
                className="border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium"
              >
                Home
              </Link>
              <Link 
                to="/problems" 
                className="border-orange-500 text-gray-900 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium"
              >
                Problems
              </Link>
               <Link 
                to="/submissions" 
                className="border-orange-500 text-gray-900 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium"
              >
                Submissions
              </Link>
             
            </div>
          </div>
          <div className="flex items-center">
            <button className="px-4 py-2 rounded-md text-sm font-medium text-white bg-orange-500 hover:bg-orange-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-orange-500 transition-colors">
              <Link to="/login">Sign In </Link>
            </button>
          </div>
        </div>
      </div>
      
   
      <div className="sm:hidden" id="mobile-menu">
        <div className="pt-2 pb-3 space-y-1">
          <Link 
            to="/" 
            className="text-gray-500 hover:bg-gray-50 hover:text-gray-700 block pl-3 pr-4 py-2 text-base font-medium"
          >
            Home
          </Link>
          <Link 
            to="/problems" 
            className="bg-orange-50 border-l-4 border-orange-500 text-orange-700 block pl-3 pr-4 py-2 text-base font-medium"
          >
            Problems
          </Link>
          <Link 
            to="/leaderboard" 
            className="text-gray-500 hover:bg-gray-50 hover:text-gray-700 block pl-3 pr-4 py-2 text-base font-medium"
          >
            Leaderboard
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
