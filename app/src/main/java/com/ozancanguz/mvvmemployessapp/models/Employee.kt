package com.ozancanguz.mvvmemployessapp.models


import com.google.gson.annotations.SerializedName
// https://dummy.restapiexample.com/api/v1/employees

data class Employee(
    @SerializedName("data")
    val `data`: List<Result>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)