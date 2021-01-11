package kumeda.timeschedule.fragment

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kumeda.timeschedule.R
import kumeda.timeschedule.TimeScheduleData
import kumeda.timeschedule.list.ListAdapter
import kumeda.timeschedule.toDate
import kumeda.timeschedule.viewmodel.TimeScheduleViewModel
import java.util.*


class AddFragment : Fragment(), TimePickerDialog.OnTimeSetListener {

    class AddObject {
        companion object {
            var title: String = ""
            var selectTime: Int = 0
        }
    }

    //ViewModel
    private lateinit var timeScheduleViewModel: TimeScheduleViewModel

    var hour = 0
    var minute = 0

    private var startTime = Date()
    private var endTime = Date()

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
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        timeScheduleViewModel = ViewModelProvider(this).get(timeScheduleViewModel::class.java)
        timeScheduleViewModel.readAllData.observe(
            viewLifecycleOwner,
            Observer { timeSchedule -> adapter.setData(timeSchedule) })

        view.start_time.setOnClickListener {

            TimePickerDialog(activity, this, hour, minute, true).show()
            AddObject.selectTime = 1

        }

        view.end_time.setOnClickListener {
            TimePickerDialog(activity, this, hour, minute, true).show()
            AddObject.selectTime = 2
        }

        view.add_button.setOnClickListener {

            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {

        AddObject.title = title_edit.text.toString()
        val contents = add_contents_edit.text.toString()

        //入力をクリア
        add_contents_edit.text.clear()
        start_time.text = "00:00"
        end_time.text = "00:00"

        if (inputCheck(AddObject.title, contents)) {
            val timeScheduleData = TimeScheduleData(
                0,
                AddObject.title,
                contents,
                startTime,
                endTime
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

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        val setTime = String.format(Locale.US, "%d:%d", hour, minute)

        if (AddObject.selectTime == 1) {
            start_time.text = setTime
            startTime = setTime.toDate()!!
        }
        if (AddObject.selectTime == 2) {
            end_time.text = setTime
            endTime = setTime.toDate()!!
        }
    }
}