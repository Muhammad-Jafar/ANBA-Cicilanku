package app.cicilan.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.cicilan.entities.Item
import app.cicilan.entities.ItemLog
import kotlinx.coroutines.flow.Flow

@Dao
interface CicilanDao {
    @Query("SELECT COUNT (id_cicilan) FROM item WHERE status = :status")
    fun countCicilan(status: String): Flow<Int?>

    @Query("SELECT * FROM item WHERE status = :status ORDER BY dibuat_pada DESC")
    fun getListCicilan(status: String): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCicilan(item: Item)

    @Query("SELECT * FROM item WHERE id_cicilan = :id")
    fun getCicilanById(id: Int): Flow<Item?>

    @Query("SELECT * FROM ItemLog WHERE id_cicilan = :id ORDER BY tgl_transaksi DESC")
    fun getListLogCicilan(id: Int): Flow<List<ItemLog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCicilanLog(itemLog: ItemLog)

    @Query("SELECT nominal_bayar FROM item WHERE id_cicilan = :id")
    fun getCurrentNominalBayar(id: Int): Int

    @Query("SELECT nominal_lunas FROM item WHERE id_cicilan = :id")
    fun getCurrentNominalLunas(id: Int): Int

    @Query("UPDATE item SET nominal_lunas = nominal_lunas + :nominal WHERE id_cicilan = :id")
    suspend fun setNominalLunas(id: Int, nominal: Int)

    @Query("UPDATE item SET status = :status WHERE id_cicilan = :id")
    suspend fun setStatusLunas(id: Int, status: String)

    @Query("UPDATE item SET lunas_pada = :lunasPada WHERE id_cicilan = :id")
    suspend fun setDateLunas(id: Int, lunasPada: Long)

    @Query("SELECT gambar_barang FROM item WHERE id_cicilan = :id")
    suspend fun getImagePathById(id: Int): String?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(item: Item)

    @Query("DELETE FROM item WHERE id_cicilan = :id")
    suspend fun deleteFromCicilan(id: Int)

    @Query("DELETE FROM ItemLog WHERE id_cicilan = :id")
    suspend fun deleteFromCicilanLog(id: Int)
}
