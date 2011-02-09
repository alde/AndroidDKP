package com.unknown.entity;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ItemActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_item);
		createTabContent();
	}

	private void createTabContent() {
		for (int i = 0; i < 15; i++) {
			double dr = Math.random();
			long r = Math.round(dr*100);
			
			TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
			TableRow row = new TableRow(this);
			TextView itemname = new TextView(this);
			TextView itemprice = new TextView(this);
			TextView itemlooted = new TextView(this);
			
			itemname.setText("item nr " + i);
			itemlooted.setText("item looted " + r + " times.");
			itemprice.setText("item nr " + i + " price");
			
			row.addView(itemname);
			row.addView(itemlooted);
			row.addView(itemprice);
			
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}
}
