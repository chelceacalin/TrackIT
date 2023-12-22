import HomeNavbar from "../Home/HomeNavbar";
import { useState, useEffect } from "react";
export default function Inbox() {
  const [user, setUser] = useState([]);

  useEffect(() => {
    setUserData();
  }, []);

  const setUserData = () => {
    setUser(JSON.parse(localStorage.getItem("user")));
  };
  return (
    <>
      <HomeNavbar firstName={user.firstName} lastName={user.lastName} />
    </>
  );
}
