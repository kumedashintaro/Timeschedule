package kumeda.timeschedule.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kumeda.timeschedule.FormaterHHMM
import kumeda.timeschedule.R
import kumeda.timeschedule.TimeScheduleData
import kumeda.timeschedule.viewmodel.TimeScheduleViewModel
import java.util.*

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

        view.update_content_edit.setText(args.currentTimeSchedule.content)
        view.update_start_time.text = FormaterHHMM.df.format(args.currentTimeSchedule.startTime)
        view.update_end_time.text = FormaterHHMM.df.format(args.currentTimeSchedule.endTime)

        view.update_button.setOnClickListener {
            updateItem()
        }

        view.delete_button.setOnClickListener {
            deleteItem()
        }

        return view
    }

    private fun updateItem() {
        val title = "タイトル"
        val content = update_content_edit.text.toString()
        val starTime = Date()
        val endTime = Date()

        if (inputCheck(content)) {

            val updateTimeSchedule = TimeScheduleData(
                args.currentTimeSchedule.id,
                title,
                content,
                starTime,
                endTime
            )

            timeScheduleViewModel.updateTimeSchedule(updateTimeSchedule)

            findNavController().navigate(R.id.action_updateFragment_to_addFragment)

            Toast.makeText(requireContext(), "修正できたで!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "エラーや！", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteItem() {
        timeScheduleViewModel.deleteTimeSchedule(args.currentTimeSchedule)
        findNavController().navigate(R.id.action_updateFragment_to_addFragment)
        Toast.makeText(requireContext(), "削除したで!", Toast.LENGTH_LONG).show()
    }

    private fun inputCheck(contents: String): Boolean {
        return !(TextUtils.isEmpty(contents))
    }
}