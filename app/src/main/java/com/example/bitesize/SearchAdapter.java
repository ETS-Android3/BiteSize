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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context context;
    private List<Business> searchList;

    public SearchAdapter(Context context, List<Business> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_recycler_items, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, final int position) {

        holder.searchName.setText(searchList.get(position).getName());

        Glide.with(context)
                .load(context.getResources().getIdentifier(searchList.get(position).getImageName(), "drawable", context.getPackageName()))
                .into(holder.searchImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Business.class);
                i.putExtra("name", searchList.get(position).getName());
                i.putExtra("price", searchList.get(position).getPrice());
                i.putExtra("rating", searchList.get(position).getRating());
                i.putExtra("image", context.getResources().getIdentifier(searchList.get(position).getImageName(), "drawable", context.getPackageName()));

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public  static class SearchViewHolder extends RecyclerView.ViewHolder{

        ImageView searchImage;
        TextView searchName;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            searchName = itemView.findViewById(R.id.all_menu_name);
            searchImage = itemView.findViewById(R.id.all_menu_image);

        }
    }
}
