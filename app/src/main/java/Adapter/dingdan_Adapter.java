package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mybijie.R;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/10/9.
 */

public class dingdan_Adapter extends BaseAdapter {
    Context context;
    ArrayList<String> list;

    public dingdan_Adapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHerdle viewHerdle=null;
        if (view==null){
            view=View.inflate(context, R.layout.dingdan_listitem,null);
            viewHerdle =  new ViewHerdle();
            viewHerdle.dingdan_zhankai= (Button) view.findViewById(R.id.dingdan_zhankai);
            viewHerdle.dingdan_lvll= (LinearLayout) view.findViewById(R.id.dingdan_lvll);
            view.setTag(viewHerdle);
        }else {
            viewHerdle= (ViewHerdle) view.getTag();
        }
        viewHerdle.dingdan_zhankai.setText(list.get(i));
        return view;
    }
    class ViewHerdle{
        Button dingdan_zhankai;
        LinearLayout dingdan_lvll;
    }
}


