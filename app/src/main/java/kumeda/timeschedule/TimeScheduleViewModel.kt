package kumeda.timeschedule

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimeScheduleViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<TimeScheduleData>>
    private val repository: TimeScheduleRepository

    init {
        val timeScheduleDao = TimeScheduleDatabase.getDatabase(application).timeScheduleDAO()
        repository = TimeScheduleRepository(timeScheduleDao)
        readAllData = repository.readAllData
    }

    fun addTimeSchedule(timeScheduleData: TimeScheduleData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTimeSchedule(timeScheduleData)
        }
    }
}