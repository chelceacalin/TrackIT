import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  deleteEmployeeById,
  getEmployeeFunc,
} from "../../services/EmployeeService";
import HomeNavbar from "../Home/HomeNavbar";
import Employee from "./Employee";
import Pagination from "./Pagination";
import SearchBar from "./SearchBar";

function EmployeeList() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [employees, setEmployees] = useState([]);

  const [currentPage, setCurrentPage] = useState(1);
  const [employeesPerPage, setEmployeesPerPage] = useState(4);
  const lastPostIndex = currentPage * employeesPerPage;
  const firstPostIndex = lastPostIndex - employeesPerPage;
  const currentEmployees = employees.slice(firstPostIndex, lastPostIndex);
  const [user, setUser] = useState({});

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await getEmployeeFunc();
        setEmployees(response.data);
      } catch (err) {
        console.error(err);
      }
      setLoading(false);
    };
    fetchData();
    setUserData();
  }, []);

  const setUserData = () => {
    setUser(JSON.parse(localStorage.getItem("user")));
  };

  const delEmployee = (e) => {
    try {
      deleteEmployeeById(e);
    } catch (err) {
      console.error(err);
    }
    window.location.reload();
  };

  const updateEmployeeFromList = (id) => {
    const trimmedId = String(id).replace("#", ""); 
    window.location.href = `/updateEmployee/${trimmedId}`;
  };

  return (
    <>
      <HomeNavbar firstName={user.firstName} lastName={user.lastName} />
      <div className="justify-between md:flex-row">
        <button
          onClick={() => {
            navigate("/addEmployee");
          }}
          className="mt-5 ml-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          Add Employee
        </button>
        <div className="flex flex-grow align-middle justify-center">
          {" "}
          {/* Search bar container */}
          <SearchBar className="align-middle" />
        </div>
      </div>

      <div className="flex shadow border-b mt-5">
        <table className="min-w-full">
          <thead className="bg-gray-50">
            <tr className="">
              <th className="text-center font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                First Name
              </th>
              <th className="text-center font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Last Name
              </th>
              <th className="text-center font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Email ID
              </th>
              <th className="text-center font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Actions
              </th>
            </tr>
          </thead>
          <tbody>
            {currentEmployees.map((employee) => {
              return (
                <tr key={employee.id}>
                  <Employee
                    employee={employee}
                    key={employee.id}
                    delEmployee={delEmployee}
                    updateEmployeeFromList={updateEmployeeFromList}
                  ></Employee>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>

      <Pagination
        totalEmployees={employees.length}
        employesPerPage={employeesPerPage}
        setCurrentPage={setCurrentPage}
        currentPage={currentPage}
      />
    </>
  );
}

export default EmployeeList;
