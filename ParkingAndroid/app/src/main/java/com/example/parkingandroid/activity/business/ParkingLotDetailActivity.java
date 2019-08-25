package com.example.parkingandroid.activity.business;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseEmptyActivity;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.ParkingLotModel;
import com.example.parkingandroid.presenter.ParkingLotPresenter;
import com.example.parkingandroid.tools.Const;
import com.example.parkingandroid.tools.StringUtil;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.OnTextChanged;

public class ParkingLotDetailActivity extends BaseEmptyActivity implements View.OnClickListener,TextWatcher {

    public static final String DATA_KEY = "model";
    ParkingLotModel parkingLotModel;

    TextView parking_name;
    TextView parking_num;
    TextView parking_money;
    TextView parking_address;

    TextView money;
    TextView start_time;
    TextView end_time;
    EditText number;
    Button btn;

    private String startTimeMills;
    private String endTimeMills;

    ParkingLotPresenter parkingLotPresenter;
    @Override
    protected int setContentRes() {
        return R.layout.activity_parking_lot_detail;
    }

    @Override
    public void initData() {
        super.initData();
        setTitle("预约");
        Intent intent = getIntent();
        if (intent != null){
            String modelJson = intent.getStringExtra(DATA_KEY);
            if (modelJson != null){
                parkingLotModel = new Gson().fromJson(modelJson,ParkingLotModel.class);
            }
        }

        parking_name = findViewById(R.id.parking_name);
        parking_num = findViewById(R.id.parking_num);
        parking_money = findViewById(R.id.parking_money);
        parking_address = findViewById(R.id.parking_address);

        money = findViewById(R.id.money);
        start_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);
        number = findViewById(R.id.number);
        number.addTextChangedListener(this);
        btn = findViewById(R.id.btn);

        start_time.setOnClickListener(this);
        end_time.setOnClickListener(this);
        btn.setOnClickListener(this);

        showParkingLotDetail();
        initPresenter();
    }

    void initPresenter(){
        parkingLotPresenter = new ParkingLotPresenter(context);
        parkingLotPresenter.setOnCallBack(new ParkingLotPresenter.OnCallBack() {
            @Override
            public void getAllParkingLot(boolean isSuccess, Object object) {

            }

            @Override
            public void addAppointment(boolean isSuccess, Object object) {
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        Toast.makeText(context, "预约成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void failed(Object object) {

            }
        });
    }

    void showParkingLotDetail(){
        if (parkingLotModel != null){
            parking_name.setText(parkingLotModel.getParking_name());
            parking_num.setText("余量:"+parkingLotModel.getParking_num());
            parking_money.setText("单价："+parkingLotModel.getParking_price()+"元/小时");
            parking_address.setText(parkingLotModel.getParking_address());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_time:
                selectTime("开始时间",new SelectTimeCallBack() {
                    @Override
                    public void handleSelectTime(Date date, View v) {
                        startTimeMills = date.getTime()/1000+"";
                        if (checkTimeCorrect()){
                            start_time.setText(StringUtil.getStrTime(startTimeMills+"","yyyy年MM月dd日 HH:mm"));
                            cacuMoney();
                        }
                    }
                });
                break;
            case R.id.end_time:
                selectTime("结束时间",new SelectTimeCallBack() {
                    @Override
                    public void handleSelectTime(Date date, View v) {
                        endTimeMills = date.getTime()/1000+"";
                        if (checkTimeCorrect()) {
                            end_time.setText(StringUtil.getStrTime(endTimeMills + "", "yyyy年MM月dd日 HH:mm"));
                            cacuMoney();
                        }
                    }
                });
                break;
            case R.id.btn:
                submitData();
                break;
        }
    }

    boolean checkTimeCorrect(){
        if (!TextUtils.isEmpty(startTimeMills) &&
                !TextUtils.isEmpty(endTimeMills)){
            if (Double.parseDouble(endTimeMills)<Double.parseDouble(startTimeMills)){
                Toast.makeText(ParkingLotDetailActivity.this, "结束时间不能在开始时间前，请重新选择", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    double cacuMoney(){
        String numberNum = number.getText().toString();
        if (checkTimeCorrect() &&
                !TextUtils.isEmpty(numberNum)){
            if (StringUtil.isNumber(startTimeMills) && StringUtil.isNumber(endTimeMills)){

                Log.e("cacuMoney start",startTimeMills);
                Log.e("cacuMoney start",Double.parseDouble(startTimeMills)+"");
                Log.e("cacuMoney end",startTimeMills);
                Log.e("cacuMoney end",Double.parseDouble(endTimeMills)+"");
                Log.e("cacuMoney cacu",(Double.parseDouble(endTimeMills) - Double.parseDouble(startTimeMills))/3600+"");

                int hours = (int)((Double.parseDouble(endTimeMills) - Double.parseDouble(startTimeMills)) / 3600);

                Log.e("cacuMoney hours",hours+"");
                int num = Integer.parseInt(number.getText().toString());
                Log.e("cacuMoney num",num+"");
                if (parkingLotModel != null){
                    double lastMoney = hours * Double.parseDouble(parkingLotModel.getParking_price()) * num;
                    money.setText("共"+lastMoney+"元");
                    Log.e("cacuMoney money",money+"");
                    return lastMoney;
                }
            }
        }
        return 0;
    }

    void submitData(){
        String input = number.getText().toString();
        if (TextUtils.isEmpty(startTimeMills)){
            Toast.makeText(context, "请选择开始时间", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(endTimeMills)){
            Toast.makeText(context, "请选择结束时间", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(input)){
            Toast.makeText(context, "请输入车位数量", Toast.LENGTH_SHORT).show();
        }else{
            if (parkingLotModel != null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("parking_id",parkingLotModel.getId());
                params.put("number",input);
                params.put("money",cacuMoney()+"");
                params.put("start_time",startTimeMills);
                params.put("end_time",endTimeMills);
                params.put("status","success");
                parkingLotPresenter.addAppointment(params);
            }
        }
    }

    void selectTime(String title,SelectTimeCallBack callBack){
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date,View v) {//选中事件回调
                if (callBack != null){
                    callBack.handleSelectTime(date,v);
                }
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("选择")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText(title)//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(getResources().getColor(R.color.fragment_two_ticket_start))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.main_color))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.base_font_color2))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.base_layout_color))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.white))//滚轮背景颜色 Night mode
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvTime.show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        cacuMoney();
    }

    //选择时间回调接口
    interface SelectTimeCallBack{
        void handleSelectTime(Date date,View v);
    }
}
