package app.cicilan.navigation.settings.donate

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import app.cicilan.navigation.BaseFragment
import app.cicilan.navigation.databinding.MainSettingsDonateBinding

class DonateFragment : BaseFragment<MainSettingsDonateBinding>(MainSettingsDonateBinding::inflate) {
    override fun renderView(bundle: Bundle?) {
        with(binding) {
            toolbarDonate.setNavigationOnClickListener { findNavController().navigateUp() }
        }
    }
}
