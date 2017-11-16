package test.bwei.com.zhanghaoqing20170909.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.bwei.com.zhanghaoqing20170909.R;

/**
 * Created by MK on 2017/9/9.
 */
public class Fragment02 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag02, container, false);
        return view;
    }
}
