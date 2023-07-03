import React, { useEffect, useState, useContext } from 'react';
import { HiOutlineUserCircle, HiChartPie } from 'react-icons/hi';
import HomeNavbar from './HomeNavbar';
import axios from 'axios';
import HomeRecentlyOpenedUrls from './HomeRecentlyOpenedUrls';
import { RouteTrackerContext } from '../RouteProvider/RouteTracker';
import { getEmployeeFunc } from '../../services/EmployeeService';
import EmployeeCard from '../Employees/EmployeeCard';
import jwtDecode from 'jwt-decode';

export default function Home() {
  const { visitedRoutes } = useContext(RouteTrackerContext);
  let [recentlyOpenedUrls, setRecentlyOpenedUrls] = useState([]);
  let [token, setToken] = useState('');
  let [user, setUser] = useState({});
  const [timeRemaining, setTimeRemaining] = useState(0);

  // Employees left down corner
  const [emps, setEmployees] = useState([]);
  const currentEmployees = emps.slice(0, 4);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getEmployeeFunc();
        setEmployees(response.data);
      } catch (err) {
        console.log(err);
      }
    };
    fetchData();
  }, []);

  // Update the list of most recent urls on refresh
  const getRecentsURLS = async (UserID) => {
    try {
      token = localStorage.getItem('token');

      const response = await axios.get(`http://localhost:8080/api/auth/recentlyOpenedURL/${UserID}/recentURLs`, {
        headers: { "Authorization": `Bearer ${token}` }
      });

      setRecentlyOpenedUrls(response.data.content.slice(0, 6));
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    setToken(storedToken);

    const storedUser = JSON.parse(localStorage.getItem('user'));
    setUser(storedUser);
    getRecentsURLS(storedUser.id);

    const decodedToken = jwtDecode(storedToken);
    const expiryDate = new Date(decodedToken.exp * 1000);

    const interval = setInterval(() => {
      const currentTime = new Date();
      const remainingTime = Math.floor((expiryDate - currentTime) / 1000);

      setTimeRemaining(remainingTime);

      if (remainingTime <= 0) {
        clearInterval(interval);
        // Additional logic for handling expired session (e.g., logout)
      }
    }, 1000);

    return () => {
      clearInterval(interval);
    };
  }, []);

  return (
    <div>
      <HomeNavbar firstName={user.firstName} lastName={user.lastName} />
      {/* Content */}
      <div className="grid grid-cols-2 h-screen gap-4">
        <div className="bg-white p-4">
          {/* Upper Left */}
          <div className="bg-blue-500 p-4 rounded-lg h-full flex items-center justify-center">
            <div className="bg-gray-200 p-4 rounded-lg">
              <div className="relative w-16 h-16 mx-auto">
                <HiChartPie className="text-gray-500 w-full h-full absolute" />
                <div className="absolute inset-0">
                  <div className="pie-chart" style={{ transform: `rotate(${Math.floor(Math.random() * 360)}deg)` }}></div>
                </div>
              </div>
              <p className="text-gray-500 text-sm text-center mt-2">Pie Chart</p>
            </div>
          </div>
        </div>

        <div className="bg-white p-4">
          {/* Top Right */}
          <div className="bg-gray-100 p-4 rounded-lg h-full">
            <h2 className="text-gray-600 text-lg mb-2">Recently Opened URLs</h2>
            <ul className="list-disc pl-6 text-gray-500">
              {recentlyOpenedUrls.map((url, index) => (
                <HomeRecentlyOpenedUrls key={index} className="text-sm" recentlyOpenedUrl={url} />
              ))}
            </ul>
          </div>
        </div>

        <div className="bg-white p-4">
          {/* Lower Left */}
          <div className="bg-gray-100 p-4 rounded-lg h-full">
            <h2 className="text-gray-600 text-lg ">Most recent Employees</h2>
            <ul className="list-disc text-gray-500">
              {emps.slice(0, 3).map((emp, index) => (
                <EmployeeCard key={index} employee={emp} />
              ))}
            </ul>
          </div>
        </div>

        <div className="mb-24 mt-4 bg-blue-500 flex items-center justify-center">
          {/* Lower Right */}
          <p className="text-white text-xl">
            Session Expiry: {timeRemaining} seconds
          </p>
        </div>
      </div>
    </div>
  );
}
