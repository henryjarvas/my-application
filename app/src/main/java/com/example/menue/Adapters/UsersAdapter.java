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
import com.example.menue.Models.UsersModel;
import com.example.menue.R;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    Context context;
    List<UsersModel> mData;
    RequestOptions options;

    public UsersAdapter(Context context, List<UsersModel> mData) {
        this.context = context;
        this.mData = mData;
        this.options = options;
    }


    @NonNull
    @Override
    public UsersAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_users,null,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, final int position) {

        UsersModel usersModel = mData.get(position);
        holder.name_holder.setText(mData.get(position).getFullname());
        holder.email_holder.setText(mData.get(position).getEmail());
        holder.contact_holder.setText(mData.get(position).getContact());
        holder.address_holder.setText(mData.get(position).getAddress());
        holder.occupation_holder.setText(mData.get(position).getOccupation());
        holder.password_holder.setText(mData.get(position).getPassword());
        holder.confirm_password_holder.setText(mData.get(position).getConfirmpassword());
        holder.users.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView name_holder,email_holder,contact_holder,address_holder,occupation_holder,password_holder,confirm_password_holder;
        CardView users;
        TextView name,email,contact,address,occupation,password,confirm_password;
        

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            users = itemView.findViewById(R.id.users);
            name_holder = itemView.findViewById(R.id.name_holder);
            email_holder = itemView.findViewById(R.id.email_holder);
            contact_holder = itemView.findViewById(R.id.contact_holder);
            address_holder = itemView.findViewById(R.id.address_holder);
            occupation_holder = itemView.findViewById(R.id.occupation_holder);
            password_holder = itemView.findViewById(R.id.password_holder);
            confirm_password_holder = itemView.findViewById(R.id.confirm_password_holder);


            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            contact = itemView.findViewById(R.id.contact);
            address = itemView.findViewById(R.id.address);
            occupation = itemView.findViewById(R.id.occupation);
            password = itemView.findViewById(R.id.password);
            confirm_password = itemView.findViewById(R.id.confirm_password);

        }
    }
}
