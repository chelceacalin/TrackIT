import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { saveEmployeeFunct } from "../../services/EmployeeService";

function AddEmployee() {
  const navigate = useNavigate();

  const [employee, setEmployee] = useState({
    id: "",
    firstName: "",
    lastName: "",
    email: "",
  });

  let saveEmployee = (e) => {
    e.preventDefault();
    saveEmployeeFunct(employee);
  };

  let handleChange = (e) => {
    const value = e.target.value;
    setEmployee({ ...employee, [e.target.name]: value });
  };

  let clearData = () => {
    setEmployee({
      id: "",
      firstName: "",
      lastName: "",
      email: "",
    });
  };

  return (
    <div className="flex max-w-2xl shadow border-b mx-auto">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Add new employee</h1>
        </div>

        <form id="create-course-form">
          <div className="items-center justify-center h-14 w-full my-4">
            <label className="block  text-gray-600 text-sm font-normal">
              First Name
            </label>
            <input
              type="text"
              name="firstName"
              value={employee.firstName}
              onChange={(e) => {
                handleChange(e);
              }}
              placeholder="  Add First Name"
              className="h-10 w-96 border mt-2 px-2 py-2 text-xs"
            ></input>
          </div>

          <div className="items-center justify-center h-14 w-full my-4">
            <label className="block  text-gray-600 text-sm font-normal">
              Last Name
            </label>
            <input
              type="text"
              name="lastName"
              value={employee.lastName}
              onChange={(e) => {
                handleChange(e);
              }}
              placeholder="  Add Last Name"
              className="h-10 w-96 border mt-2 px-2 py-2 text-xs"
            ></input>
          </div>

          <div className="items-center justify-center h-14 w-full my-4">
            <label className="block  text-gray-600 text-sm font-normal">
              Email Name
            </label>
            <input
              type="email"
              name="email"
              value={employee.email}
              onChange={(e) => {
                handleChange(e);
              }}
              placeholder="  Add Email"
              className="h-10 w-96 border mt-2 px-2 py-2 text-xs"
            ></input>
          </div>

          <div className="items-center justify-center h-14 w-full my-10 ">
            <button
              onClick={(e) => {
                saveEmployee(e);
                navigate("/employeeList");
              }}
              className="m-auto bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mr-4 w-24"
            >
              Save
            </button>
            <button className="m-auto bg-red-300 hover:bg-red-500 text-white font-bold py-2 px-4 rounded w-24">
              Clear
            </button>
          </div>
        </form>
        <footer className="align-baseline bg-gray-100 text-center py-2 text-gray-600 text-sm font-normal">
          <p className="text-gray-600 text-sm font-normal">
            <a onClick={() => navigate("/employeeList")}>
              Back To Main Page
            </a>
          </p>
        </footer>
      </div>
    </div>
  );
}

export default AddEmployee;
