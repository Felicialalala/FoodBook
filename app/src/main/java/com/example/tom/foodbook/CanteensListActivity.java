package com.example.tom.foodbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tom.foodbook.Adapter.OfferCanteenAdapter;
import com.example.tom.foodbook.Entity.Canteen;

import java.util.ArrayList;
import java.util.List;

public class CanteensListActivity extends AppCompatActivity implements OfferCanteenAdapter.ViewHolder.ClickListener {

    public static final String MIBA_TAG = "MIBA_TAG";

    private ArrayList<Canteen> offerCanteenList;
    private RecyclerView rvOfferCanteen;
    private RecyclerView.LayoutManager offerCanteenLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private OfferCanteenAdapter offerCanteenAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_canteens);

        /* ************************************************************************************** */

        //RecyclerView.ItemDecoration itemDecoration
        rvOfferCanteen = (RecyclerView) findViewById(R.id.rv_offer_canteen);
        rvOfferCanteen.setHasFixedSize(true);
        offerCanteenLayoutManager = new LinearLayoutManager(this);
        rvOfferCanteen.setLayoutManager(offerCanteenLayoutManager);

        /* ************************************************************************************** */

        CsvHelper csvHelper = new CsvHelper(CanteensListActivity.this);
        this.offerCanteenList = csvHelper.getCanteens();

        offerCanteenAdapter = new OfferCanteenAdapter(offerCanteenList, CanteensListActivity.this, this);
        rvOfferCanteen.setAdapter(offerCanteenAdapter);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_list_of_canteens);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CsvHelper csvHelper = new CsvHelper(CanteensListActivity.this);

                ArrayList<Canteen> canteensTest = csvHelper.getCanteens();

                offerCanteenAdapter.setData(canteensTest);

                Toast toast = Toast.makeText(getApplicationContext(), "List of canteens updated", Toast.LENGTH_SHORT);
                toast.show();

                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getBaseContext(), CanteenOverviewActivity.class);
        intent.putExtra("canteenId", offerCanteenList.get(position).getId());
        startActivity(intent);

    }
}
