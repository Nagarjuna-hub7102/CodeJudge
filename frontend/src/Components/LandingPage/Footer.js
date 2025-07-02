import React from 'react';
import { Github } from 'lucide-react';

const Footer = () => {
  return (
    <footer className="bg-slate-800 text-slate-300">
      <div className="max-w-6xl mx-auto px-4 py-12">
        <div className="flex flex-col md:flex-row justify-between items-center">
          <p className="text-sm mb-4 md:mb-0">
            Built by students using React, Spring Boot, MySQL & Judge0 API.
          </p>

          <div className="flex space-x-4">
            <a
              href="https://github.com/codejudge"
              className="hover:text-white transition-colors"
              aria-label="GitHub"
            >
              <Github className="h-5 w-5" />
            </a>
          </div>
        </div>

        <div className="mt-8 pt-8 border-t border-slate-700 flex flex-col md:flex-row justify-between">
          <p className="text-xs text-slate-400 mb-4 md:mb-0">
            © {new Date().getFullYear()} CodeJudge. All rights reserved.
          </p>

          <div className="flex flex-wrap gap-4 text-xs text-slate-400">
            <a href="/about" className="hover:text-white transition-colors">About</a>
            <a href="/privacy" className="hover:text-white transition-colors">Privacy</a>
            <a href="/terms" className="hover:text-white transition-colors">Terms</a>
            <a href="/contact" className="hover:text-white transition-colors">Contact</a>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
