package kumeda.timeschedule.repository

import androidx.lifecycle.LiveData
import kumeda.timeschedule.data.TimeScheduleDAO
import kumeda.timeschedule.TimeScheduleData


class TimeScheduleRepository(private val timeScheduleDao: TimeScheduleDAO) {

    val readAllData: LiveData<List<TimeScheduleData>> = timeScheduleDao.readAllData()

    //suspend修飾子は、コルーチンや他のサスペンド関数から呼び出す必要があることをコンパイラに伝えます。
    suspend fun addTimeSchedule(timeScheduleData: TimeScheduleData) {
        timeScheduleDao.addTimeSchedule(timeScheduleData)
    }

    suspend fun updateTimeSchedule(timeScheduleData: TimeScheduleData){
        timeScheduleDao.updateTimeSchedule(timeScheduleData)
    }

    suspend fun deleteTimeSchedule(timeScheduleData: TimeScheduleData){
        timeScheduleDao.deleteTimeSchedule(timeScheduleData)
    }
}

