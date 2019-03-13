package com.cc.greendao_demo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cc.greendao_demo.activity.R;
import com.cc.greendao_demo.model.UserInfo;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    Activity activity;

    List<UserInfo> data;

    LayoutInflater inflater;

    public ListViewAdapter(Activity activity, List<UserInfo> datas){
        this.activity = activity;
        this.data = datas;

        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public UserInfo getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //使用自定义ViewHolder
        ViewHolder viewHolder = null;

        //为空时加载，并为ViewHolder设置tab
        if(convertView == null){
            convertView = inflater.inflate(R.layout.listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.linkou = convertView.findViewById(R.id.linkou);
            viewHolder.txt_id = convertView.findViewById(R.id.txt_id);
            viewHolder.txt_name = convertView.findViewById(R.id.txt_name);
            viewHolder.txt_phonenum = convertView.findViewById(R.id.txt_phonenum);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt_id.setText(data.get(position).getId() + "");
        viewHolder.txt_name.setText(data.get(position).getName() + "");
        viewHolder.txt_phonenum.setText(data.get(position).getPhonenum() + "");


        /**
         * 点击事件
         */
        viewHolder.linkou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Linster.textItemOnClick(v, position);
            }
        });

        /**
         * 长按事件
         */
        viewHolder.linkou.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LongLinster.textItemOnLongClick(v, position);
                return true;
            }
        });


        return convertView;
    }

    class ViewHolder{
        TextView txt_id, txt_name, txt_phonenum;
        LinearLayout linkou;
    }


    /**
     * 事件接口
     */
    public ItemOnClickLinster Linster;

    public void setLinster(ItemOnClickLinster linster) {
        Linster = linster;
    }

    public interface ItemOnClickLinster{
        void textItemOnClick(View view, int position);
    }


    public ItemOnLongClickLinster LongLinster;

    public void setlongLinster(ItemOnLongClickLinster longLinster) {
        LongLinster = longLinster;
    }

    public interface ItemOnLongClickLinster{
        void textItemOnLongClick(View view, int position);
    }

}
