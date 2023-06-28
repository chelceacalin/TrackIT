import axios from 'axios';

const RECENT_URL_BASE_URL = 'http://localhost:8080/api/recentlyOpenedUrl';

export const saveRecentURL = (URL) => {
let object={
  "path":URL
}

  return axios.post(RECENT_URL_BASE_URL, object)
    .then(res => {
      console.log(res);
      return res;
    })
    .catch((err) => {
      console.log(err);
    });
};

export const getRecentsURLS=()=>{

  return axios.get(RECENT_URL_BASE_URL+"?pageNo=0&pageSize=4").then(res => {
    return res;
  })
  .catch((err)=>{
    console.log(err);
  });
}

