package com.gnm.marvelcinematicuniverse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridMarvelAdapter extends RecyclerView.Adapter<GridMarvelAdapter.GridViewHolder> {
    private Context context;
    private ArrayList<Marvel> listMarvel;

    private ArrayList<Marvel> getListMarvel() {
        return listMarvel;
    }

    public void setListMarvel(ArrayList<Marvel> listMarvel) {
        this.listMarvel = listMarvel;
    }

    public GridMarvelAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Glide.with(context)
                .load(getListMarvel().get(position).getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.tvJudul.setText(getListMarvel().get(position).getName());
        holder.tvTahun.setText(getListMarvel().get(position).getYear());
    }

    @Override
    public int getItemCount() {
        return getListMarvel().size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul,tvTahun;

        GridViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvJudul = itemView.findViewById(R.id.txtJudul);
            tvTahun = itemView.findViewById(R.id.txtTahun);

        }
    }
}

