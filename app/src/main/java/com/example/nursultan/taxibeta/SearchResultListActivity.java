package com.example.nursultan.taxibeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    List<Data> mDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_list);

        mRecyclerView = findViewById(R.id.recycler_view);
        mDataList = new ArrayList<>();
        mAdapter = new MyAdapter(mDataList);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        getData();

    }

    private void getData() {
        Data data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);

        data = new Data("Nursultan", "Age");
        mDataList.add(data);


        mAdapter.notifyDataSetChanged();

    }

}
