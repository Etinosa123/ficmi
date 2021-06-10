package com.hfad.myficmiapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.myficmiapp.R;
import com.hfad.myficmiapp.model.DevotionModel;

import java.util.List;

public class DevotionAdapter extends RecyclerView.Adapter<DevotionAdapter.ViewHolder> {

    List<DevotionModel> list;
    Context context;

    public DevotionAdapter( Context context,List<DevotionModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DevotionAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.devotion, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.passage.setText(list.get(position).getPassage());
        holder.scripture.setText(list.get(position).getScripture());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView date , scripture, passage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            scripture = itemView.findViewById(R.id.scripture);
            passage = itemView.findViewById(R.id.passage);
        }
    }
}
