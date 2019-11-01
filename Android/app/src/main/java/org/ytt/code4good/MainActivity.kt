/*
 * Created by Yip Tsz To on 11/1/19 10:24 PM
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val needInit = needInit()

        if (needInit()) {
            // Intent to init activity
        }

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = MyAdapter(Array(100) { "$it" })

        findViewById<RecyclerView>(R.id.view_list).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter

        }
    }

    private fun needInit(): Boolean {
        val age: Int
        try {
            age = resources.getInteger(PREF_AGE)
            Log.e("Age", age.toString())
        } catch (e: Resources.NotFoundException) {
            return true
        }
        return age <= 0
    }

    private class MyAdapter(private val myList: Array<String>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            val textView = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
            return MyViewHolder(textView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = myList[position]
            holder.itemView.setOnClickListener {
                startActivity(it.context, Intent(it.context, ChatActivity::class.java), null)
            }
        }


        override fun getItemCount() = myList.size
    }

    companion object {
        private const val PREF_AGE = 0
    }
}
