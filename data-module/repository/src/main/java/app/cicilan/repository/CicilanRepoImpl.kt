package app.cicilan.repository

import android.net.Uri
import androidx.room.withTransaction
import app.cicilan.db.CicilanDb
import app.cicilan.db.DbProvider
import app.cicilan.entities.ItemEntity
import app.cicilan.entities.ItemLogEntity
import app.cicilan.entities.ModalForm
import app.cicilan.repositories.CicilanRepository
import app.cicilan.util.currentDate
import kotlinx.coroutines.runBlocking
import java.io.File

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class CicilanRepoImpl(
    private val databaseProvider: DbProvider,
) : CicilanRepository {

    private val db by lazy {
        databaseProvider.provide("cicilan", CicilanDb::class.java)
    }

    private val dao = db.cicilanDao()
    override suspend fun insert(add: ModalForm) {
        val nominalMembayar = add.hargaBarang - add.uangMuka
        val perBulan = nominalMembayar / add.periode
        val laba = (nominalMembayar * 0.05).toInt()
        val totalLaba = laba * add.periode
        val nominalPerBulan = (perBulan + laba)
        val item = ItemEntity(
            add.id!!, currentDate, null, add.gambarBarang, add.namaPenyicil,
            add.namaBarang, add.kategori, add.hargaBarang, add.uangMuka, nominalMembayar,
            0, add.periode, add.tenggatBayar, perBulan, laba, nominalPerBulan, totalLaba, "NO",
        )
        dao.insertCicilan(item)
    }

    override suspend fun update(update: ModalForm) {
        if (update.id != null) {
            val set = dao.getCicilanById(update.id!!)
            set.collect {
                val image = if (update.gambarBarang == it?.gambarBarang) {
                    it?.gambarBarang
                } else {
                    update.gambarBarang
                }
                val nominalMembayar = update.hargaBarang - update.uangMuka
                val perBulan = nominalMembayar / update.periode
                val laba = (nominalMembayar * 0.05).toInt()
                val totalLaba = laba * update.periode
                val nominalPerBulan = (perBulan + laba)

                val item = ItemEntity(
                    it?.idCicilan!!, it.dibuatPada, null, image, update.namaPenyicil,
                    update.namaBarang, update.kategori, update.hargaBarang, update.uangMuka,
                    nominalMembayar, it.nominalLunas, update.periode, update.tenggatBayar,
                    perBulan, laba, nominalPerBulan, totalLaba, "NO",
                )
                dao.updateItem(item)
            }
        }
    }

    override suspend fun updateNominal(itemLog: ItemLogEntity) {
        val nominalBayar = dao.getCurrentNominalBayar(itemLog.idCicilan)
        val nominalLunas = dao.getCurrentNominalLunas(itemLog.idCicilan)
        val currentNominaLunas = itemLog.nominalTransaksi + nominalLunas

        if (currentNominaLunas == nominalBayar) {
            runBlocking {
                db.withTransaction {
                    dao.setNominalLunas(itemLog.idCicilan, itemLog.nominalTransaksi)
                    dao.addCicilanLog(itemLog)
                }
                db.withTransaction {
                    dao.setStatusLunas(itemLog.idCicilan, "YES")
                    dao.setDateLunas(itemLog.idCicilan, currentDate)
                }
            }
        } else {
            db.withTransaction {
                dao.setNominalLunas(itemLog.idCicilan, itemLog.nominalTransaksi)
                dao.addCicilanLog(itemLog)
            }
        }
    }

    override suspend fun delete(id: String) {
        db.withTransaction {
            with(dao) {
                deleteFromCicilan(id)
                deleteFromCicilanLog(id)
                getImagePathById(id).also {
                    Uri.parse(it).path?.let { path ->
                        File(path).delete()
                    }
                }
            }
        }
    }
}
