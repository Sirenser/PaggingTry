package com.example.paggingtry.util

import androidx.paging.PagingData
import com.example.paggingtry.models.Person


sealed class ApiState {
    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data: PagingData<Person>) : ApiState()
    object Empty : ApiState()
}
