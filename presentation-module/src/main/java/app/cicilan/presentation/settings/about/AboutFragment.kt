package app.cicilan.presentation.settings.about

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import app.cicilan.presentation.BaseFragment
import app.cicilan.presentation.databinding.MainSettingsAboutBinding

class AboutFragment : BaseFragment<MainSettingsAboutBinding>(MainSettingsAboutBinding::inflate) {

    override fun renderView(bundle: Bundle?) {
        with(binding) {
            toolbarAbout.setNavigationOnClickListener { findNavController().navigateUp() }
        }
    }
}
