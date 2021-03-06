package kumeda.timeschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kumeda.timeschedule.Converters
import kumeda.timeschedule.TimeScheduleData

@Database(entities = [TimeScheduleData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TimeScheduleDatabase : RoomDatabase() {

    abstract fun timeScheduleDAO(): TimeScheduleDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        //スレッドセーフ
        private var INSTANCE: TimeScheduleDatabase? = null

        fun getDatabase(context: Context): TimeScheduleDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TimeScheduleDatabase::class.java,
                    "time_schedule_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}