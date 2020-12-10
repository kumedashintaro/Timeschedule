package kumeda.timeschedule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_schedule_table")
data class TimeScheduleData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String
)