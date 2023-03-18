package com.example.paggingtry.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.paggingtry.api.RickAndMortyApi
import com.example.paggingtry.models.Person
import java.io.IOException
import javax.inject.Inject

class PersonsPagingSource @Inject constructor(val api: RickAndMortyApi) :
    PagingSource<Int, Person>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        return try {
            val position = params.key ?: 1
            val response = api.getUsersPage(position)

            LoadResult.Page(
                response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.info.pages) null else position + 1
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }


    }

}


