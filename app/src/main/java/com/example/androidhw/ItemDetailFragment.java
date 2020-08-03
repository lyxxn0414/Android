package com.example.androidhw;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

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
		likeButton=view.findViewById(R.id.likeBtn);
		VideoView videoView=view.findViewById(R.id.videoPlayer);
		videoView.setVideoURI(Uri.parse(item.feedurl));
		like.setText(""+item.likecount);
		MediaController mc = new MediaController(this.getContext());
		videoView.setMediaController(mc);
		videoView.start();
		LottieAnimationView lottieAnimationView=view.findViewById(R.id.animation_view);
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
		return view;
	}
    
    public static ItemDetailFragment newInstance(Video item) {
    	ItemDetailFragment fragmentDemo = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("video", (Serializable) item);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }
}
