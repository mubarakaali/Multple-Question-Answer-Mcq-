package com.fivesol.kapsulemcqs.presentation.old

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivesol.kapsulemcqs.databinding.ActivitySubCategoryBinding
import com.fivesol.kapsulemcqs.presentation.MainMcqViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.utils.EventObserver

@AndroidEntryPoint
class SubCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubCategoryBinding
    private val viewModel: MainMcqViewModel by viewModels()
    private var categoryType = 0
    private var categoryId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.categoryListRv.layoutManager = LinearLayoutManager(this)
        categoryType = intent.getIntExtra("categoryPosition", 0)
        categoryId = intent.getIntExtra("categoryId", 0)
        viewModel.getSubCategoryList(categoryType, categoryId)
        Log.d("jejeje ", "SubCategoryActivity onCreate categoryType: " + categoryType)
        Log.d("jejeje ", "SubCategoryActivity onCreate categoryId:.............. " + categoryId)
        lifecycleScope.launch {
            viewModel.subCategoryMcqCase.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding.loader.visibility = View.VISIBLE
                        Log.d("jejeje ", "SubCategoryActivity Loading: " + it.data)

                    }
                    is Resource.Valid -> {
                        if (it.data.isEmpty()) {
                            showEmptyList()
                        } else {
                            hideEmptyList()
                            binding.titleBar.text = it.data[0].category!!.name
                            binding.categoryListRv.adapter =
                                McqAdapter(this@SubCategoryActivity, it.data)
                        }
                        Log.d("jejeje ", "SubCategoryActivity Valid: " + it.data)

                    }
                    is Resource.Invalid -> {
                        hideEmptyList()
                        Toast.makeText(this@SubCategoryActivity, it.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun showEmptyList() {
        binding.loader.visibility = View.GONE
        binding.noDataFoundWrapper.visibility = View.VISIBLE
    }

    private fun hideEmptyList() {
        binding.noDataFoundWrapper.visibility = View.GONE
        binding.loader.visibility = View.GONE
    }

}