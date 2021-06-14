package com.example.goconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static androidx.recyclerview.widget.RecyclerView.*;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {

    Context context;
    ArrayList<MyData> servicesList = new ArrayList<>();
    private Object ViewHolder;
    private OnItemListener onItemListener;

    public MyAdapter(Context context, ArrayList list, OnItemListener onItemListener){
        this.servicesList = list;
        this.context = context;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public MyAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.service_layout, parent, false);

        ViewHolder viewHolder =new viewHolder(view, onItemListener);
        return (MyAdapter.viewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.viewHolder holder, int position) {
        MyData data = servicesList.get(position);

        holder.image.setImageResource(data.getImage());
        holder.name.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public interface OnItemListener {
        void onItemClick(int adapterPosition);
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        OnItemListener onItemListerner;
        ImageView image;
        TextView name;
        public viewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            image = itemView.findViewById(R.id.service_pic);
            name = itemView.findViewById(R.id.service_name);

            this.onItemListerner = onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListerner.onItemClick(getAdapterPosition());
        }
    }
}
