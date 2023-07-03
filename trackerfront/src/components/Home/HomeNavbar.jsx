import React from 'react'

function HomeNavbar({firstName,lastName}) {
  return (
   <nav className="bg-gray-900 py-4 px-6 flex justify-between items-center">
   <div className="flex items-center justify-end w-full">
     
     <div className="ml-3 text-white text-sm font-medium">{firstName} {lastName}</div>
     <div className="ml-5">
     <span>
       <img
         //src={user.avatar}
         src="https://fastly.picsum.photos/id/874/200/300.jpg?hmac=rJgHohZZtli5gr1B42TQbIuoC-GrMDffD-Xukd2Grj8"
         alt="User Avatar"
         className="w-8 h-8 rounded-full"
       />
     </span>
     </div>
   </div>
 </nav>
  )
}

export default HomeNavbar