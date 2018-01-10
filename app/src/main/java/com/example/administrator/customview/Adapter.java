package com.example.administrator.customview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.customview.fragment.HomeBlankFragment;
import com.example.administrator.customview.fragment.MaterialFragment;
import com.example.administrator.customview.fragment.PlusOneFragment;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/08
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class Adapter extends FragmentPagerAdapter {

    public Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeBlankFragment.newInstance();

            case 1:
                return PlusOneFragment.newInstance();

            case 2:
                return MaterialFragment.newInstance();

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
