import axios from "axios";
import jwtDecode from "jwt-decode";
import { useContext, useEffect, useState } from "react";
import { HiChartPie } from "react-icons/hi";
import {
  getEmployeeByEMAIL,
  getEmployeeFunc,
} from "../../services/EmployeeService";
import EmployeeCard from "../Employees/EmployeeCard";
import { RouteTrackerContext } from "../RouteProvider/RouteTracker";
import HomeNavbar from "./HomeNavbar";
import HomeRecentlyOpenedUrls from "./HomeRecentlyOpenedUrls";
import { pieArcClasses, PieChart } from "@mui/x-charts";
export default function Home() {
  const { visitedRoutes } = useContext(RouteTrackerContext);
  let [recentlyOpenedUrls, setRecentlyOpenedUrls] = useState([]);
  let [token, setToken] = useState("");
  let [user, setUser] = useState({});
  const [timeRemaining, setTimeRemaining] = useState(0);

  // Employees left down corner
  const [emps, setEmployees] = useState([]);
  const currentEmployees = emps.slice(0, 4);
  let uniqueData;
  const [actualData, setActualData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getEmployeeFunc();
        setEmployees(response.data);
      } catch (err) {
        console.error(err);
      }
    };
    fetchData();
  }, []);

  // Update the list of most recent urls on refresh
  const getRecentsURLS = async (UserID) => {
    try {
      token = localStorage.getItem("token");
      const response = await axios.get(
        `http://localhost:8080/api/auth/recentlyOpenedURL/${UserID}/recentURLs`,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

     
      uniqueData = Array.from(
        new Set(response.data.content.map((item) => item.path))
      );

      String.prototype.hashCode = function() {
        var hash = 0,
          i, chr;
        if (this.length === 0) return hash;
        for (i = 0; i < this.length; i++) {
          chr = this.charCodeAt(i);
          hash = ((hash << 5) - hash) + chr;
          hash |= 0; // Convert to 32bit integer
        }
        return hash;
      }

      setActualData(
        uniqueData.map((elem, index) => ({
          id: index,
          value: elem.hashCode(),
          label: "Series " + String.fromCharCode(65 + index),
        }))
      );
      console.log(actualData);
      setRecentlyOpenedUrls(response.data.content.slice(0, 6));
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    let storedUser = JSON.parse(localStorage.getItem("user"));

    getEmployeeByEMAIL(storedUser.email).then((data) => {
      setUser(data.data);
      localStorage.setItem("user", JSON.stringify(data.data));
    });
    storedUser = JSON.parse(localStorage.getItem("user"));
    getRecentsURLS(storedUser.id);
    const decodedToken = jwtDecode(storedToken);
    const expiryDate = new Date(decodedToken.exp * 1000);

    const interval = setInterval(() => {
      const currentTime = new Date();
      const remainingTime = Math.floor((expiryDate - currentTime) / 1000);

      setTimeRemaining(remainingTime);

      if (remainingTime <= 0) {
        clearInterval(interval);
      }
    }, 1000);

    return () => {
      clearInterval(interval);
    };
  }, []);

  return (
    <div>
      <HomeNavbar
        firstName={user.firstName}
        lastName={user.lastName}
        id={user.id}
      />
      {/* Content */}
      <div className="grid grid-cols-2 h-screen gap-4">
        <div className="bg-white p-4">
          {/* Upper Left */}
          <div className="bg-blue-500 p-4 rounded-lg h-full flex items-center justify-center">
            <PieChart
              series={[
                {
                  data: actualData,
                },
              ]}
              width={400}
              height={200}
            />
          </div>
        </div>

        <div className="bg-white p-4">
          {/* Top Right */}
          <div className="bg-gray-100 p-4 rounded-lg h-full">
            <h2 className="text-gray-600 text-lg mb-2">Recently Opened URLs</h2>
            <ul className="list-disc pl-6 text-gray-500">
              {recentlyOpenedUrls.map((url, index) => (
                <HomeRecentlyOpenedUrls
                  key={index}
                  className="text-sm"
                  recentlyOpenedUrl={url}
                />
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
          <p className="text-white text-xl">Session Expiry: 12433 seconds</p>
        </div>
      </div>
    </div>
  );
}
