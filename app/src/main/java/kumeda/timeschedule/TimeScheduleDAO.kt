package kumeda.timeschedule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TimeScheduleDAO {

    @Insert
    fun insert(timeScheduleData : TimeScheduleData)


    @Query("select * from timescheduledata")
    fun getAll(): LiveData<List<TimeScheduleData>>
}