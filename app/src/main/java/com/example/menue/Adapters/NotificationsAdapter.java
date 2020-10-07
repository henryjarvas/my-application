package com.example.menue.Adapters;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.menue.Models.NotificationsModel;
import com.example.menue.Models.SavingModel;
import com.example.menue.R;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {
    Context context;
    List<NotificationsModel> mData;
    RequestOptions options;
    TextView money;



    public NotificationsAdapter(Context context, List<NotificationsModel> mData) {
        this.context = context;
        this.mData = mData;
        this.options = options;
    }


    @NonNull
    @Override
    public NotificationsAdapter.NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_notifications,null,false);
        return new NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, final int position) {

        NotificationsModel notificationsModel = mData.get(position);
        holder.name_holder.setText(mData.get(position).getFullname());
        holder.account_holder.setText(mData.get(position).getAmount());
        holder.status_holder.setText(mData.get(position).getStatus());
        holder.amount_holder.setText(mData.get(position).getAmount());
        holder.saving_date_holder.setText(mData.get(position).getSaving_date());
        holder.balance_holder.setText(mData.get(position).getBalance());
        holder.notifications.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));
/*
        if (holder.status_holder.equals("deposit")){
            holder.amount_holder.setVisibility(View.GONE);
            holder.amount.setVisibility(View.GONE);

        }else {
            holder.amount_holder.setVisibility(View.VISIBLE);
            holder.amount.setVisibility(View.VISIBLE);
        }*/

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder {

        TextView name_holder,account_holder,status_holder,amount_holder,saving_date_holder,balance_holder;
        CardView notifications;
        TextView fullname,account,status,amount,saving_date,balance;
        

        public NotificationsViewHolder(@NonNull View itemView) {
            super(itemView);

            notifications = itemView.findViewById(R.id.notifications);
            name_holder = itemView.findViewById(R.id.name_holder);
            account_holder = itemView.findViewById(R.id.account_holder);
            status_holder = itemView.findViewById(R.id.status_holder);
            amount_holder = itemView.findViewById(R.id.amount_holder);
            saving_date_holder = itemView.findViewById(R.id.saving_date_holder);
            balance_holder = itemView.findViewById(R.id.balance_holder);




            fullname = itemView.findViewById(R.id.fullname);
            account = itemView.findViewById(R.id.account);
            status = itemView.findViewById(R.id.status);
            amount = itemView.findViewById(R.id.amount);
            saving_date = itemView.findViewById(R.id.saving_date);
            balance = itemView.findViewById(R.id.balance);

        }
    }
}
