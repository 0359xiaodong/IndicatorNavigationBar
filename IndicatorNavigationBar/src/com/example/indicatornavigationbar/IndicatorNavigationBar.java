/*
 * Copyright (c) 2014, �ൺ˾ͨ�Ƽ����޹�˾ All rights reserved.
 * File Name��IndicatorNavigationBar.java
 * Version��V1.0
 * Author��zhaokaiqiang
 * Date��2014-10-17
 */
package com.example.indicatornavigationbar;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @ClassName: com.mengle.activity.IndicatorNavigationBar
 * @Description: ����ָʾ���ĵײ�������
 * @author zhaokaiqiang
 * @date 2014-10-17 ����11:07:40
 * 
 */
public class IndicatorNavigationBar extends LinearLayout implements
		OnClickListener, OnPageChangeListener {

	// ������Ĭ�ϸ߶ȣ�������ָʾ���߶ȣ���λ��dp
	private static final int HEIGHT_NAVIGATION_BAR = 40;
	// ָʾ��Ĭ�ϸ߶ȣ���λ��dp
	private static final int HEIGHT_INDICATOR = 4;

	private Context context;
	private ViewPager viewPager;
	// ָʾ��
	private ImageView ivBottomLine;
	// ��ǰ��ʾ��index
	private int currIndex = 0;
	// �洢�ƶ�λ��
	private int positions[];
	// ��������
	private int titleCount;

	public IndicatorNavigationBar(Context context) {
		super(context);
		this.context = context;
	}

	/**
	 * 
	 * @Description: ��������������
	 * @param viewGroup
	 *            Ҫ�����ڵĸ�����
	 * @param titles
	 *            �ײ���ʾ�ĵ�������
	 * @param viewPager
	 *            �󶨵�ViewPager����
	 * @return void
	 */
	public void attachToParent(ViewGroup viewGroup, String[] titles,
			ViewPager viewPager) {

		this.viewPager = viewPager;
		titleCount = titles.length;

		// ��ʼ��������
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				dip2px(HEIGHT_NAVIGATION_BAR + HEIGHT_INDICATOR)));
		setBackgroundColor(getResources().getColor(android.R.color.transparent));
		setOrientation(VERTICAL);

		// ����������
		LinearLayout ll_navigation = new LinearLayout(context);
		ll_navigation.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, dip2px(HEIGHT_NAVIGATION_BAR)));
		ll_navigation.setBackgroundColor(getResources().getColor(
				android.R.color.holo_blue_light));
		ll_navigation.setOrientation(HORIZONTAL);

		// ���ɵ�����ť(TextView)
		for (int i = 0; i < titles.length; i++) {

			TextView textView = new TextView(context);
			textView.setLayoutParams(new LayoutParams(0,
					dip2px(HEIGHT_NAVIGATION_BAR), 1));
			textView.setText(titles[i]);
			textView.setGravity(Gravity.CENTER);
			textView.setTextSize(16);
			textView.setTextColor(getResources()
					.getColor(android.R.color.white));
			textView.setId(i);
			textView.setOnClickListener(this);
			ll_navigation.addView(textView);
		}
		// ��ӵ���
		this.addView(ll_navigation);

		// ָʾ������
		LinearLayout ll_indicator = new LinearLayout(context);
		ll_indicator.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, dip2px(HEIGHT_INDICATOR)));
		ll_indicator.setBackgroundColor(getResources().getColor(
				android.R.color.holo_blue_light));
		ll_indicator.setOrientation(HORIZONTAL);

		// ָʾ��
		ivBottomLine = new ImageView(context);
		ivBottomLine.setImageResource(android.R.color.holo_orange_light);
		ivBottomLine.setScaleType(ScaleType.MATRIX);
		ivBottomLine
				.setLayoutParams(new LinearLayout.LayoutParams(
						getScreenWidth(context) / titleCount,
						dip2px(HEIGHT_INDICATOR)));
		ll_indicator.addView(ivBottomLine);
		// ���ָʾ��
		this.addView(ll_indicator);

		viewGroup.addView(this);
		viewPager.setOnPageChangeListener(this);

		// ��ʼ���ƶ�λ��
		positions = new int[titleCount];
		positions[0] = 0;
		int distance = (int) (getScreenWidth(context) / titleCount);
		for (int i = 1; i < titleCount; i++) {
			positions[i] = distance * i;
		}

	}

	@Override
	public void onClick(View v) {
		viewPager.setCurrentItem(v.getId());
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {

		Animation animation = new TranslateAnimation(currIndex * positions[1],
				positions[position], 0, 0);
		currIndex = position;
		animation.setFillAfter(true);
		animation.setDuration(300);
		ivBottomLine.startAnimation(animation);
	}

	private int dip2px(float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	// ��ȡ��Ļ���
	private int getScreenWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		return dm.widthPixels;
	}
}
