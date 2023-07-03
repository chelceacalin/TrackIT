import axios from 'axios';

const EMPLOYEE_API_BASE_URL = 'http://localhost:8080/api/auth/employees';

export const saveEmployeeFunct = (employee) => {
  return axios.post(EMPLOYEE_API_BASE_URL, employee)
  .then(res => {
    console.log(res);
    return res;
  })
  .catch((err)=>{
    console.log(err);
  });
};

export const getEmployeeFunc=()=>{

  return axios.get(EMPLOYEE_API_BASE_URL+"?pageNo=0&pageSize=10000") .then(res => {
    return res;
  })
  .catch((err)=>{
    console.log(err);
  });
}



export const deleteEmployeeById=(id)=>{
 return axios.delete(EMPLOYEE_API_BASE_URL+ "/" + id)
  .then(response => {
    console.log(response.data);
  })
  .catch(error => {
    // Handle error
    console.error(error);
  });

}
export const updateEmployeeById=(id,employee)=>{
  try {
   return  axios.put("http://localhost:8080/api/auth/employees/"+id, employee);
  } catch (error) {
    console.error('Error updating employee:', error);
    throw error;
  }
 }

 export const getEmployeeByid=(id)=>{
  return axios.get(EMPLOYEE_API_BASE_URL+"/"+id) .then(res => {
    return res;
  })
  .catch((err)=>{
    console.log(err);
  });
}

export const getEmployeeByEMAIL = (email) => {
  const token = localStorage.getItem('token');
  
  return axios.get(`http://localhost:8080/api/auth/employeeByEmail?email=${email}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then(res => {
      return res;
    })
    .catch((err) => {
      console.log(err);
    });
};


export const getEmployeesByEmail=(email)=>{
  return axios.get("http://localhost:8080/api/auth/employeesByMail?email="+email)
}
