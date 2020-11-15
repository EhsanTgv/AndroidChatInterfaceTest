package com.taghavi.androidchatinterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taghavi.androidchatinterface.models.Message
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val messageAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = messageAdapter
        populateData()
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
}