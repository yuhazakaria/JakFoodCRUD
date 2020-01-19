package com.example.jakfoodcrud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jakfoodcrud.BuildConfig;
import com.example.jakfoodcrud.R;
import com.example.jakfoodcrud.model.DataMakananItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    Context context;
    List<DataMakananItem> data;
    private OnFoodClick click;



    public FoodAdapter(Context context, List<DataMakananItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodViewHolder(LayoutInflater.from(context).inflate(R.layout.item_food, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

        holder.tvTitle.setText(data.get(position).getMakanan());
        holder.tvTanggal.setText(data.get(position).getInsertTime());
        String images = BuildConfig.BASE_URL + "Uploads/" + data.get(position).getFotoMakanan();
        Picasso.get().load(images).into(holder.imgMakanan);

        holder.itemView.setOnClickListener(view -> click.onItemClick(position));

    }

    public interface OnFoodClick {
    void onItemClick(int position);
    }

    public void setOnClickListener(OnFoodClick onClick){
        click = onClick;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMakanan;
        TextView tvTitle, tvTanggal;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMakanan = itemView.findViewById(R.id.item_images);
            tvTitle = itemView.findViewById(R.id.item_desk);
            tvTanggal = itemView.findViewById(R.id.item_time);
        }
    }
}
