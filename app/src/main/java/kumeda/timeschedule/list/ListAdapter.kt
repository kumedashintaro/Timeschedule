package kumeda.timeschedule.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_row.view.*
import kumeda.timeschedule.R
import kumeda.timeschedule.TimeScheduleData
import kumeda.timeschedule.fragment.AddFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.startTime_text
import java.text.SimpleDateFormat


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var timeScheduleList = emptyList<TimeScheduleData>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return timeScheduleList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val df = SimpleDateFormat("HH:mm")

        val currentItem = timeScheduleList[position]
        holder.itemView.id_text.text = currentItem.id.toString()
        holder.itemView.content_text.text = currentItem.content
        holder.itemView.startTime_text.text =  df.format(currentItem.startTime)
        holder.itemView.endTime_text.text =  df.format(currentItem.endTime)

        holder.itemView.rowLayout.setOnClickListener {
            val action = AddFragmentDirections.actionAddFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(timeScheduleData: List<TimeScheduleData>){
        this.timeScheduleList = timeScheduleData
        notifyDataSetChanged()
    }

}