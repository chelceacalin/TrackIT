import axios from 'axios';

const RECENT_URL_BASE_URL = 'http://localhost:8080/api/recentlyOpenedUrl';

export const saveRecentURL = (URL) => {
  return axios.post(RECENT_URL_BASE_URL, URL)
  .then(res => {
    console.log(res);
    return res;
  })
  .catch((err)=>{
    console.log(err);
  });
};

export const getRecentsURLS=()=>{

  return axios.get(RECENT_URL_BASE_URL+"?pageNo=0&pageSize=3") .then(res => {
    return res;
  })
  .catch((err)=>{
    console.log(err);
  });
}

