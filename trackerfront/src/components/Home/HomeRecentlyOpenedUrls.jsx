import React from 'react';
import {useNavigate} from 'react-router-dom'
export default function HomeRecentlyOpenedUrls( {recentlyOpenedUrl} ) {
    const navigate=useNavigate();
    const handleClick=(e)=>{
        console.log(recentlyOpenedUrl);
      //  navigate(recentlyOpenedUrl);
      const extractedPath = recentlyOpenedUrl.split('/').pop();
      navigate(extractedPath);
    }

  return (
    <div className="bg-white p-2">
          <li className="text-sm" onClick={(e)=>{ handleClick(e);}}  >{recentlyOpenedUrl} </li>
    </div>
  );
}
