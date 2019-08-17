package com.example.parkingandroid.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.parkingandroid.R;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

    @BindView(R.id.tv_empty)
    TextView tv_empty;

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

        baseRecyclerAdapter.openLoadAnimation(false);
        smartRefreshLayout.setEnableRefresh(pullRefreshEnable);
        smartRefreshLayout.setEnableLoadMore(loadRefreshEnable);
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                handleRefreshResult();
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                handleloadMoreResult();
            }
        });
    }

    public void setTitle(String t){
        title.setText(t);
    }

    /**
     * 设置item布局
     * @return
     */
    protected int getItemLayoutRes(){
        return R.layout.activity_base_list_default_item;
    }

    protected void setData(List sources){
        this.sources = sources;
        if (sources !=null && sources.size()>0){
            showEmptyTips(false);
            baseRecyclerAdapter.setData(sources);
        }else{
            showEmptyTips(true);
        }

    }

    /**
     * 处理下拉刷新结果
     */
    protected void handleRefreshResult(){

    }

    /**
     * 处理加载更多结果
     */
    protected void handleloadMoreResult(){

    }

    protected void convertData(BaseViewHolder helper, Object item){
        //helper.setText(R.id.title,item.toString());
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

    private void showEmptyTips(boolean enable){
        if (enable){
            tv_empty.setVisibility(View.VISIBLE);
        }else{
            tv_empty.setVisibility(View.GONE);
        }
    }
}
