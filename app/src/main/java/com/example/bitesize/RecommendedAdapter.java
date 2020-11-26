package com.example.bitesize;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder> {

    private Context context;
    private List<Business> recommendedList;

    public RecommendedAdapter(Context context, List<Business> recommendedList) {
        this.context = context;
        this.recommendedList = recommendedList;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommended_recycler_items, parent, false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, final int position) {

        holder.recommendedName.setText(recommendedList.get(position).getName());
        holder.recommendedRating.setText(String.valueOf(recommendedList.get(position).getRating()));
        holder.recommendedCity.setText(recommendedList.get(position).getCity());
        holder.recommendedReviewCount.setText(recommendedList.get(position).getReviewCount());
        holder.recommendedPrice.setText(recommendedList.get(position).getPrice());

        Glide.with(context).load(recommendedList.get(position).getImageResourceId()).into(holder.recommendedImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Business.class);
                i.putExtra("name", recommendedList.get(position).getName());
                i.putExtra("price", recommendedList.get(position).getPrice());
                i.putExtra("rating", recommendedList.get(position).getRating());
                i.putExtra("image", recommendedList.get(position).getImageResourceId());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public static class RecommendedViewHolder extends RecyclerView.ViewHolder{

        ImageView recommendedImage;
        TextView recommendedName, recommendedRating, recommendedReviewCount, recommendedCity, recommendedPrice;

        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);

            recommendedImage = itemView.findViewById(R.id.recommended_image);
            recommendedName = itemView.findViewById(R.id.recommended_name);
            recommendedRating = itemView.findViewById(R.id.recommended_rating);
            recommendedReviewCount = itemView.findViewById(R.id.recommended_review_count);
            recommendedCity = itemView.findViewById(R.id.recommended_city);
            recommendedPrice = itemView.findViewById(R.id.recommended_price);

        }
    }


}
