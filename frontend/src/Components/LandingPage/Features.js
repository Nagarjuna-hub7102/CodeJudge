import React from 'react';

import { features } from '../../data/LandingPageData';

function FeatureCard({ icon, title, description }) {
  return (
    <div
      className="bg-white p-6 rounded-lg border border-slate-200"
      role="region"
      aria-labelledby={title}
    >
      <div className="text-orange-500 mb-4">{icon}</div>
      <h3 id={title} className="text-xl font-semibold text-slate-800 mb-2">
        {title}
      </h3>
      <p className="text-slate-600">{description}</p>
    </div>
  );
}


function FeatureCards() {
  return (
    <section className="bg-slate-50 py-16" aria-label="Features">
      <div className="max-w-6xl mx-auto px-4">
        <h2 className="text-3xl font-bold text-center text-slate-800 mb-12">
          Level Up Your Coding Skills
        </h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {features.map(({ icon, title, description }) => (
            <FeatureCard
              key={title}
              icon={icon}
              title={title}
              description={description}
            />
          ))}
        </div>
      </div>
    </section>
  );
}

export default FeatureCards;
