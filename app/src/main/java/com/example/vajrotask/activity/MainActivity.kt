package com.example.vajrotask.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.vajrotask.R
import com.example.vajrotask.helper.BottomNavigationBehavior
import com.example.vajrotask.helper.HomeTabViewAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var homeTabViewAdapter: HomeTabViewAdapter
    lateinit var bottomNavigationBehavior: BottomNavigationBehavior

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNavigationBehavior = BottomNavigationBehavior()
        val layoutParams = navigationView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = bottomNavigationBehavior

        homeTabViewAdapter = HomeTabViewAdapter(this)
        viewPager.adapter = homeTabViewAdapter
        viewPager.offscreenPageLimit = 3
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

    }

    override fun onStart() {
        super.onStart()

        val pageChangeCallback: ViewPager2.OnPageChangeCallback =
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> {
                            navigationView.selectedItemId = R.id.navigation_dashboard
                        }
                        1 -> {
                            navigationView.selectedItemId = R.id.navigation_home
                        }
                    }
                }

                override fun onPageScrollStateChanged(state: Int) {
                    bottomNavigationBehavior.showBottomNavigationView(navigationView)
                }
            }
        viewPager.registerOnPageChangeCallback(pageChangeCallback)

        navigationView.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_dashboard -> {
                        viewPager.currentItem = 0
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_home -> {
                        viewPager.currentItem = 1
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })
    }

}