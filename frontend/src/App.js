import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LandingPage from './Pages/LandingPage'
import ProblemsPage from './Pages/ProblemPage'
import ProblemDescriptionPage from './Pages/ProblemDescriptionPage';
import SubmissionPage from './Pages/SubmissionPage';
import ProgressPage from './Pages/ProgressPage';
import LoginRegisterPage from './Pages/LoginRegisterPage';



function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LandingPage/>} />
        <Route path="/problems" element={<ProblemsPage />} />
        <Route path="/description" element={<ProblemDescriptionPage/>}/>
        <Route path="/submissions" element={<SubmissionPage/>}/>
        <Route path="/progress" element={<ProgressPage/>}/>
        <Route path="/login" element={<LoginRegisterPage/>}/>
      </Routes>
    </Router>
  );
}

export default App;
