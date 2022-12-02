package com.ozancanguz.mvvmemployessapp.data

import com.ozancanguz.mvvmemployessapp.data.network.EmployeeApi
import com.ozancanguz.mvvmemployessapp.models.Employee
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val employeeApi: EmployeeApi) {


    suspend fun getEmployees(): Response<Employee> {
        return employeeApi.getEmployees()
    }


}