import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate,useParams } from 'react-router-dom';
const ProfilePic = () => {
    const { id } = useParams();
  const [selectedImage, setSelectedImage] = useState(null);
  let navigate=useNavigate();
  const handleImageDrop = (event) => {
    event.preventDefault();
    const file = event.dataTransfer.files[0];
    setSelectedImage(file);
  };

  const handleImageBrowse = (event) => {
    const file = event.target.files[0];
    setSelectedImage(file);
  };

  const handleImageUpload = async () => {
    if (selectedImage) {
      const formData = new FormData();
      formData.append('image', selectedImage);

      try {
        const response = await axios.post(
          `http://localhost:8080/api/auth/images/${id}`,
          formData
        );
        navigate('/home')
        console.log(response.data); // Do something with the response if needed
      } catch (error) {
        console.error(error);
      }
    }
  };

  return (
    <div className="container mx-auto p-6">
      <h2 className="text-2xl font-bold mb-4">Image Upload</h2>
      <div
        className="border-dashed border-2 border-gray-400 p-4 rounded-lg mb-4"
        onDrop={handleImageDrop}
        onDragOver={(event) => event.preventDefault()}
      >
        {selectedImage ? (
          <img
            src={URL.createObjectURL(selectedImage)}
            alt="Selected"
            className="w-40 h-40 object-cover rounded-lg"
          />
        ) : (
          <div className="text-gray-500">Drop or Browse to select an image</div>
        )}
      </div>
      <input type="file" accept="image/*" onChange={handleImageBrowse} className="mb-4" />
      <button
        onClick={handleImageUpload}
        className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded"
      >
        Upload
      </button>
    </div>
  );
};

export default ProfilePic;
