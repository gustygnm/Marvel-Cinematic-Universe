package com.gnm.marvelcinematicuniverse;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvCategory;
    private ArrayList<Marvel> list;
    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;
    boolean doubleBackToExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();

        if (savedInstanceState == null) {
            list.addAll(MarvelData.getListData());
            showRecyclerList();
            mode = R.id.action_list;

        } else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            ArrayList<Marvel> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            setActionBarTitle(stateTitle);
            list.addAll(stateList);
            setMode(stateMode);
        }
    }

    private void showSelectedMarvel(Marvel marvel) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("url", marvel.getPhoto());
        i.putExtra("judul", marvel.getName());
        i.putExtra("tahun", marvel.getYear());
        i.putExtra("quotes", marvel.getQuoter());
        i.putExtra("isi", marvel.getIsi());
        startActivity(i);
    }

    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListMarvelAdapter listMarvelAdapter = new ListMarvelAdapter(this);
        listMarvelAdapter.setListMarvel(list);
        rvCategory.setAdapter(listMarvelAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMarvel(list.get(position));
            }
        });
    }

    private void showRecyclerGrid() {
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridMarvelAdapter gridMarvelAdapter = new GridMarvelAdapter(this);
        gridMarvelAdapter.setListMarvel(list);
        rvCategory.setAdapter(gridMarvelAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMarvel(list.get(position));
            }
        });
    }

    private void showRecyclerCardView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewMarvelAdapter cardViewMarvelAdapter = new CardViewMarvelAdapter(this);
        cardViewMarvelAdapter.setListMarvel(list);
        rvCategory.setAdapter(cardViewMarvelAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        setMode(item.getItemId());

        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
//        String title = null;
        switch (selectedMode) {
            case R.id.action_list:
//                title = "Mode List";
                showRecyclerList();
                break;

            case R.id.action_grid:
//                title = "Mode Grid";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
//                title = "Mode CardView";
                showRecyclerCardView();
                break;
        }
        mode = selectedMode;
//        setActionBarTitle(title);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExit) {
            finishAffinity();
        } else {
            Toast.makeText(this, "Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        int timee = 2000;
        this.doubleBackToExit = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExit = false;
            }
        }, timee);
    }
}
