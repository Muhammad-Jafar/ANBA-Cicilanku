package app.cicilan.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

@Entity()
data class ItemLog(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_log")
    val idLog: Int,
    @ColumnInfo(name = "id_cicilan") val idCicilan: Int,
    @ColumnInfo(name = "tgl_transaksi") val tglTransaksi: Long,
    @ColumnInfo(name = "nominal_transaksi") val nominalTransaksi: Int,
    @ColumnInfo(name = "catatan") val catatan: String?,
)