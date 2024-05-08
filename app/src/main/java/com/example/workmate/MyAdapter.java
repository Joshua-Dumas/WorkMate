package com.example.workmate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

// Adapter class for RecyclerView
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;

    // Constructor
    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    // Create ViewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for RecyclerView item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    // Bind data to ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Load image using Glide library
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);

        // Set name and job title
        holder.recName.setText(dataList.get(position).getDataName());
        holder.recJob.setText(dataList.get(position).getDataJob());

        // Set click listener for item
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open detail activity with data passed through intent
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Job", dataList.get(holder.getAdapterPosition()).getDataJob());
                intent.putExtra("Name", dataList.get(holder.getAdapterPosition()).getDataName());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Email", dataList.get(holder.getAdapterPosition()).getDataEmail());
                intent.putExtra("Number", dataList.get(holder.getAdapterPosition()).getDataNumber());
                context.startActivity(intent);
            }
        });
    }

    // Get item count
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // Method to update data list for search functionality
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

// ViewHolder class
class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recName, recJob;
    CardView recCard;

    // Constructor
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        // Initialize views
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recJob = itemView.findViewById(R.id.recJob);
        recName = itemView.findViewById(R.id.recName);
    }
}
