package com.fivesol.kapsulemcqs.presentation.main_category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fivesol.kapsulemcqs.databinding.ActivityMainBinding
import com.fivesol.kapsulemcqs.presentation.MainMcqViewModel
import com.fivesol.kapsulemcqs.presentation.dialogue.NetworkErrorDialogue
import com.fivesol.kapsulemcqs.presentation.old.MainCategoryListener
import com.fivesol.kapsulemcqs.presentation.old.PostAdapter
import com.fivesol.kapsulemcqs.presentation.old.SubCategoryActivity
import com.googleads.AdsManagement
import com.googleads.BannerAdsManagement
import com.googleads.ads_type.InterstitialTypeAds
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.domain.models.main_category.Data
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainMcqViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var googleAdsManagement: AdsManagement

    private var networkErrorDialogue: NetworkErrorDialogue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        binding.cateList.layoutManager = mLayoutManager
        setObservables()
        BannerAdsManagement.bannerAds(this,binding.bannerAdView)
    }

    private fun setObservables() {
        binding.loader.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.mainCategory().collect {
                when (it) {
                    is Resource.Loading -> {
                        Log.d("jejeje ", "setObservables:Loading " + it.data)
//                        it.data?.let { categoryList ->
//                            binding.loader.visibility = View.GONE
////                            setAdapter(categoryList)
//                        }
                    }
                    is Resource.Valid -> {
                        Log.d("jejeje ", "setObservables:Valid " + it.data)
                        if (it.data.isEmpty()) {
                            showEmptyList()
                        } else {
                            setAdapter(it.data)
                            hideEmptyList()
                        }
                    }
                    is Resource.Invalid -> {
                        Log.e("jejeje ", "setObservables:Invalid " + it.data)
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
//                    showErrorDialogue()
                    }
                }
            }
        }

    }

    private fun showEmptyList() {
        binding.noDataFoundWrapper.visibility = View.VISIBLE
        binding.loader.visibility = View.GONE
    }

    private fun hideEmptyList() {
        binding.noDataFoundWrapper.visibility = View.GONE
        binding.loader.visibility = View.GONE
    }

    private fun setAdapter(categoryList: List<Data>) {
        binding.cateList.setAdapter(
            PostAdapter(
                this@MainActivity,
                categoryList,
                onCategoryClick
            )
        )
    }

    private fun showErrorDialogue() {
        networkErrorDialogue = NetworkErrorDialogue(this) {
            networkErrorDialogue = null
            finishAffinity()
        }
        networkErrorDialogue?.show()
    }

    val onCategoryClick = object : MainCategoryListener {
        override fun onMainCategoryClick(categoryPosition: Int, categoryId: Int) {
            googleAdsManagement.createInterstitialAd {
                type = InterstitialTypeAds.DEFAULT_SELECTION
                onAdDismiss = {
                    gotToSubCategory(categoryPosition, categoryId)
                }
                onAdFailed = {
                    gotToSubCategory(categoryPosition, categoryId)
                }
            }.showCached(this@MainActivity)

        }

    }

    private fun gotToSubCategory(categoryPosition: Int, categoryId: Int) {
        startActivity(
            Intent(
                this@MainActivity,
                SubCategoryActivity::class.java
            ).putExtra("categoryPosition", categoryPosition)
                .putExtra("categoryId", categoryId)
        )
    }
}