package com.example.parkingandroid.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.parkingandroid.R;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseListActivity<T> extends BaseActivity{

    public boolean pullRefreshEnable = true;
    public boolean loadRefreshEnable = true;
    public Context context;
    public List<T> sources;
    public BaseRecyclerAdapter baseRecyclerAdapter;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @OnClick(R.id.back)
    void back(){
        backAction();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        init();
    }

    void init(){
        context = this;
        sources = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter(context,sources,getItemLayoutRes()) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {
                convertData(helper,item);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(baseRecyclerAdapter);

        smartRefreshLayout.setEnableRefresh(pullRefreshEnable);
        smartRefreshLayout.setEnableLoadMore(loadRefreshEnable);
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
    }

    public void setTitle(String t){
        title.setText(t);
    }

    protected int getItemLayoutRes(){
        return R.layout.activity_base_list_default_item;
    }

    protected void setData(List sources){
        this.sources = sources;
        baseRecyclerAdapter.setData(sources);
    }

    protected void convertData(BaseViewHolder helper, Object item){
        helper.setText(R.id.title,item.toString());
    }

    protected void backAction(){
        finish();
    }

    protected void setPullRefreshEnable(boolean pullRefreshEnable){
        this.pullRefreshEnable = pullRefreshEnable;
        smartRefreshLayout.setEnableRefresh(pullRefreshEnable);
    }

    protected void setLoadRefreshEnable(boolean loadRefreshEnable){
        this.loadRefreshEnable = loadRefreshEnable;
        smartRefreshLayout.setEnableLoadMore(loadRefreshEnable);
    }

    protected void finishRefresh(){
        smartRefreshLayout.finishRefresh();
    }

    protected void finishLoadMore(){
        smartRefreshLayout.finishLoadMore();
    }
}
