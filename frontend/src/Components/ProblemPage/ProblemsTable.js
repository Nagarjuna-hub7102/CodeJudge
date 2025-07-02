import React from 'react';

const DifficultyBadge = ({ difficulty }) => {
  const badgeClasses = {
    'Easy': 'bg-green-100 text-green-800',
    'Medium': 'bg-yellow-100 text-yellow-800',
    'Hard': 'bg-red-100 text-red-800'
  };

  return (
    <span className={`px-2 py-1 inline-flex text-xs leading-4 font-medium rounded-full ${badgeClasses[difficulty]}`}>
      {difficulty}
    </span>
  );
};

const ProblemsTable = ({ problems, filteredProblems }) => {
  return (
    <div className="overflow-x-auto bg-white rounded-lg shadow">
      <table className="min-w-full divide-y divide-gray-200">
        <thead>
          <tr>
            <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Problem Name
            </th>
            <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Difficulty
            </th>
            <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Category
            </th>
            <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Action
            </th>
          </tr>
        </thead>
        <tbody className="divide-y divide-gray-200">
          {filteredProblems.length > 0 ? (
            filteredProblems.map((problem) => (
              <tr key={problem.id} className="hover:bg-gray-50">
                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                  {problem.name}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm">

                  <DifficultyBadge difficulty={problem.difficulty} />
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                  
                  {problem.category}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm">
                  <a
                    href={`/problems/${problem.id}`}
                    className="inline-flex items-center px-3 py-1 rounded text-sm font-medium bg-orange-500 text-white hover:bg-orange-600 transition-colors"
                  >
                    Solve
                  </a>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4" className="px-6 py-10 text-center text-sm text-gray-500">
                No problems found matching your filters.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ProblemsTable;