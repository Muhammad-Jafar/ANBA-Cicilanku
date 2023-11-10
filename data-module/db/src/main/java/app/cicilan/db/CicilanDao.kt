package app.cicilan.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.cicilan.entities.ItemEntity
import app.cicilan.entities.ItemLogEntity
import kotlinx.coroutines.flow.Flow

interface CicilanDao {
    @Query("SELECT COUNT (id_cicilan) FROM cicilan WHERE status = :status")
    fun countCicilan(status: String): Flow<Int?>

    @Query("SELECT * FROM cicilan WHERE status = :status ORDER BY dibuat_pada DESC")
    fun getListCicilan(status: String): Flow<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCicilan(item: ItemEntity)

    @Query("SELECT * FROM cicilan WHERE id_cicilan = :id")
    fun getCicilanById(id: String): Flow<ItemEntity?>

    @Query("SELECT * FROM cicilanLog WHERE id_cicilan = :id ORDER BY tgl_transaksi DESC")
    fun getListLogCicilan(id: String): Flow<List<ItemLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCicilanLog(itemLog: ItemLogEntity)

    @Query("SELECT nominal_bayar FROM cicilan WHERE id_cicilan = :id")
    fun getCurrentNominalBayar(id: Int): Int

    @Query("SELECT nominal_lunas FROM cicilan WHERE id_cicilan = :id")
    fun getCurrentNominalLunas(id: Int): Int

    @Query("UPDATE cicilan SET nominal_lunas = nominal_lunas + :nominal WHERE id_cicilan = :id")
    suspend fun setNominalLunas(id: Int, nominal: Int)

    @Query("UPDATE cicilan SET status = :status WHERE id_cicilan = :id")
    suspend fun setStatusLunas(id: Int, status: String)

    @Query("UPDATE cicilan SET lunas_pada = :lunasPada WHERE id_cicilan = :id")
    suspend fun setDateLunas(id: Int, lunasPada: Long)

    @Query("SELECT gambar_barang FROM cicilan WHERE id_cicilan = :id")
    suspend fun getImagePathById(id: String): String?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(item: ItemEntity)

    @Query("DELETE FROM cicilan WHERE id_cicilan = :id")
    suspend fun deleteFromCicilan(id: String)

    @Query("DELETE FROM cicilanLog WHERE id_cicilan = :id")
    suspend fun deleteFromCicilanLog(id: String)
}
