package com.cc.greendao_demo.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.cc.greendao_demo.adapter.ListViewAdapter;
import com.cc.greendao_demo.cc.DaoMaster;
import com.cc.greendao_demo.cc.DaoSession;
import com.cc.greendao_demo.cc.UserInfoDao;
import com.cc.greendao_demo.db.MyApplication;
import com.cc.greendao_demo.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * greenDao的增删改查
 * @author 财财
 * @version 2019年3月13日14:08:00
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Activity activity = MainActivity.this;

    ListView listView;

    Button bt1;

    List<UserInfo> datas = new ArrayList<>();

    ListViewAdapter listViewAdapter;


    UserInfoDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取数据库操作dao
        dao = MyApplication.getInstances().getDaoSession().getUserInfoDao();

        initView();
        initData();
    }



    private void initData() {
        //查询所有的数据
        datas = dao.queryBuilder().build().list();
        //使用adapter显示到listview中
        listViewAdapter = new ListViewAdapter(activity, datas);
        //绑定
        listView.setAdapter(listViewAdapter);

        /**
         * 实现点击事件（修改）
         */
        listViewAdapter.setLinster(new ListViewAdapter.ItemOnClickLinster() {
            @Override
            public void textItemOnClick(View view, int position) {
                Log.d("TAG", "单击事件");
                //修改数据不能相同，否则会报错
                dao.update(new UserInfo(datas.get(position).getId(), datas.get(position).getName() + "我被修改啦", datas.get(position).getPhonenum()));
                //刷新数据
                notifyListView();
            }
        });

        /**
         * 实现长按事件（删除）
         */
        listViewAdapter.setlongLinster(new ListViewAdapter.ItemOnLongClickLinster() {
            @Override
            public void textItemOnLongClick(View view, int position) {
                Log.d("TAG", "长按事件");
                //根据对应的id删掉该列数据
                dao.deleteByKey(datas.get(position).getId());
                //刷新数据
                notifyListView();
            }
        });
    }

    private void initView() {
        listView = findViewById(R.id.listview);
        bt1 = findViewById(R.id.button1);

        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.button1){
            //添加数据
            dao.insert(new UserInfo(null, "张三", "15698512368"));
            dao.insert(new UserInfo(null, "李四", "15698512368"));
            dao.insert(new UserInfo(null, "王五", "15698512368"));
            dao.insert(new UserInfo(null, "赵六", "15698512368"));
            dao.insert(new UserInfo(null, "刘七", "15698512368"));
            dao.insert(new UserInfo(null, "曹八", "15698512368"));
            dao.insert(new UserInfo(null, "苏九", "15698512368"));
            dao.insert(new UserInfo(null, "寇十", "15698512368"));
            dao.insert(new UserInfo(null, "陈一", "15698512368"));
            dao.insert(new UserInfo(null, "庄二", "15698512368"));
            notifyListView();

        }
    }

    /**
     * 跟新ListView,不知为啥adapter.notifyDataSetChanged()没反应
     */
    public void notifyListView(){
        //先清空集合里面的数据
        datas.clear();
        //重新赋值
        datas = dao.loadAll();
        //重新绑定
        listViewAdapter = new ListViewAdapter(MainActivity.this, datas);
        listView.setAdapter(listViewAdapter);
    }

}
