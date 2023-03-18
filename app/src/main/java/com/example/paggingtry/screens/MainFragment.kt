package com.example.paggingtry.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paggingtry.MainApp
import com.example.paggingtry.R
import com.example.paggingtry.adapters.PersonAdapter
import com.example.paggingtry.adapters.PersonPagingAdapter
import com.example.paggingtry.databinding.FragmentMainBinding
import com.example.paggingtry.util.ApiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {


    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val pagingAdapter = PersonPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.application as MainApp).appComponent.inject(this)

        viewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.recyclerView.adapter = pagingAdapter

        viewModel.getPersons()

        lifecycleScope.launchWhenCreated {
            viewModel.personsStateFlow.collect {
                when (it) {
                    is ApiState.Loading -> {
                        binding.recyclerView.isVisible = false

                        binding.tvError.isVisible = false
                        binding.button.isVisible = false

                        binding.tvLoading.isVisible = true
                        binding.pbLoading.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerView.isVisible = false

                        binding.tvError.isVisible = true
                        binding.button.isVisible = true

                        binding.tvLoading.isVisible = false
                        binding.pbLoading.isVisible = false

                        Log.d("MyLog", "${it.msg}")
                    }
                    is ApiState.Success -> {
                        binding.recyclerView.isVisible = true

                        binding.tvError.isVisible = false
                        binding.button.isVisible = false

                        binding.tvLoading.isVisible = false
                        binding.pbLoading.isVisible = false

                        pagingAdapter.submitData(it.data)

                    }
                    is ApiState.Empty -> {

                    }
                }
            }
        }

        binding.button.setOnClickListener {
            viewModel.getPersons()
        }

    }

}