package {{inputs.app_package}}.basicfeature.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import {{inputs.app_package}}.basicfeature.data.local.model.RocketCached
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

    @Query("SELECT * FROM RocketCached")
    fun getRockets(): Flow<List<RocketCached>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRockets(rockets: List<RocketCached>)
}
