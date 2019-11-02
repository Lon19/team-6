/*
 * Created by Yip Tsz To on 11/2/19 4:18 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val needInit = needInit()

        if (needInit()) {
            // TODO Intent to init activity
        }

        /*val viewManager = LinearLayoutManager(this)
        val viewAdapter = MyAdapter(Array(100) { "$it" })

        findViewById<RecyclerView>(R.id.view_list).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter

        }*/

        val viewPager = findViewById<ViewPager>(R.id.view_pager).apply {
            adapter = MyMainViewPagerAdapter(supportFragmentManager)
            setOnTouchListener { _, _ -> true }
        }

        findViewById<BottomNavigationView>(R.id.bottom_nav).apply {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_task -> viewPager.currentItem = 0
                    R.id.action_game -> viewPager.currentItem = 1
                    R.id.action_chat -> viewPager.currentItem = 2
                    else -> viewPager.currentItem = 3
                }

                true
            }

            itemIconTintList = null
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

    /*private class MyAdapter(private val myList: Array<String>) :
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
    }*/

    private class MyMainViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> TaskFragment()
                1 -> GameFragment()
                2 -> ChatFragment()
                else -> Fragment() // TODO Room
            }
        }

        override fun getCount() = 4
    }

    companion object {
        private const val PREF_AGE = 0
    }
}
