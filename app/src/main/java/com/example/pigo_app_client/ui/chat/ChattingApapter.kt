package com.example.pigo_app_client.ui.chat

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
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_chat_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val messageData  = chatList[position]
        val userName = messageData.userName;
        val content = messageData.messageContent;
        when(messageData.viewType) {

            CHAT_MINE -> {
                holder.message.text = content
            }
            CHAT_PARTNER ->{
                holder.userName.text = userName
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