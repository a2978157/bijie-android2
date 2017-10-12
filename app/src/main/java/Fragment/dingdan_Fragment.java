package Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.administrator.mybijie.R;

import java.util.ArrayList;

import Utils.MyListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/10/8.
 */

public class dingdan_Fragment extends Fragment implements View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.dingdan_lv)
    MyListView dingdanLv;
    ArrayList<MyData> list = new ArrayList<>();
    @BindView(R.id.fudong_fanhui)
    ImageButton fudongFanhui;
    @BindView(R.id.dingdan_sv)
    ScrollView dingdanSv;
    private myAdapter myAdapter;
    int[] location =new int[2];


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.fragment_dingdan, null);
        unbinder = ButterKnife.bind(this, v);
        for (int i = 0; i < 20; i++) {
            MyData data = new MyData();
            data.name = "aa" + i;
            list.add(data);
        }
        myAdapter = new myAdapter();
        dingdanLv.setAdapter(myAdapter);
        dingdanSv.setOnTouchListener(new View.OnTouchListener() {
            private int lastY = 0;
            private int touchEventId = 10000;   //设置了一个标记值，区分我是在做滑动监听
            Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == touchEventId) {
                        //只有滑动时触发
                        if (lastY != dingdanSv.getScrollY()) {
                            //scrollview一直在滚动，会触发
                            handler.sendMessageDelayed(
                                    handler.obtainMessage(touchEventId, dingdanSv), 5);
                            //拿到当时手指点击scrollview的y轴位置
                            lastY = dingdanSv.getScrollY();
                            //拿到当前控件在屏幕的那个距离，距离值保存在参数中
                            dingdanLv.getLocationOnScreen(location);
                            int locationy = location[1];
                            Log.e(TAG, "handleMessage: "+locationy);
                            if (locationy<-100){
                                fudongFanhui.setVisibility(View.VISIBLE);
                            }else {
                                fudongFanhui.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            };

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE://移动
                        handler.sendEmptyMessage(touchEventId);
                        break;
                    case MotionEvent.ACTION_UP://抬起
                        handler.sendEmptyMessageDelayed(touchEventId, 50);
                        break;
                }
                return false;
            }

        });
        fudongFanhui.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        dingdanSv.smoothScrollTo(0,0);
        fudongFanhui.setVisibility(View.GONE);
    }

    /**
     * ListView适配器
     */
    private class myAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyData data = list.get(position);
            ViewHolder viewHerdle = null;
            if (convertView == null) {
                convertView = convertView.inflate(getActivity(), R.layout.dingdan_listitem, null);
                viewHerdle = new ViewHolder();
                viewHerdle.dingdan_zhankai = (Button) convertView.findViewById(R.id.dingdan_zhankai);
                viewHerdle.dingdan_lvll = (LinearLayout) convertView.findViewById(R.id.dingdan_lvll);
                convertView.setTag(viewHerdle);
            } else {
                viewHerdle = (ViewHolder) convertView.getTag();
            }
            if (data.expand) {
                viewHerdle.dingdan_lvll.setVisibility(View.VISIBLE);
            } else {
                viewHerdle.dingdan_lvll.setVisibility(View.GONE);
            }
            viewHerdle.dingdan_zhankai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeItemVisibility(position);
                }
            });
            viewHerdle.dingdan_zhankai.setText(data.name);
            return convertView;
        }

        /**
         * 控制展开的方法
         *
         * @param position
         */
        private void changeItemVisibility(int position) {
            MyData data = list.get(position);
            if (data.expand) {
                data.expand = !data.expand;
            } else {
                data.expand = true;
            }
            notifyDataSetChanged();
        }

        class ViewHolder {
            Button dingdan_zhankai;
            LinearLayout dingdan_lvll;
        }
    }

    private class MyData {
        boolean expand;
        String name;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
