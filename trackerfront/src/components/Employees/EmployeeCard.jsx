import React from 'react';

const EmployeeCard = ({ employee }) => {
  return (
    <div className="bg-white shadow-lg rounded-lg overflow-hidden">
      <div className="flex items-center p-4">
        <img
        //   src={employee.image}
        src="https://fastly.picsum.photos/id/874/200/300.jpg?hmac=rJgHohZZtli5gr1B42TQbIuoC-GrMDffD-Xukd2Grj8"
          alt={employee.firstName}
          className="w-12 h-12 rounded-full mr-4"
        />
        <div>
          <h3 className="text-lg font-bold">{employee.firstName}</h3>
          <p className="text-sm text-gray-600">{employee.email}</p>
        </div>
      </div>
    </div>
  );
};

export default EmployeeCard;
