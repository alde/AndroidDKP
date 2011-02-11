package com.unknown.entity;

import java.util.ArrayList;
import java.util.List;
import com.unknown.entity.json.CharacterItem;
import com.unknown.entity.json.User;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UserInfoActivity extends Activity {

	User user = new User();
	private LayoutInflater inflater;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_user);
		this.user = (User) getIntent().getSerializableExtra("user");
		inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		ImageView imgView = (ImageView) findViewById(R.id.UserClassImage);
		TextView charName = (TextView) findViewById(R.id.cName);
		TextView charShares = (TextView) findViewById(R.id.cShares);
		TextView charDkp = (TextView) findViewById(R.id.cDkp);
		TextView charEarnedDkp = (TextView) findViewById(R.id.cEarnedDkp);
		TextView charSpentDkp = (TextView) findViewById(R.id.cSpentDkp);
		
		charName.setText(user.getUsername());

		if (user.getRole().equalsIgnoreCase("deathknight")) {
			imgView.setImageResource(R.drawable.deathknight);
			charName.setTextColor(Color.parseColor("#C41F3B"));
		} else if (user.getRole().equalsIgnoreCase("druid")) {
			imgView.setImageResource(R.drawable.druid);
			charName.setTextColor(Color.parseColor("#FF7D0A"));
		} else if (user.getRole().equalsIgnoreCase("hunter")) {
			imgView.setImageResource(R.drawable.hunter);
			charName.setTextColor(Color.parseColor("#ABD473"));
		} else if (user.getRole().equalsIgnoreCase("mage")) {
			imgView.setImageResource(R.drawable.mage);
			charName.setTextColor(Color.parseColor("#69CCF0"));
		} else if (user.getRole().equalsIgnoreCase("shaman")) {
			imgView.setImageResource(R.drawable.shaman);
			charName.setTextColor(Color.parseColor("#0070DE"));
		} else if (user.getRole().equalsIgnoreCase("priest")) {
			imgView.setImageResource(R.drawable.priest);
			charName.setTextColor(Color.parseColor("#FFFFFF"));
		} else if (user.getRole().equalsIgnoreCase("rogue")) {
			imgView.setImageResource(R.drawable.rogue);
			charName.setTextColor(Color.parseColor("#FFF569"));
		} else if (user.getRole().equalsIgnoreCase("warlock")) {
			imgView.setImageResource(R.drawable.warlock);
			charName.setTextColor(Color.parseColor("#9482C9"));
		} else if (user.getRole().equalsIgnoreCase("warrior")) {
			imgView.setImageResource(R.drawable.warrior);
			charName.setTextColor(Color.parseColor("#C79C6E"));
		} else if (user.getRole().equalsIgnoreCase("paladin")) {
			imgView.setImageResource(R.drawable.paladin);
			charName.setTextColor(Color.parseColor("#F58CBA"));
		}
		charDkp.setText(user.getDKP() + "");
		charEarnedDkp.setText(user.getDKPEarned() + "");
		charSpentDkp.setText(user.getDKPSpent() + "");
		charShares.setText(user.getShares() + "");

		List<CharacterItem> citems = new ArrayList<CharacterItem>();
		citems.addAll(user.getCharItems());
		TableLayout table = (TableLayout) findViewById(R.id.LootTable);
		table.removeAllViews();
		for (CharacterItem ci : citems) {
			TableRow row = (TableRow)inflater.inflate(R.layout.info_user_loot_row, null);
			TextView itemname = ((TextView)row.findViewById(R.id.ItemLootName));
			TextView price = ((TextView)row.findViewById(R.id.ItemLootPrice));
			if (ci.getName().length() < 30) {
				itemname.setText(" " + ci.getName());
			} else {
				itemname.setText(" " + ci.getName().substring(0, 30) + "...");
			}
			if (ci.getQuality().equalsIgnoreCase("epic")) {
				itemname.setTextColor(Color.parseColor("#9035CF"));
			} else if (ci.getQuality().equalsIgnoreCase("legendary")) {
				itemname.setTextColor(Color.parseColor("#DE800D"));
			}
			
			price.setText("   "+ ci.getPrice());
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		}

	}

	public void close(View t) {
		finish();
	}
}
