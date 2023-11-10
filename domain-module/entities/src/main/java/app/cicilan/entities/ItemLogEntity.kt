package app.cicilan.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

@Parcelize
@Entity(tableName = "cicilanLog")
data class ItemLogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_log")
    val idLog: String,
    @ColumnInfo(name = "id_cicilan") val idCicilan: Int,
    @ColumnInfo(name = "tgl_transaksi") val tglTransaksi: Long,
    @ColumnInfo(name = "nominal_transaksi") val nominalTransaksi: Int,
    @ColumnInfo(name = "catatan") val catatan: String?,
) : Parcelable, Serializable {

}
