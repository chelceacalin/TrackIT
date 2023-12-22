import axios from 'axios';
import { useEffect, useState } from 'react';
const EmployeeCard = ({ employee }) => {
  const [userAvatar, setUserAvatar] = useState('');
  
  
  const fetchUserAvatar = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/auth/imagesByEmpId/${employee.id}`, {
        responseType: 'blob', 
      });

      const blob = new Blob([response.data], { type: 'image/png' }); 
      const avatarUrl = URL.createObjectURL(blob); 

      setUserAvatar(avatarUrl);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchUserAvatar();
  }, []);



  
  return (
    <div className="bg-white shadow-lg rounded-lg overflow-hidden">
      <div className="flex items-center p-4">
      <img
                src={userAvatar}
                alt="User Avatar"
                className="w-8 h-8 rounded-full"
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
