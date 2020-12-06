package kumeda.timeschedule

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[TimeScheduleData::class], version = 1)
abstract  class TimeScheduleDatabase: RoomDatabase() {
    abstract fun timeScheduleDAO(): TimeScheduleDAO
}