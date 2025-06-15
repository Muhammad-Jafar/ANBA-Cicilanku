package app.cicilan.navigation.home.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.cicilan.entities.UiState
import app.cicilan.navigation.BaseFragment
import app.cicilan.navigation.DetailViewModel
import app.cicilan.navigation.databinding.MainDetailLogBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class DetailLogFragment : BaseFragment<MainDetailLogBinding>(MainDetailLogBinding::inflate) {

    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailLogFragmentArgs by navArgs()
    private val logAdapter = DetailLogAdapter()

    override fun renderView(bundle: Bundle?) {
        viewModel.cicilanId = args.cicilanId
        with(binding) {
            toolbarDetailLog.setNavigationOnClickListener { findNavController().navigateUp() }
            rvDetailLog.adapter = logAdapter
            viewModel.apply {
                getLog(args.cicilanId)
                loadLogCicilan.onEach { state ->
                    when (state) {
                        is UiState.Loading -> {}
                        is UiState.Error -> {}
                        is UiState.Success -> {
                            if (state.data != null) {
                                viewEmpty.visibility = View.GONE
                                logAdapter.submitList(state.data)
                            } else {
                                viewEmpty.visibility = View.VISIBLE
                            }
                        }
                    }
                }.launchIn(lifecycleScope)
            }
        }
    }
}
