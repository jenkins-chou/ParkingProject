package com.example.parkingandroid.activity.business;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.parkingandroid.dialog.CommonTipsDialog;
import com.example.parkingandroid.intent.IntentManager;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.ParkingLotAppointmentModel;
import com.example.parkingandroid.models.business.ParkingLotModel;
import com.example.parkingandroid.presenter.ParkingLotPresenter;
import com.example.parkingandroid.tools.Const;
import com.example.parkingandroid.tools.StringUtil;
import com.github.library.BaseViewHolder;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class AppointmentOrderActivity extends BaseListActivity<ParkingLotModel> {

    ParkingLotPresenter parkingLotPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的预约订单");
        setLoadRefreshEnable(false);
    }

    @Override
    protected int getItemLayoutRes() {
        return R.layout.activity_parking_appointment_item;
    }

    @Override
    protected void convertData(BaseViewHolder helper, Object item) {
        super.convertData(helper, item);
        ParkingLotAppointmentModel parkingLotModel = new Gson().fromJson(new Gson().toJson(item),ParkingLotAppointmentModel.class);
        helper.setText(R.id.parking_name,parkingLotModel.getParking_name());
        helper.setText(R.id.parking_num,"预定数量:"+parkingLotModel.getNumber());
        helper.setText(R.id.parking_address,parkingLotModel.getParking_address());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.default_parking_img);
        requestOptions.error(R.mipmap.default_parking_img);
        Glide.with(context).load(BaseAPI.base_url+parkingLotModel.getImg()).apply(requestOptions).into((ImageView) helper.getView(R.id.item_img));

        TextView textView = helper.getView(R.id.btn);
        if (parkingLotModel !=null && StringUtil.isNumber(parkingLotModel.getEnd_time())){
            Log.e("parkingLotModel",Double.parseDouble(parkingLotModel.getEnd_time())+"");
            Log.e("parkingLotModel",System.currentTimeMillis()/1000+"");
            Log.e("parkingLotModel",(Double.parseDouble(parkingLotModel.getEnd_time())>(System.currentTimeMillis()/1000))+"");
            if (Double.parseDouble(parkingLotModel.getEnd_time())>(System.currentTimeMillis()/1000)){
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommonTipsDialog.showTip(context,"使用码",parkingLotModel.getCode(),false);
                    }
                });
            }else{
                textView.setText("已过期/已使用");
                textView.setBackgroundColor(Color.parseColor("#eeeeee"));
            }
        }else{
            textView.setText("已过期/已使用");
            textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        }

    }

    @Override
    public void initData() {
        super.initData();

        parkingLotPresenter = new ParkingLotPresenter(context);
        parkingLotPresenter.setOnCallBack(new ParkingLotPresenter.OnCallBack() {
            @Override
            public void getAllParkingLot(boolean isSuccess, Object object) {

            }

            @Override
            public void addAppointment(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllAppointmentByUserID(boolean isSuccess, Object object) {
                finishRefresh();
                finishLoadMore();
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        List<ParkingLotAppointmentModel> parkingLotModelList = resultModel.getData();
                        if (parkingLotModelList != null && parkingLotModelList.size()>0){
                            setData(parkingLotModelList);
                            Log.e("parkingLotModelList",""+resultModel.toString());
                        }
                    }
                }
            }

            @Override
            public void failed(Object object) {

            }
        });

        Map<String,String> params = RS.getBaseParams(this);
        parkingLotPresenter.getAllAppointmentByUserID(params);
    }

    @Override
    protected void handleRefreshResult() {
        super.handleRefreshResult();
        Map<String,String> params = RS.getBaseParams(this);
        parkingLotPresenter.getAllAppointmentByUserID(params);
    }
}
