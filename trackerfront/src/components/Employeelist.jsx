import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getEmployeeFunc, deleteEmployeeById } from "../services/EmployeeService";
import Employee from "./Employee";
import Pagination from "./Pagination";
import SearchBar from './SearchBar';

function Employeelist() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [employees, setEmployees] = useState([]);

  {/* Pagination */}
  const [currentPage, setCurrentPage] = useState(1)
  const [employesPerPage, setEmployeesPerPage] = useState(4)
  const lastPostIndex = currentPage * employesPerPage;
  const firstPostIndex = lastPostIndex - employesPerPage;
  const currentEmployees = employees.slice(firstPostIndex, lastPostIndex);

  useEffect(() => {
    let response;
    const fetchData = async () => {
      setLoading(true);
      try {
        response = await getEmployeeFunc();
        setEmployees(response.data);
      } catch (err) {
        console.log(err);
      }
      setLoading(false);
    }
    fetchData();
    console.log(employees);
  }, [])

  let delEmployee = (e) => {
    try {
      deleteEmployeeById(e);
    } catch (e) {
      console.log(e);
    }
    window.location.reload();
  }

  let updateEmployeeFromList = (id) => {
    navigate("/updateEmployee/" + id);
  }

  return (
    <>
     <div className="flex justify-between items-center"> {/* Parent container */}
        <button
          onClick={(e) => {
            navigate("/addEmployee");
          }}
          className="mt-5 ml-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          Add Employee
        </button>
        <div className="flex justify-center flex-grow"> {/* Search bar container */}
          <SearchBar className="align-middle" />
        </div>
      </div>

      <div className="flex shadow border-b">
        <table className="min-w-full mt-10">
          <thead className="bg-gray-50">
            <tr>
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
        employesPerPage={employesPerPage}
        setCurrentPage={setCurrentPage}
        currentPage={currentPage}
      />
    </>
  );
}

export default Employeelist;
