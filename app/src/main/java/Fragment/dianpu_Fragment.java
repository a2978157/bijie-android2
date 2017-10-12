package Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.administrator.mybijie.R;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import Utils.MyGridView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dianpu.DianPu_KaiDian_Activity;
import dianpu.DianPu_ShuMaYanQuan;

import static com.example.administrator.mybijie.R.drawable.dianpu_kaidian;


/**
 * 店铺界面
 * Created by Administrator on 2017/10/8.
 */

public class dianpu_Fragment extends Fragment implements PullToRefreshBase.OnRefreshListener2<ScrollView> {
    Unbinder unbinder;
    //GridView的图片
    int[] gviv = {R.drawable.dianpu_xinzeng, R.drawable.dianpu_shangpin, R.drawable.dianpu_kucun, R.drawable.dianpu_zhuxiao
            , R.drawable.dianpu_dianyuanguanli, R.drawable.dianpu_huiyuan, R.drawable.dianpu_dianpushezhi, R.drawable.dianpu_dianpuzhuangxiu, R.drawable.dianpu_huoyuanshichang,
            R.drawable.dianpu_zhinengfenxi, R.drawable.dianpu_pingjiaguanli, R.drawable.dianpu_gengduo};
    //GridView的点击效果图片
    int[] gviv2 = {R.drawable.dianpu_xinzeng2, R.drawable.dianpu_shangpin2, R.drawable.dianpu_kucun2, R.drawable.dianpu_zhuxiao2
            , R.drawable.dianpu_dianyuanguanli2, R.drawable.dianpu_huiyuan2, R.drawable.dianpu_dianpushezhi2, R.drawable.dianpu_dianpuzhuangxiu2, R.drawable.dianpu_huoyuanshichang2,
            R.drawable.dianpu_zhinengfenxi2, R.drawable.dianpu_pingjiaguanli2, R.drawable.dianpu_gengduo2};
    //ViewPager的图片
    int[] ii = {R.drawable.dianpu_lunbo1, R.drawable.dianpu_lubo2, R.drawable.dianpu_lunbo3};
    View[] Views = new View[ii.length];//ViewPager存View的数组
    List<View> listv = new ArrayList<View>();//ViewPager存View的集合
    boolean isLog = false;
    boolean isEe = true;
    int nxl = 0;
    //接受到消息轮播一次图片
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (isLog)
                    return;
                if (!isEe)
                    return;
                nxl++;
                dianpuVp.setCurrentItem(nxl);
            }
        }
    };
    private GvAdapter gvAdapter;

    @BindView(R.id.dianpu_kaidian)
    Button dianpuKaidian;
    @BindView(R.id.dianpu_gv)
    MyGridView dianpuGv;
    @BindView(R.id.dianpu_vp)
    ViewPager dianpuVp;
    @BindView(R.id.dianpu_vp1)
    ImageView dianpuVp1;
    @BindView(R.id.dianpu_vp2)
    ImageView dianpuVp2;
    @BindView(R.id.dianpu_vp3)
    ImageView dianpuVp3;
    @BindView(R.id.dianpu_shoukuan)
    ImageView dianpuShoukuan;
    @BindView(R.id.dianpu_shuma)
    ImageView dianpuShuma;
    @BindView(R.id.dianpu_shaoma)
    ImageView dianpuShaoma;
    @BindView(R.id.dianpu_weilianjie)
    ImageView dianpuWeilianjie;
    @BindView(R.id.dianpu_sv)
    PullToRefreshScrollView dianpuSv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.fragment_dianpu, null);
        unbinder = ButterKnife.bind(this, v);
        /**
         * GridView
         */
        //实例化一个适配器
        gvAdapter = new GvAdapter();
        //为GridView设置适配器
        dianpuGv.setAdapter(gvAdapter);
        onclick();

        /**
         * 刷新框架
         */
        ILoadingLayout loadingLayoutProxy = dianpuSv
                .getLoadingLayoutProxy();
        // 提示用户松手刷新时的文本
        loadingLayoutProxy.setReleaseLabel("释放时刷新");
        // 正在加载数据时显示的文本
        loadingLayoutProxy.setRefreshingLabel("正在刷新");
        // 下拉时显示的文本
        loadingLayoutProxy.setPullLabel("继续拉刷新");
        // 获取一个bitmap对象
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        // 设置刷新动画中的图片
        loadingLayoutProxy.setLoadingDrawable(new BitmapDrawable(
                getResources(), bitmap));
        dianpuSv.setOnRefreshListener(this);

        /**
         * 轮播图
         */
        initImg();

        return v;
    }

    @OnClick({R.id.dianpu_shoukuan, R.id.dianpu_shuma, R.id.dianpu_shaoma, R.id.dianpu_weilianjie,R.id.dianpu_kaidian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dianpu_shoukuan://收款  实现点击效果
                dianpuShoukuan.setBackgroundResource(R.drawable.dianpu_shoukuan2);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dianpuShoukuan.setBackgroundResource(R.drawable.dianpu_shoukuan);
                    }
                }, 100);
                break;
            case R.id.dianpu_shuma://输码验券
                dianpuShuma.setBackgroundResource(R.drawable.dianpu_shumayanquan2);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dianpuShuma.setBackgroundResource(R.drawable.dianpu_shumayanquan);
                    }
                }, 100);
                break;
            case R.id.dianpu_shaoma://扫码验券
                dianpuShaoma.setBackgroundResource(R.drawable.dianpu_shaomayanquan2);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dianpuShaoma.setBackgroundResource(R.drawable.dianpu_shaomayanquan);
                    }
                }, 100);
                startActivity(new Intent(getActivity(), DianPu_ShuMaYanQuan.class));
                break;
            case R.id.dianpu_weilianjie://未连接
                dianpuWeilianjie.setBackgroundResource(R.drawable.dianpu_weilianjie2);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dianpuWeilianjie.setBackgroundResource(R.drawable.dianpu_weilianjie);
                    }
                }, 100);
                break;
            case R.id.dianpu_kaidian://立即开店
                startActivity(new Intent(getActivity(), DianPu_KaiDian_Activity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 下拉刷新方法
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dianpuSv.onRefreshComplete();
            }
        }, 2000);
    }

    /**
     * 上拉加载方法
     *
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dianpuSv.onRefreshComplete();
            }
        }, 2000);
    }


    /**
     * GridView适配器
     */
    private class GvAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return gviv.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHerdle viewHerdle = null;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.dianpu_gridviewitem, null);
                viewHerdle = new ViewHerdle();
                viewHerdle.dianpu_gv_iv = (ImageView) convertView.findViewById(R.id.dianpu_gv_iv);
                viewHerdle.dianpu_gv_ll = (LinearLayout) convertView.findViewById(R.id.dianpu_gv_ll);
                convertView.setTag(viewHerdle);
            } else {
                viewHerdle = (ViewHerdle) convertView.getTag();
            }
            viewHerdle.dianpu_gv_iv.setBackgroundResource(gviv[position]);
            return convertView;
        }

        class ViewHerdle {
            ImageView dianpu_gv_iv;
            LinearLayout dianpu_gv_ll;
        }
    }

    /**
     * GridView点击事件  实现选中功能的核心方法 start
     */
    private void onclick() {
        // TODO Auto-generated method stub
        dianpuGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                final View dianpuiv = view.findViewById(R.id.dianpu_gv_iv);
                dianpuiv.setBackgroundResource(gviv2[position]);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dianpuiv.setBackgroundResource(gviv[position]);
                    }
                }, 100);
            }
        });
    }

    /**
     * ViewPager适配器
     */
    public class VpAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            container.removeView(Views[position % listv.size()]);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            int p = position % listv.size();
            View imageView = listv.get(p);
            ViewParent parent = imageView.getParent();
            if (parent != null) {
                ViewGroup vp = (ViewGroup) parent;
                vp.removeView(imageView);
            }
            container.addView(listv.get(p));
            return listv.get(p);
        }
    }

    /**
     * 给ViewPager添加图片、添加适配器、滑动监听
     */
    private void initImg() {
        for (int i = 0; i < ii.length; i++) {
            ImageView img = new ImageView(getActivity());
            img.setImageResource(ii[i]);
            listv.add(img);
        }
        dianpuVp.setAdapter(new VpAdapter());
        startTime();
        dianpuVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                vpclean();
                switch (arg0 % Views.length) {
                    case 0:
                        dianpuVp1.setBackgroundResource(R.drawable.dianpu_vpheidian);
                        break;
                    case 1:
                        dianpuVp2.setBackgroundResource(R.drawable.dianpu_vpheidian);
                        break;
                    case 2:
                        dianpuVp3.setBackgroundResource(R.drawable.dianpu_vpheidian);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
                nxl = arg0;
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
                if (arg0 == ViewPager.SCROLL_STATE_IDLE) {
                    isLog = false;
                } else {
                    isLog = true;
                }
            }
        });
    }

    /**
     * ViewPager圆点初始化
     */
    private void vpclean() {
        dianpuVp1.setBackgroundResource(R.drawable.dianpu_vpyuandian);
        dianpuVp2.setBackgroundResource(R.drawable.dianpu_vpyuandian);
        dianpuVp3.setBackgroundResource(R.drawable.dianpu_vpyuandian);
    }

    /**
     * 3秒让handler发送一次消息
     */
    private class TimeThread extends Thread {
        @Override
        public void run() {
            while (isEe) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }
    }

    /**
     * 开始发送消息
     */
    private void startTime() {
        new TimeThread().start();
    }
}
