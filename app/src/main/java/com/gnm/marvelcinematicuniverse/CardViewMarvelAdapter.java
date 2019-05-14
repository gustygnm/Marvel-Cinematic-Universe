package com.gnm.marvelcinematicuniverse;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewMarvelAdapter extends RecyclerView.Adapter<CardViewMarvelAdapter.CardViewViewHolder> {
    private Context context;
    private ArrayList<Marvel> listMarvel;

    private ArrayList<Marvel> getListMarvel() {
        return listMarvel;
    }

    public void setListMarvel(ArrayList<Marvel> listMarvel) {
        this.listMarvel = listMarvel;
    }

    public CardViewMarvelAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder cardViewViewHolder, int i) {
        Marvel p = getListMarvel().get(i);
        Glide.with(context)
                .load(p.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgPhoto);
        cardViewViewHolder.tvName.setText(p.getName());
        cardViewViewHolder.tvRemarks.setText(p.getYear());
        cardViewViewHolder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("url", getListMarvel().get(position).getPhoto());
                i.putExtra("judul", getListMarvel().get(position).getName());
                i.putExtra("tahun", getListMarvel().get(position).getYear());
                i.putExtra("quotes", getListMarvel().get(position).getQuoter());
                i.putExtra("isi", getListMarvel().get(position).getIsi());
                context.startActivity(i);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListMarvel().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvRemarks;
        Button btnFavorite;

        CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
        }
    }
}