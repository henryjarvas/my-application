package com.example.menue.Adapters;

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
import com.example.menue.Models.StatusModel;
import com.example.menue.R;

import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {
    Context context;
    List<StatusModel> mData;
    RequestOptions options;

    public StatusAdapter(Context context, List<StatusModel> mData) {
        this.context = context;
        this.mData = mData;
        this.options = options;
    }


    @NonNull
    @Override
    public StatusAdapter.StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_status,null,false);
        return new StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusAdapter.StatusViewHolder holder, final int position) {

        StatusModel statusModel = mData.get(position);
        holder.name_holder.setText(mData.get(position).getFullname());
        holder.account_holder.setText(mData.get(position).getAccount());
        holder.status_holder.setText(mData.get(position).getStatus());
        holder.amount_holder.setText(mData.get(position).getAmount());
        holder.saving_date_holder.setText(mData.get(position).getSaving_date());
        holder.balance_holder.setText(mData.get(position).getBalance());
        holder.transaction_status.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class StatusViewHolder extends RecyclerView.ViewHolder {

        TextView name_holder,account_holder,status_holder, amount_holder,saving_date_holder, balance_holder;
        CardView transaction_status;
        TextView fullname,account,status,amount,saving_date,balance;
        

        public StatusViewHolder(@NonNull View itemView) {
            super(itemView);

            transaction_status = itemView.findViewById(R.id.transaction_status);
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
