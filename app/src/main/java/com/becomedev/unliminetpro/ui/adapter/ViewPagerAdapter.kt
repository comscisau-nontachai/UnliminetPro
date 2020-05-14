package com.becomedev.unliminetpro.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.becomedev.unliminetpro.ui.AisFragment
import com.becomedev.unliminetpro.ui.DtacFragment
import com.becomedev.unliminetpro.ui.TrueFragment

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> TrueFragment()
            1 -> AisFragment()
            else -> DtacFragment()

    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "TRUE"
            1 -> "AIS"
            else -> "DTAC"
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

}