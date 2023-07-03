import axios from 'axios';

const RECENT_URL_BASE_URL = 'http://localhost:8080/api/auth/recentlyOpenedURL';

export const saveRecentURL = (URL) => {
  const user = JSON.parse(localStorage.getItem('user')); // Retrieve the user object from localStorage

  let object = {
    path: URL,
    employee: {
      id: user.id,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      password: user.password,
      role: user.role
    }
  };

  const token = localStorage.getItem('token'); // Retrieve the authentication token from localStorage

  return axios.post(RECENT_URL_BASE_URL, object, {
    headers: {
      Authorization: `Bearer ${token}`, // Include the token in the Authorization header
    },
  })
    .then(res => {
      console.log(res);
      return res;
    })
    .catch((err) => {
      console.log(err);
    });
};
