package app.cicilan.repositories.repository

import androidx.core.net.toUri
import androidx.room.withTransaction
import app.cicilan.component.util.currentDate
import app.cicilan.entities.Item
import app.cicilan.entities.ItemLog
import app.cicilan.entities.ModalForm
import app.cicilan.local.db.CicilanDao
import app.cicilan.local.db.CicilanDb
import app.cicilan.repositories.contracts.CicilanRepository
import java.io.File
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class CicilanRepoImpl(
    private val db: CicilanDb,
    private val dao: CicilanDao,
) : CicilanRepository {

    override fun get(status: String): Flow<List<Item>> =
        flow {
            val list = dao.getListCicilan(status)

            if (list != null) emit(list)
            else emit(listOf())
        }

    override fun getById(id: Int): Flow<Item> =
        flow {
            val getCicilan = dao.getCicilanById(id)

            emit(getCicilan)
        }

    override fun count(status: String): Flow<Int> =
        flow {
            val counted = dao.countCicilan(status)

            if (counted != null) emit(counted)
            else emit(0)
        }

    override suspend fun insert(add: ModalForm) {
        val nominalMembayar = add.hargaBarang - add.uangMuka
        val perBulan = nominalMembayar / add.periode
        val laba = (nominalMembayar * 0.05).toInt()
        val totalLaba = laba * add.periode
        val nominalPerBulan = (perBulan + laba)
        val item = Item(
            add.id!!, currentDate, null, add.gambarBarang, add.namaPenyicil,
            add.namaBarang, add.kategori, add.hargaBarang, add.uangMuka, nominalMembayar,
            0, add.periode, add.tenggatBayar, perBulan, laba, nominalPerBulan, totalLaba, "NO",
        )
        dao.insertCicilan(item)
    }

    override suspend fun update(update: ModalForm) {
        if (update.id != null) {
            val cicilan = dao.getCicilanById(update.id!!)

            with(cicilan) {
                val image =
                    if (update.gambarBarang == gambarBarang) {
                        gambarBarang
                    } else {
                        update.gambarBarang
                    }
                val nominalMembayar = update.hargaBarang - update.uangMuka
                val perBulan = nominalMembayar / update.periode
                val laba = (nominalMembayar * 0.05).toInt()
                val totalLaba = laba * update.periode
                val nominalPerBulan = (perBulan + laba)

                val item = Item(
                    idCicilan, dibuatPada, null, image, update.namaPenyicil,
                    update.namaBarang, update.kategori, update.hargaBarang, update.uangMuka,
                    nominalMembayar, nominalLunas, update.periode, update.tenggatBayar,
                    perBulan, laba, nominalPerBulan, totalLaba, "NO",
                )
                dao.updateItem(item)
            }
        }
    }

    override suspend fun updateNominal(itemLog: ItemLog) {
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

    override suspend fun delete(id: Int) {
        db.withTransaction {
            with(dao) {
                deleteFromCicilan(id)
                deleteFromCicilanLog(id)
                getImagePathById(id).also {
                    it?.toUri()
                        ?.path
                        ?.let { path -> File(path).delete() }
                }
            }
        }
    }
}
