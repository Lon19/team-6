/*
 * Created by Yip Tsz To on 11/1/19 10:24 PM
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.ytt.code4good.databinding.ListItemWithImageBinding
import org.ytt.code4good.viewModels.ChatViewModel

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val viewManager = LinearLayoutManager(this)
        val viewAdapter =
            Adapter(List(10) {
                ChatViewModel(
                    application,
                    R.drawable.head_placeholder,
                    "I go to school by bus"
                )
            })

        findViewById<RecyclerView>(R.id.view_list).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }


//        val socket = IO.socket()
    }

    private class Adapter(val data: List<ChatViewModel>) :
        RecyclerView.Adapter<Adapter.MyViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ListItemWithImageBinding.inflate(layoutInflater, parent, false)

            return MyViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = data[position]
            holder.bind(item)
        }

        override fun getItemCount() = data.size

        private class MyViewHolder(private val binding: ListItemWithImageBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: ChatViewModel) {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
    }
}