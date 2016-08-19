package com.example.qiyue.customprogress;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class CustomDailog implements View.OnClickListener {

    private AlertDialog dialog;
    private View contentView;

    public CustomDailog(Context context, @LayoutRes int layout){
        contentView = LayoutInflater.from(context).inflate(layout,null);
        contentView.setOnClickListener(this);
        dialog = new AlertDialog.Builder(context,R.style.Dialog).create();
    }

    public CustomDailog(Context context,View contentView){
        this.contentView = contentView;
        contentView.setOnClickListener(this);
        dialog = new AlertDialog.Builder(context,R.style.Dialog).create();
    }

    @Override
    public void onClick(View v) {
          if (onButtonClick != null){
              onButtonClick.onClick(v);
          }
    }

    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    private OnButtonClick onButtonClick;

    public interface OnButtonClick{
        public void onClick(View view);
    }

    public void show(){
        if (dialog!=null) {
            dialog.show();
            dialog.setContentView(contentView);
        }
    }

    public void dismiss(){
        if (dialog!=null) {
            dialog.dismiss();
        }
    }
}
