package com.example.bitesize;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;



import java.util.List;

public class ReviewListAdapter extends ArrayAdapter<Review> {
    private Activity context;
    private List<Review> reviewList;

    public ReviewListAdapter(Activity context, List<Review> reviewList) {
        super(context, R.layout.list_layout, reviewList);
        this.context = context;
        this.reviewList = reviewList;
    }

    public ReviewListAdapter(Context context, int resource, List<Review> objects, Activity context1, List<Review> studentList) {
        super(context, resource, objects);
        this.context = context1;
        this.reviewList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.review_recycler_items, null, true);

        TextView tvName = listViewItem.findViewById(R.id.user_name);
        TextView tvRating = listViewItem.findViewById(R.id.user_rating);
        TextView tvReview = listViewItem.findViewById(R.id.user_review);

        Review review = reviewList.get(position);
        tvName.setText(review.getName());
        tvRating.setText(String.valueOf(review.getRating()));
        tvReview.setText(review.getReview());
        return listViewItem;
    }

}
