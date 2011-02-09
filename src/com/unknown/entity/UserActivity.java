package com.unknown.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UserActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_user);
		createTabContent();
	}

	private void createTabContent() {
		JsonHandler js = new JsonHandler();
		try {
			List<User> users = new ArrayList<User>();
			users.addAll(js.parseJSONUsers());

			 Collections.sort(users, new Comparator<User>() {

		                        @Override
		                        public int compare(User t, User t1) {
		                                return t.getDKP() < t1.getDKP() ? 1 : -1;
		                        }
		                });
			TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);

			for (User u : users) {
				TableRow row = new TableRow(this);
				TextView charname = new TextView(this);
				TextView chardkp = new TextView(this);
				ImageView charclass = new ImageView(this);

				charname.setText(u.getUsername());
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
				chardkp.setText("" + u.getDKP());
				if (u.getDKP() < 0) {
					chardkp.setTextColor(Color.RED);
				} else {
					chardkp.setTextColor(Color.GREEN);
				}
				charclass.setPadding(2, 0, 4, 0);
				chardkp.setPadding(0,0,8,0);
				row.addView(charclass);
				row.addView(charname);
				row.addView(chardkp);
				row.setPadding(0, 4, 0, 4);

				table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}