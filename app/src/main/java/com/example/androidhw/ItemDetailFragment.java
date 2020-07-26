package com.example.androidhw;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class ItemDetailFragment extends Fragment {
	private Video item;

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
		VideoView videoView=view.findViewById(R.id.videoPlayer);
		videoView.setVideoURI(Uri.parse(item.feedurl));
		like.setText(""+item.likecount);
		MediaController mc = new MediaController(this.getContext());
		videoView.setMediaController(mc);
		videoView.start();
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
