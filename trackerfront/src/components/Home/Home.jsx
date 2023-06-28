import React, { useEffect, useState, useContext } from 'react';
import { HiOutlineUserCircle, HiChartPie } from 'react-icons/hi';
import HomeNavbar from './HomeNavbar';
import { getRecentsURLS } from '../../services/RecentlyOpenedUrlService';
import HomeRecentlyOpenedUrls from './HomeRecentlyOpenedUrls';
import { RouteTrackerContext } from '../RouteProvider/RouteTracker';
export default function Home() {

  const { visitedRoutes } = useContext(RouteTrackerContext);
  //console.log(visitedRoutes);

  const [recentlyOpenedUrls, setRecentlyOpenedUrls] = useState([]);

  useEffect(() => {
    const loadUrls = async () => {
      try {
        const mostRecent = await getRecentsURLS();
        setRecentlyOpenedUrls(mostRecent.data);
      } catch (err) {
        console.log(err);
      }
    };

    loadUrls();
  }, []);

  const employees = [
    { id: 1, name: 'Employee 1' },
    { id: 2, name: 'Employee 2' },
    { id: 3, name: 'Employee 3' },
    { id: 4, name: 'Employee 4' },
    // Add more employees as needed
  ];

  return (
    <div>
      <HomeNavbar />
      {/* Content */}
      <div className="grid grid-cols-2 h-screen gap-4">
        <div className="bg-white p-4">
          {/* Upper Left */}
          <div className="bg-gray-100 p-4 rounded-lg h-full flex items-center justify-center">
            <div className="bg-gray-200 p-4 rounded-lg">
              <HiChartPie className="text-gray-500 w-16 h-16 mx-auto" />
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
            <h2 className="text-gray-600 text-lg mb-2">Employees</h2>
            <ul className="list-disc pl-6 text-gray-500">
              {employees.map((employee) => (
                <li key={employee.id} className="text-sm">
                  {employee.name}
                </li>
              ))}
            </ul>
          </div>
        </div>
        <div className="bg-gray-800 flex items-center justify-center">
          {/* Lower Right */}
          <div className="bg-blue-500 w-2/3 h-2/3 rounded-lg flex items-center justify-center">
            <p className="text-white text-lg">Lower Right</p>
          </div>
        </div>
      </div>
    </div>
  );
}
