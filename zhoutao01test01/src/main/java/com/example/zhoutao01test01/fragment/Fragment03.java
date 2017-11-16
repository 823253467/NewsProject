package com.example.zhoutao01test01.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.zhoutao01test01.R;
import com.example.zhoutao01test01.Second;

/**
 * Created by MK on 2017/9/2.
 */
public class Fragment03 extends Fragment{

    private ImageView imageview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag03, container, false);
        imageview = (ImageView) view.findViewById(R.id.image01);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AnimationSet animationSet = new AnimationSet(true);

        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
        alphaAnimation.setDuration(500);//设置动画持续时间为500毫秒

        Animation scaleAnimation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);//设置动画持续时间为500毫秒
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setStartOffset(0);
        scaleAnimation.setInterpolator(getActivity(), android.R.anim.decelerate_interpolator);//设置动画插入器
        Animation translateAnimation = new TranslateAnimation(0, 100, 0, 100);
        translateAnimation.setDuration(500);
        translateAnimation.setInterpolator(getActivity(), android.R.anim.cycle_interpolator);//设置动画插入器
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
        imageview.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getActivity(), Second.class);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}
