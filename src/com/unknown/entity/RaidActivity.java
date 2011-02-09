package com.unknown.entity;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RaidActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_raid);
		createTabContent();
	}

	private void createTabContent() {
		for (int i = 0; i < 15; i++) {
			double dr = Math.random();
			long r = Math.round(dr * 100);
			TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
			TableRow row = new TableRow(this);
			TextView raidname = new TextView(this);
			raidname.setText("raid id: " + i);
			TextView raidshares = new TextView(this);
			raidshares.setText("shares:  " + r);
			TextView raiddate = new TextView(this);
			raiddate.setText("date: " + i);
			row.addView(raidname);
			row.addView(raidshares);
			row.addView(raiddate);
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}
}
