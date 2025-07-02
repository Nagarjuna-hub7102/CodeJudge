import React from 'react';

const HeroSection = () => {
  return (
    <section className="bg-white py-16">
      <div className="max-w-6xl mx-auto px-4">
        <div className="flex flex-col-reverse md:flex-row items-center">
          
          <div className="md:w-1/2 mt-10 md:mt-0">
            <h1 className="text-4xl md:text-5xl font-bold text-slate-800 leading-tight">
              Practice. Solve. Grow.
            </h1>
            <p className="mt-4 text-xl text-slate-600 max-w-lg">
              "Every developer starts by solving problems they thought they couldn't."
            </p>
            <div className="mt-8">
              <a 
                href="/problems" 
                className="bg-orange-500 text-white px-4 py-2 rounded hover:bg-orange-600 transition-colors"
              >
                Start Solving
              </a>
            </div>
          </div>

         
          <div className="md:w-1/2 flex justify-center">
            <div className="bg-slate-50 p-6 rounded-lg border border-slate-200">
              <div className="flex items-center mb-4">
                <div className="w-3 h-3 bg-red-500 rounded-full mr-2"></div>
                <div className="w-3 h-3 bg-yellow-500 rounded-full mr-2"></div>
                <div className="w-3 h-3 bg-green-500 rounded-full"></div>
              </div>
              <pre className="text-sm text-slate-700 font-mono whitespace-pre-wrap">
{`function findMax(arr) {
  if (arr.length === 0) {
    return null;
  }

  let max = arr[0];

  for (let i = 1; i < arr.length; i++) {
    if (arr[i] > max) {
      max = arr[i];
    }
  }

  return max;
}

// Time Complexity: O(n)
// Space Complexity: O(1)`}
              </pre>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default HeroSection;
