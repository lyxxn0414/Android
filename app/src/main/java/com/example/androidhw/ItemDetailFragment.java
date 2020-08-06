package com.example.androidhw;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.androidhw.like.CircleView;
import com.example.androidhw.like.DotsView;
import com.example.androidhw.like.LikeButton;

import java.io.Serializable;

public class ItemDetailFragment extends Fragment {
	private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
	private static final AccelerateDecelerateInterpolator ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
	private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);
	private Video item;
	private LikeButton likeButton;
	private static int time=0;
	private View gift;
	private TextView textView;
	private String[] gift_items={"rocket","race car"};
	private static ItemDetailActivity detailActivity;
	private static LottieAnimationView rocket_anim,present_anim,pumpkin_anim;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		item=(Video)getArguments().getSerializable("video");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
		TextView like=view.findViewById(R.id.likenum);
		textView=view.findViewById(R.id.description2);
		likeButton=view.findViewById(R.id.likeBtn);
		VideoView videoView=view.findViewById(R.id.videoPlayer);
		videoView.setVideoURI(Uri.parse(item.feedurl));
		gift=view.findViewById(R.id.gift);
		String num=""+item.likecount;
		like.setText(""+item.likecount);
		if(item.likecount>=10000){
			like.setText(num.substring(0,num.length()-4)+"W");
		}
		textView.setText("@"+item.nickname+"\n\n");
		textView.append(item.description+"\n");
		LottieAnimationView lottieAnimationView=view.findViewById(R.id.animation_view);
		MediaController mc = new MediaController(this.getContext());;
		videoView.setMediaController(mc);
		videoView.start();
		videoView.requestFocus();
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				time++;
				Log.d("click", "onClick: ");
				if(time==2){
					lottieAnimationView.setVisibility(View.VISIBLE);
					lottieAnimationView.playAnimation();
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							lottieAnimationView.setVisibility(View.INVISIBLE);
						}
					},1500);
					time=0;
					likeButton.setLiked(true);
				}
			}
		});
		gift.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				detailActivity.showPop(view.findViewById(R.id.flDetailContainer));
			}
		});
		rocket_anim=view.findViewById(R.id.rocket_animation);
		present_anim=view.findViewById(R.id.present_anim);
		pumpkin_anim=view.findViewById(R.id.pumpkin_anim);
		return view;
	}
    
    public static ItemDetailFragment newInstance(Video item,ItemDetailActivity activity) {
    	ItemDetailFragment fragmentDemo = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("video", (Serializable) item);
        fragmentDemo.setArguments(args);
        detailActivity=activity;
        return fragmentDemo;
    }

    public static void playRocket(){
		rocket_anim.setVisibility(View.VISIBLE);
		rocket_anim.playAnimation();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				rocket_anim.setVisibility(View.INVISIBLE);
			}
		},2000);
	}
	public static void playPresent(){
		present_anim.setVisibility(View.VISIBLE);
		present_anim.playAnimation();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				present_anim.setVisibility(View.INVISIBLE);
			}
		},2000);
	}

	public static void playPumpkin(){
		pumpkin_anim.setVisibility(View.VISIBLE);
		pumpkin_anim.playAnimation();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				pumpkin_anim.setVisibility(View.INVISIBLE);
			}
		},2000);
	}



}
