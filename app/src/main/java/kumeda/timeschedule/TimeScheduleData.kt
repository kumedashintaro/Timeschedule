package kumeda.timeschedule

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "time_schedule_table")
class TimeScheduleData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String,
    val content: String
) : Parcelable