package app.cicilan.navigation.settings.about

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import app.cicilan.navigation.BaseFragment
import app.cicilan.navigation.databinding.MainSettingsAboutBinding

class AboutFragment : BaseFragment<MainSettingsAboutBinding>(MainSettingsAboutBinding::inflate) {

    override fun renderView(bundle: Bundle?) {
        with(binding) {
            toolbarAbout.setNavigationOnClickListener { findNavController().navigateUp() }
        }
    }
}
