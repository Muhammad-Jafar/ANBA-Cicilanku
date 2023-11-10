package app.cicilan.presentation.settings.donate

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import app.cicilan.presentation.BaseFragment
import app.cicilan.presentation.databinding.MainSettingsDonateBinding

class DonateFragment : BaseFragment<MainSettingsDonateBinding>(MainSettingsDonateBinding::inflate) {
    override fun renderView(bundle: Bundle?) {
        with(binding) {
            toolbarDonate.setNavigationOnClickListener { findNavController().navigateUp() }
        }
    }
}
