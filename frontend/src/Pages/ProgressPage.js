import React from 'react'
import {progresses} from '../data/problems'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ArrowLeft } from 'lucide-react';

const ProgressPage = () => {
 const [selectedProblem,setSelectedProblem] = useState(null);

  const navigate = useNavigate();


 
  const openModal = (progress) => {
    setSelectedProblem(progress);
  };



  const closeModal = () => {
    setSelectedProblem(null);
  };


  

  return (
    <div className="min-h-screen bg-white">
      <div className="container mx-auto px-4 py-8">
        <div className="mb-8">
 <button
  onClick={() => navigate(-1)}
  className="text-orange-500 hover:text-orange-600 text-sm font-medium flex items-center mb-4"
>
  <ArrowLeft className="h-5 w-5 mr-1" />
  Back
</button>

  
  <h1 className="text-3xl font-bold text-slate-900 mb-2">Progress</h1>
  <p className="text-slate-600">View your Progress</p>
</div>

        
        <div className="bg-white rounded-lg border border-slate-200 overflow-hidden">
          <div className="overflow-x-auto">
            <table className="min-w-full divide-y divide-slate-200">


              <thead className="bg-slate-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">
                    Progress ID
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">
                    Problem Name
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">
                    Language
                  </th>
                  
                  <th className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">
                    Action
                  </th>
                </tr>
              </thead>



              <tbody className="bg-white divide-y divide-slate-200">
                {progresses.map((progress) => (
                  <tr key={progress.id} className="hover:bg-slate-50 transition-colors">
                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-slate-900">
                      {progress.id}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-slate-900">
                      {progress.problemName}
                    </td>
                     <td className="px-6 py-4 whitespace-nowrap text-sm text-slate-900">
                      {progress.language}
                    </td>
                   
                    <td className="px-6 py-4 whitespace-nowrap">
                      <button
                        onClick={() => openModal(progress)}
                        className="text-orange-500 underline cursor-pointer hover:text-orange-600 transition-colors text-sm font-medium"
                      >
                        Code
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>

     
      {selectedProblem && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
          <div className="bg-white p-6 rounded-lg max-w-3xl w-full shadow-lg max-h-[80vh] overflow-y-auto relative">
          
           

            
            <div className="mb-6 pr-8">
              <h2 className="text-2xl font-bold text-slate-900 mb-2">
                {selectedProblem.problemName}

                
              </h2>
             
            </div>

           
            <div className="mb-4">
              <h3 className="text-lg font-semibold text-slate-900 mb-3"> Code:</h3>
              <pre className="font-mono text-sm bg-slate-100 p-4 rounded overflow-x-auto border">
                <code className="text-slate-800">

                  {selectedProblem.code}
                </code>
              </pre>
            </div>

          
            <div className="flex justify-end pt-4 border-t border-slate-200">
              <button
                onClick={closeModal}
                className="px-4 py-2 bg-slate-100 text-slate-700 rounded hover:bg-slate-200 transition-colors font-medium"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default ProgressPage
