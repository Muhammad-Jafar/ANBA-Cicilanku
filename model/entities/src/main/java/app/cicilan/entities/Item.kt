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
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_cicilan")
    val idCicilan: Int,
    @ColumnInfo(name = "dibuat_pada")
    val dibuatPada: Long,
    @ColumnInfo(name = "lunas_pada")
    val lunasPada: Long?,
    @ColumnInfo(name = "gambar_barang")
    val gambarBarang: String?,
    @ColumnInfo(name = "nama_penyicil")
    val namaPenyicil: String,
    @ColumnInfo(name = "nama_barang")
    val namaBarang: String,
    @ColumnInfo(name = "kategori")
    val kategori: String,
    @ColumnInfo(name = "harga_barang")
    val hargaBarang: Int,
    @ColumnInfo(name = "uang_muka")
    val uangMuka: Int,
    @ColumnInfo(name = "nominal_bayar")
    val nominalBayar: Int, // harga_barang - uang_muka
    @ColumnInfo(name = "nominal_lunas")
    val nominalLunas: Int?, // nominal_per_bulan - nominal_bayar
    @ColumnInfo(name = "periode")
    val periode: Int,
    @ColumnInfo(name = "tenggat_bayar")
    val tenggatBayar: Int,
    @ColumnInfo(name = "per_bulan")
    val perBulan: Int, // nominal_bayar : periode (Pure utang tanpa laba)
    @ColumnInfo(name = "laba_per_bulan")
    val labaPerBulan: Int, // nominal_bayar * 5%
    @ColumnInfo(name = "nominal_per_bulan")
    val nominalPerBulan: Int, // per bulan + laba
    @ColumnInfo(name = "total_laba")
    val totalLaba: Int,
    @ColumnInfo(name = "status")
    val status: String,
)
