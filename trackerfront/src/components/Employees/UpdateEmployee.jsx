import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  getEmployeeByid,
  updateEmployeeById,
} from "../../services/EmployeeService";

function UpdateEmployee() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");

  const [employee, setEmployee] = useState({
    id: "",
    firstName: "",
    lastName: "",
  });

  useEffect(() => {
    const retrieveEmployee = async (idEmp) => {
      const trimmedId = idEmp.replace("#", "");
      const emp = await getEmployeeByid(trimmedId);
      setEmployee(emp.data);
    };
    retrieveEmployee(id);
  }, [id]);

  const handleUpdate = async (e) => {
    e.preventDefault();

    const updatedEmployee = {
      firstName: firstName || employee.firstName,
      lastName: lastName || employee.lastName,
    };

    try {
      await updateEmployeeById(employee.id, updatedEmployee);
      navigate("/");
    } catch (error) {
      console.error("Error updating employee:", error);
    }
  };

  return (
    <div className="flex max-w-xl shadow border-b mx-auto mt-20">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Update employee</h1>
        </div>

        <form id="update-employee-form" onSubmit={handleUpdate}>
          <div className="items-center justify-center h-14 w-full my-4">
            <label className="block text-gray-600 text-sm font-normal">
              First Name
            </label>
            <input
              type="text"
              placeholder="Edit First Name"
              defaultValue={employee.firstName}
              onChange={(e) => setFirstName(e.target.value)}
              className="h-10 w-96 border mt-2 px-2 py-2 text-xs"
            ></input>
          </div>

          <div className="items-center justify-center h-14 w-full my-4">
            <label className="block text-gray-600 text-sm font-normal">
              Last Name
            </label>
            <input
              type="text"
              placeholder="Edit Last Name"
              defaultValue={employee.lastName}
              onChange={(e) => setLastName(e.target.value)}
              className="h-10 w-96 border mt-2 px-2 py-2 text-xs"
            ></input>
          </div>

          <div className="items-center justify-center h-14 w-full my-10 ">
            <button
              onClick={() => navigate("/employeeList")}
              type="submit"
              className="m-auto bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mr-4 w-24"
            >
              Update
            </button>

            <button
              onClick={() => navigate("/employeeList")}
              className="m-auto bg-red-300 hover:bg-red-500 text-white font-bold py-2 px-4 rounded w-24"
            >
              Cancel
            </button>
          </div>
        </form>

        <footer className="align-baseline bg-gray-100 text-center py-2 text-gray-600 text-sm font-normal">
          <p className="text-gray-600 text-sm font-normal hover:bg-slate-200">
            <a href="" onClick={() => navigate("/employeeList")}>
              Back To Employee List
            </a>
          </p>
        </footer>
      </div>
    </div>
  );
}

export default UpdateEmployee;
