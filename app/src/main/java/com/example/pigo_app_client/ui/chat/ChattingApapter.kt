package com.example.pigo_app_client.ui.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pigo_app_client.Models.Message
import com.example.pigo_app_client.R
import com.example.pigo_app_client.databinding.ActivityChattingBinding

class ChattingApapter(val chatList : ArrayList<Message>) : RecyclerView.Adapter<ChattingApapter.ViewHolder>() {

    val CHAT_MINE = 0
    val CHAT_PARTNER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View? = null
        when(viewType){
            0->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.row_chat_user, parent, false)
                Log.d("user inflating","viewType : ${viewType}")
            }
            1->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.row_chat_partner, parent, false)
                Log.d("user inflating","viewType : ${viewType}")
            }
        }

        return ViewHolder(view!!)
    }
    override fun getItemViewType(position: Int): Int {
        return chatList[position].viewType
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val messageData  = chatList[position]
        Log.d("onBindViewHolder MessageData : ", messageData.name)
        val userName = messageData.name;
        val content = messageData.msg;
        when(messageData.viewType) {

            CHAT_MINE -> {
                holder.message.text = content
            }
            CHAT_PARTNER ->{
                holder.userName.text = messageData.name
                holder.message.text = content
            }
        }

    }

    override fun getItemCount() = chatList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName = itemView.findViewById<TextView>(R.id.username)
        val message = itemView.findViewById<TextView>(R.id.message)
        val text = itemView.findViewById<TextView>(R.id.text)
    }


}