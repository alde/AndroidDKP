package com.unknown.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.http.client.ClientProtocolException;

import com.unknown.entity.UserInfoActivity;
import com.unknown.entity.json.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UserActivity extends Activity implements Runnable, View.OnClickListener {

	private List<User> users;
	private ProgressDialog pd;
	private LayoutInflater inflater;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_user);
		setTitle("DKP");
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		update();
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	private void createTabContent() {
		Collections.sort(users, new Comparator<User>() {

			@Override
			public int compare(User t, User t1) {
				return t.getDKP() < t1.getDKP() ? 1 : -1;
			}
		});
		TableLayout table = (TableLayout) findViewById(R.id.UsersTable);

		table.removeAllViews();
		for (User u : users) {
			if (u.isActive()) {
				TableRow row = (TableRow) inflater.inflate(R.layout.user_row, null);
				TextView charname = ((TextView) row.findViewById(R.id.UserName));
				TextView chardkp = ((TextView) row.findViewById(R.id.UserDKP));
				ImageView charclass = ((ImageView) row.findViewById(R.id.UserImage));

				charname.setText("  " + u.getUsername());
				if (u.getRole().equalsIgnoreCase("deathknight")) {
					charclass.setImageResource(R.drawable.deathknight);
					charname.setTextColor(Color.parseColor("#C41F3B"));
				} else if (u.getRole().equalsIgnoreCase("druid")) {
					charclass.setImageResource(R.drawable.druid);
					charname.setTextColor(Color.parseColor("#FF7D0A"));
				} else if (u.getRole().equalsIgnoreCase("hunter")) {
					charclass.setImageResource(R.drawable.hunter);
					charname.setTextColor(Color.parseColor("#ABD473"));
				} else if (u.getRole().equalsIgnoreCase("mage")) {
					charclass.setImageResource(R.drawable.mage);
					charname.setTextColor(Color.parseColor("#69CCF0"));
				} else if (u.getRole().equalsIgnoreCase("shaman")) {
					charclass.setImageResource(R.drawable.shaman);
					charname.setTextColor(Color.parseColor("#0070DE"));
				} else if (u.getRole().equalsIgnoreCase("priest")) {
					charclass.setImageResource(R.drawable.priest);
					charname.setTextColor(Color.parseColor("#FFFFFF"));
				} else if (u.getRole().equalsIgnoreCase("rogue")) {
					charclass.setImageResource(R.drawable.rogue);
					charname.setTextColor(Color.parseColor("#FFF569"));
				} else if (u.getRole().equalsIgnoreCase("warlock")) {
					charclass.setImageResource(R.drawable.warlock);
					charname.setTextColor(Color.parseColor("#9482C9"));
				} else if (u.getRole().equalsIgnoreCase("warrior")) {
					charclass.setImageResource(R.drawable.warrior);
					charname.setTextColor(Color.parseColor("#C79C6E"));
				} else if (u.getRole().equalsIgnoreCase("paladin")) {
					charclass.setImageResource(R.drawable.paladin);
					charname.setTextColor(Color.parseColor("#F58CBA"));
				}
				chardkp.setText("  " + u.getDKP() + " ");
				if (u.getDKP() < 0) {
					chardkp.setTextColor(Color.RED);
				} else {
					chardkp.setTextColor(Color.GREEN);
				}

				row.setTag(u);
				charclass.setPadding(0, 4, 0, 0);
				charname.setTextSize(24);
				chardkp.setTextSize(24);
				row.setOnClickListener(this);
				table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			}
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
		this.users = new ArrayList<User>();
		this.users.addAll(js.parseJSONUsers());
		handler.sendEmptyMessage(0);
	}

	public void update() {
		pd = ProgressDialog.show(this, "Working..", "Building DKP tables", true, false);
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void onClick(View v) {
		TableRow tr = (TableRow) v;
		User usr = (User) tr.getTag();
		Intent intent = new Intent().setClass(this, UserInfoActivity.class);
		intent.putExtra("user", usr);
		startActivity(intent);
	}
}