package com.example.parkingandroid.activity.business;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseEmptyActivity;
import com.example.parkingandroid.api.BaseAPI;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.CarModel;
import com.example.parkingandroid.models.business.DriverModel;
import com.example.parkingandroid.presenter.CarPresenter;
import com.example.parkingandroid.presenter.DriverPresenter;
import com.example.parkingandroid.tools.Const;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CarActivity extends BaseEmptyActivity implements View.OnClickListener{

    private boolean isChangePhoto = false;
    private String imagePath;//本地图像url
    private String imageUrl;//服务器图像url

    ImageView photo;

    EditText car_number;
    EditText type;
    EditText owner;
    EditText brand;
    EditText model;
    EditText car_code;
    EditText engine_number;

    Button btn;

    CarPresenter carPresenter;
    CarModel carModel;

    @Override
    protected int setContentRes() {
        return R.layout.activity_car;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("车辆信息");
    }

    @Override
    public void initData() {
        super.initData();
        initPresenter();
    }

    @Override
    public void initView() {
        super.initView();
        car_number = findViewById(R.id.car_number);
        type = findViewById(R.id.type);
        owner = findViewById(R.id.owner);
        brand = findViewById(R.id.brand);
        model = findViewById(R.id.model);
        car_code = findViewById(R.id.car_code);
        engine_number = findViewById(R.id.engine_number);

        photo = findViewById(R.id.photo);
        btn = findViewById(R.id.btn);
        photo.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PictureConfig.CHOOSE_REQUEST:
                // 图片、视频、音频选择结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size()>0){
                    imagePath = selectList.get(0).getCutPath();
                    RequestOptions requestOptions = new RequestOptions();
                    Glide.with(this).load(imagePath).apply(requestOptions).into(photo);
                    isChangePhoto = true;
                    //uploadFile(imagePath);
                    //setLoadingEnable(true);
                }else {
                    imagePath = "";
//                    RequestOptions requestOptions = new RequestOptions();
//                    requestOptions.circleCrop();
//                    requestOptions.placeholder(R.mipmap.avatar2);
//                    requestOptions.error(R.mipmap.avatar2);
//                    Glide.with(context).load(BaseAPI.base_url+userModel.getUser_avatar()).apply(requestOptions).into(avatar);
                }
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.photo:
                selectPhoto();
                break;
            case R.id.btn:
                // 1 初始无图片
                //2 初始有图片，无修改
                //3 初始有图片，有修改
                if (isChangePhoto){
                    uploadFile(imagePath);
                }else{
                    Log.e("onClick","submitData");
                    submitData();
                }
                break;
        }
    }

    void selectPhoto(){

        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(false)// 是否可预览视频 true or false
                .enablePreviewAudio(false) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
//                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(2,1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
//                .compressSavePath(getPath())//压缩图片保存地址
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(false)// 是否开启点击声音 true or false
//                .selectionMedia(false)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
//                .recordVideoSecond()//视频秒数录制 默认60s int
                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    void uploadFile(String path){
        /**
         * 自定义实体参数类请参考:
         * 请求注解 {@link org.xutils.http.annotation.HttpRequest}
         * 请求注解处理模板接口 {@link org.xutils.http.app.ParamsBuilder}
         *
         * 需要自定义类型作为callback的泛型时, 参考:
         * 响应注解 {@link org.xutils.http.annotation.HttpResponse}
         * 响应注解处理模板接口 {@link org.xutils.http.app.ResponseParser}
         *
         * 示例: 查看 org.xutils.sample.http 包里的代码
         */
        RequestParams params = new RequestParams(BaseAPI.base_upload_url);
        //        params.setSslSocketFactory(...); // 设置ssl
        //        params.addQueryStringParameter("wd", "xUtils");
        params.setMultipart(true);
        params.addBodyParameter("uploadFile", new File(path));
        //        BaiduParams params = new BaiduParams();
        //        params.wd = "xUtils";
        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        // params.setMultipart(true);
        // 上传文件方式 1
        // params.uploadFile = new File("/sdcard/test.txt");
        // 上传文件方式 2
        // params.addBodyParameter("uploadFile", new File("/sdcard/test.txt"));
        Callback.Cancelable cancelable
                = x.http().post(params,
                /**
                 * 1. callback的泛型:
                 * callback参数默认支持的泛型类型参见{@link org.xutils.http.loader.LoaderFactory},
                 * 例如: 指定泛型为File则可实现文件下载, 使用params.setSaveFilePath(path)指定文件保存的全路径.
                 * 默认支持断点续传(采用了文件锁和尾端校验续传文件的一致性).
                 * 其他常用类型可以自己在LoaderFactory中注册,
                 * 也可以使用{@link org.xutils.http.annotation.HttpResponse}
                 * 将注解HttpResponse加到自定义返回值类型上, 实现自定义ResponseParser接口来统一转换.
                 * 如果返回值是json形式, 那么利用第三方的json工具将十分容易定义自己的ResponseParser.
                 * 如示例代码{@link org.xutils.sample.http.BaiduResponse}, 可直接使用BaiduResponse作为
                 * callback的泛型.
                 *
                 * 2. callback的组合:
                 * 可以用基类或接口组合个种类的Callback, 见{@link org.xutils.common.Callback}.
                 * 例如:
                 * a. 组合使用CacheCallback将使请求检测缓存或将结果存入缓存(仅GET请求生效).
                 * b. 组合使用PrepareCallback的prepare方法将为callback提供一次后台执行耗时任务的机会,
                 * 然后将结果给onCache或onSuccess.
                 * c. 组合使用ProgressCallback将提供进度回调.
                 * ...(可参考{@link org.xutils.image.ImageLoader}
                 * 或 示例代码中的 {@link org.xutils.sample.download.DownloadCallback})
                 *
                 * 3. 请求过程拦截或记录日志: 参考 {@link org.xutils.http.app.RequestTracker}
                 *
                 * 4. 请求Header获取: 参考 {@link org.xutils.http.app.RequestInterceptListener}
                 *
                 * 5. 其他(线程池, 超时, 重定向, 重试, 代理等): 参考 {@link org.xutils.http.RequestParams}
                 *
                 **/
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("onSuccess",""+result);
                        imageUrl = result;
                        submitData();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        //setLoadingEnable(false);
                        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        if (ex instanceof HttpException) { // 网络错误
                            HttpException httpEx = (HttpException) ex;
                            int responseCode = httpEx.getCode();
                            String responseMsg = httpEx.getMessage();
                            String errorResult = httpEx.getResult();
                            // ...
                        } else { // 其他错误
                            // ...
                        }
                        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(Callback.CancelledException cex) {
                        //setLoadingEnable(false);
                        Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinished() {
                        //setLoadingEnable(false);
                    }
                });
    }

    void submitData(){
        Map<String,String> params = RS.getBaseParams(this);

        params.put("car_number",car_number.getText().toString() + "");
        params.put("type",type.getText().toString()+ "");
        params.put("owner",owner.getText().toString()+ "");
        params.put("brand",brand.getText().toString()+ "");
        params.put("model",model.getText().toString() + "");
        params.put("car_code",car_code.getText().toString()+ "");
        params.put("engine_number",engine_number.getText().toString()+ "");

        if (!TextUtils.isEmpty(imageUrl)){
            params.put("photo",imageUrl+ "");
        }
        if (carModel != null){
            params.put("id",carModel.getId()+ "");
        }
        carPresenter.setCar(params);
    }

    void initPresenter(){
        carPresenter = new CarPresenter(context);
        carPresenter.setOnCallBack(new CarPresenter.OnCallBack() {
            @Override
            public void getCar(boolean isSuccess, Object object) {
                if (isSuccess && object != null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(),Const.KEY_RES_CODE_200)){
                        List<CarModel> carModels = resultModel.getData();
                        if (carModels != null && carModels.size()>=1){
                            carModel = carModels.get(0);
                            showDriverInfo(carModel);
                        }
                    }
                }
            }

            @Override
            public void setCar(boolean isSuccess, Object object) {
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        Toast.makeText(context, "更新成功", Toast.LENGTH_SHORT).show();
                        carPresenter.getCar(RS.getBaseParams(CarActivity.this));
                    }
                }
            }
        });
        carPresenter.getCar(RS.getBaseParams(this));
    }

    void showDriverInfo(CarModel carModel){
        if (carModel != null){

//            award_time.setText(carModel.getAward_time());
//            validity.setText(carModel.getValidity());
//            awrad_unit.setText(carModel.getAwrad_unit());
//            number.setText(carModel.getNumber());

            car_number.setText(carModel.getCar_number());
            type.setText(carModel.getType());
            owner.setText(carModel.getOwner());
            brand.setText(carModel.getBrand());
            model.setText(carModel.getModel());
            car_code.setText(carModel.getCar_code());
            engine_number.setText(carModel.getEngine_number());

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(R.mipmap.icon_card);
            requestOptions.placeholder(R.mipmap.icon_card);
            Glide.with(context).load(BaseAPI.base_url+carModel.getPhoto()).apply(requestOptions).into(photo);
        }
    }

}
