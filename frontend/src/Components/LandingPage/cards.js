import React from 'react';
import { Github, Linkedin } from 'lucide-react';
import { team } from '../../data/LandingPageData';

function TeamMemberCard({ name, role, github, linkedin }) {
  return (
    <div className="bg-white p-6 rounded-lg border border-slate-200 text-center">
      <div className="mb-4">
      

        <div className="mx-auto w-16 h-16 flex items-center justify-center rounded-full bg-orange-100 text-orange-700 font-bold text-xl">
          {name.split(' ').map(n => n[0]).join('')}
        </div>
      </div>
      <h3 className="text-lg font-semibold text-slate-800">{name}</h3>
      <p className="text-sm text-slate-600 mb-4">{role}</p>
      <div className="flex justify-center gap-4 text-slate-600">
        {github && (
          <a href={github} target="_blank" rel="noopener noreferrer" aria-label="GitHub">
            <Github className="h-5 w-5 hover:text-orange-500 transition-colors" />
          </a>
        )}
        {linkedin && (
          <a href={linkedin} target="_blank" rel="noopener noreferrer" aria-label="LinkedIn">
            <Linkedin className="h-5 w-5 hover:text-orange-500 transition-colors" />
          </a>
        )}
      </div>
    </div>
  );
}

function TeamSection() {
  

  return (
    <section className="py-16 bg-slate-50">
      <div className="max-w-6xl mx-auto px-4">
        <h2 className="text-3xl font-bold text-center text-slate-800 mb-12">
          Meet the Team
        </h2>
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-8">
          {team.map((member, index) => (
            <TeamMemberCard
              key={index}
              name={member.name}
              role={member.role}
              github={member.github}
              linkedin={member.linkedin}
            />
          ))}
        </div>
      </div>
    </section>
  );
}

export default TeamSection;
