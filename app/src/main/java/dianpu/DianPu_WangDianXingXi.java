package dianpu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.mybijie.R;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;

import org.zackratos.ultimatebar.UltimateBar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 店铺立即开店网店信息界面
 * Created by Administrator on 2017/10/11.
 */

public class DianPu_WangDianXingXi extends Activity {
    @BindView(R.id.wangdianxinxi_fanhui)
    LinearLayout wangdianxinxiFanhui;
    @BindView(R.id.wangdianxinxi_touxiang)
    ImageView wangdianxinxiTouxiang;
    @BindView(R.id.wangdianxinxi_yingye)
    ImageView wangdianxinxiYingye;
    @BindView(R.id.wangdianxinxi_jingying)
    ImageView wangdianxinxiJingying;
    @BindView(R.id.wangdianxinxi_fangzhu)
    ImageView wangdianxinxiFangzhu;
    @BindView(R.id.wangdianxinxi_shenfeng)
    ImageView wangdianxinxiShenfeng;
    @BindView(R.id.wangdianxinxi_qita)
    ImageView wangdianxinxiQita;
    @BindView(R.id.wangdianxinxi_shenfeng2)
    ImageView wangdianxinxiShenfeng2;
    private Dialog dialog;
    private WindowManager.LayoutParams wl;
    private TextView diolog_paizhao;
    private TextView diolog_xiangche;
    private ImageButton diolog_quxiao;
    private ImageCaptureManager captureManager; // 相机拍照处理类
    private ArrayList<String> imagePaths = null;
    int isIv = 1111;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//手机旋转不触发横屏
        //沉淀式一体栏状态栏和导航栏一个颜色
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.daohang_tv_lan));
        setContentView(R.layout.dianpu_wangdianxinxi);
        ButterKnife.bind(this);
        MyDialog();
    }

    @OnClick({R.id.wangdianxinxi_fanhui, R.id.wangdianxinxi_touxiang, R.id.wangdianxinxi_yingye, R.id.wangdianxinxi_jingying,
            R.id.wangdianxinxi_fangzhu, R.id.wangdianxinxi_shenfeng, R.id.wangdianxinxi_qita,R.id.wangdianxinxi_shenfeng2})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wangdianxinxi_fanhui:
                finish();
                break;
            case R.id.wangdianxinxi_touxiang:
                // 设置点击外围解散
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                isIv = 1111;
                break;
            case R.id.wangdianxinxi_yingye:
                // 设置点击外围解散
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                isIv = 2222;
                break;
            case R.id.wangdianxinxi_jingying:
                // 设置点击外围解散
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                isIv = 3333;
                break;
            case R.id.wangdianxinxi_fangzhu:
                // 设置点击外围解散
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                isIv = 4444;
                break;
            case R.id.wangdianxinxi_shenfeng:
                // 设置点击外围解散
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                isIv = 5555;
                break;
            case R.id.wangdianxinxi_qita:
                // 设置点击外围解散
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                isIv = 6666;
                break;
            case R.id.wangdianxinxi_shenfeng2:
                // 设置点击外围解散
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                isIv = 7777;
                break;
        }
    }

    public void MyDialog() {
        LayoutInflater inflater = LayoutInflater.from(DianPu_WangDianXingXi.this);
        // 引入窗口配置文件
        View inflate = inflater.inflate(R.layout.diolog_xuanzheiv, null);
        diolog_paizhao = (TextView) inflate.findViewById(R.id.diolog_paizhao);
        diolog_xiangche = (TextView) inflate.findViewById(R.id.diolog_xiangche);
        diolog_quxiao = (ImageButton) inflate.findViewById(R.id.diolog_quxiao);
        dialog = new Dialog(DianPu_WangDianXingXi.this, R.style.transparentFrameWindowStyle);
        dialog.setContentView(inflate, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        diolog_paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.onWindowAttributesChanged(wl);
                dialog.dismiss();
                try {
                    if (captureManager == null) {
                        captureManager = new ImageCaptureManager(DianPu_WangDianXingXi.this);
                    }
                    Intent intent3 = captureManager.dispatchTakePictureIntent();
                    startActivityForResult(intent3, ImageCaptureManager.REQUEST_TAKE_PHOTO);
                } catch (IOException e) {
                    Toast.makeText(DianPu_WangDianXingXi.this, com.foamtrace.photopicker.R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        diolog_xiangche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.onWindowAttributesChanged(wl);
                dialog.dismiss();
                PhotoPickerIntent intent = new PhotoPickerIntent(DianPu_WangDianXingXi.this);
                intent.setSelectModel(SelectModel.SINGLE);
                intent.setShowCarema(true);
                startActivityForResult(intent, 111);
            }
        });
        diolog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case 111:
                    loadAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                // 调用相机拍照
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
                    if (captureManager.getCurrentPhotoPath() != null) {
                        captureManager.galleryAddPic();
                        ArrayList<String> paths = new ArrayList<>();
                        paths.add(captureManager.getCurrentPhotoPath());
                        loadAdpater(paths);
                    }
                    break;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths) {
        if (imagePaths == null) {
            imagePaths = new ArrayList<>();
        }
        imagePaths.clear();
        imagePaths.addAll(paths);
        File file = new File(imagePaths.get(0));
        if (isIv == 1111) {
            Glide.with(DianPu_WangDianXingXi.this)
                    .load(file)
                    .centerCrop()
                    .crossFade()
                    .into(wangdianxinxiTouxiang);
        }
        if (isIv == 2222) {
            Glide.with(DianPu_WangDianXingXi.this)
                    .load(file)
                    .centerCrop()
                    .crossFade()
                    .into(wangdianxinxiYingye);
        }
        if (isIv == 3333) {
            Glide.with(DianPu_WangDianXingXi.this)
                    .load(file)
                    .centerCrop()
                    .crossFade()
                    .into(wangdianxinxiJingying);
        }
        if (isIv == 4444) {
            Glide.with(DianPu_WangDianXingXi.this)
                    .load(file)
                    .centerCrop()
                    .crossFade()
                    .into(wangdianxinxiFangzhu);
        }
        if (isIv == 5555) {
            Glide.with(DianPu_WangDianXingXi.this)
                    .load(file)
                    .centerCrop()
                    .crossFade()
                    .into(wangdianxinxiShenfeng);
        }
        if (isIv == 6666) {
            Glide.with(DianPu_WangDianXingXi.this)
                    .load(file)
                    .centerCrop()
                    .crossFade()
                    .into(wangdianxinxiQita);
        }
        if (isIv == 7777) {
            Glide.with(DianPu_WangDianXingXi.this)
                    .load(file)
                    .centerCrop()
                    .crossFade()
                    .into(wangdianxinxiShenfeng2);
        }
    }
}
