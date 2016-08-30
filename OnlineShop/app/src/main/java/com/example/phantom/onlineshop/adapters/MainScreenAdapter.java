package com.example.phantom.onlineshop.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phantom.onlineshop.R;

public class MainScreenAdapter extends RecyclerView.Adapter<MainScreenAdapter.ViewHolder> {
    private String[] names;
    private int[] images;
    private Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public MainScreenAdapter(String[] names, int[] images) {
        this.names = names;
        this.images = images;
    }

    @Override
    public MainScreenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView;
        ImageView imageView;
        TextView textView;
        Drawable drawable;
        cardView = holder.cardView;

        imageView = (ImageView) cardView.findViewById(R.id.info_image);
        textView = (TextView) cardView.findViewById(R.id.info_text);

        drawable = cardView.getResources().getDrawable(images[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(names[position]);

        textView.setText(names[position]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }


}
