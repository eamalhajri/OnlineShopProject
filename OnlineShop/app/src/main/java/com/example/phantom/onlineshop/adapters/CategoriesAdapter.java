package com.example.phantom.onlineshop.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phantom.onlineshop.R;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    private Context context;
    private String[] names;
    private int[] images;
    private Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imageView;
        private TextView textView;

        public CategoryViewHolder(View v) {
            super(v);
            cardView = (CardView) v;
            imageView = (ImageView) cardView.findViewById(R.id.info_image);
            textView = (TextView) cardView.findViewById(R.id.info_text);
        }
    }

    public CategoriesAdapter(String[] names, int[] images, Context context) {
        this.names = names;
        this.images = images;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        Drawable drawable = ContextCompat.getDrawable(context, images[position]);
        holder.imageView.setImageDrawable(drawable);
        holder.imageView.setContentDescription(names[position]);
        holder.textView.setText(names[position]);
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
