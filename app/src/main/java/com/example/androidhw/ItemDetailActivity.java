package com.example.androidhw;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class ItemDetailActivity extends FragmentActivity {
	ItemDetailFragment fragmentItemDetail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		Video item=new Video();
		item.feedurl=(String)getIntent().getSerializableExtra("feedurl");
		item._id=(String)getIntent().getSerializableExtra("_id");
		item.avatar=(String)getIntent().getSerializableExtra("avatar");
		item.description=(String)getIntent().getSerializableExtra("description");
		item.nickname=(String)getIntent().getSerializableExtra("nickname");
		item.likecount=(long)getIntent().getSerializableExtra("likecount");
		if (savedInstanceState == null) {
			fragmentItemDetail = ItemDetailFragment.newInstance(item);
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.flDetailContainer, fragmentItemDetail);
			ft.commit();
		}
	}

}
