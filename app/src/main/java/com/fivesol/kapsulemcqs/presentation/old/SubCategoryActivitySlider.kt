package com.fivesol.kapsulemcqs.presentation.old

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.fivesol.kapsulemcqs.databinding.ActivitySubCategorySliderBinding
import com.fivesol.kapsulemcqs.presentation.MainMcqViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.utils.EventObserver

@AndroidEntryPoint
class SubCategoryActivitySlider : AppCompatActivity() {
    lateinit var binding: ActivitySubCategorySliderBinding
    private val viewModel: MainMcqViewModel by viewModels()
    private var categoryType = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubCategorySliderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryType = intent.getIntExtra("categoryPosition",0)
        viewModel.getSubCategoryList(categoryType,0)
//        binding.titleBar.text = it.data.!!.name
        Log.d("jejeje ", "SubCategoryActivity onCreate categoryType: "+categoryType)
        lifecycleScope.launch {
            viewModel.subCategoryMcqCase.collect {
                when(it){
                    is Resource.Loading->{
                        binding.loader.visibility = View.VISIBLE
                    }
                    is Resource.Valid->{
                        binding.loader.visibility = View.GONE
                        binding.loader.visibility = View.GONE
                        binding.categoryListRv.adapter = McqAdapter( this@SubCategoryActivitySlider,it.data)
                    }
                    is Resource.Invalid->{
                        binding.loader.visibility = View.GONE

                    }
                }
            }
        }

//        viewModel.subCategoryMcgs.observe(this, EventObserver {
//            binding.loader.visibility = View.VISIBLE
//            it.mcqs?.let { mcqList ->
//                binding.titleBar.text = it.category!!.name
//                binding.loader.visibility = View.GONE
//                binding.categoryListRv.adapter = McqAdapter( this,mcqList,it.category!!.name!!)
//            }
//            Log.d("jejeje ", "SubCategoryActivity onCreate: ${it.mcqs}")
//        })
    }
}