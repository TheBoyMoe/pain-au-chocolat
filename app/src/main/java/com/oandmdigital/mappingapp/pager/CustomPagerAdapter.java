package com.oandmdigital.mappingapp.pager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;


import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;


import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.ui.CustomListViewFragment;
import com.oandmdigital.mappingapp.ui.CustomMapFragment;

public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    private final int LIST_FRAGMENT = 0;
    private final int MAP_FRAGMENT = 1;
    private final int PAGE_COUNT = 2;
    private Context mContext;
    private String[] titles = {"LIST", "MAP"};
    private int[] images = {
            R.drawable.ic_menu_white_24dp,
            R.drawable.ic_map_white_24dp
    };

    public CustomPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case LIST_FRAGMENT:
                return CustomListViewFragment.newInstance();
            case MAP_FRAGMENT:
                return CustomMapFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    // sets both an image and title
    @Override
    public CharSequence getPageTitle(int position) {
        // retrieve the image
        Drawable image = ContextCompat.getDrawable(mContext, images[position]);
        if(image != null) {
            // adjust the vertical height(2nd value) of the image to center it vertically with the text
            image.setBounds(0, 10, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            SpannableString str = new SpannableString("    " + titles[position]);
            //SpannableString str = new SpannableString("    "); // for images only
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            str.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return str;
        }
        // no image found, simply return the title
        return titles[position];
    }


}
