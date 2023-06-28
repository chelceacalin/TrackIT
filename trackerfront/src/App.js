import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Employeelist from './components/Employees/Employeelist';
import AddEmployee from './components/Employees/AddEmployee';
import UpdateEmployee from './components/Employees/UpdateEmployee';
import Home from './components/Home/Home';
import Inbox from './components/Inbox/Inbox';
import Sidebar from './components/Sidebar/Sidebar';
import { RouteTrackerProvider } from './components/RouteProvider/RouteTracker';


export default function App() {
  return (
    <Router>
      <RouteTrackerProvider>
        <div style={{ display: 'flex' }}>
          <Sidebar />
          <div style={{ flex: '1' }}>
            <Routes>
              <Route index path="/" element={<Home />} />
              <Route path="/employeeList" element={<Employeelist />} />
              <Route path="/addEmployee" element={<AddEmployee />} />
              <Route path="/inbox" element={<Inbox />} />
              <Route path="/updateEmployee/:id" element={<UpdateEmployee />} />
            </Routes>
          </div>
        </div>
      </RouteTrackerProvider>
    </Router>
  );
}
