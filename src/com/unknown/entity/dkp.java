package com.unknown.entity;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
		spec = tabHost.newTabSpec("chars").setIndicator("DKP", res.getDrawable(R.drawable.ic_tab_user)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, ItemActivity.class);
		spec = tabHost.newTabSpec("items").setIndicator("Items", res.getDrawable(R.drawable.ic_tab_item)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, RaidActivity.class);
		spec = tabHost.newTabSpec("raids").setIndicator("Raids", res.getDrawable(R.drawable.ic_tab_raid)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}

	public void refresh() {
		Activity v = getCurrentActivity();
		if (v.getTitle().toString().equalsIgnoreCase("DKP")) {
			UserActivity ua = (UserActivity) v;
			ua.update();
		} else if (v.getTitle().toString().equalsIgnoreCase("Items")) {
			ItemActivity ia = (ItemActivity) v;
			ia.update();
		} else if (v.getTitle().toString().equalsIgnoreCase("Raids")) {
			RaidActivity ra = (RaidActivity) v;
			ra.update();
		}
	}

	public void quit() {
		this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.refresh:
			refresh();
			return true;
		case R.id.quit:
			quit();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}