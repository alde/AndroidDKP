package com.unknown.entity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class dkp extends TabActivity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Resources res = getResources();
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		TabHost.TabSpec spec;
		Intent intent;

		intent = new Intent().setClass(this, UserActivity.class);
		spec = tabHost.newTabSpec("chars").setIndicator("Chars", res.getDrawable(R.drawable.ic_tab_user)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, ItemActivity.class);
		spec = tabHost.newTabSpec("items").setIndicator("Items", res.getDrawable(R.drawable.ic_tab_item)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, RaidActivity.class);
		spec = tabHost.newTabSpec("raids").setIndicator("Raids", res.getDrawable(R.drawable.ic_tab_raid)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}
}