import { Route, BrowserRouter as Router, Routes, useLocation } from 'react-router-dom';
import LogIn from './components/Authentication/LogIn';
import SignUp from './components/Authentication/SignUp';
import AddEmployee from './components/Employees/AddEmployee';
import Employeelist from './components/Employees/Employeelist';
import UpdateEmployee from './components/Employees/UpdateEmployee';
import Home from './components/Home/Home';
import ProfilePic from './components/Home/ProfilePic';
import Inbox from './components/Inbox/Inbox';
import { RouteTrackerProvider } from './components/RouteProvider/RouteTracker';
import Sidebar from './components/Sidebar/Sidebar';
function App() {
  return (
    <Router>
      <RouteTrackerProvider>
        <MainContent />
      </RouteTrackerProvider>
    </Router>
  );
}
function MainContent() {
  const location = useLocation();
  const isLoginOrSignUp = location.pathname === '/' || location.pathname === '/signUp';

  return (
    <div style={{ display: 'flex' }}>
      {!isLoginOrSignUp && <Sidebar />} {/* Render the Sidebar only if the current path is not the login or sign-up route */}
      <div style={{ flex: '1' }}>
        <Routes>
          <Route index path="/" element={<LogIn />} />
          <Route path="/home" element={<Home />} />
          <Route path="/employeeList" element={<Employeelist />} />
          <Route path="/addEmployee" element={<AddEmployee />} />
          <Route path="/inbox" element={<Inbox />} />
          <Route path="/signUp" element={<SignUp />} />
          <Route path="/home/:id/profilePic" element={<ProfilePic />} />
          <Route path="/updateEmployee/:id" element={<UpdateEmployee />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;