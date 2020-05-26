package com.mapps.pixabayclientapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mapps.pixabayclientapp.R;
import com.mapps.pixabayclientapp.models.Image;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder> {

    public List<Image> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    private ItemClickListener mClickListener;

    public MainRecyclerAdapter(Context context, ItemClickListener itemClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_image_item_layout, parent, false);
        view.getLayoutParams().height = parent.getMeasuredHeight() / 5;
        return new MyViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load(mData.get(position).getPreviewURL()).centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemClickListener mClickListener;

        ImageView imageView;
        MyViewHolder(View view, ItemClickListener itemClickListener) {
            super(view);
            imageView = view.findViewById(R.id.image_item);

            mClickListener = itemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onItemClick(view, getAdapterPosition());
        }

    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setData(List<Image> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

}
