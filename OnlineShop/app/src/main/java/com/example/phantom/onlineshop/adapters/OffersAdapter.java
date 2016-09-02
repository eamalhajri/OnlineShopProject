package com.example.phantom.onlineshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phantom.onlineshop.DetailedActivity;
import com.example.phantom.onlineshop.R;
import com.example.phantom.onlineshop.models.Offer;
import com.example.phantom.onlineshop.other.PicassoClient;

import java.util.ArrayList;


public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.RecyclerViewHolders> {
    private ArrayList<Offer> offerList;
    private Listener listener;
    private Context context;

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        public TextView name, weight, price, description;
        private CardView cardView;

        public RecyclerViewHolders(View v) {
            super(v);
            cardView = (CardView) v;
            name = (TextView) cardView.findViewById(R.id.offer_name);
            weight = (TextView) cardView.findViewById(R.id.offer_weight);
            price = (TextView) cardView.findViewById(R.id.offer_price);
            description = (TextView) cardView.findViewById(R.id.offer_description);
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
        CardView cardView = holder.cardView;
        holder.name.setText(offerList.get(position).getName());
     //   holder.weight.setText(offerList.get(position).getWeight());
        holder.price.setText(offerList.get(position).getPrice());
        //holder.name.setText(offerList.get(position).getName());
        String imageUrl = offerList.get(position).getPicture();
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
                String keyName = (offerList.get(position).getName());
               // String keyWeight = (offerList.get(position).getWeight());
                String keyPrice = (offerList.get(position).getPrice());
                String keyImageUrl = (offerList.get(position).getPicture());
                String keyDescription = (offerList.get(position).getDescription());
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra(DetailedActivity.getKeyName(), keyName);
               // intent.putExtra(DetailedActivity.getKeyWeight(), keyWeight);
                intent.putExtra(DetailedActivity.getKeyPrice(), keyPrice);
                intent.putExtra(DetailedActivity.getKeyImageUrl(), keyImageUrl);
                intent.putExtra(DetailedActivity.getKeyDescription(), keyDescription);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }
}
