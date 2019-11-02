package org.ytt.code4good

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.ytt.code4good.viewModels.GameViewModel

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_game, container, false)

        val viewPager = view.findViewById<ViewPager>(R.id.view_pager_inner).also {
            it.adapter = MyFragmentPagerAdapter(
                activity!!.application,
                activity!!.supportFragmentManager
            )
        }

        val tab = view.findViewById<TabLayout>(R.id.tab_layout)
        tab.setupWithViewPager(viewPager)

        return view
    }

    private class MyFragmentPagerAdapter(application: Application, fm: FragmentManager) :
        FragmentPagerAdapter(fm) {
        private val popularGamesList = listOf(
            GameViewModel(application, "1. Ninja Fight", R.drawable.ic_game_ninja, "Action"),
            GameViewModel(application, "2. TwentyNine", R.drawable.ic_game_twentynine, "Strategy"),
            GameViewModel(application, "3. Chess", R.drawable.ic_game_chess, "Strategy"),
            GameViewModel(application, "4. Pictionary", R.drawable.ic_game_ninja, "Art"),
            GameViewModel(application, "5. Jungle Free", R.drawable.ic_game_jungle, "Adventure"),
            GameViewModel(application, "6. Connect Four", R.drawable.ic_game_connect4, "Strategy"),
            GameViewModel(application, "7. Ninja Fight", R.drawable.ic_game_ninja, "Action"),
            GameViewModel(application, "7. Ninja Fight", R.drawable.ic_game_ninja, "Action"),
            GameViewModel(application, "7. Ninja Fight", R.drawable.ic_game_ninja, "Action"),
            GameViewModel(application, "8. Ninja Fight", R.drawable.ic_game_ninja, "Action")
        )

        private val categories =
            listOf("Action", "Adventure", "Quiz", "Role play", "Strategy", "Sports", "Real time")

        override fun getItem(position: Int) = GameListFragment(
            position,
            popularGamesList,
            categories
        )

        override fun getPageTitle(position: Int) =
            when (position) {
                1 -> "Categories"
                else -> "Popular"
            }

        override fun getCount() = 2
    }
}