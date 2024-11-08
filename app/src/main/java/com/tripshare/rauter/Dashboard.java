package com.tripshare.rauter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tripshare.rauter.fragment.CommunityFragment;
import com.tripshare.rauter.fragment.HomeFragment;
import com.tripshare.rauter.fragment.RidesFragment;
import com.tripshare.rauter.fragment.SettingsFragment;
import com.tripshare.rauter.fragment.WalletFragment;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //view pager and tab layout
        viewPager = findViewById(R.id.viewpager);
        addTabs(viewPager);
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setTintList(ColorStateList.valueOf(Color.parseColor("#E6A46A")));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setTintList(ColorStateList.valueOf(Color.parseColor("#707070")));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_community);
        tabLayout.getTabAt(2).setIcon(R.drawable.wallet);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_rides);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_settings);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), "Home", 0);
        adapter.addFrag(new CommunityFragment(), "Community", 1);
        adapter.addFrag(new WalletFragment(), "Wallet", 2);
        adapter.addFrag(new RidesFragment(), "Rides", 3);
        adapter.addFrag(new SettingsFragment(), "Settings", 4);
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private final List<Integer> mFragmenttag = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title, int tag){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
            mFragmenttag.add(tag);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}