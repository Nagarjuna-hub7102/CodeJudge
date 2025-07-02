import React from 'react';
import { problemData } from '../../data/problems';

const LeftPanel = () => {
  const difficultyClass = (difficulty) => {
    switch (difficulty.toLowerCase()) {
      case 'easy':
        return 'bg-green-100 text-green-800';
      case 'medium':
        return 'bg-yellow-100 text-yellow-800';
      case 'hard':
        return 'bg-red-100 text-red-800';
      default:
        return 'bg-gray-100 text-gray-800';
    }
  };

 

  return (
    <div className="md:w-1/2 overflow-y-auto max-h-[80vh]">
      <div className="space-y-6">
        
        <div>
          <div className="text-orange-500 text-sm font-medium mb-1">
            {problemData.category}
          </div>
          <h1 className="text-2xl font-semibold text-slate-800 mb-2">
            {problemData.title}
          </h1>
          <span className={`inline-block px-3 py-1 rounded-full text-xs font-medium ${difficultyClass(problemData.difficulty)}`}>
            {problemData.difficulty}
          </span>
        </div>

       
        <div>
          <h2 className="text-lg font-semibold text-slate-800 mb-3">Description</h2>
          <p className="text-slate-700 leading-relaxed">
            {problemData.description}
          </p>
        </div>

     
        <div>
          <h2 className="text-lg font-semibold text-slate-800 mb-3">Input Format</h2>
          <p className="text-slate-700 leading-relaxed">
            {problemData.inputFormat}
          </p>
        </div>

       
        <div>
          <h2 className="text-lg font-semibold text-slate-800 mb-3">Output Format</h2>
          <p className="text-slate-700 leading-relaxed">
            {problemData.outputFormat}
          </p>
        </div>



        <div>
          <h2 className="text-lg font-semibold text-slate-800 mb-3">Constraints</h2>
          <ul className="space-y-2">
            {problemData.constraints.map((constraint, index) => (
              <li key={index} className="text-slate-700 font-mono text-sm">
                • {constraint}
              </li>
            ))}
          </ul>
        </div>

        <div>
          <h2 className="text-lg font-semibold text-slate-800 mb-3">Examples</h2>
          <div className="space-y-4">
            {problemData.examples.map((example, index) => (
              <div key={index} className="bg-slate-50 p-4 rounded border">
                <div className="font-semibold text-slate-800 mb-2">
                  Example {index + 1}:
                </div>
                <div className="space-y-2 text-sm">
                  <div>
                    <span className="font-medium text-slate-700">Input: </span>
                    <code className="font-mono bg-white px-2 py-1 rounded border">
                      {example.input}
                    </code>
                  </div>
                  <div>
                    <span className="font-medium text-slate-700">Output: </span>
                    <code className="font-mono bg-white px-2 py-1 rounded border">
                      {example.output}
                    </code>
                  </div>
                  <div>
                    <span className="font-medium text-slate-700">Explanation: </span>
                    <span className="text-slate-600">{example.explanation}</span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default LeftPanel;