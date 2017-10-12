package Fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.administrator.mybijie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;


/**
 * Created by Administrator on 2017/10/8.
 */

public class wode_Fragment extends Fragment {
    @BindView(R.id.map)
    MapView map;
    Unbinder unbinder;
    private AMap aMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.fragment_wode, null);
        unbinder = ButterKnife.bind(this, v);
        map.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = this.map.getMap();
        }
        LatLng mapCenterPoint = getMapCenterPoint();//获取屏幕中心的经纬度坐标
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setScaleControlsEnabled(true);
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 获取屏幕中心的经纬度坐标
     * @return
     */
    public LatLng getMapCenterPoint() {
        int left = map.getLeft();
        int top = map.getTop();
        int right = map.getRight();
        int bottom = map.getBottom();
        // 获得屏幕点击的位置
        int x = (int) (map.getX() + (right - left) / 2);
        int y = (int) (map.getY() + (bottom - top) / 2);
        Projection projection = aMap.getProjection();
        LatLng pt = projection.fromScreenLocation(new Point(x, y));
        return pt;
    }
}
