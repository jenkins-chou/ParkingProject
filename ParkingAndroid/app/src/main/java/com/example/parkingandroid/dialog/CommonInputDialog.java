package com.example.parkingandroid.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkingandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CommonInputDialog extends Dialog {

    private Context context;
    private String title;
    private String inputHint;
    private Unbinder unbinder;
    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private OnClickListener onClickListener;


    @BindView(R.id.title)
    TextView titleTv;

    @BindView(R.id.input)
    EditText input;

    @BindView(R.id.top_line)
    View top_line;
    @BindView(R.id.bottom_line)
    View bottom_line;
    @BindView(R.id.button_bar)
    LinearLayout button_bar;

    @BindView(R.id.confirm)
    TextView confirm;

    @BindView(R.id.cancel)
    TextView cancel;
    private String confirm_str = "确定";
    private String cancel_str = "取消";

    @OnClick(R.id.cancel)
    void click_cancel(){
        dismiss();
        if (onClickListener!=null){
            onClickListener.cancel();
        }
    }

    @OnClick(R.id.confirm)
    void click_confirm(){
        if (TextUtils.isEmpty(input.getText().toString())){
            Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        if (onClickListener!=null){
            onClickListener.getInput(input.getText().toString());
            dismiss();
        }
    }

    public CommonInputDialog(Context context, String title, String inputHint, boolean iscancelable) {
        super(context, R.style.CommonDialogStyle);
        this.context = context;
        this.title = title;
        this.inputHint = inputHint;
        this.iscancelable = iscancelable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        initData();
        initSize();
        initView();
    }

    //初始化数据
    public void initData() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.common_input_dialog, null);
        unbinder = ButterKnife.bind(this,view);
        setContentView(view);
    }

    //初始化大小
    public void initSize() {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.7); // 宽度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        setCancelable(iscancelable);//点击外部可不可以dismiss
    }

    void initView(){
        titleTv.setText(title);
        confirm.setText(confirm_str);
        cancel.setText(cancel_str);
        input.setHint(inputHint);

        if (onClickListener!=null){
            bottom_line.setVisibility(View.VISIBLE);
            button_bar.setVisibility(View.VISIBLE);
            top_line.setVisibility(View.GONE);
        }
    }

    public CommonInputDialog setConfirmButton(String string){
        this.confirm_str = string;
        return this;
    }

    public CommonInputDialog setCancelButton(String string){
        this.cancel_str = string;
        return this;
    }


    public static CommonInputDialog create(Context context, String title, String inputHint, boolean iscancelable,OnClickListener onClickListener){
        final CommonInputDialog dialog = new CommonInputDialog(context,title,inputHint,iscancelable);
        dialog.setOnClickListener(onClickListener);
        return dialog;
    }

    public CommonInputDialog setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
        return this;
    }

    public interface OnClickListener{
        void cancel();
        void getInput(String inputStr);
    }

}