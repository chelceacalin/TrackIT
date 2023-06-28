import React from 'react'

export default function Employee({ employee, delEmployee,updateEmployeeFromList }) {
    const deleteEmployee = () => {
      delEmployee(employee.id);
    };

    const updateEmployee=(e)=>{
        updateEmployeeFromList(employee.id);
    }
  return (
    <>
    <td className="text-center">{employee.firstName}</td>
    <td className="text-center">{employee.lastName}</td>
    <td className="text-center">{employee.email}</td>
    <td className="text-center">
      <a href="#" className="mr-4" onClick={(e)=>{ updateEmployee(e); }}>Edit</a>
      <span className="mr-4">|</span>
      <a href="#" onClick={deleteEmployee}>Delete</a>
    </td>
    </>
  )
}
