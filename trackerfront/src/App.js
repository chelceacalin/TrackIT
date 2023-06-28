import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import Employeelist from './components/Employeelist';
import AddEmployee from './components/AddEmployee';
import UpdateEmployee from './components/UpdateEmployee';

export default function App() {
  return (
    <Router>
      <div style={{ display: 'flex' }}>
        <Navbar />
        <div style={{ flex: '1' }}>
          <Routes>
            <Route index path="/" element={<Employeelist />} />
            <Route path="/employeeList" element={<Employeelist />} />
            <Route path="/addEmployee" element={<AddEmployee />} />
            <Route path="/updateEmployee/:id" element={<UpdateEmployee />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}
