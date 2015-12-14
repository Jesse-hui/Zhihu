package effectmobi.com.zhihu.effectmobi.com.zhihu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import effectmobi.com.zhihu.R;
import effectmobi.com.zhihu.effectmobi.com.zhihu.adapter.LeftListViewAdapter;

/**
 * User: Jesse Pinkman
 * Date: 2015-12-09
 * Time: 20:31
 */

public class LeftMenuFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    TextView tv_backup;
    TextView tv_download;

    ListView lv_item;
    BaseAdapter listViewAdapter;

    List<String> mDatas;

    //实现fragment 和 activity 通讯
    //第一步：
    OnMyItemClickListener onMyItemClickListener;

    public interface  OnMyItemClickListener{
        public void onMyItemClicked();
    }

    //第二步：
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onMyItemClickListener = (OnMyItemClickListener) context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString() + "must implement OnMyItemClickListener");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_menu, container, false);
        tv_backup = (TextView) view.findViewById(R.id.tv_backup);
        tv_download = (TextView) view.findViewById(R.id.tv_download);
        tv_backup.setOnClickListener(this);
        tv_download.setOnClickListener(this);

        //listview
        initDatas();

        lv_item = (ListView) view.findViewById(R.id.lv_item);
        listViewAdapter = new LeftListViewAdapter(mDatas,getContext());
        lv_item.setAdapter(listViewAdapter);
        lv_item.setOnItemClickListener(this);

        return view;
    }

    //数组填充
    private void initDatas() {
        mDatas = new ArrayList<String>();
        mDatas.add("日常心理学");
        mDatas.add("用户推荐");
        mDatas.add("电影日报");
        mDatas.add("不许无聊");
        mDatas.add("设计日报");
        mDatas.add("公司日报");
        mDatas.add("长腿妹子");
        mDatas.add("大胸妹子");
        mDatas.add("知乎妹子");
        mDatas.add("财经日报");
        mDatas.add("开始游戏");
        mDatas.add("音乐日报");
        mDatas.add("动漫");
        mDatas.add("GTA5");
        mDatas.add("日韩");
        mDatas.add("HIGH 购");
        mDatas.add("天猫");
        mDatas.add("京东");
        mDatas.add("推广");
    }

    //选项条目点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(),"用户点击了:"+mDatas.get(position),Toast.LENGTH_SHORT).show();
        onMyItemClickListener.onMyItemClicked();
    }

    @Override
    public void onClick(View v) {
        int btnId = v.getId();
        switch (btnId){
            case  R.id.tv_backup:
                Toast.makeText(getContext(),"收藏帖子", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.tv_download:
                Toast.makeText(getContext(),"离线缓存", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}