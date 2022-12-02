package com.ozancanguz.mvvmemployessapp.data

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class Repository @Inject constructor(private val remotedatasource:RemoteDataSource) {

    val remote=remotedatasource
}