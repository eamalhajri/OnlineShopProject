package com.example.phantom.onlineshop.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phantom.onlineshop.R;


public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.RecyclerViewHolders> {
    private String[] drawerTitles;
    private Listener listener;
    Context context;

    public static interface Listener {
        public void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView drawerName;

        public RecyclerViewHolders(View v) {
            super(v);
            cardView = (CardView) v;
            drawerTitles = cardView.getResources().getStringArray(R.array.drawer_titles);
            drawerName = (TextView) cardView.findViewById(R.id.drawer_name);
        }
    }

    public DrawerAdapter(Context context, String[] drawerTitles) {
        this.context = context;
        this.drawerTitles = drawerTitles;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawer_item, viewGroup, false);
        return new RecyclerViewHolders(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        final CardView cardView = holder.cardView;
        holder.drawerName.setText(drawerTitles[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return drawerTitles.length;
    }
}
