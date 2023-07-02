import React from 'react';
import { HiChartPie, HiUser, HiInbox, HiLogout } from 'react-icons/hi';
import { useNavigate } from 'react-router-dom';

export default function Sidebar() {
  const navigate = useNavigate();

  return (
    <div className="bg-gray-900 h-screen w-28 flex flex-col items-center justify-between">
      <div className="mt-8">
        <SidebarItem icon={HiChartPie} text="Dashboard"  onClick={()=>{
          navigate('/home')
        }}/>
        <SidebarItem
          icon={HiUser}
          text="Employees"
          onClick={() => navigate('/employeeList')}
        />
        <SidebarItem icon={HiInbox} text="Inbox" badge="3" hasBadgeOnTop={false} onClick={()=>{
          navigate('/inbox');
        }} />
      </div>
      <div className="mb-8">
        <SidebarItem icon={HiLogout} text="Log Out" />
      </div>
    </div>
  );
}

function SidebarItem({ icon: Icon, text, badge, onClick, hasBadgeOnTop = true }) {
  return (
    <button
      onClick={onClick}
      className="flex items-center mt-6 text-gray-300 hover:text-white"
    >
      <Icon className="w-6 h-6" />
      <span className="text-xs ml-1">{text}</span>
      {badge && (
        <span
          className={`bg-red-500 text-white text-xs rounded-full py-0.5 px-2 ml-1 ${
            hasBadgeOnTop ? 'mt-1' : 'mb-1'
          }`}
        >
          {badge}
        </span>
      )}
    </button>
  );
}
