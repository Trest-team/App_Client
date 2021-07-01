package com.example.pigo_app_client.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.pigo_app_client.Models.Message
import com.example.pigo_app_client.Models.MessageDatabase
import com.example.pigo_app_client.Models.MessageType
import com.example.pigo_app_client.databinding.ActivityChattingBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URISyntaxException

class ChattingActivity : AppCompatActivity() {


    private lateinit var binding: ActivityChattingBinding

    var userName = "류호성"
    var roomName = "류호성봇"
    
    lateinit var mSocket: Socket
    

    val gson: Gson = Gson()

    val chatList: ArrayList<Message> = arrayListOf();
    lateinit var chattingAdapter: ChattingApapter

    var db : MessageDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        chattingAdapter = ChattingApapter(chatList)
        binding.recyclerView.adapter = chattingAdapter;

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        db = MessageDatabase.getInstance(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            var savedContacts = db!!.messageDao().getChat(roomName)
            if(savedContacts.isNotEmpty()){
                chatList.addAll(savedContacts)
            }
        }

        try {
            //IO.socket 메소드는 은 저 URL 을 토대로 클라이언트 객체를 Return 합니다.
            mSocket = IO.socket("https://trest-server.loca.lt")
            Log.d("chatActivity socket", "connected")
        } catch (e: URISyntaxException) {
            Log.d("chatActivity socket", "failed")
        }

        mSocket.connect()
        mSocket.on("reception", reception)
        mSocket.on(Socket.EVENT_CONNECT, onConnect)

        binding.btnSend.setOnClickListener {
            val msg = binding.editText1.text.toString()
            val obj = JSONObject()
            db = MessageDatabase.getInstance(applicationContext)
            obj.put("name", userName)
            obj.put("msg", msg)
            obj.put("room", roomName)
            mSocket.emit("chat", obj)
            val message = Message(userName, msg, roomName,MessageType.CHAT_MINE.index)
            addItemToRecyclerView(message)
            CoroutineScope(Dispatchers.IO).launch {
                db!!.messageDao().insert(message)
            }
        }
    }

    var reception = Emitter.Listener {
        Log.d("새로운 메시지 :", it[0].toString() )
        val chat: Message = gson.fromJson(it[0].toString(), Message::class.java)
        db = MessageDatabase.getInstance(applicationContext)
        chat.viewType = MessageType.CHAT_PARTNER.index
        addItemToRecyclerView(chat)
        CoroutineScope(Dispatchers.IO).launch {
            db!!.messageDao().insert(chat)
        }
        Log.d("chat Message:", chat.toString())
    }

    var onConnect = Emitter.Listener {
        val obj = JSONObject()
        obj.put("name", userName)
        obj.put("room", roomName)
        mSocket.emit("subscribe", obj)

    }

    private fun addItemToRecyclerView(message: Message) {
        runOnUiThread {
            chatList.add(message)
            chattingAdapter.notifyItemInserted(chatList.size)
            binding.editText1.setText("")
            binding.recyclerView.scrollToPosition(chatList.size - 1)
        }
    }

}