package com.moviles.marketplace.ui.activities.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.R
import com.moviles.marketplace.models.Menssage

class MsgAdapter(var data: ArrayList<Menssage>) :
    RecyclerView.Adapter<MsgAdapter.MsgViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_msg_chat, parent, false)
        return MsgViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val msg = data[position]
        if (msg.user_id == sharedPref.getUserId().toLong()) {
            if(msg.message!=null){
                holder.rightLayout.visibility = View.VISIBLE
                holder.leftLayout.visibility = View.GONE
                holder.rightMsg.text =  msg.message
            }else if(msg.url!=null){
                val context = holder.rightImageView.context

                holder.rightImageView.visibility = View.VISIBLE

                holder.rightLayout.visibility = View.VISIBLE
                holder.rightMsg.visibility = View.GONE

                holder.leftLayout.visibility = View.GONE

                val url: String = msg.url.toString()

                Glide.with(context)
                    .load(url)
                    .into(holder.rightImageView)
                holder.rightMsg.text =  msg.url
            }else if(msg.latitude!=null){

                holder.rightFragmentMap.visibility = View.VISIBLE

                holder.rightLayout.visibility = View.VISIBLE
                holder.rightMsg.visibility = View.GONE
                holder.rightImageView.visibility = View.GONE

                holder.leftLayout.visibility = View.GONE

                val fragment = holder.rightFragmentMap.getFragment<MapsMenssageFragment>()
                fragment.latitude = msg.latitude.toDouble()
                fragment.longitude = msg.longitude!!.toDouble()
            }

        } else {
            if(msg.message!=null){
                holder.leftLayout.visibility = View.VISIBLE
                holder.rightLayout.visibility = View.GONE
                holder.leftMsg.text = msg.message
            }else if(msg.url!=null){
                val context = holder.leftImageView.context

                holder.rightImageView.visibility = View.VISIBLE

                holder.leftLayout.visibility = View.VISIBLE
                holder.leftMsg.visibility = View.GONE

                holder.leftLayout.visibility = View.GONE

                val url: String = msg.url.toString()

                Glide.with(context)
                    .load(url)
                    .into(holder.leftImageView)
                holder.rightMsg.text =  msg.url
            }else if(msg.latitude!=null){

                holder.leftFragmentMap.visibility = View.VISIBLE

                holder.leftLayout.visibility = View.VISIBLE
                holder.leftMsg.visibility = View.GONE
                holder.leftImageView.visibility = View.GONE

                holder.rightLayout.visibility = View.GONE

                val fragment = holder.leftFragmentMap.getFragment<MapsMenssageFragment>()
                fragment.latitude = msg.latitude.toDouble()
                fragment.longitude = msg.longitude!!.toDouble()
            }
        }
    }

    fun setNewData(menssage: java.util.ArrayList<Menssage>) {
        this.data = menssage
        notifyDataSetChanged()
    }

    class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var leftLayout: ConstraintLayout = view.findViewById(R.id.left_layout)
        var leftMsg: TextView = view.findViewById(R.id.textLeftMenssage)
        var leftImageView: ImageView = view.findViewById(R.id.imageLeftMenssage)
        var leftFragmentMap: FragmentContainerView = view.findViewById(R.id.fragmentLeftContainerView)

        var rightLayout: ConstraintLayout = view.findViewById(R.id.right_layout)
        var rightMsg: TextView = view.findViewById(R.id.textRightMenssage)
        var rightImageView: ImageView = view.findViewById(R.id.imageRightMenssage)
        var rightFragmentMap: FragmentContainerView = view.findViewById(R.id.fragmentRightContainerView)
    }
}