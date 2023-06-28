import React, { useState, useEffect } from 'react';
import { getEmployeesByEmail } from '../../services/EmployeeService';
import { useNavigate } from 'react-router-dom';
export default function SearchBar() {
  
  const navigate=useNavigate();
  const [search, setSearch] = useState('');
  const [searchData, setSearchData] = useState([]);

  const handleChange = (e) => {
    setSearch(e.target.value);
  };

  useEffect(() => {
    if (search !== '') {
      getEmployeesByEmail(search)
        .then((res) => {
            console.log(res.data);
          setSearchData(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, [search]);

  return (
    <span className="flex mt-8 flex-col">
      <span className="flex space-x-1">
        <input
          type="text"
          onChange={handleChange}
          value={search}
          className="block w-full px-4 py-2 text-purple-700 bg-white border rounded-full focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
          placeholder="Search..."
        />
        <button className="px-4 text-white bg-purple-600 rounded-full">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="w-5 h-5"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            strokeWidth={2}
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
            />
          </svg>
        </button>
      </span>
      <span className="mt-2">
        {searchData.slice(0,3).map((data, index) => {
          return (
            <a  target="_blank" key={index} className="block" onClick={()=>{
                navigate("/updateEmployee/"+data.id);
            }}>
              {data.email}
            </a>
          );
        })}
      </span>
    </span>
  );
}
