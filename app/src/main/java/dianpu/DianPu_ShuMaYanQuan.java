package dianpu;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mybijie.R;

import org.zackratos.ultimatebar.UltimateBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 扫码验券界面
 * Created by Administrator on 2017/10/12.
 */

public class DianPu_ShuMaYanQuan extends Activity {
    @BindView(R.id.shumayanquan_fanhui)
    LinearLayout shumayanquanFanhui;
    @BindView(R.id.dianyuanxinxi_tijiao)
    TextView dianyuanxinxiTijiao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//手机旋转不触发横屏
        //沉淀式一体栏状态栏和导航栏一个颜色
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.daohang_tv_lan));
        setContentView(R.layout.dianpu_shumayanquan);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.shumayanquan_fanhui})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.shumayanquan_fanhui:
                finish();
                break;
        }
    }
}
