package com.unknown.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.client.ClientProtocolException;

import com.unknown.entity.json.ItemLooter;
import com.unknown.entity.json.Items;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends Activity implements Runnable, View.OnClickListener {

	private List<Items> items;
	private ProgressDialog pd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_item);
		setTitle("Items");
		update();

	}

	private void createTabContent() {

		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
		table.removeAllViews();
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

			row.setId(i.getId());
			row.setOnClickListener(this);
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}

	@Override
	public void run() {
		try {
			buildJSON();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			pd.dismiss();
			createTabContent();

		}
	};

	private void buildJSON() throws ClientProtocolException, IOException {
		JsonHandler js = new JsonHandler();
		this.items = new ArrayList<Items>();
		this.items.addAll(js.parseJSONItems());
		handler.sendEmptyMessage(0);
	}

	public void update() {
		pd = ProgressDialog.show(this, "Working..", "Building Item tables", true, false);

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void onClick(View v) {
		TableRow tr = (TableRow) v;
		int id = tr.getId();
		Items itm = new Items();
		for (Items i : items) {
			if (i.getId() == id) {
				itm = i;
				break;
			}
		}
		List<ItemLooter> looters = itm.getLooterList();
		Collections.reverse(looters);
		String foo = itm.getItemname();
		if (!looters.isEmpty()) {
			if (looters.size() > 10)
				foo += "\n\nLooted by (latest 10): ";
			else
				foo += "\n\nLooted by:";

			int count = 0;
			for (ItemLooter il : looters) {
				count++;
				foo += "\n" + il.getName() + " on " + il.getDate().substring(0, 10);
				if (count == 10)
					break;
			}
		}

		Toast toast = Toast.makeText(this, foo, Toast.LENGTH_LONG);
		toast.show();
	}
}
