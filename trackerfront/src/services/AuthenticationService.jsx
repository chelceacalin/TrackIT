import axios from "axios";

const BASE_AUTH_URL = "http://localhost:8080/api/auth";

export const SingIn = (employee) => {
  return axios
    .post(BASE_AUTH_URL + "/signin", employee)
    .then((res) => {
      return res;
    })
    .catch((err) => {
      console.error(err);
      alert("Login failed");
    });
};

export const SignUpFct = (employee) => {
  return axios
    .post(BASE_AUTH_URL + "/signup", employee)
    .then((res) => {
      localStorage.setItem("token",res.data.token);
      return res;
    })
    .catch((err) => {
      console.error("err auth " +err);
      alert("Login failed");
    });
};
