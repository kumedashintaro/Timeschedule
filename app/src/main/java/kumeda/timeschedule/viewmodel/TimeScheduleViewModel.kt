package kumeda.timeschedule.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kumeda.timeschedule.TimeScheduleData
import kumeda.timeschedule.data.TimeScheduleDatabase
import kumeda.timeschedule.repository.TimeScheduleRepository

class TimeScheduleViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<TimeScheduleData>>
    private val repository: TimeScheduleRepository

    init {
        val timeScheduleDao = TimeScheduleDatabase.getDatabase(
            application
        ).timeScheduleDAO()
        repository =
            TimeScheduleRepository(timeScheduleDao)
        readAllData = repository.readAllData
    }

    fun addTimeSchedule(timeScheduleData: TimeScheduleData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTimeSchedule(timeScheduleData)
        }
    }

    fun updateTimeSchedule(timeScheduleData: TimeScheduleData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTimeSchedule(timeScheduleData)
        }
    }

    fun deleteTimeSchedule(timeScheduleData: TimeScheduleData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTimeSchedule(timeScheduleData)
        }
    }
}