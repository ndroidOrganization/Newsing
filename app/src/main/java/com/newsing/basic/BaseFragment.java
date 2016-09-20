package com.newsing.basic;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/9/20 0020.
 *
 */
public class BaseFragment extends Fragment {
    /**
     * subClass must override this method to tell what tabs show display
     * @return
     */
    public String getTabName(){
        return "undefined";
    }
}
