import React from 'react'

import { Sidebar } from 'flowbite-react';
import { HiArrowSmRight, HiChartPie, HiInbox, HiShoppingBag, HiTable, HiUser, HiViewBoards } from 'react-icons/hi';
import { useNavigate } from 'react-router-dom';

export default function Navbar() {
  const navigate = useNavigate();
  return (
    <Sidebar aria-label="Default sidebar example" className="custom-sidebar ml-4 mr-4">
      <Sidebar.Items className='mt-4'>
        <Sidebar.ItemGroup className='mb-3 border-b '>
          <Sidebar.Item href="#" icon={HiChartPie} >
            <p className='font-bold'>Dashboard</p>
          </Sidebar.Item>
          <Sidebar.Item href="#" icon={HiUser} className="mt-2">
            <p className='font-bold'  onClick={()=>{
              navigate("/employeeList")
            }}>Employees</p>
          </Sidebar.Item>
          <Sidebar.Item href="#" icon={HiInbox} label=" 3 " className="mt-2">
            <p className='font-bold' >Inbox</p>
          </Sidebar.Item>
          
          <Sidebar.Item href="#"  className="mt-2">
            <p className='font-bold'>Log Out</p>
          </Sidebar.Item>
        </Sidebar.ItemGroup>
      </Sidebar.Items>
    </Sidebar>
  );
}
