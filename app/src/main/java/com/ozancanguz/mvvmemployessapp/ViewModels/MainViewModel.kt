package com.ozancanguz.mvvmemployessapp.ViewModels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ozancanguz.mvvmemployessapp.data.Repository
import com.ozancanguz.mvvmemployessapp.models.Employee
import com.ozancanguz.mvvmemployessapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository,
                                        application: Application):AndroidViewModel(application)

{

    //1
    var employeeResponse= MutableLiveData<NetworkResult<Employee>>()

    //5
 fun getEmployees() = viewModelScope.launch {
      getRecipesSafeCall()
   }

     //4
    private suspend fun getRecipesSafeCall() {
        employeeResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getEmployees()
                employeeResponse.value = handleFoodRecipesResponse(response)
            } catch (e: Exception) {
                employeeResponse.value = NetworkResult.Error("Employe not found.")
            }
        } else {
            employeeResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    //3
    private fun handleFoodRecipesResponse(response: Response<Employee>): NetworkResult<Employee>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }

            response.isSuccessful -> {
                val employees = response.body()
                return NetworkResult.Success(employees!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    // 2
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}