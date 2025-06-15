package app.cicilan.navigation

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.cicilan.component.util.mapWithStateInWhileSubscribed
import app.cicilan.component.util.runInBackground
import app.cicilan.entities.CalculateState
import app.cicilan.entities.ItemLog
import app.cicilan.entities.ModalForm
import app.cicilan.entities.UiState
import app.cicilan.repositories.contracts.CicilanRepository
import app.cicilan.repositories.contracts.SettingRepository
import app.cicilan.usecases.CountCicilanUseCase
import app.cicilan.usecases.GetListCicilanUseCase
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

open class MainViewModel : ViewModel()

class HomeViewModel(
    countCicilan: CountCicilanUseCase,
    private val getCicilan: GetListCicilanUseCase,
) : MainViewModel() {

    val getTotalCurrent = countCicilan("NO")
        .mapWithStateInWhileSubscribed(0)
    val getTotalDone = countCicilan("YES")
        .mapWithStateInWhileSubscribed(0)

    fun getList(status: String) = getCicilan(status)
        .mapWithStateInWhileSubscribed(listOf())

    private val _perBulanValue = MutableLiveData(CalculateState())
    val perBulanValue get() = _perBulanValue
    var labaValue = 0

    fun calculate(harga: Int, dp: Int, periode: Int) {
        when {
            harga < 1 -> _perBulanValue.value = CalculateState(hargaError = R.string.fill_data)
            dp < 1 -> _perBulanValue.value = CalculateState(dpError = R.string.fill_data)
            dp > harga -> _perBulanValue.value = CalculateState(dpError = R.string.form_dp_lessThan_harga)
            periode < 1 -> _perBulanValue.value = CalculateState(periodeError = R.string.fill_data)
            else -> {
                val nominalMembayar = harga - dp
                val nominalPerBulan = (nominalMembayar / periode)
                val laba = (nominalMembayar * 0.05).toInt()
                _perBulanValue.value =
                    CalculateState(isResultThere = nominalPerBulan)
                labaValue = laba
            }
        }
    }
}

class FormViewModel(private val repo: CicilanRepository) : MainViewModel() {
    var imageUri: Uri? = null

    private val _dataModal = MutableSharedFlow<ModalForm>()
    val dataModal get() = _dataModal

    fun save(item: ModalForm) = runInBackground { repo.insert(item) }
}

class DetailViewModel(
    /*private val cicilanLog: GetListCicilanLogUseCase,
    private val useCaseViewer: CicilanViewerUseCase,*/
) : MainViewModel() {
    var cicilanId = 0

    /*private val _getDetail = MutableStateFlow<State>(State.Empty)
    val loadDetail get() = _getDetail
    fun getDetail(id: String) = runInBackground {
        useCaseViewer.getById(id).run {
            _getDetail.value = State.Success(this.toString())
        }
    }*/

    private val _getLog = MutableSharedFlow<UiState<List<ItemLog>>>(1)
    val loadLogCicilan get() = _getLog
    fun getLog(id: Int) = runInBackground {
        /*cicilanLog.invoke(id).collectLatest { state ->
            when (state) {
                is State.Loading -> {}
                is State.Empty -> {}
                is State.Error -> state.cause
                is State.Success -> state.data
            }
        }*/
    }

    /*fun updateNominal(item: ItemLogEntity) = runInBackground { repo.updateNominal(item) }
    fun delete(cicilanId: String) = runInBackground { repo.delete(cicilanId) }*/
}

class SettingsViewModel(private val repo: SettingRepository) : MainViewModel() {
    val getTheme = repo.getTheme()
    val getLanguage = repo.getLang()

    fun saveThemeValue(value: Int) =
        runInBackground { repo.saveTheme(value) }

    fun saveLangValue(value: String) =
        runInBackground { repo.saveLang(value) }
}
