package com.example.phantom.onlineshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phantom.onlineshop.DetailedActivity;
import com.example.phantom.onlineshop.other.PicassoClient;
import com.example.phantom.onlineshop.R;
import com.example.phantom.onlineshop.models.PostList;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolders> {
    private ArrayList<PostList> postLists;
    private Context context;
    private Listener listener;

    public RecyclerViewAdapter(Context context, ArrayList<PostList> postLists) {
        this.context = context;
        this.postLists = postLists;
    }

    public static interface Listener {
        public void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model, viewGroup, false);
        return new RecyclerViewHolders(layoutView);
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        public TextView name, weight, price, description;
        private CardView cardView;

        public RecyclerViewHolders(View v) {
            super(v);
            cardView = (CardView) v;
            name = (TextView) cardView.findViewById(R.id.name);
            weight = (TextView) cardView.findViewById(R.id.weight);
            price = (TextView) cardView.findViewById(R.id.price);
            description = (TextView) cardView.findViewById(R.id.description);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.RecyclerViewHolders holder, final int position) {
            CardView cardView = holder.cardView;
            holder.name.setText(postLists.get(position).getName());
            holder.weight.setText(postLists.get(position).getWeight());
            holder.price.setText(postLists.get(position).getPrice());
            String imageUrl = postLists.get(position).getImageUrl();
            ImageView imageView = (ImageView) cardView.findViewById(R.id.imageView);
            if (imageUrl.length() > 20) {
                PicassoClient.downloadImage(context, imageUrl, imageView);
            }
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(position);
                    }
                    String keyName = (postLists.get(position).getName());
                    String keyWeight = (postLists.get(position).getWeight());
                    String keyPrice = (postLists.get(position).getPrice());
                    String keyImageUrl = (postLists.get(position).getImageUrl());
                    String keyDescription = (postLists.get(position).getDescription());
                    Intent intent = new Intent(context, DetailedActivity.class);
                    intent.putExtra(DetailedActivity.getKeyName(), keyName);
                    intent.putExtra(DetailedActivity.getKeyWeight(), keyWeight);
                    intent.putExtra(DetailedActivity.getKeyPrice(), keyPrice);
                    intent.putExtra(DetailedActivity.getKeyImageUrl(), keyImageUrl);
                    intent.putExtra(DetailedActivity.getKeyDescription(), keyDescription);
                    context.startActivity(intent);
                }
            });
        }

    @Override
    public int getItemCount() {
        return this.postLists.size();
    }
}