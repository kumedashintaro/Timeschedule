package kumeda.timeschedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var timeScheduleList = emptyList<TimeScheduleData>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return timeScheduleList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = timeScheduleList[position]
        holder.itemView.id_text.text = currentItem.id.toString()
        holder.itemView.content_text.text = currentItem.content

    }

    fun setData(timeScheduleData: List<TimeScheduleData>){
        this.timeScheduleList = timeScheduleData
        notifyDataSetChanged()
    }

}