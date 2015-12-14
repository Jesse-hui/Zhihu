package effectmobi.com.zhihu.effectmobi.com.zhihu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import effectmobi.com.zhihu.InfoActivity;
import effectmobi.com.zhihu.R;
import effectmobi.com.zhihu.effectmobi.com.zhihu.adapter.ContentListViewAdapter;
import effectmobi.com.zhihu.effectmobi.com.zhihu.bean.MainItem;

/**
 * User: Jesse Pinkman
 * Date: 2015-12-10
 * Time: 15:13
 */

/**
 * 主界面
 * 根据滚动效果，可以看出这个整体是一个ListView，图片轮播控件则被当作它的headerView，下面则是各个文章。
 * Headview是一个图片轮播控件，需要自定义，效果是：轮播的图片左下方显示标题， 右下方有圆点标示当前图片位置，
 *         点击图片，跳转到新的activity。
 *
 * 需要解决：
 * swiperefreshlayout listview 滑动冲突
 *
 */
public class MainContentFragment extends Fragment implements AbsListView.OnScrollListener,AdapterView.OnItemClickListener{



    //主界面是由一个listView构成
    ListView lv_content_list;

    List<MainItem> mMainItems;

    ContentListViewAdapter adapter;

    //解决滑动冲突
    MyListViewScoreListener myListViewScoreListener;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MainItem mainItem = mMainItems.get(position-1);
//        Toast.makeText(getContext(),mainItem.getTitle(),Toast.LENGTH_SHORT).show();

        //打开新的界面
        startActivity(new Intent(getContext(), InfoActivity.class));

    }

    public interface  MyListViewScoreListener{
        public void setSwiperefreshEnable(boolean b);

        public void onListviewScorlTobottom();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            myListViewScoreListener = (MyListViewScoreListener) context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString() + "must implement MyListViewScoreListener");
        }
    }


    /**
     * 重写listView的滚动事件，防止和swiperefreshlayout 滑动冲突
     * @param absListView
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        View firstView = absListView.getChildAt(firstVisibleItem);

        // 当firstVisibleItem是第0位。如果firstView==null说明列表为空，需要刷新;或者top==0说明已经到达列表顶部, 也需要刷新
        if (firstVisibleItem == 0 && (firstView == null || firstView.getTop() == 0)) {
            myListViewScoreListener.setSwiperefreshEnable(true);
        } else {
            myListViewScoreListener.setSwiperefreshEnable(false);
            //判断是否滚动到底部
            if (firstVisibleItem + visibleItemCount == totalItemCount) {
                // 加载更多
                addMore();

                //通知界面更新listview
                //myListViewScoreListener.onListviewScorlTobottom();

            }
        }


    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        lv_content_list = (ListView) view.findViewById(R.id.lv_content_list);
        lv_content_list.setOnScrollListener(this);
        lv_content_list.setOnItemClickListener(this);

        initMainItems();

        adapter = new ContentListViewAdapter(getContext(), mMainItems);

        View lunboView = LayoutInflater.from(getContext()).inflate(R.layout.header,lv_content_list,false);
        lv_content_list.addHeaderView(lunboView);
//        lv_content_list.addFooterView(lunboView);
        lv_content_list.setAdapter(adapter);


        return  view;
    }

    //初始化数据
    private void initMainItems() {
        mMainItems = new ArrayList<MainItem>();
        MainItem mainItem1 = new MainItem("习近平五大发展理念 贯穿“十三五”全程",R.drawable.pic_item);
        MainItem mainItem2 = new MainItem("屠呦呦领取诺贝尔奖 奖金约300万元(图)",R.drawable.pic_item);
        MainItem mainItem3 = new MainItem("刘强东讽阿里打假不作为 管理假货其实很简单",R.drawable.pic_item);
        MainItem mainItem4 = new MainItem("28地区上调最低工资标准 上调地区数量远超去年",R.drawable.pic_item);
        MainItem mainItem5 = new MainItem("铁路部门回应验证码难识别：识读正确率达七成",R.drawable.pic_item);
        MainItem mainItem6 = new MainItem("中国第一富二代王思聪的奢靡生活，国民老公",R.drawable.pic_item);
        MainItem mainItem7 = new MainItem("胡歌卖萌照红遍网络 萌翻众网友,喵喵喵",R.drawable.pic_item);
        MainItem mainItem8 = new MainItem("跑男关系大起，你以为关系就那么好？",R.drawable.pic_item);
        for(int i=0; i< 3;i++){
            mMainItems.add(mainItem1);
            mMainItems.add(mainItem2);
            mMainItems.add(mainItem3);
            mMainItems.add(mainItem4);
            mMainItems.add(mainItem5);
            mMainItems.add(mainItem6);
            mMainItems.add(mainItem7);
            mMainItems.add(mainItem8);
            mMainItems.add(mainItem4);
            mMainItems.add(mainItem5);
        }


    }

    /**
     * 加载更多,底部
     */
    private  void addMore(){
        if(mMainItems.size() > 40){
            return;
        }
        for(int i=0; i<3;i++){
            MainItem mainItem8 = new MainItem("跑男关系大起，你以为关系就那么好？",R.drawable.pic_item);
            mMainItems.add(mainItem8);
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * 顶部加载更多
     */
    public void loadMoreAndFlush(){
        for(int i=0; i<3;i++){
            MainItem mainItem1 = new MainItem("习近平五大发展理念 贯穿“十三五”全程",R.drawable.pic_item);
            mMainItems.add(mainItem1);
            mMainItems.set(0,mainItem1);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}