package com.example.kotlindemo.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kotlindemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivityAdapter extends RecyclerView.Adapter<HomeActivityAdapter.MyView> {

    Context context;
    ArrayList<String> modelArrayList;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    int Seconds, Minutes, MilliSeconds;
    MyView myHolder;

    public HomeActivityAdapter(Context context, ArrayList<String> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        handler = new Handler();
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homescreen_adapter, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyView holder, final int position) {

//        holder.homeAdapter_tv.setText(modelArrayList.get(holder.getAdapterPosition()));
        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHolder = holder;
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                holder.btnReset.setEnabled(false);

            }
        });

        holder.btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHolder = holder;
                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                holder.btnReset.setEnabled(true);
            }
        });

        holder.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHolder = holder;
                MillisecondTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;
                Seconds = 0;
                Minutes = 0;
                MilliSeconds = 0;

                holder.tvTimer.setText("00:00:00");

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class MyView extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_image)
        CircleImageView adapter_image;

        @BindView(R.id.tvTimer)
        TextView tvTimer;

        @BindView(R.id.btStart)
        Button btnStart;

        @BindView(R.id.btPause)
        Button btnPause;

        @BindView(R.id.btReset)
        Button btnReset;

        public MyView(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            myHolder.tvTimer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }
    };

}
