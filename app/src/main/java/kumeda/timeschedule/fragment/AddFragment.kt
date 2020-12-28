package kumeda.timeschedule.fragment

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.update_start_time
import kotlinx.android.synthetic.main.fragment_add.view.*
import kumeda.timeschedule.*
import kumeda.timeschedule.data.TimeScheduleDAO
import kumeda.timeschedule.TimeScheduleData
import kumeda.timeschedule.data.TimeScheduleDatabase
import kumeda.timeschedule.list.ListAdapter
import kumeda.timeschedule.viewmodel.TimeScheduleViewModel


class AddFragment : Fragment(), TimePickerDialog.OnTimeSetListener {


    //ViewModel
    private lateinit var timeScheduleViewModel: TimeScheduleViewModel

    var hour = 0
    var minute = 0
    private var savedHour = 0
    private var savedMinute = 0

    private lateinit var db: TimeScheduleDatabase
    private lateinit var dao: TimeScheduleDAO

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        this.db = Room.databaseBuilder(
//            requireContext(),
//            TimeScheduleDatabase::class.java,
//            "timeSchedule.db"
//        ).build()
//        this.dao = this.db.timeScheduleDAO()
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        //Providerが必要、でgetしている
        timeScheduleViewModel = ViewModelProvider(this).get(TimeScheduleViewModel::class.java)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.title_text
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        timeScheduleViewModel = ViewModelProvider(this).get(timeScheduleViewModel::class.java)
        timeScheduleViewModel.readAllData.observe(
            viewLifecycleOwner,
            Observer { timeSchedule -> adapter.setData(timeSchedule) })







        view.update_start_time.setOnClickListener {

            TimePickerDialog(activity, this, hour, minute, true).show()

        }
//TODO:endStart入力を分けるようにする
        view.update_end_time.setOnClickListener {
            // TimePickerDialog(activity, this, hour, minute, true).show()
        }

        view.update_button.setOnClickListener {

            insertDataToDatabase()

//            //コールチン、メインスレッドでデータベースに書き込みは不可、
//            GlobalScope.launch {
//                withContext(Dispatchers.IO) {
//                    val timeScheduleData = TimeScheduleData(id = 0, title = "タイムスケジュール")
//                    dao.addTimeSchedule(timeScheduleData)
//                }
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(requireContext(), "OK!", Toast.LENGTH_SHORT).show()
//                }
//            }

        }
        return view
    }

    private fun insertDataToDatabase() {
        val title = title_edit.text.toString()
        val contents = add_contents_edit.text.toString()
        //TODO 時間を実装

        if (inputCheck(title, contents)) {
            val timeScheduleData = TimeScheduleData(
                0,
                title,
                contents
            )
            //databaseに追加
            timeScheduleViewModel.addTimeSchedule(timeScheduleData)
            Toast.makeText(requireContext(), "データベースに追加出来たで！", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "入力漏れあるで！", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(title: String, contents: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(contents))
    }


//    override fun onStart() {
//        super.onStart()
//        this.dao.readAllData().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//            println(it)
//        })
//    }


    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        savedHour = hour
        savedMinute = minute
        update_start_time.text = "$savedHour:$savedMinute"
        //end_time.text = "$savedHour:$savedMinute"
    }

}