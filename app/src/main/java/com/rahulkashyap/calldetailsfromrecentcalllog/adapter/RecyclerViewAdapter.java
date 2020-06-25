package com.rahulkashyap.calldetailsfromrecentcalllog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahulkashyap.calldetailsfromrecentcalllog.R;
import com.rahulkashyap.calldetailsfromrecentcalllog.util.Utils;
import com.rahulkashyap.calldetailsfromrecentcalllog.util.model.CallLogInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<CallLogInfo> callLogInfoArrayList;

    public RecyclerViewAdapter(ArrayList<CallLogInfo> readCallLogs, Context applicationContext) {
        this.context = applicationContext;
        this.callLogInfoArrayList = readCallLogs;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CallLogInfo object = callLogInfoArrayList.get(position);
        Date dateObj = new Date(object.getDate());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy   hh:mm a");
        holder.number.setVisibility(View.GONE);
        holder.imageView.setVisibility(View.GONE);

        if (object.getName()!=null) {
            holder.name.setText(object.getName());

        }else {
            holder.name.setText(object.getNumber());

        }
        holder.date.setText(formatter.format(dateObj));
        holder.duration.setText(Utils.formatSeconds(object.getDuration()));
    }

    @Override
    public int getItemCount() {
        return callLogInfoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,date,calltype,number,duration;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_big_text);
            date = itemView.findViewById(R.id.item_small_text);
            imageView = itemView.findViewById(R.id.item_photo_placeholder);
            number = itemView.findViewById(R.id.item_header);
            duration = itemView.findViewById(R.id.duration);


        }
    }

}
