package com.example.parkingandroid.activity.business;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseListActivity;
import com.example.parkingandroid.api.BaseAPI;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.intent.IntentManager;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.ParkingLotModel;
import com.example.parkingandroid.presenter.ParkingLotPresenter;
import com.example.parkingandroid.tools.Const;
import com.github.library.BaseViewHolder;
import com.google.gson.Gson;

import java.util.List;

public class ParkingLotListActivity extends BaseListActivity<ParkingLotModel> {
    ParkingLotPresenter parkingLotPresenter;
    @Override
    protected int getItemLayoutRes() {
        return R.layout.activity_parking_item;
    }

    @Override
    public void initData() {
        super.initData();
        initPresenter();
        setLoadRefreshEnable(false);
        setTitle("停车场列表");
    }

    @Override
    protected void convertData(BaseViewHolder helper, Object item) {
        super.convertData(helper, item);
        ParkingLotModel parkingLotModel = new Gson().fromJson(new Gson().toJson(item),ParkingLotModel.class);
        helper.setText(R.id.parking_name,parkingLotModel.getParking_name());
        helper.setText(R.id.parking_num,"余量:"+parkingLotModel.getParking_num());
        helper.setText(R.id.parking_address,parkingLotModel.getParking_address());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.default_parking_img);
        requestOptions.error(R.mipmap.default_parking_img);
        Glide.with(context).load(BaseAPI.base_url+parkingLotModel.getImg()).apply(requestOptions).into((ImageView) helper.getView(R.id.item_img));

        TextView textView = helper.getView(R.id.btn);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentManager.intentToParkingLotDetail(context,ParkingLotDetailActivity.DATA_KEY,new Gson().toJson(parkingLotModel));
            }
        });
    }

    void initPresenter(){
        parkingLotPresenter = new ParkingLotPresenter(context);
        parkingLotPresenter.setOnCallBack(new ParkingLotPresenter.OnCallBack() {
            @Override
            public void getAllParkingLot(boolean isSuccess, Object object) {
                finishRefresh();
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        List<ParkingLotModel> parkingLotModelList = resultModel.getData();
                        if (parkingLotModelList != null && parkingLotModelList.size()>0){
                            Log.e("parkingLotModelList",""+parkingLotModelList.toString());
                            setData(parkingLotModelList);
                        }
                    }
                }
            }

            @Override
            public void failed(Object object) {

            }
        });
        getData();
    }

    void getData(){
        parkingLotPresenter.getAllParkingLot(RS.getBaseParams(context));
    }

    @Override
    protected void handleRefreshResult() {
        super.handleRefreshResult();
        getData();
    }
}
