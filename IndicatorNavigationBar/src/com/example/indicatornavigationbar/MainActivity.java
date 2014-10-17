package com.example.indicatornavigationbar;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {

	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private LinearLayout ll_top;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InitViewPager();
	}

	private void InitViewPager() {
		ll_top = (LinearLayout) findViewById(R.id.ll_top);
		mPager = (ViewPager) findViewById(R.id.vPager);
		fragmentsList = new ArrayList<Fragment>();

		Fragment activityfragment = TestFragment.newInstance("����һ");
		Fragment groupFragment = TestFragment.newInstance("�����");
		Fragment friendsFragment = TestFragment.newInstance("������");
		Fragment chatFragment = TestFragment.newInstance("������");

		fragmentsList.add(activityfragment);
		fragmentsList.add(groupFragment);
		fragmentsList.add(friendsFragment);
		fragmentsList.add(chatFragment);

		mPager.setAdapter(new MyFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentsList));
		mPager.setCurrentItem(0);

		IndicatorNavigationBar navigationBar = new IndicatorNavigationBar(this);
		navigationBar.attachToParent(ll_top, new String[] { "��ҳ", "��Ϣ", "����",
				"����" }, mPager);
	}

	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		private ArrayList<Fragment> fragmentsList;

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public MyFragmentPagerAdapter(FragmentManager fm,
				ArrayList<Fragment> fragments) {
			super(fm);
			this.fragmentsList = fragments;
		}

		@Override
		public int getCount() {
			return fragmentsList.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentsList.get(arg0);
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

	}

}