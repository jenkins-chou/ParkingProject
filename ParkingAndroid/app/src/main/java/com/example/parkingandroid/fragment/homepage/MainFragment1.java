package com.example.parkingandroid.fragment.homepage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.parkingandroid.R;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.dialog.CommonTipsDialog;
import com.example.parkingandroid.fragment.business.BaseFragment;
import com.example.parkingandroid.intent.IntentManager;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.ParkingLotModel;
import com.example.parkingandroid.models.business.UserModel;
import com.example.parkingandroid.presenter.ParkingLotPresenter;
import com.example.parkingandroid.tools.AccountTool;
import com.example.parkingandroid.tools.Const;
import com.example.parkingandroid.tools.StringUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment1 extends BaseFragment {

    AMap aMap;
    List<Marker> markers;
    ParkingLotPresenter parkingLotPresenter;
    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.login_tips)
    RelativeLayout loginTipsLayout;

    @OnClick(R.id.login_btn)
    void goLogin(){
        IntentManager.intentToLogin(context);
    }

    @OnClick(R.id.get_parking)
    void get_parking(){
        if (!AccountTool.isLogin(context)){
            CommonTipsDialog.create(context,"温馨提示","请登录后重试,是否跳转登录界面",false)
                    .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                        @Override
                        public void cancel() {

                        }

                        @Override
                        public void confirm() {
                            IntentManager.intentToLogin(context);
                        }
                    }).show();
        }else{
            IntentManager.intentToParkingLotList(this.getContext());
        }
    }

    @OnClick(R.id.refresh_btn)
    void refresh_btn(){
        if (parkingLotPresenter != null){
            parkingLotPresenter.getAllParkingLot(RS.getBaseParams(context));
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main_one;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AccountTool.isLogin(context)){
            UserModel userModel = AccountTool.getLoginUser(context);
            if (userModel!=null){
                loginTipsLayout.setVisibility(View.GONE);
            }
        }else{
            loginTipsLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initData(Bundle bundle) {
        mapView.onCreate(bundle);// 此方法必须重写
        aMap = mapView.getMap();
        markers = new ArrayList<>();
        requestLocationPermission();
        initPresenter();
    }

    void initPresenter(){
        parkingLotPresenter = new ParkingLotPresenter(context);
        parkingLotPresenter.setOnCallBack(new ParkingLotPresenter.OnCallBack() {
            @Override
            public void getAllParkingLot(boolean isSuccess, Object object) {
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        List<ParkingLotModel> parkingLotModelList = resultModel.getData();
                        if (parkingLotModelList != null && parkingLotModelList.size()>0){
                            Log.e("parkingLotModelList",""+parkingLotModelList.toString());
                            setMarker(parkingLotModelList);
                        }
                    }
                }
            }

            @Override
            public void addAppointment(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllAppointmentByUserID(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });

        parkingLotPresenter.getAllParkingLot(RS.getBaseParams(context));
    }

    void requestLocationPermission(){
        if(ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(this.getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},200);
        }else{
            startLocaion();//开始定位
            Log.e("requestLocation","权限已开启");
        }
    }

    void startLocaion(){
        if (aMap != null){
            Log.e("aMap","aMap not null");
            MyLocationStyle myLocationStyle;
            myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
            //myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//只定位一次
            myLocationStyle.showMyLocation(true);
            //设置自定义定位图标
            //myLocationStyle.myLocationIcon(BitmapDescriptor.CREATOR.createFromParcel());
            aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
            aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
            aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

            //addMapMarker();
        }
    }

    void setMarker(List<ParkingLotModel> parkingLotModelList){
        if (aMap != null){
            removeAllMarker();
            for (Object object:parkingLotModelList){
                ParkingLotModel parkingLotModel = new Gson().fromJson(new Gson().toJson(object),ParkingLotModel.class);
                Log.e("setMarker",parkingLotModel.toString());
                Log.e("setMarker getLatitude",StringUtil.isNumber(parkingLotModel.getLatitude())+"");
                Log.e("setMarker getLongitude",StringUtil.isNumber(parkingLotModel.getLongitude())+"");
                if (StringUtil.isNumber(parkingLotModel.getLatitude())
                        &&StringUtil.isNumber(parkingLotModel.getLongitude())){
                    addMapMarker(parkingLotModel.getParking_address(),
                            parkingLotModel.getParking_num()+"",
                            Double.parseDouble(parkingLotModel.getLongitude()),
                            Double.parseDouble(parkingLotModel.getLatitude()));
                }
            }
        }
    }

    void removeAllMarker(){
        if (markers != null && markers.size()>0){
            for (Marker marker : markers){
                if (marker != null){
                    marker.destroy();
                }
            }
        }
    }

    void addMapMarker(String address,String size,double lng,double lat){
        //添加标记
        //LatLng latLng = new LatLng(39.906901,116.397972);
        //final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker"));

        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(new LatLng(lat,lng));
        markerOption.title("共"+size+"个车位").snippet(address+"");
        markerOption.draggable(true);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.icon_parking_2)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        Marker marker = aMap.addMarker(markerOption);
        markers.add(marker);
        // 定义 Marker 点击事件监听
        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            // marker 对象被点击时回调的接口
            // 返回 true 则表示接口已响应事件，否则返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.e("onMarkerClick",""+marker.getId()+","+marker.getTitle()+",");
                return false;
            }
        };
        // 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(markerClickListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 200://刚才的识别码
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){//用户同意权限,执行我们的操作
                    startLocaion();//开始定位
                }else{//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    //Toast.makeText(MainActivity.this,"未开启定位权限,请手动到设置去开启权限",Toast.LENGTH_LONG).show();
                }
                break;
            default:break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }
}
