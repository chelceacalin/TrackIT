export default function HomeRecentlyOpenedUrls({ recentlyOpenedUrl }) {

  const handleClick = () => {
      window.location.href =recentlyOpenedUrl.path.split('/').pop();
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
