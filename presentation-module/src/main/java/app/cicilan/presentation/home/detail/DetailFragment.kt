package app.cicilan.presentation.home.detail

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.cicilan.presentation.BaseFragment
import app.cicilan.presentation.DetailViewModel
import app.cicilan.presentation.R
import app.cicilan.presentation.databinding.DialogInputNominalBinding
import app.cicilan.presentation.databinding.MainDetailBinding
import app.cicilan.util.addAutoConverterToMoneyFormat
import app.cicilan.util.afterInputNumberChanged
import app.cicilan.util.dotPixel
import app.cicilan.util.getNumber
import app.cicilan.util.rupiahFormat
import app.cicilan.util.showMessage
import app.cicilan.util.showSoftKeyboard
import app.cicilan.util.vibrate
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<MainDetailBinding>(MainDetailBinding::inflate) {
    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()

    override fun renderView(bundle: Bundle?) {
        viewModel.cicilanId = args.cicilanId
        binding.toolbarDetail.setNavigationOnClickListener { findNavController().navigateUp() }
        viewModel.apply {
            /*getDetail(args.cicilanId)
            loadDetail.onEach { state ->
                when (state) {
                    is State.Load -> setDetailData(state.data)
                }
            }.launchIn(lifecycleScope)*/
        }
    }

    /*private fun setDetailData(data: Map<app.cicilan.model.CicilanEntity, app.cicilan.model.ItemEntity>) = with(binding) {
        data.keys.map { cicilan ->
            sectionName.setContentLayout(cicilan.namaPenyicil)
            sectionCreatedAt.setContentLayout(cicilan.dibuatPadaFormat)
            sectionDoneAt.setContentLayout(cicilan.lunasPadaFormat)

            sectionLabaPerBulan.setContentItem(cicilan.labaPerBulanToRupiah.plus(" ${getString(R.string.per_bulan)}"))
            sectionTotalLaba.setContentItem("+ ".plus(cicilan.totalLabaToRupiah))

            sectionNominalPerBulan.setContentItem(cicilan.nominalPerBulanToRupiah)
            sectionTotalPerBulan.setContentItem(cicilan.nominalPerBulanToRupiah)

            deleteButton.setOnClickListener {
                popupDialog(
                    getString(R.string.delete_button),
                    getString(R.string.confirm_delete_data),
                ).setPositiveButton(getString(R.string.delete_button)) { dialog, _ ->
                    viewModel.delete(cicilan.idCicilan)
                    dialog.dismiss()
                    showMessage(getString(R.string.deleted_successfully))
                    findNavController().navigateUp()
                }.show()
            }

            *//*data.values.map { item ->
                toolbarDetail.title = item.namaBarang
                avatarPreview.apply {
                    scaleType = if (item.gambarBarang != null) {
                        setImageURI(item.gambarBarang.toUri())
                        ImageView.ScaleType.CENTER_CROP
                    } else {
                        setImageResource(R.drawable.icon_bg_image)
                        ImageView.ScaleType.CENTER
                    }
                }
                sectionCategory.setContentLayout(item.kategori)

                targetProgressBar.apply {
                    max = item.nominalBayar
                    setProgressCompat(item.nominalLunas, true)
                }
                val persen =
                    ((item.nominalLunas.toFloat() / item.nominalBayar.toFloat()) * 100F).roundToInt()
                dataPersen.text = persen.toString()

                sectionLunas.apply {
                    setTextColor(
                        valueOf(
                            MaterialColors.getColor(
                                rootView,
                                R.attr.colorOnLeafContainer,
                            ),
                        ),
                    )
                    setContentLayout(item.nominalLunasToRupiah)
                }
                sectionLunasYet.apply {
                    setTextColor(
                        valueOf(
                            MaterialColors.getColor(
                                rootView,
                                R.attr.colorOnRoseContainer,
                            ),
                        ),
                    )
                    setContentLayout(rupiahFormat(item.nominalBayar - item.nominalLunas))
                }

                sectionThingPrice.setContentItem(item.hargaBarangtoRupiah)
                sectionFirstPay.setContentItem(item.uangMukatoRupiah)
                sectionnominalCicilan.setContentItem(item.nominalBayarToRupiah)

                sectionPeriode.setContentItem(item.periode.toString())
                sectionTenggat.setContentItem(item.tenggatBayar)

                editButton.setOnClickListener {
                    val dataModal = app.cicilan.model.Modal(
                        cicilan.idCicilan,
                        item.gambarBarang,
                        cicilan.namaPenyicil,
                        item.namaBarang,
                        item.kategori,
                        item.hargaBarang,
                        item.uangMuka,
                        item.periode,
                        item.tenggatBayar,
                    )
                    if (cicilan.status == "YES") {
                        showMessage(getString(R.string.edit_data_lunas))
                    } else {
                        return@setOnClickListener
                    }
                    findNavController()
                        .navigate(DetailFragmentDirections.actionDetailBerjalanToForm(dataModal))
                }

                payDebtButton.apply {
                    if (cicilan.status == "YES") {
                        isClickable = false
                        text = getString(R.string.lunas_button_desc)
                    } else {
                        setOnClickListener {
                            doPayCicilan(
                                item.nominalLunas,
                                item.nominalBayar,
                                cicilan.nominalPerBulan,
                            )
                        }
                    }
                }
            }*//*
        }

        logTransactionButton.setOnClickListener {
            val destinationLog = DetailFragmentDirections.actionDetailBerjalanToLog(args.cicilanId)
            findNavController().navigate(destinationLog)
        }
    }*/

    private fun doPayCicilan(lunas: Int, utang: Int, nominalBayar: Int) {
        val inputForm = DialogInputNominalBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext()).apply {
            setContentView(inputForm.root)
            behavior.maxHeight = 800.dotPixel()
            dismissWithAnimation = true
        }

        with(inputForm) {
            root.doOnPreDraw { dialog.behavior.peekHeight = it.height }
            fun validateInput(): Boolean = nominalInput.text.getNumber().let {
                if (it < (utang - lunas)) {
                    nominalInputLayout.error = when {
                        it < 1 -> getString(R.string.fill_data)
                        it < 100000 -> getString(R.string.input_min_limit)
                        it > (utang - lunas) -> getString(R.string.input_fill_over)
                        else -> return true
                    }
                } else {
                    nominalInputLayout.error = when {
                        it > (utang - lunas) -> getString(R.string.input_fill_over)
                        else -> return true
                    }
                }
                return true
            }
            with(nominalInput) {
                requestFocus()
                showSoftKeyboard()
                afterInputNumberChanged {
                    doInputButton.isEnabled = nominalInput.text.getNumber() > 1
                    validateInput()
                }
                addAutoConverterToMoneyFormat(nominalInputLayout)
                hint = rupiahFormat(nominalBayar)
            }

            noteInput.apply {
                hint = getString(R.string.note_here)
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        nominalInput.text.getNumber().let {
                            if (it < (utang - lunas)) {
                                when {
                                    it < 1 -> getString(R.string.fill_data)
                                    it < 100000 -> showMessage(getString(R.string.input_min_limit))
                                    it > (utang - lunas) -> showMessage(getString(R.string.input_fill_over))
                                    else -> {
                                        /*val dataInput = ItemLogEntity(
                                            0,
                                            args.cicilanId,
                                            currentDate,
                                            it,
                                            noteInput.text.toString(),
                                        )
                                        viewModel.updateNominal(dataInput)*/
                                        dialog.dismiss()
                                    }
                                }
                            } else {
                                when {
                                    it > (utang - lunas) -> getString(R.string.input_fill_over)
                                    else -> {
                                        /*val dataInput = ItemLogEntity(
                                            0,
                                            args.cicilanId,
                                            currentDate,
                                            it,
                                            noteInput.text.toString(),
                                        )
                                        viewModel.updateNominal(dataInput)*/
                                        dialog.dismiss()
                                    }
                                }
                            }
                        }
                    }
                    false
                }
                doInputButton.setOnClickListener {
                    nominalInput.text.getNumber().let {
                        if (it < (utang - lunas)) {
                            nominalInputLayout.apply {
                                startAnimation(
                                    AnimationUtils.loadAnimation(
                                        requireContext(),
                                        R.anim.shake,
                                    ),
                                )
                                vibrate()
                            }
                            when {
                                it < 1 -> getString(R.string.fill_data)
                                it < 100000 -> getString(R.string.input_min_limit)
                                it > (utang - lunas) -> getString(R.string.input_fill_over)
                                else -> {
                                    /*val dataInput = ItemLogEntity(
                                        0,
                                        args.cicilanId,
                                        currentDate,
                                        it,
                                        noteInput.text.toString(),
                                    )
                                    viewModel.updateNominal(dataInput)*/
                                    dialog.dismiss()
                                }
                            }
                        } else {
                            nominalInputLayout.apply {
                                startAnimation(
                                    AnimationUtils.loadAnimation(
                                        requireContext(),
                                        R.anim.shake,
                                    ),
                                )
                                vibrate()
                            }
                            when {
                                it > (utang - lunas) -> getString(R.string.input_fill_over)
                                else -> {
                                    /*val dataInput = ItemLogEntity(
                                        0,
                                        args.cicilanId,
                                        currentDate,
                                        it,
                                        noteInput.text.toString(),
                                    )
                                    viewModel.updateNominal(dataInput)*/
                                    dialog.dismiss()
                                }
                            }
                        }
                    }
                }
            }
            cancelButton.setOnClickListener { dialog.dismiss() }
        }
        dialog.show()
    }
}
