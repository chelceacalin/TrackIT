import axios from 'axios';

const BASE_AUTH_URL = 'http://localhost:8080/api/auth';

export const SingIn = (employee) => {
  return axios.post(BASE_AUTH_URL+"/signin", employee)
  .then(res => {
    console.log(res);
    return res;
  })
  .catch((err)=>{
    console.log(err);
    alert('Login failed')
  });
};
