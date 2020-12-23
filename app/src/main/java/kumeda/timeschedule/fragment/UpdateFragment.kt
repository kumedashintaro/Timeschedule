package kumeda.timeschedule.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kumeda.timeschedule.R
import kumeda.timeschedule.TimeScheduleViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var timeScheduleViewModel: TimeScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        timeScheduleViewModel = ViewModelProvider(this).get(TimeScheduleViewModel::class.java)

        view.update_contents_edit.setText(args.currentTimeSchedule.content)

        view.update_button.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem() {
        val contents = update_contents_edit
    }

}