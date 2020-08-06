package com.example.androidhw;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class GiftPopWindow extends PopupWindow {
    private Context mContext;
    private View view;
    private TextView rocket,present,pumpkin,cancel;

    public GiftPopWindow(Context mContext, View.OnClickListener onClickListener){
        this.view= LayoutInflater.from(mContext).inflate(R.layout.pop_up,null);

        rocket=view.findViewById(R.id.rocket);
        present=view.findViewById(R.id.present);
        pumpkin=view.findViewById(R.id.pumpkin);
        cancel=view.findViewById(R.id.cancel);

        cancel.setOnClickListener(v -> dismiss());

        rocket.setOnClickListener(onClickListener);
        present.setOnClickListener(onClickListener);
        pumpkin.setOnClickListener(onClickListener);

        this.setOutsideTouchable(true);

        this.view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height=view.findViewById(R.id.pop_layout).getTop();
                int y=(int)event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });

        this.setContentView(this.view);

        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        this.setFocusable(true);

        ColorDrawable dw=new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);

        this.setAnimationStyle(R.style.pop_anim);
    }
}
