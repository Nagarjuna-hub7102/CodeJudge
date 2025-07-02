import React from 'react';
import { Search } from 'lucide-react';
import {categories,difficulties} from '../../data/categories';




const FilterBar = ({ 
  selectedCategory, 
  setSelectedCategory, 
  selectedDifficulty, 
  setSelectedDifficulty,
  searchQuery,
  setSearchQuery
}) => {
  return (
    <div className="bg-white p-4 rounded-lg shadow-sm mb-6 border border-gray-100">
      <div className="mb-4">
        <div className="relative">
          <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
            <Search className="h-5 w-5 text-gray-400" />
          </div>
          <input
            type="text"
            placeholder="Search problems..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            className="pl-10 block w-full rounded-md border-gray-300 bg-gray-50 py-2 text-gray-900 focus:border-orange-500 focus:ring-orange-500 shadow-sm"
          />
        </div>
      </div>




      <div className="flex flex-col space-y-4">
        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-2">Categories</h3>
          <div className="flex flex-wrap gap-2">
            {categories.map((category) => (
              <button
                key={category}

                onClick={() => setSelectedCategory(category === 'All' ? null : category)}
                className={`px-3 py-1 rounded-full text-sm ${
                  (category === 'All' && !selectedCategory) || selectedCategory === category
                    ? 'bg-gray-800 text-white'
                    : 'bg-gray-100 text-gray-800 hover:bg-gray-200'
                }`}


              >
                {category}
              </button>
            ))}
          </div>
        </div>

        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-2">Difficulty</h3>
          <div className="flex flex-wrap gap-2">
            {difficulties.map((difficulty) => (

              <button
                key={difficulty.name}
                onClick={() => setSelectedDifficulty(
                  selectedDifficulty === difficulty.name ? null : difficulty.name
                )}

                className={`px-3 py-1 rounded-full text-sm ${
                  selectedDifficulty === difficulty.name
                    ? `bg-${difficulty.color}-500 text-white`
                    : `bg-${difficulty.color}-100 text-${difficulty.color}-800 hover:bg-${difficulty.color}-200`
                }`}
              >
                
                {difficulty.name}
              </button>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default FilterBar;