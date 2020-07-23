package com.diwaves.news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class HomePageAdapter(fm: FragmentManager, mFragments: ArrayList<Fragment>) :
    FragmentPagerAdapter(fm) {
    private var mFragments = ArrayList<Fragment>()
    var fm: FragmentManager? = null

    init {
        this.fm = fm
        this.mFragments = mFragments;
    }

    override fun getItem(position: Int): Fragment {
        return mFragments.get(position)
    }

    override fun getCount(): Int {
        return mFragments.size
    }
}