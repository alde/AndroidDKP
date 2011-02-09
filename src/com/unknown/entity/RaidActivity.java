package com.unknown.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.unknown.entity.json.Raid;

public class RaidActivity extends Activity implements Runnable {
	
	private List<Raid> raids;
	private ProgressDialog pd;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_raid);
		setTitle("Raids");
		update();
	}

	private void createTabContent() {
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);

			for (Raid r : raids) {
				TableRow row = new TableRow(this);
				TextView raid = new TextView(this);
				TextView date = new TextView(this);
				String raidname = "";
				String comment = "";
				if (r.getName().length() < 30) {
					raidname = r.getName();
				} else {
					raidname = r.getName().substring(0, 30) + "...";
				}
				if (r.getComment().length() < 30) {
					comment = r.getComment();
				} else {
					comment = r.getComment().substring(0, 30) + "...";
				}

				raid.setText(raidname + "\n  " + comment);
				date.setText(r.getDate().substring(0, 10) + " ");

				row.addView(raid);
				row.addView(date);
				row.setMinimumHeight(40);

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
}
