package com.example.phantom.onlineshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phantom.onlineshop.DetailedActivity;
import com.example.phantom.onlineshop.R;
import com.example.phantom.onlineshop.models.Offer;
import com.example.phantom.onlineshop.other.DataForDetailedActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.RecyclerViewHolders> {
    private ArrayList<Offer> offerList;
    private Listener listener;
    private Context context;

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        public TextView name, weight, price, description;
        private CardView cardView;
        private ImageView imageView;

        public RecyclerViewHolders(View v) {
            super(v);
            cardView = (CardView) v;
            name = (TextView) cardView.findViewById(R.id.offer_name);
            weight = (TextView) cardView.findViewById(R.id.offer_weight);
            price = (TextView) cardView.findViewById(R.id.offer_price);
            description = (TextView) cardView.findViewById(R.id.offer_description);
            imageView = (ImageView) cardView.findViewById(R.id.imageView);
        }
    }

    public OffersAdapter(Context context, ArrayList<Offer> offers) {
        this.context = context;
        this.offerList = offers;
    }

    public static interface Listener {
        public void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_item, viewGroup, false);
        return new RecyclerViewHolders(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        final CardView cardView = holder.cardView;
        holder.name.setText(offerList.get(position).getName());
        holder.weight.setText(offerList.get(position).getParamMap().get("Вес"));
        holder.price.setText(offerList.get(position).getPrice());
        String imageUrl = offerList.get(position).getPicture();
        if (imageUrl.length() > 15) {
            Picasso.with(holder.imageView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.imageView);
        }
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
                String keyName = (offerList.get(position).getName());
                String keyWeight = (offerList.get(position).getParamMap().get("Вес"));
                String keyPrice = (offerList.get(position).getPrice());
                String keyImageUrl = (offerList.get(position).getPicture());
                String keyDescription = (offerList.get(position).getDescription());

                DataForDetailedActivity data = new DataForDetailedActivity(keyName, keyWeight, keyPrice, keyImageUrl, keyDescription);

                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("DATA", data);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }
}
