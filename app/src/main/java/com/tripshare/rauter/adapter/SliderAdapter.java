package com.tripshare.rauter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.tripshare.rauter.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    private int[] slides_images = {
            R.drawable.share,
            R.drawable.save,
            R.drawable.connect
    };

    private String[] slide_text1 = {
            "Share",
            "Save",
            "Connect",
    };

    private String[] slide_text2 = {
            "Share your ride easily with the help of\nRauter and meet exciting people to ride along\nwith",
            "You'll save more in the economy\nas both a rider and sharer\nearn as you join a ride",
            "Ignite your curiosity and elevate your\nsocial connection with our\ncomprehensive community powered platform."
    };

    @Override
    public int getCount() {
        return slides_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView image = view.findViewById(R.id.image);
        TextView text1 = view.findViewById(R.id.text1);
        TextView text2 = view.findViewById(R.id.text2);


        image.setImageResource(slides_images[position]);
        text1.setText(slide_text1[position]);
        text2.setText(slide_text2[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout)object);

    }
}
