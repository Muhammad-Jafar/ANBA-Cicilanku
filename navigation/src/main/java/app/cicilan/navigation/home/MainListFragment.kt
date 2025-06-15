package app.cicilan.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import app.cicilan.navigation.databinding.MainHomeListBinding
import app.cicilan.navigation.home.HomeFragment.Companion.ARGS_TAB
import app.cicilan.navigation.home.current.CurrentAdapter
import app.cicilan.navigation.home.done.DoneAdapter

class MainListFragment : Fragment() {
    private var _binding: MainHomeListBinding? = null
    private val binding get() = _binding!!

    /*private val viewModel: HomeViewModel by viewModel()*/
    private val currentAdapter = CurrentAdapter().apply { stateRestorationPolicy = PREVENT_WHEN_EMPTY }
    private val doneAdapter = DoneAdapter().apply { stateRestorationPolicy = PREVENT_WHEN_EMPTY }
    private var tabName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = MainHomeListBinding.inflate(
            LayoutInflater.from(parentFragment?.context),
            container,
            false,
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabName = arguments?.getString(ARGS_TAB)
        with(binding) {
            /*viewModel.apply {
                if (tabName == TAB_CURRENT) {
                    rvCicilanBerjalan.adapter = currentAdapter.also {
                        getCurrent()
                        getCurrentList.onEach { state ->
                            when (state) {
                                is app.cicilan.model.ListState.Empty -> {
                                    progressBar.visibility = View.GONE
                                    rvCicilanBerjalan.visibility = View.GONE
                                    viewEmptyCurrent.visibility = View.VISIBLE
                                }

                                is app.cicilan.model.ListState.Success -> {
                                    progressBar.visibility = View.GONE
                                    viewEmptyCurrent.visibility = View.GONE
                                    rvCicilanBerjalan.visibility = View.VISIBLE
                                    currentAdapter.submitList(state.data)
                                }
                            }
                        }.launchIn(lifecycleScope)
                    }
                } else {
                    rvCicilanLunas.adapter = doneAdapter.also {
                        getDone()
                        getDoneList.onEach { state ->
                            when (state) {
                                is app.cicilan.model.ListState.Empty -> {
                                    progressBar.visibility = View.GONE
                                    rvCicilanBerjalan.visibility = View.GONE
                                    viewEmptyDone.visibility = View.VISIBLE
                                }

                                is app.cicilan.model.ListState.Success -> {
                                    progressBar.visibility = View.GONE
                                    viewEmptyDone.visibility = View.GONE
                                    rvCicilanLunas.visibility = View.VISIBLE
                                    doneAdapter.submitList(state.data)
                                }
                            }
                        }.launchIn(lifecycleScope)
                    }
                }
            }*/
        }
    }
}
