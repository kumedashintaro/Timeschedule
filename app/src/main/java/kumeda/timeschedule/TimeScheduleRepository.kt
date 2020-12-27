package kumeda.timeschedule

import androidx.lifecycle.LiveData


class TimeScheduleRepository(private val timeScheduleDao: TimeScheduleDAO) {

    val readAllData: LiveData<List<TimeScheduleData>> = timeScheduleDao.readAllData()

    //suspend修飾子は、コルーチンや他のサスペンド関数から呼び出す必要があることをコンパイラに伝えます。
    suspend fun addTimeSchedule(timeScheduleData: TimeScheduleData) {
        timeScheduleDao.addTimeSchedule(timeScheduleData)
    }

    suspend fun updateTimeSchedule(timeScheduleData: TimeScheduleData){
        timeScheduleDao.updateTimeSchedule(timeScheduleData)
    }
}

