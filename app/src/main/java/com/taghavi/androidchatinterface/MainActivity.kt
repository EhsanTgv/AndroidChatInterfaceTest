package com.taghavi.androidchatinterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taghavi.androidchatinterface.models.Message
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val messageAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = messageAdapter
        populateData()

        button.setOnClickListener {
            val message = Message(text = editText.text.toString(), sendBy = "me")
            val sendMessageItem = SendMessageItem(message)

            messageAdapter.add(sendMessageItem)
            editText.text.clear()

            receiveAutoResponse()
        }
    }

    private fun populateData() {
        val data = listOf<Message>()
        data.forEach {
            if (it.sendBy == "me") {
                messageAdapter.add(SendMessageItem(it))
            } else {
                messageAdapter.add(ReceiveMessageItem(it))
            }
        }
    }

    private fun receiveAutoResponse() {
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            val receive = Message(
                text = "Salut l'amis j'espere que vous allez bien, je suis tres bien j'ai manger to day",
                sendBy = "me"
            )
            val receiveItem = ReceiveMessageItem(receive)

            messageAdapter.add(receiveItem)
        }
    }
}