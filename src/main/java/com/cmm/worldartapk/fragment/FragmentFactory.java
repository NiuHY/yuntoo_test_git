package com.cmm.worldartapk.fragment;


import com.cmm.worldartapk.base.BaseFragment;

import java.util.HashMap;

public class FragmentFactory {

	// 测试1
	private static final int TEST_1 = 0;
	// 测试2
	private static final int TEST_2 = 1;
	// 测试3
	private static final int TEST_3 = 2;

	private static HashMap<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();

	public static BaseFragment createFragment(int position) {
		// BaseFragment fragment = null;
		// 从缓存中取出
		BaseFragment fragment = mFragments.get(position);
		if (null == fragment) {
			switch (position) {
			case TEST_1:
				fragment = new Test1_Fragment();
				break;
			case TEST_2:
				fragment = new Test2_Fragment();
				break;
			case TEST_3:
				fragment = new Test3_Fragment();
				break;

			default:
				break;
			}
			// 把frament加入到缓存中
			mFragments.put(position, fragment);
		}
		return fragment;
	}
}
