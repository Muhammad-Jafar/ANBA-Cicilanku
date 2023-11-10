package app.cicilan.presentation.home.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.cicilan.entities.State
import app.cicilan.presentation.BaseFragment
import app.cicilan.presentation.DetailViewModel
import app.cicilan.presentation.databinding.MainDetailLogBinding
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
                        is State.Loading -> {}
                        is State.Error -> {}
                        is State.Empty -> viewEmpty.visibility = View.VISIBLE
                        is State.Success -> {
                            viewEmpty.visibility = View.GONE
                            logAdapter.submitList(state.data)
                        }
                    }
                }.launchIn(lifecycleScope)
            }
        }
    }
}
