import LeftPanel from "../Components/DescriptionPage/LeftPanel";
import Navbar from "../Components/DescriptionPage/Navbar";
import RightPanel from "../Components/DescriptionPage/RightPanel";



const ProblemDescriptionPage = () => {
  return (
    <div className="min-h-screen bg-white">
      <Navbar/>
      <main className="flex flex-col md:flex-row max-w-7xl mx-auto px-4 py-8 gap-6">
       <LeftPanel/>
       <RightPanel/>
      </main>
    </div>
  );
};

export default ProblemDescriptionPage;