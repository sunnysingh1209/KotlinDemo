package com.example.kotlindemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlindemo.R
import com.example.kotlindemo.model.VideoModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.main_activity_adapter.view.*

class MainActivityAdapter(
    val mainModelList: MutableList<VideoModel>,
    var context: Context,
    val clickListner: (VideoModel) -> Unit
) :
    RecyclerView.Adapter<MainActivityAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_activity_adapter, parent, false)
        return MyView(view)
    }

    override fun getItemCount(): Int {
        return mainModelList.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.mainAdapter_name?.text = mainModelList[holder.adapterPosition].title
        holder.mainAdapter_Addess?.text = mainModelList.get(holder.adapterPosition).description

        holder.mainAdapter_Img?.let {
            Glide.with(context.applicationContext)
                .load(mainModelList.get(position).urlMedium)
                .into(it)
        }

        holder.mainAdapter_LL?.setOnClickListener {
            clickListner(mainModelList.get(holder.adapterPosition))
        }

//        (holder as MyView).bind(mainModelList[position], context, clickListner)

    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mainAdapter_name: TextView? = null
        var mainAdapter_Addess: TextView? = null
        var mainAdapter_Img: CircleImageView? = null
        var mainAdapter_LL: LinearLayout? = null

        init {
            mainAdapter_LL = itemView.mainAdapter_LL
            mainAdapter_Img = itemView.mainAdapter_Img
            mainAdapter_Addess = itemView.mainAdapter_Addess
            mainAdapter_name = itemView.mainAdapter_name
        }

        /* fun bind(person: MainModel, context: Context, clickListener: (MainModel) -> Unit) {
             itemView.mainAdapter_name.text = person.namemainAdapter_LL
             itemView.mainAdapter_Addess.text = person.addess
             itemView.mainAdapter_Img?.let {
                 Glide.with(context.applicationContext)
                     .load(person.imgUrl)
                     .into(it)
             }

             itemView.setOnClickListener { clickListener(person) }
         }*/

    }
}