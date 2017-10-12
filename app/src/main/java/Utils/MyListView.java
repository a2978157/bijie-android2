package Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/10/9.
 */

public class MyListView extends ListView{
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public MyListView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //右移2位空出前两位的测量单位，得到最总的最大值
        int i = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, i);
    }
}