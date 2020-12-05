package kumeda.timeschedule.fragment

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kumeda.timeschedule.R


class AddFragment : Fragment(), TimePickerDialog.OnTimeSetListener {

    var hour = 0
    var minute = 0
    private var savedHour = 0
    private var savedMinute = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        view.start_time.setOnClickListener{

            TimePickerDialog(activity, this, hour, minute, true).show()

        }
//TODO:endStart入力を分けるようにする
        view.end_time.setOnClickListener{
           // TimePickerDialog(activity, this, hour, minute, true).show()
        }

        return view
    }



    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        savedHour = hour
        savedMinute = minute
            start_time.text = "$savedHour:$savedMinute"
            //end_time.text = "$savedHour:$savedMinute"
    }

}