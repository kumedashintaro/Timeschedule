package kumeda.timeschedule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TimeScheduleDAO {

    //onConflict = OnConflictStrategy.IGNORE。
    // 選択された onConflict ストラテジーは、
    // 新しい単語が既にリストにある単語と全く同じ場合は無視します。
    @Insert
    fun insert(timeScheduleData : TimeScheduleData)

    //IDの昇順で並び変える
    @Query("select * from time_schedule_table ORDER BY id ASC")
    fun getAll(): LiveData<List<TimeScheduleData>>
}