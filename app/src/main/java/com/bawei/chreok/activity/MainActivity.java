package com.bawei.chreok.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bawei.chreok.MyDecoration;
import com.bawei.chreok.R;
import com.bawei.chreok.adapter.ListAdapter;
import com.bawei.chreok.bean.Bean;
import com.bawei.chreok.util.JsonUtils;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListAdapter adapter;
    private String path1="http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";
    private List<Bean.DataBean> data;
    private ArrayList<Bean.DataBean> beanArrayList=new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String ss= (String) msg.obj;
            Gson gson=new Gson();
            Bean bean = gson.fromJson(ss, Bean.class);
            data = bean.getData();
            adapter = new ListAdapter(data,MainActivity.this);
            recyclerView.setAdapter(adapter);
            //添加分割线
            recyclerView.addItemDecoration(new MyDecoration(MainActivity.this, MyDecoration.VERTICAL_LIST));
            adapter.setRecyclerViewOnItemClickListener(new ListAdapter.RecyclerViewOnItemClickListener() {
                @Override
                public void onItemClickListener(View view, int position) {
                    //设置选中的项
                    adapter.setSelectItem(position);
                }
                @Override
                public boolean onItemLongClickListener(View view, int position) {
                    adapter.setShowBox();
                    //设置选中的项
                    adapter.setSelectItem(position);
                    adapter.notifyDataSetChanged();
                    return true;
                }
            });
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        recyclerView = (RecyclerView) findViewById(R.id.list);
        findViewById(R.id.commit).setOnClickListener(this);
        initData();
        //布局管理器
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onClick(View v) {
        //获取你选中的item
        Map<Integer, Boolean> map = adapter.getMap();
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i)) {
                beanArrayList.add(data.get(i));
            }
        }
        Intent intent=new Intent(this,Main2Activity.class);
        Log.e("0000000000",beanArrayList.size()+"");
        intent.putExtra("aa",beanArrayList);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //全选
            case R.id.all:
                Map<Integer, Boolean> map = adapter.getMap();
                for (int i = 0; i < map.size(); i++) {
                    map.put(i, true);
                    adapter.notifyDataSetChanged();
                }
                break;
            //全不选
            case R.id.no_all:
                Map<Integer, Boolean> m = adapter.getMap();
                for (int i = 0; i < m.size(); i++) {
                    m.put(i, false);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * 为列表添加测试数据
     */
    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(path1);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = urlConnection.getInputStream();
                        String s1 = JsonUtils.jsonUtils(inputStream);
                        Message msg=new Message();
                        msg.obj=s1;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
