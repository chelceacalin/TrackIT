import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function HomeNavbar({ firstName, lastName, id }) {
  const navigate = useNavigate();
  const [userAvatar, setUserAvatar] = useState('');
  let [storedUser] = useState({})
  storedUser = JSON.parse(localStorage.getItem('user'));

  const fetchUserAvatar = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/auth/imagesByEmpId/${storedUser.id}`, {
        responseType: 'blob', // Set the response type to 'blob' to receive binary data
      });

      const blob = new Blob([response.data], { type: 'image/png' }); // Create a blob from the binary data
      const avatarUrl = URL.createObjectURL(blob); // Convert the blob to a data URL

      setUserAvatar(avatarUrl);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchUserAvatar();
  }, []);

  return (
    <nav className="bg-gray-900 py-4 px-6 flex justify-between items-center">
      <div className="flex items-center justify-end w-full">
        <div className="ml-3 text-white text-sm font-medium">{firstName} {lastName}</div>
        <div className="ml-5">
          <span>
            <label htmlFor="image-upload" className="cursor-pointer">
              <img
                src={userAvatar}
                alt="User Avatar"
                className="w-8 h-8 rounded-full"
                onClick={()=>{
                  navigate(`${id}/profilePic`)
                }}
              />
            </label>
          </span>
        </div>
      </div>
    </nav>
  );
}

export default HomeNavbar;
