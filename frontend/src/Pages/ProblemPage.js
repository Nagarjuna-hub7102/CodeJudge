import React, { useState, useEffect } from 'react';
import Navbar from '../Components/ProblemPage/Navbar';
import FilterBar from '../Components/ProblemPage/FilterBar';
import ProblemsTable from '../Components/ProblemPage/ProblemsTable';
import problems from '../data/problems'
const ProblemsPage = () => {

  
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [selectedDifficulty, setSelectedDifficulty] = useState(null);
  const [searchQuery, setSearchQuery] = useState('');
  const [filteredProblems, setFilteredProblems] = useState(problems);

  
  useEffect(() => {
    let result = [...problems];
    
 
    if (selectedCategory) {
      result = result.filter(problem => problem.category === selectedCategory);
    }
    
    
    if (selectedDifficulty) {
      result = result.filter(problem => problem.difficulty === selectedDifficulty);
    }
    
    
    if (searchQuery) {
      const lowercaseQuery = searchQuery.toLowerCase();
      result = result.filter(problem => 
        problem.name.toLowerCase().includes(lowercaseQuery) || 
        problem.category.toLowerCase().includes(lowercaseQuery)
      );
    }
    
    setFilteredProblems(result);
  }, [selectedCategory, selectedDifficulty, searchQuery]);

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      
      <main className="max-w-6xl mx-auto px-4 py-8">
        <div className="mb-6">
          <h1 className="text-2xl font-bold text-gray-900">Coding Problems</h1>
          <p className="text-gray-600 mt-1">Practice your coding skills with these challenges</p>
        </div>
        
        <FilterBar 
          selectedCategory={selectedCategory}
          setSelectedCategory={setSelectedCategory}
          selectedDifficulty={selectedDifficulty}
          setSelectedDifficulty={setSelectedDifficulty}
          searchQuery={searchQuery}
          setSearchQuery={setSearchQuery}
        />
        
        <div className="mb-4 flex justify-between items-center">
          <div className="text-sm text-gray-600">
            Showing {filteredProblems.length} of {problems.length} problems
          </div>
          
          <div className="flex space-x-2">
            <button className="px-3 py-1 text-sm bg-white border border-gray-300 rounded-md hover:bg-gray-50">
              Newest
            </button>
            <button className="px-3 py-1 text-sm bg-white border border-gray-300 rounded-md hover:bg-gray-50">
              Most Solved
            </button>
          </div>
        </div>
        
        <ProblemsTable 
          problems={problems} 
          filteredProblems={filteredProblems} 
        />
      </main>
    </div>
  );
};

export default ProblemsPage;