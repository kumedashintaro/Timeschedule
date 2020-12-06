package kumeda.timeschedule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data  class TimeScheduleData(
    @PrimaryKey(autoGenerate = true)
    var id: Int, val title: String) {
}