package com.unknown.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.unknown.entity.json.Raid;
import com.unknown.entity.json.RaidItem;
import com.unknown.entity.json.RaidReward;

public class RaidActivity extends Activity implements Runnable, View.OnClickListener {

	private List<Raid> raids;
	private ProgressDialog pd;
	private LayoutInflater inflater;
	private Toast toast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_raid);
		setTitle("Raids");
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		update();
	}

	private void createTabContent() {
		TableLayout table = (TableLayout) findViewById(R.id.RaidsTable);
		table.removeAllViews();
		for (Raid r : raids) {
			TableRow row = (TableRow) inflater.inflate(R.layout.raid_row, null);
			TextView raid = ((TextView) row.findViewById(R.id.RaidInfo));
			TextView date = ((TextView) row.findViewById(R.id.RaidDate));
			String raidname = "";
			String comment = "";
			if (r.getRaidname().length() < 30) {
				raidname = r.getRaidname();
			} else {
				raidname = r.getRaidname().substring(0, 30) + "...";
			}
			if (r.getComment().length() < 30) {
				comment = r.getComment();
			} else {
				comment = r.getComment().substring(0, 30) + "...";
			}
			row.setTag(r);
			raid.setText(raidname + "\n  " + comment);
			date.setText(r.getDate().substring(0, 10) + " ");
			
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
		this.raids = new ArrayList<Raid>();
		this.raids.addAll(js.parseJSONRaids());
		handler.sendEmptyMessage(0);
	}

	public void update() {
		pd = ProgressDialog.show(this, "Working..", "Building Raid tables", true, false);
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void onClick(View v) {
		if (toast != null) {
			this.toast.cancel();
		}
		TableRow tr = (TableRow) v;
		Raid raid = (Raid) tr.getTag();

		List<RaidItem> ri = raid.getRaidItems();
		List<RaidReward> rr = raid.getRaidRewards();
		String foo = "";
//		if (!ri.isEmpty()) {
			foo = "Loot:";
			for (RaidItem r : ri) {
				if (r.getName().length() > 10)
					foo += "\n" + r.getName().substring(0, 10) + "..." + " looted by " + r.getLooter();
				else
					foo += "\n" + r.getName() + " looted by " + r.getLooter();
			}
//		}
//		if (!rr.isEmpty()) {
			foo = "\n\nRewards:";
			for (RaidReward r : rr) {
				if (r.getComment().length() > 10)
					foo += "\n" + r.getComment().substring(0, 10) + "..." + " - " + r.getShares() + " shares.";
				else
					foo += "\n" + r.getComment() + " - " + r.getShares() + " shares.";
			}
//		}

		this.toast = Toast.makeText(this, foo, Toast.LENGTH_LONG);
		this.toast.show();

	}
}
