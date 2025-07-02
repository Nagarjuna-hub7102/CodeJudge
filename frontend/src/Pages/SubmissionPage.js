import { useState } from 'react';
import submissions from '../data/submissions'
import { useNavigate } from 'react-router-dom';
import { ArrowLeft } from 'lucide-react';


const SubmissionPage = () => {
  const [selectedSubmission, setSelectedSubmission] = useState(null);

  const navigate = useNavigate();


 
  const openModal = (submission) => {
    setSelectedSubmission(submission);
  };



  const closeModal = () => {
    setSelectedSubmission(null);
  };


  const getStatusBadge = (status) => {
    if (status === "Passed") {
      return (
        <span className="bg-green-100 text-green-800 px-2 py-1 rounded text-sm font-medium">
          Passed
        </span>
      );
    } else {
      return (
        <span className="bg-red-100 text-red-800 px-2 py-1 rounded text-sm font-medium">
          Failed
        </span>
      );
    }
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

  
  <h1 className="text-3xl font-bold text-slate-900 mb-2">Submissions</h1>
  <p className="text-slate-600">View your coding problem submissions and results</p>
</div>

        
        <div className="bg-white rounded-lg border border-slate-200 overflow-hidden">
          <div className="overflow-x-auto">
            <table className="min-w-full divide-y divide-slate-200">


              <thead className="bg-slate-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">
                    Submission ID
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">
                    Problem Name
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">
                    Status
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">
                    Time
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">
                    Action
                  </th>
                </tr>
              </thead>



              <tbody className="bg-white divide-y divide-slate-200">
                {submissions.map((submission) => (
                  <tr key={submission.id} className="hover:bg-slate-50 transition-colors">
                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-slate-900">
                      {submission.id}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-slate-900">
                      {submission.problemName}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      {getStatusBadge(submission.status)}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-slate-500">
                      {submission.time}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      <button
                        onClick={() => openModal(submission)}
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

     
      {selectedSubmission && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
          <div className="bg-white p-6 rounded-lg max-w-3xl w-full shadow-lg max-h-[80vh] overflow-y-auto relative">
          
           

            
            <div className="mb-6 pr-8">
              <h2 className="text-2xl font-bold text-slate-900 mb-2">
                {selectedSubmission.problemName}

                
              </h2>
             
            </div>

           
            <div className="mb-4">
              <h3 className="text-lg font-semibold text-slate-900 mb-3">Submitted Code:</h3>
              <pre className="font-mono text-sm bg-slate-100 p-4 rounded overflow-x-auto border">
                <code className="text-slate-800">

                  {selectedSubmission.code}
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
};

export default SubmissionPage;