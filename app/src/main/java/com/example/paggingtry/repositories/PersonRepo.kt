package com.example.paggingtry.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paggingtry.api.RickAndMortyApi
import com.example.paggingtry.models.Person
import com.example.paggingtry.paging.PersonsPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PersonRepo @Inject constructor(private var api: RickAndMortyApi) {


    fun getPage() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { PersonsPagingSource(api) }
    ).flow


}