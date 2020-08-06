package com.example.androidhw;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class ItemDetailActivity extends FragmentActivity {
	ItemDetailFragment fragmentItemDetail;
	LottieAnimationView rocket,race_car;
	GiftPopWindow giftPopWindow;

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
			fragmentItemDetail = ItemDetailFragment.newInstance(item,this);
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.flDetailContainer, fragmentItemDetail);
			ft.commit();
		}
		rocket=findViewById(R.id.rocket_animation);
	}

	public void showPop(View view){
		giftPopWindow=new GiftPopWindow(this,onClickListener);
		giftPopWindow.showAtLocation(findViewById(R.id.flDetailContainer), Gravity.CENTER,0,0);
	}



	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {

			switch (v.getId()) {
				case R.id.rocket:
					giftPopWindow.dismiss();
					ItemDetailFragment.playRocket();
					break;
				case R.id.present:
					giftPopWindow.dismiss();
					ItemDetailFragment.playPresent();
					break;
				case R.id.pumpkin:
					giftPopWindow.dismiss();
					ItemDetailFragment.playPumpkin();
					break;
			}
		}

	};
}
