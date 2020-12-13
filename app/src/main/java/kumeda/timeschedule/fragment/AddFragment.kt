package kumeda.timeschedule.fragment

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kumeda.timeschedule.R
import kumeda.timeschedule.TimeScheduleDAO
import kumeda.timeschedule.TimeScheduleData
import kumeda.timeschedule.TimeScheduleDatabase


class AddFragment : Fragment(), TimePickerDialog.OnTimeSetListener {

    var hour = 0
    var minute = 0
    private var savedHour = 0
    private var savedMinute = 0

    private lateinit var db: TimeScheduleDatabase
    private lateinit var dao: TimeScheduleDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.db = Room.databaseBuilder(
            requireContext(),
            TimeScheduleDatabase::class.java,
            "timeSchedule.db"
        ).build()
        this.dao = this.db.timeScheduleDAO()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        view.start_time.setOnClickListener {

            TimePickerDialog(activity, this, hour, minute, true).show()

        }
//TODO:endStart入力を分けるようにする
        view.end_time.setOnClickListener {
            // TimePickerDialog(activity, this, hour, minute, true).show()
        }

        view.add_button.setOnClickListener {
            //コールチン、メインスレッドでデータベースに書き込みは不可、
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    val timeScheduleData = TimeScheduleData(id = 0, title = "タイムスケジュール")
                    dao.addTimeSchedule(timeScheduleData)
                }
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "OK!", Toast.LENGTH_SHORT).show()
                }
            }

        }
        return view
    }

    override fun onStart() {
        super.onStart()
        this.dao.readAllData().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            println(it)
        })
    }


    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        savedHour = hour
        savedMinute = minute
        start_time.text = "$savedHour:$savedMinute"
        //end_time.text = "$savedHour:$savedMinute"
    }

}