package com.example.vajrotask.helper


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vajrotask.ui.dashboard.DashboardFragment
import com.example.vajrotask.ui.home.HomeFragment

class HomeTabViewAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return DashboardFragment.newInstance("","")
        } else {
            return HomeFragment()
        }
    }
}
