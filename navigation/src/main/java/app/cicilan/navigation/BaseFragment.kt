package app.cicilan.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.transition.FadeProvider
import com.google.android.material.transition.MaterialSharedAxis

abstract class BaseFragment<T : ViewBinding>(private val setupBinding: (LayoutInflater, ViewGroup?, Boolean) -> T) :
    Fragment() {

    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        var animation = super.onCreateAnimation(transit, enter, nextAnim)
        if (animation == null && nextAnim != 0) {
            animation =
                AnimationUtils.loadAnimation(context, nextAnim)
        }
        animation?.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationRepeat(p0: Animation?) {
                view?.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            }

            override fun onAnimationStart(p0: Animation?) {
                view?.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            }

            override fun onAnimationEnd(p0: Animation?) {
                view?.setLayerType(View.LAYER_TYPE_NONE, null)
            }
        })
        return animation
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        registerTracer()
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
            .apply { secondaryAnimatorProvider = FadeProvider() }
            .setInterpolator(DecelerateInterpolator())
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
            .apply { secondaryAnimatorProvider = FadeProvider() }
            .setInterpolator(DecelerateInterpolator())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?):
        View? {
        _binding = setupBinding(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        renderView(bundle)
    }

    abstract fun renderView(bundle: Bundle?)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
