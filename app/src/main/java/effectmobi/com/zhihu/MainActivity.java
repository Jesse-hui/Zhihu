package effectmobi.com.zhihu;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import effectmobi.com.zhihu.effectmobi.com.zhihu.fragment.LeftMenuFragment;
import effectmobi.com.zhihu.effectmobi.com.zhihu.fragment.MainContentFragment;

public class MainActivity extends AppCompatActivity implements LeftMenuFragment.OnMyItemClickListener,MainContentFragment.MyListViewScoreListener {

    FrameLayout fl_content;
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;

    //刷新组件
    SwipeRefreshLayout swipeRefreshLayout;

    MainContentFragment mainContentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sr);

        setMainFragment();

        initView();
    }

    /**
     * 初始化中间的fragment
     */
    private void setMainFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mainContentFragment = new MainContentFragment();
        fragmentManager.beginTransaction().replace(R.id.fl_content,mainContentFragment).commit();
    }


    /**
     * 初始化界面
     */
    private void initView() {
        toolbar.setTitle("今日要闻");
        setSupportActionBar(toolbar);

        //左上角按钮
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //刷新组件
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mainContentFragment.loadMoreAndFlush();


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
                showSnackbar("刷新成功");
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ResId = item.getItemId();
        switch (ResId){
            case R.id.action_mode:
                showSnackbar("昼夜模式切换");
                break;
            case  R.id.action_setting:
                showSnackbar("系统设置");
                break;
            case  R.id.ab_search:
                //TODO:searchView 操作
                break;
            case  R.id.ab_share:
                //TODO: share 操作
                break;
        }
        return true;
    }

    private long firstTime;
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            closeMenu();
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Snackbar sb = Snackbar.make(fl_content, "再按一次退出", Snackbar.LENGTH_SHORT);
                sb.getView().setBackgroundColor(getResources().getColor(android.R.color.black));
                sb.show();
                firstTime = secondTime;
            } else {
                finish();
            }
        }
    }

    /**
     * 关闭左边抽屉menu
     */
    private void closeMenu() {
        mDrawerLayout.closeDrawers();
    }

    /**
     * 显示消息
     * @param msg
     */
    private void showSnackbar(String msg){
        Snackbar sb = Snackbar.make(fl_content, msg, Snackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
        sb.show();
    }


    //leftmenu 条目点击事件
    @Override
    public void onMyItemClicked() {
        closeMenu();
    }

    @Override
    public void setSwiperefreshEnable(boolean b) {
        swipeRefreshLayout.setEnabled(b);
    }

    //listview 已经滚动到底部
    @Override
    public void onListviewScorlTobottom() {
        showSnackbar("滚动到底部啦,准备加载更多");
    }
}
