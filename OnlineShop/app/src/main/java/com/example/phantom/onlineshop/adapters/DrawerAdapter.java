package com.example.phantom.onlineshop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phantom.onlineshop.R;


public class DrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] drawerTitles;
    private Listener listener;
    Header header;
    Context context;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public static interface Listener {
        public void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public DrawerAdapter(Context context, Header header, String[] drawerTitles) {
        this.context = context;
        this.header = header;
        this.drawerTitles = drawerTitles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawer_header, viewGroup, false);
            return new VHHeader(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawer_item, viewGroup, false);
            return new VHItem(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof VHHeader) {
            //cast holder to VHHeader and set data for header.
        } else if (holder instanceof VHItem) {
            VHItem VHitem = (VHItem) holder;
            String drawerTitlesItem = getItem(position);
            VHitem.drawerName.setText(drawerTitlesItem);
            VHitem.drawerName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(position);
                    }
                }
            });
        }
    }

    private String getItem(int position) {
        return drawerTitles[position - 1];
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return drawerTitles.length + 1;
    }

    public class VHItem extends RecyclerView.ViewHolder {
        private TextView drawerName;

        public VHItem(View v) {
            super(v);
            drawerTitles = itemView.getResources().getStringArray(R.array.drawer_titles);
            drawerName = (TextView) itemView.findViewById(R.id.drawer_name);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        private TextView name, email;
        private ImageView avatar;

        public VHHeader(View v) {
            super(v);
            name = (TextView) itemView.findViewById(R.id.header_name);
            email = (TextView) itemView.findViewById(R.id.header_email);
            avatar = (ImageView) itemView.findViewById(R.id.header_avatar);
        }
    }
}
