import React from 'react';
import { topics } from '../../data/LandingPageData';

function TopicBadge({ name, color }) {
  return (
    <div
      className={`${color} px-4 py-2 rounded font-medium text-sm cursor-pointer hover:opacity-90 transition-opacity`}
    >
    {name}
    </div>
  );
}

function RoadmapPreview() {
  

  return (
    <section className="py-16 bg-white">
      <div className="max-w-6xl mx-auto px-4">
        <h2 className="text-3xl font-bold text-center text-slate-800 mb-4">
          DSA Roadmap
        </h2>
        <p className="text-slate-600 text-center mb-8 max-w-2xl mx-auto">
          Follow our structured path to master Data Structures & Algorithms.
        </p>

        <div className="overflow-x-auto pb-4">
          <div className="flex space-x-3 min-w-max px-2 py-4">
            {topics.map((topic, index) => (
              <TopicBadge key={index} name={topic.name} color={topic.color} />
            ))}
          </div>
        </div>

        <div className="text-center mt-8">
          <a
            href="https://roadmap.sh/datastructures-and-algorithms"
            className="text-orange-500 hover:text-orange-600 font-medium"
          >
            View Complete Roadmap
          </a>
        </div>
      </div>
    </section>
  );
}

export default RoadmapPreview;
