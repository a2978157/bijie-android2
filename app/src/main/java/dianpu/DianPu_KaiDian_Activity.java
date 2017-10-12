package dianpu;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mybijie.R;

import org.zackratos.ultimatebar.UltimateBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 店铺立即开店界面
 * Created by Administrator on 2017/10/11.
 */

public class DianPu_KaiDian_Activity extends Activity {
    @BindView(R.id.kaidian_iv)
    ImageView kaidianIv;
    @BindView(R.id.kaidian_xiayibu)
    Button kaidianXiayibu;
    @BindView(R.id.kaidian_zhankaijiemian)
    LinearLayout kaidianZhankaijiemian;
    @BindView(R.id.kaidian_xuanzhezhiwei)
    RelativeLayout kaidianXuanzhezhiwei;
    @BindView(R.id.kaidian_shitidian)
    RadioButton kaidianShitidian;
    @BindView(R.id.kaidian_wangdian)
    RadioButton kaidianWangdian;
    @BindView(R.id.kaidian_xuanzhetv)
    TextView kaidianXuanzhetv;
    @BindView(R.id.kaidian_view)
    View kaidianView;
    @BindView(R.id.kaidian_fanhui)
    LinearLayout kaidianFanhui;
    boolean isZhangkai = false;//控制展开界面
    @BindView(R.id.kaidian_zhankaitv)
    ImageView kaidianZhankaitv;
    @BindView(R.id.kaidian_woshidianzhang)
    TextView kaidianWoshidianzhang;
    @BindView(R.id.kaidian_woshidianyuan)
    TextView kaidianWoshidianyuan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//手机旋转不触发横屏
        //沉淀式一体栏状态栏和导航栏一个颜色
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.daohang_tv_lan));
        setContentView(R.layout.dianpu_kaidian);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.kaidian_fanhui, R.id.kaidian_iv, R.id.kaidian_shitidian, R.id.kaidian_wangdian,
            R.id.kaidian_xiayibu, R.id.kaidian_xuanzhezhiwei,R.id.kaidian_woshidianzhang,R.id.kaidian_woshidianyuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.kaidian_fanhui://返回键销毁界面
                finish();
                break;
            case R.id.kaidian_xuanzhezhiwei://选择职位
                if (isZhangkai == false) {
                    kaidianZhankaijiemian.setVisibility(View.VISIBLE);
                    kaidianZhankaitv.setBackgroundResource(R.drawable.kaidian_shouhui);
                    isZhangkai = true;
                } else {
                    kaidianZhankaijiemian.setVisibility(View.GONE);
                    kaidianZhankaitv.setBackgroundResource(R.drawable.kaidian_zhankai);
                    isZhangkai = false;
                }
                break;
            case R.id.kaidian_shitidian://实体店
                isZhangkai = false;
                kaidianZhankaitv.setBackgroundResource(R.drawable.kaidian_zhankai);
                kaidianXuanzhezhiwei.setClickable(true);
                kaidianXiayibu.setBackgroundResource(R.drawable.kaidian_xiayibu);
                kaidianZhankaitv.setVisibility(View.VISIBLE);
                kaidianView.setBackgroundResource(R.color.dianyuanxinxi_edit);
                kaidianXuanzhetv.setText("请选择职位");
                break;
            case R.id.kaidian_wangdian://网店
                kaidianXiayibu.setBackgroundResource(R.drawable.kaidian_xiayibu_seletor);
                kaidianZhankaitv.setVisibility(View.GONE);
                kaidianView.setBackgroundResource(R.color.bai);
                kaidianZhankaijiemian.setVisibility(View.GONE);
                kaidianXuanzhetv.setText("点击下一步填写资料");
                kaidianWoshidianyuan.setTextColor(getBaseContext().getResources().getColorStateList(R.color.daohanghei));
                kaidianWoshidianzhang.setTextColor(getBaseContext().getResources().getColorStateList(R.color.daohanghei));
                kaidianXuanzhezhiwei.setClickable(false);
                break;
            case R.id.kaidian_xiayibu://下一步
                if (kaidianXuanzhetv.getText().equals("请选择职位")){
                    Toast.makeText(this, "请选择职位", Toast.LENGTH_SHORT).show();
                }else if (kaidianXuanzhetv.getText().equals("点击下一步填写资料")){
                    startActivity(new Intent(this,DianPu_WangDianXingXi.class));
                }else if (kaidianXuanzhetv.getText().equals("我是店员")){
                    startActivity(new Intent(this,DianPu_DianYuanXingXi.class));
                }else if (kaidianXuanzhetv.getText().equals("我是店长")){
                    startActivity(new Intent(this,DianPu_DianZhangXingXi.class));
                }
                break;
            case R.id.kaidian_woshidianzhang://我是店长
                kaidianWoshidianzhang.setTextColor(getBaseContext().getResources().getColorStateList(R.color.daohanghong));
                kaidianWoshidianyuan.setTextColor(getBaseContext().getResources().getColorStateList(R.color.daohanghei));
                kaidianXuanzhetv.setText("我是店长");
                kaidianZhankaijiemian.setVisibility(View.GONE);
                kaidianZhankaitv.setBackgroundResource(R.drawable.kaidian_zhankai);
                isZhangkai = false;
                kaidianXiayibu.setBackgroundResource(R.drawable.kaidian_xiayibu_seletor);
                break;
            case R.id.kaidian_woshidianyuan://我是店员
                kaidianWoshidianzhang.setTextColor(getBaseContext().getResources().getColorStateList(R.color.daohanghei));
                kaidianWoshidianyuan.setTextColor(getBaseContext().getResources().getColorStateList(R.color.daohanghong));
                kaidianXuanzhetv.setText("我是店员");
                kaidianZhankaijiemian.setVisibility(View.GONE);
                kaidianZhankaitv.setBackgroundResource(R.drawable.kaidian_zhankai);
                isZhangkai = false;
                kaidianXiayibu.setBackgroundResource(R.drawable.kaidian_xiayibu_seletor);
                break;
        }
    }
}
