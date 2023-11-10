package app.cicilan.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

@Parcelize
data class ModalForm(
    val id: String?,
    val gambarBarang: String? = null,
    val namaPenyicil: String,
    val namaBarang: String,
    val kategori: String,
    val hargaBarang: Int,
    val uangMuka: Int,
    val periode: Int,
    val tenggatBayar: Int,
) : Parcelable
