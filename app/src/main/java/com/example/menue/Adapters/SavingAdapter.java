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
import com.example.menue.Models.SavingModel;
import com.example.menue.Models.UsersModel;
import com.example.menue.R;

import java.util.List;

public class SavingAdapter extends RecyclerView.Adapter<SavingAdapter.SavingsViewHolder> {
    Context context;
    List<SavingModel> mData;
    RequestOptions options;

    public SavingAdapter(Context context, List<SavingModel> mData) {
        this.context = context;
        this.mData = mData;
        this.options = options;
    }


    @NonNull
    @Override
    public SavingAdapter.SavingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_savings,null,false);
        return new SavingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavingsViewHolder holder, final int position) {

        SavingModel savingModel = mData.get(position);
        holder.name_holder.setText(mData.get(position).getFullname());
        holder.contact_holder.setText(mData.get(position).getContact());
        holder.saving_date_holder.setText(mData.get(position).getSaving_date());
        holder.account_holder.setText(mData.get(position).getAccount());
        holder.period_holder.setText(mData.get(position).getPeriod());
        holder.frequency_holder.setText(mData.get(position).getFrequency());
        holder.balance_holder.setText(mData.get(position).getBalance());
        holder.payment_option_holder.setText(mData.get(position).getPayment_option());
        holder.savings.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SavingsViewHolder extends RecyclerView.ViewHolder {

        TextView name_holder,contact_holder,saving_date_holder,account_holder,period_holder,frequency_holder,balance_holder,payment_option_holder;
        CardView savings;
        TextView fullname,contact,saving_date,account,period,frequency,balance,payment_option;
        

        public SavingsViewHolder(@NonNull View itemView) {
            super(itemView);

            savings = itemView.findViewById(R.id.savings);
            name_holder = itemView.findViewById(R.id.name_holder);
            contact_holder = itemView.findViewById(R.id.contact_holder);
            saving_date_holder = itemView.findViewById(R.id.saving_date_holder);
            account_holder = itemView.findViewById(R.id.account_holder);
            balance_holder = itemView.findViewById(R.id.balance_holder);
            period_holder = itemView.findViewById(R.id.period_holder);
            frequency_holder = itemView.findViewById(R.id.frequency_holder);
            payment_option_holder = itemView.findViewById(R.id.payment_option_holder);


            fullname = itemView.findViewById(R.id.fullname);
            contact = itemView.findViewById(R.id.contact);
            saving_date = itemView.findViewById(R.id.saving_date);
            account = itemView.findViewById(R.id.account);
            period = itemView.findViewById(R.id.period);
            frequency = itemView.findViewById(R.id.frequency);
            balance = itemView.findViewById(R.id.balance);
            payment_option = itemView.findViewById(R.id.payment_option);

        }
    }
}
