package com.unknown.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
			if (r.getRaidname().length() < 30) {
				raidname = " " + r.getRaidname();
			} else {
				raidname = " " + r.getRaidname().substring(0, 28) + "...";
			}
			row.setTag(r);
			raid.setText(raidname);
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
		Collections.sort(raids, new Comparator<Raid>() {
                        @Override
                        public int compare(Raid t, Raid t1) {
                                return t1.getDate().compareTo(t.getDate());
                        }
                });
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

		String foo = "\nRaid: " + raid.getRaidname() + "\n\nComment: " + raid.getComment() + "\n\nDate: " + raid.getDate();
		this.toast = Toast.makeText(this, foo, Toast.LENGTH_LONG);
		this.toast.show();

	}
}
