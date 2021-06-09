package com.example.pigo_app_client.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pigo_app_client.Models.Message
import com.example.pigo_app_client.Models.MessageType
import com.example.pigo_app_client.databinding.ActivityChattingBinding
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import java.net.URISyntaxException

class ChattingActivity : AppCompatActivity() {


    private lateinit var binding: ActivityChattingBinding

    var userName = "류호성"

    lateinit var mSocket: Socket;
    lateinit var roomName: String;

    val chatList: ArrayList<Message> = arrayListOf();
    lateinit var chattingAdapter: ChattingApapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        chattingAdapter = ChattingApapter(chatList)
        binding.recyclerView.adapter = chattingAdapter;

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        try {
            //IO.socket 메소드는 은 저 URL 을 토대로 클라이언트 객체를 Return 합니다.
            mSocket = IO.socket("https://trest-server.loca.lt")
            Log.d("chatActivity socket", "connected")
        } catch (e: URISyntaxException) {
            Log.d("chatActivity socket", "failed")
        }

        mSocket.connect()
        mSocket.on("reception", reception)

        binding.btnSend.setOnClickListener {
            val msg = binding.editText1.text.toString()
            val room = "1"
            val obj = JSONObject()
            obj.put("name", userName)
            obj.put("msg", msg)
            obj.put("room", room)

            val message = Message(userName, msg, room,MessageType.CHAT_MINE.index)
            Log.d("왜안돼",obj.toString())
            addItemToRecyclerView(message)
            mSocket.emit("chat", obj)

        }
    }

    var reception = Emitter.Listener {
        Log.d("새로운 메시지 :", it[0].toString() )
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