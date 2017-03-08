package com.example.akash.gallery;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by akash on 12-01-2017.
 */

public class ViewAdapter extends PagerAdapter {
    int images[] = {R.drawable.back0,
            R.drawable.back1,
            R.drawable.back2,
            R.drawable.back3,
            R.drawable.back4,
            R.drawable.back5,
            R.drawable.back6,
            R.drawable.back7,
            R.drawable.back8,
            R.drawable.back9
    };
    Activity activity;
    LayoutInflater inflater;
    ImageView iv_inner,iv_outer;

    public ViewAdapter(Activity activity,int[] images) {
        this.images = images;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater= (LayoutInflater) activity.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.fragments_slideshow,container,false);
        iv_inner= (ImageView) v.findViewById(R.id.iv_inner);
        iv_outer= (ImageView) v.findViewById(R.id.iv_outer);
        DisplayMetrics dis=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(dis);
        Glide.with(activity.getApplicationContext()).load(images[position]).override(300,300).fitCenter().into(iv_inner);
        Glide.with(activity.getApplicationContext()).load(images[position])
                .bitmapTransform(new BlurTransformation(activity.getApplicationContext()))
                .override(200,200)
                .into(iv_outer);
        container.addView(v);
        return v;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }
    class view extends ViewPager {

        public view(Context context) {
            super(context);
        }

        public view(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

    }
}
