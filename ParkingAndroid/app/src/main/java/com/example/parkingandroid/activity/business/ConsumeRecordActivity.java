package com.example.parkingandroid.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseListActivity;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.ConsumeRecordModel;
import com.example.parkingandroid.models.business.CouponModel;
import com.example.parkingandroid.presenter.ConsumeRecordPresenter;
import com.example.parkingandroid.tools.Const;
import com.example.parkingandroid.tools.StringUtil;
import com.github.library.BaseViewHolder;
import com.google.gson.Gson;

import java.util.List;

public class ConsumeRecordActivity extends BaseListActivity<ConsumeRecordModel> {

    ConsumeRecordPresenter consumeRecordPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("消费记录");
    }

    @Override
    protected int getItemLayoutRes() {
        return R.layout.activity_consume_record_item;
    }

    @Override
    protected void convertData(BaseViewHolder helper, Object item) {
//        super.convertData(helper, item);
        Log.e("ConsumeRecordModel",item.toString()+"");
        ConsumeRecordModel consumeRecordModel = new Gson().fromJson(new Gson().toJson(item),ConsumeRecordModel.class);
        helper.setText(R.id.detail,consumeRecordModel.getDetail());
        helper.setText(R.id.money,"￥"+consumeRecordModel.getMoney());
        helper.setText(R.id.consume_time,"消费时间:"+StringUtil.getStrTime(consumeRecordModel.getCreate_time(),"yyyy年MM月dd日 HH:mm:ss"));
    }

    @Override
    public void initData() {
        super.initData();

        consumeRecordPresenter = new ConsumeRecordPresenter(context);
        consumeRecordPresenter.setOnCallBack(new ConsumeRecordPresenter.OnCallBack() {
            @Override
            public void getConsumeRecord(boolean isSuccess, Object object) {
                finishRefresh();
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        List<ConsumeRecordModel> consumeRecordModels = resultModel.getData();
                        Log.e("consumeRecordModels",consumeRecordModels.toString());
                        if (consumeRecordModels != null){
                            setData(consumeRecordModels);
                        }
                    }
                }
            }

            @Override
            public void failed(Object object) {

            }
        });

        consumeRecordPresenter.getConsumeRecord(RS.getBaseParams(this));
    }

    @Override
    protected void handleRefreshResult() {
        super.handleRefreshResult();
        consumeRecordPresenter.getConsumeRecord(RS.getBaseParams(this));
    }

    @Override
    protected void handleloadMoreResult() {
        super.handleloadMoreResult();
    }
}
