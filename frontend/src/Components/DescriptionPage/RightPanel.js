import React, { useState } from 'react';

const RightPanel = () => {
  const [code, setCode] = useState(`function twoSum(nums, target) {
    // Write your solution here
    
}`);
  const [testInput, setTestInput] = useState('[2,7,11,15]\n9');
  const [output, setOutput] = useState('');
  const [isRunning, setIsRunning] = useState(false);

  const handleRunCode = () => {
    setIsRunning(true);
  

    setTimeout(() => {
      setOutput('Output: [0,1]\nExpected: [0,1]\n✅ Test case passed!');
      setIsRunning(false);
    }, 1500);
  };

  const handleSubmit = () => {
    setIsRunning(true);
    setTimeout(() => {
      setOutput('Running all test cases...\n✅ All test cases passed!\n🎉 Solution accepted!');
      setIsRunning(false);
    }, 2000);
  };

  return (
    <div className="md:w-1/2 space-y-4">
      

      <div>
        <label className="block text-slate-800 font-medium mb-2">
          Your Code
        </label>
        <textarea
          value={code}
          onChange={(e) => setCode(e.target.value)}
          className="w-full h-64 bg-slate-100 p-4 rounded border border-slate-300 font-mono text-sm resize-none focus:outline-none focus:ring-2 focus:ring-orange-500 focus:border-transparent"
          placeholder="Write your solution here..."
        />
      </div>

      
      <div>
        <label className="block text-slate-800 font-medium mb-2">
          Test Input
        </label>
        <textarea
          value={testInput}
          onChange={(e) => setTestInput(e.target.value)}
          className="w-full h-20 bg-white p-3 rounded border border-slate-300 font-mono text-sm resize-none focus:outline-none focus:ring-2 focus:ring-orange-500 focus:border-transparent"
          placeholder="Enter test input..."
        />
      </div>

     
      <div className="flex gap-4 pt-2">
        <button
          onClick={handleRunCode}
          disabled={isRunning}
          className="bg-slate-600 text-white px-6 py-2 rounded hover:bg-slate-700 disabled:opacity-50 disabled:cursor-not-allowed font-medium"
        >
          {isRunning ? 'Running...' : 'Run Code'}
        </button>
        <button
          onClick={handleSubmit}
          disabled={isRunning}
          className="bg-orange-500 text-white px-6 py-2 rounded hover:bg-orange-600 disabled:opacity-50 disabled:cursor-not-allowed font-medium"
        >
          {isRunning ? 'Submitting...' : 'Submit'}
        </button>
      </div>

  
      {output && (
        <div>
          <label className="block text-slate-800 font-medium mb-2">
            Output
          </label>
          <div className="bg-slate-50 border p-3 rounded">
            <pre className="font-mono text-sm text-slate-700 whitespace-pre-wrap">
              {output}
            </pre>
          </div>
        </div>
      )}
    </div>
  );
};

export default RightPanel;