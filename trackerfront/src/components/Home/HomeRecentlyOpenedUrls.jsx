import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function HomeRecentlyOpenedUrls({ recentlyOpenedUrl }) {
  const navigate = useNavigate();

  const handleClick = () => {
    const extractedPath = recentlyOpenedUrl.path.split('/').pop();
    navigate(extractedPath);
  };


  const [date, timeWithMilliseconds] = recentlyOpenedUrl.dateSearched.split("T");
  const [time] = timeWithMilliseconds.split(".");

  return (
    <div style={{ display: 'flex', alignItems: 'center' }}>
      <li className="text-sm" onClick={handleClick}>
        {recentlyOpenedUrl.path}
      </li>
      <span style={{ marginLeft: '1rem' }}>{date} {time}</span>
    </div>
  );
}
