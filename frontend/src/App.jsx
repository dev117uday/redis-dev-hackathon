import './App.css';
import MainTable from './components/MainTable';
import NavBar from './components/NavBar';
import { Routes, Route } from "react-router-dom"
import InfoPage from './components/InfoPage';
import SaveForm from './components/SaveForm';

function App() {
  return (
    <div>
      <NavBar />
      <div className='container mt-3'>
        <Routes>
          <Route path="/" element={<MainTable />} />
          <Route path="/:shortUrl/" element={<InfoPage />} />
          <Route path="/save" element={<SaveForm />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
