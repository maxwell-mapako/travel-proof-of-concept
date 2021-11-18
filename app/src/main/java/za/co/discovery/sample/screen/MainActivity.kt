package za.co.discovery.sample.screen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import za.co.discovery.sample.screen.adapter.ImageAdapter
import za.co.discovery.sample.core.AbstractActivity
import za.co.discovery.sample.core.extensions.gone
import za.co.discovery.sample.core.extensions.visible
import za.co.discovery.sample.databinding.ActivityMainBinding
import za.co.discovery.sample.domain.Content
import za.co.discovery.sample.screen.viewmodel.MainViewModel
import za.co.discovery.sample.screen.viewmodel.contract.IMainViewModel

class MainActivity : AbstractActivity<ActivityMainBinding>() {

    private val imageAdapter: ImageAdapter = ImageAdapter()
    private val viewModel: IMainViewModel by viewModels<MainViewModel>()

    private fun setUpViewModelObservers() {
        viewModel.model.observe(this) {
            onData(it)
        }
        viewModel.loading.observe(this) {
            if (it) {
                requireBinding().recyclerView.gone()
                requireBinding().loadingSpinner.visible()
            }
            else {
                requireBinding().loadingSpinner.gone()
                requireBinding().recyclerView.visible()
            }
        }
    }

    private fun onData(content: List<Content>) {
        imageAdapter.submitList(content)
    }

    private fun setUpRecycler() {
        val flexboxLayoutManager = FlexboxLayoutManager(
            applicationContext,
            FlexDirection.ROW,
            FlexWrap.WRAP
        )

        with (requireBinding()) {
            recyclerView.layoutManager = flexboxLayoutManager
            recyclerView.adapter = imageAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(requireBinding().root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setUpRecycler()
        lifecycleScope.launchWhenStarted {
            setUpViewModelObservers()
        }
        lifecycleScope.launchWhenResumed {
            viewModel.requestData()
        }
    }
}