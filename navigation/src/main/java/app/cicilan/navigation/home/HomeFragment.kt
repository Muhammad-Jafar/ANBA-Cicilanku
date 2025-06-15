package app.cicilan.navigation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import app.cicilan.component.util.addAutoConverterToMoneyFormat
import app.cicilan.component.util.afterInputNumberChanged
import app.cicilan.component.util.getNumber
import app.cicilan.component.util.runWhenResumed
import app.cicilan.component.util.runWhenStarted
import app.cicilan.component.util.rupiahFormat
import app.cicilan.navigation.BaseFragment
import app.cicilan.navigation.HomeViewModel
import app.cicilan.navigation.R
import app.cicilan.navigation.databinding.DialogCalculateCicilanBinding
import app.cicilan.navigation.databinding.MainHomeBinding
import com.google.android.material.color.MaterialColors.getColor
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<MainHomeBinding>(MainHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModel()

    override fun renderView(bundle: Bundle?) {
        with(binding) {
            with(viewModel) {
                runWhenStarted {
                    launch { getTotalCurrent.collectLatest { belumLunasContent.text = it.toString() } }
                    launch { getTotalDone.collectLatest { sudahLunasContent.text = it.toString() } }
                }
            }
            toolbarHome.apply {
                inflateMenu(R.menu.main_option_menu)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.settingMenu -> findNavController().navigate(R.id.action_main_to_settings)
                        R.id.prediksiCicilan -> showCalculate()
                    }
                    true
                }
            }
            addItem.setOnClickListener { findNavController().navigate(R.id.action_main_to_form) }

            viewOfTabs.adapter = SectionPagerAdapter()
            viewOfTabs.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if (position == 0) {
                        addItem.show()
                    } else {
                        addItem.hide()
                    }
                    super.onPageSelected(position)
                }
            })

            val tabTitle = listOf(R.string.debt_now, R.string.debt_done)
            TabLayoutMediator(tabs, viewOfTabs) { tab, position ->
                tab.text = getString(tabTitle[position])
            }.attach()
        }
    }

    private fun showCalculate() {
        val dialogForm = DialogCalculateCicilanBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(requireContext(), R.style.CustomDialog).apply {
            setCancelable(false)
            setTitle("Mini calculator cicilan")
            setView(dialogForm.root)
            setPositiveButton(getString(R.string.ok_button)) { dialog, _ -> dialog.dismiss() }
        }
        with(dialogForm) {
            calculateLabel.text = rupiahFormat(0)
            labaLabel.text = rupiahFormat(0)
            hargaInput.addAutoConverterToMoneyFormat(hargaInputLayout)
            dpInput.addAutoConverterToMoneyFormat(dpInputLayout)
            periodeInput.addAutoConverterToMoneyFormat(periodeInputLayout)

            with(viewModel) {
                runWhenResumed {
                    perBulanValue.observe(viewLifecycleOwner) { state ->
                        calculateLabel.text = when {
                            state.hargaError != null -> getString(R.string.calculating)
                            state.dpError != null -> getString(R.string.calculating)
                            state.periodeError != null -> getString(R.string.calculating)
                            else -> rupiahFormat(state.isResultThere)
                        }
                        labaLabel.setTextColor(getColor(labaLabel, R.attr.colorLeaf))
                        labaLabel.text = "+ ".plus(rupiahFormat(labaValue)).plus("/ Bulan")
                    }
                }

                hargaInput.afterInputNumberChanged {
                    calculate(
                        hargaInput.text.getNumber(),
                        dpInput.text.getNumber(),
                        periodeInput.text.getNumber(),
                    )
                }
                dpInput.afterInputNumberChanged {
                    calculate(
                        hargaInput.text.getNumber(),
                        dpInput.text.getNumber(),
                        periodeInput.text.getNumber(),
                    )
                }
                periodeInput.afterInputNumberChanged {
                    calculate(
                        hargaInput.text.getNumber(),
                        dpInput.text.getNumber(),
                        periodeInput.text.getNumber(),
                    )
                }
            }
        }
        dialog.show()
    }

    inner class SectionPagerAdapter :
        FragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle) {
        override fun getItemCount(): Int = 2
        override fun createFragment(position: Int): Fragment {
            val fragment = MainListFragment()
            val data = Bundle()

            if (position == 0) {
                data.putString(ARGS_TAB, TAB_CURRENT)
            } else {
                data.putString(ARGS_TAB, TAB_DONE)
            }

            fragment.arguments = data
            return fragment
        }
    }

    companion object {
        /* Home list */
        const val ARGS_TAB = "Tab argument"
        const val TAB_CURRENT = "Tab current"
        const val TAB_DONE = "Tab done"
    }
}
