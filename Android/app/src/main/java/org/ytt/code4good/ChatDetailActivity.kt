/*
 * Created by Yip Tsz To on 11/2/19 8:21 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.ytt.code4good.databinding.ListItemTaskBinding
import org.ytt.code4good.databinding.ListItemWithImageSingleBinding
import org.ytt.code4good.viewModels.ConvoViewModel
import org.ytt.code4good.viewModels.TaskViewModel

class ChatDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_detail)
        val pos = intent.getIntExtra(ChatFragment.EXTRA_CHAT_POS, 0)

        // TODO get details using pos

        val icon = R.drawable.head2
        val name = "Lisa"

        setSupportActionBar(findViewById(R.id.view_toolbar))

        supportActionBar?.setIcon(icon)
        supportActionBar?.title = name
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)


        val viewManager = LinearLayoutManager(this)
        val viewAdapter = ChatAdapter(listOf())

        findViewById<RecyclerView>(R.id.view_list).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter

            addItemDecoration(DividerItemDecoration(this@ChatDetailActivity, DividerItemDecoration.VERTICAL))
        }
    }

    private class ChatAdapter(val mDataList: List<ConvoViewModel>) :
        RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ListItemWithImageSingleBinding.inflate(layoutInflater, parent, false)

            return MyViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = mDataList[position]
            holder.bind(item)
        }

        override fun getItemCount() = mDataList.size

        private class MyViewHolder(private val binding: ListItemWithImageSingleBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: ConvoViewModel) {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
    }
}