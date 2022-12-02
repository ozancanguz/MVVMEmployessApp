package com.ozancanguz.mvvmemployessapp.data.network

import com.ozancanguz.mvvmemployessapp.models.Employee
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApi {

    @GET("api/v1/employees")
    suspend fun getEmployees(): Response<Employee>
}