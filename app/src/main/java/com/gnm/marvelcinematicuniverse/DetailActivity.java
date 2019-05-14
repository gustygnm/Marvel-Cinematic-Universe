package com.gnm.marvelcinematicuniverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flaviofaria.kenburnsview.KenBurnsView;

public class DetailActivity extends AppCompatActivity {

    KenBurnsView imgBg;
    TextView txtJudul, txtTahun, txtQuotes, txtIsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        imgBg = findViewById(R.id.imgBg);
        txtJudul = findViewById(R.id.txtJudul);
        txtTahun = findViewById(R.id.txtTahun);
        txtQuotes = findViewById(R.id.txtQuotes);
        txtIsi = findViewById(R.id.txtIsi);

        Glide.with(this)
                .load(getIntent().getStringExtra("url"))
                .into(imgBg);

        txtJudul.setText(getIntent().getStringExtra("judul"));
        txtTahun.setText(getIntent().getStringExtra("tahun"));
        txtQuotes.setText(getIntent().getStringExtra("quotes"));
        txtIsi.setText(getIntent().getStringExtra("isi"));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
