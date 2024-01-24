package com.example.quota;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class classAdapter extends RecyclerView.Adapter<classAdapter.ClassViewHolder>{
    ArrayList<ClassItem> classItems;
    Context context;

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public classAdapter(Context context, ArrayList<ClassItem> classItems) {
        this.classItems = classItems;
        this.context = context;
    }


    public static class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
TextView section_tv;
TextView roll;
TextView grade_tv;
        public ClassViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            section_tv = itemView.findViewById(R.id.section_tv);
            grade_tv = itemView.findViewById(R.id.grade_tv);
            itemView.setOnClickListener(v->onItemClickListener.onClick(getAdapterPosition()));
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(),0,0, "EDIT");
            menu.add(getAdapterPosition(),1,0, "DELETE");
        }
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item,parent,false);
        return new ClassViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        if (holder.roll != null){
        holder.roll.setText(classItems.get(position).getRoll());}
        holder.section_tv.setText(classItems.get(position).getSection());
        holder.grade_tv.setText(classItems.get(position).getGrade());
    }

    @Override
    public int getItemCount() {
        return classItems.size();
    }
}
