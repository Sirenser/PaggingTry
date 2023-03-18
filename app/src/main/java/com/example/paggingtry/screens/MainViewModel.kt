package com.example.paggingtry.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.paggingtry.repositories.PersonRepo
import com.example.paggingtry.util.ApiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel(private val repo: PersonRepo) : ViewModel() {

    private val _personsStateFlow: MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)

    val personsStateFlow: StateFlow<ApiState> = _personsStateFlow

    fun getPersons() = viewModelScope.launch {

        _personsStateFlow.value = ApiState.Loading
        delay(3000L)
        repo.getPage().catch { e ->
            _personsStateFlow.value = ApiState.Failure(e)
        }.collect { data ->
            _personsStateFlow.value = ApiState.Success(data)
        }

    }



}


@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(
    private val repo: PersonRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }

}
