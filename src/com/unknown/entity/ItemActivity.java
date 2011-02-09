package com.unknown.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;

import com.unknown.entity.json.Items;

import android.app.Activity;
import android.graphics.Color;
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
		JsonHandler js = new JsonHandler();
		try {
			List<Items> items = new ArrayList<Items>();
			items.addAll(js.parseJSONItems());

			TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);

			for (Items i : items) {
				TableRow row = new TableRow(this);
				TextView itemname = new TextView(this);
				TextView price = new TextView(this);
				TextView pricehc = new TextView(this);
				if (i.getItemname().length() < 30) {
					itemname.setText(i.getItemname());
				} else {
					itemname.setText(i.getItemname().substring(0, 30) + "...");
				}
				if (i.getQuality().equalsIgnoreCase("epic")) {
					itemname.setTextColor(Color.parseColor("#9035CF"));
				} else if (i.getQuality().equalsIgnoreCase("legendary")) {
					itemname.setTextColor(Color.parseColor("#DE800D"));
				}
				price.setText(i.getPrice() + "  ");
				pricehc.setText(i.getPrice_hc() + "  ");

				row.addView(itemname);
				row.addView(price);
				row.addView(pricehc);
				row.setMinimumHeight(40);

				table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
