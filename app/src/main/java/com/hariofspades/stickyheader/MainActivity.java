package com.hariofspades.stickyheader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.HeaderAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.helpers.ClickListenerHelper;
import com.mikepenz.fastadapter.listeners.ClickEventHook;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String[] headers = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    RecyclerView recyclerView;
    FastItemAdapter fastAdapter;
    List<Vaccination> vaccinations;
    StickyHeaderAdapter stickyHeaderAdapter;
    HeaderAdapter headerAdapter;
    ItemAdapter itemAdapter;
    StickyRecyclerHeadersDecoration decoration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        setupAdapters();
    }

    private void setupAdapters() {
        fastAdapter = new FastItemAdapter();
        stickyHeaderAdapter = new StickyHeaderAdapter();
        headerAdapter = new HeaderAdapter();
        itemAdapter = new ItemAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(stickyHeaderAdapter.wrap(itemAdapter.wrap(headerAdapter.wrap(fastAdapter))));
        decoration = new StickyRecyclerHeadersDecoration(stickyHeaderAdapter);
        recyclerView.addItemDecoration(decoration);
        //recyclerView.setAdapter(fastAdapter);
        fastAdapter.add(getItems());
        setupListeners();
    }

    private void setupListeners() {
        stickyHeaderAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                decoration.invalidateHeaders();
            }
        });
        fastAdapter.withSelectable(true);
        fastAdapter.withItemEvent(new ClickEventHook<Vaccination>() {

            @Nullable
            @Override
            public List<View> onBindMany(@NonNull RecyclerView.ViewHolder viewHolder) {
                List<View> views=new ArrayList<View>();
                if (viewHolder instanceof Vaccination.ViewHolder) {
                    views.add(((Vaccination.ViewHolder) viewHolder).date);
                    views.add(((Vaccination.ViewHolder) viewHolder).title);
                    views.add(((Vaccination.ViewHolder) viewHolder).info);
                    return views;
                }
                return super.onBindMany(viewHolder);
            }

            @Override
            public void onClick(View v, int position, FastAdapter<Vaccination> fastAdapters, Vaccination item) {
                if(v.getId()==R.id.date){
                    //recyclerView.setAdapter(null);
                    Toast.makeText(MainActivity.this, "Date", Toast.LENGTH_SHORT).show();
                    Vaccination vaccine=new Vaccination();
                    vaccine.setHeader(vaccinations.get(position).getHeader());
                    vaccine.setTitle(vaccinations.get(position).getTitle());
                    vaccine.setInfo(vaccinations.get(position).getInfo());
                    vaccine.setDate("HelloWorld");
                    vaccinations.set(position,vaccine);
                    fastAdapter.setNewList(vaccinations);

                }
                else if(v.getId()==R.id.title) {
                    Toast.makeText(MainActivity.this, "title", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "info", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private List<Vaccination> getItems(){
        vaccinations=new ArrayList<>();
        for(int i=1;i<=20;i++){
            Vaccination vaccine=new Vaccination();
            vaccine.setHeader(headers[i/5]);
            vaccine.setTitle("Title"+i);
            vaccine.setDate("Date"+i);
            vaccine.setInfo("Info"+i);
            vaccinations.add(vaccine);
        }
        return vaccinations;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
