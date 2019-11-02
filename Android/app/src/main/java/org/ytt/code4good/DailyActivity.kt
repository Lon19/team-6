/*
 * Created by Yip Tsz To on 11/2/19 4:38 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class DailyActivity : AppCompatActivity(), WelcomeFragment.OnForwardEvent,
    FeelingFragment.OnForwardEvent,NewFriendFragment.OnForwardEvent {
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        viewPager = findViewById<ViewPager>(R.id.view_pager_daily).apply {
            adapter = DailyViewPagerAdapter(
                supportFragmentManager,
                this@DailyActivity,
                this@DailyActivity,
                this@DailyActivity
            )
            setOnTouchListener { _, _ -> true }
        }
    }

    private class DailyViewPagerAdapter(
        fm: FragmentManager,
        val welcomeEvent: WelcomeFragment.OnForwardEvent,
        val feelingEvent: FeelingFragment.OnForwardEvent,
        val newFriendEvent: NewFriendFragment.OnForwardEvent
    ) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> WelcomeFragment(welcomeEvent)
                1 -> FeelingFragment(feelingEvent)
                else -> NewFriendFragment(newFriendEvent)
            }
        }

        override fun getCount() = 5
    }

    override fun welcomeTouchListener() {
        viewPager.currentItem++
    }

    override fun feelingTouchListener() {
        viewPager.currentItem++
    }

    override fun newFriendTouchListener() {
        viewPager.currentItem++
    }

    override fun newFriendExitListener() {
        finish()
    }
}