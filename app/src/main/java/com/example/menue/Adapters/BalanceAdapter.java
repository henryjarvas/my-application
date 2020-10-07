package com.example.menue.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.menue.Models.BalanceModel;
import com.example.menue.Models.NotificationsModel;
import com.example.menue.R;

import java.util.List;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder> {
    Context context;
    List<BalanceModel> mData;
    RequestOptions options;
    TextView bonus_btn;



    public BalanceAdapter(Context context, List<BalanceModel> mData) {
        this.context = context;
        this.mData = mData;
        this.options = options;
    }


    @NonNull
    @Override
    public BalanceAdapter.BalanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_balance,null,false);
        return new BalanceViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull BalanceViewHolder holder, final int position) {

        BalanceModel balanceModel = mData.get(position);
        holder.name_holder.setText(mData.get(position).getFullname());
        holder.account_holder.setText(mData.get(position).getAccount());
        holder.amount_holder.setText(mData.get(position).getAmount());
        holder.balance.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class BalanceViewHolder extends RecyclerView.ViewHolder {

        TextView name_holder,account_holder,amount_holder;
        CardView balance;
        TextView fullname,account,amount,bonus_text ;
        Button bonus_btn;
        

        public BalanceViewHolder(@NonNull View itemView) {
            super(itemView);

            balance = itemView.findViewById(R.id.balance);
            name_holder = itemView.findViewById(R.id.name_holder);
            account_holder = itemView.findViewById(R.id.account_holder);
            amount_holder = itemView.findViewById(R.id.amount_holder);




            fullname = itemView.findViewById(R.id.fullname);
            account = itemView.findViewById(R.id.account);
            amount = itemView.findViewById(R.id.amount);
            bonus_text = itemView.findViewById(R.id.bonus_text);

        }

    }

}
