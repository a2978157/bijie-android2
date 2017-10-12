package com.example.administrator.mybijie;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.Timer;
import java.util.TimerTask;

import Fragment.dianpu_Fragment;
import Fragment.dingdan_Fragment;
import Fragment.wode_Fragment;
import Fragment.xiaoxi_Fragment;
import Utils.DensityUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 导航界面
 */
public class MainActivity extends FragmentActivity {
    @BindView(R.id.zhuye_fl)
    FrameLayout zhuyeFl;
    @BindView(R.id.dingdan_img)
    ImageView dingdanImg;
    @BindView(R.id.dingdan_tv)
    TextView dingdanTv;
    @BindView(R.id.dingdan)
    LinearLayout dingdan;
    @BindView(R.id.dianpu_img)
    ImageView dianpuImg;
    @BindView(R.id.dianpu_tv)
    TextView dianpuTv;
    @BindView(R.id.dianpu)
    LinearLayout dianpu;
    @BindView(R.id.xiaoxi_img)
    ImageView xiaoxiImg;
    @BindView(R.id.xiaoxi_tv)
    TextView xiaoxiTv;
    @BindView(R.id.xiaoxi)
    LinearLayout xiaoxi;
    @BindView(R.id.wode_img)
    ImageView wodeImg;
    @BindView(R.id.wode_tv)
    TextView wodeTv;
    @BindView(R.id.wode)
    LinearLayout wode;
    @BindView(R.id.daohang)
    LinearLayout daohang;
    private FragmentManager fm;
    private dingdan_Fragment dingdan_fragment;
    private dianpu_Fragment dianpu_fragment;
    private xiaoxi_Fragment xiaoxi_fragment;
    private wode_Fragment wode_fragment;
    boolean isExit = false;//判断双击退出的标记

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//手机旋转不触发横屏
        //沉淀式一体栏状态栏和导航栏一个颜色
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.daohang_tv_lan));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        dianpu_fragment = new dianpu_Fragment();
        ft.replace(R.id.zhuye_fl, dianpu_fragment);
        ft.commit();
    }

    @OnClick({R.id.dingdan, R.id.dianpu, R.id.xiaoxi, R.id.wode})
    public void daohangClick(View v) {
        hindAll();
        initYan();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.dingdan:
                if (dingdan_fragment == null) {
                    dingdan_fragment = new dingdan_Fragment();
                    ft.add(R.id.zhuye_fl, dingdan_fragment);
                } else {
                    ft.show(dingdan_fragment);
                }
                dingdanTv.setTextColor(getResources().getColor(R.color.daohang_tv_lan));
                dingdanImg.setBackgroundResource(R.drawable.daohang_dingdan1);
                ft.commit();
                break;
            case R.id.dianpu:
                if (dianpu_fragment == null) {
                    ft.add(R.id.zhuye_fl, dianpu_fragment);
                } else {
                    ft.show(dianpu_fragment);
                }
                dianpuTv.setTextColor(getResources().getColor(R.color.daohang_tv_lan));
                dianpuImg.setBackgroundResource(R.drawable.daohang_dianpu1);
                ft.commit();
                break;
            case R.id.xiaoxi:
                if (xiaoxi_fragment == null) {
                    xiaoxi_fragment = new xiaoxi_Fragment();
                    ft.add(R.id.zhuye_fl, xiaoxi_fragment);
                } else {
                    ft.show(xiaoxi_fragment);
                }
                xiaoxiTv.setTextColor(getResources().getColor(R.color.daohang_tv_lan));
                xiaoxiImg.setBackgroundResource(R.drawable.daohang_xiaoxi1);
                ft.commit();
                break;
            case R.id.wode:
                if (wode_fragment == null) {
                    wode_fragment = new wode_Fragment();
                    ft.add(R.id.zhuye_fl, wode_fragment);
                } else {
                    ft.show(wode_fragment);
                }
                wodeTv.setTextColor(getResources().getColor(R.color.daohang_tv_lan));
                wodeImg.setBackgroundResource(R.drawable.daohang_wode1);
                ft.commit();
                break;
        }
    }

    /**
     * 隐藏fragment
     */
    private void hindAll() {
        FragmentTransaction ft = fm.beginTransaction();
        if (dingdan_fragment != null) {
            ft.hide(dingdan_fragment);
        }
        if (dianpu_fragment != null) {
            ft.hide(dianpu_fragment);
        }
        if (xiaoxi_fragment != null) {
            ft.hide(xiaoxi_fragment);
        }
        if (wode_fragment != null) {
            ft.hide(wode_fragment);
        }
        ft.commit();
    }

    /**
     * 初始化导航按键属性
     */
    private void initYan() {
        dingdanTv.setTextColor(getResources().getColor(R.color.daohang_tv_hui));
        dianpuTv.setTextColor(getResources().getColor(R.color.daohang_tv_hui));
        xiaoxiTv.setTextColor(getResources().getColor(R.color.daohang_tv_hui));
        wodeTv.setTextColor(getResources().getColor(R.color.daohang_tv_hui));
        dingdanImg.setBackgroundResource(R.drawable.daohang_dingdan2);
        dianpuImg.setBackgroundResource(R.drawable.daohang_dianpu2);
        xiaoxiImg.setBackgroundResource(R.drawable.daohang_xiaoxi2);
        wodeImg.setBackgroundResource(R.drawable.daohang_wode2);
    }

    /**
     * 双击退出，返回键相应
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            dblClick();//调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private void dblClick() {
        Timer timer = null;
        if (isExit == false) {
            isExit = true;//为true准备退出
            Toast.makeText(this, "再按一次退出应用！", Toast.LENGTH_SHORT).show();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;//取消退出
                }
            }, 3000);//3秒钟内没有点击第二下就取消退出
        } else {
            finish();
            System.exit(0);
        }
    }
}
