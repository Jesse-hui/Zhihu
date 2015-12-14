package effectmobi.com.zhihu.effectmobi.com.zhihu.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import effectmobi.com.zhihu.R;

/**
 * User: Jesse Pinkman
 * Date: 2015-12-10
 * Time: 16:44
 */

/**
 * 自定义 View 实现轮播
 */
public class LunboView extends FrameLayout implements ViewPager.OnPageChangeListener,View.OnClickListener{

    Context mContext;
    ViewPager vp_listHeader;
    LinearLayout ll_dot;
    List<View> views;//图片
    List<ImageView> iv_dots; //小圆点标识
    boolean autoPlay = true; //自动播放
    int delayTime = 2000;
    int currentItem = 0;

    List<String> mTitles;//存放标题
    List<Integer> mImages;// 存放图片

    Handler handler = new Handler();


    private ImageLoader mImageloader;
    private DisplayImageOptions options;


    public LunboView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        mImageloader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        setData();

        initView();
    }

    public LunboView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LunboView(Context context) {
        this(context,null);
    }

    //设置数据
    private void setData() {
        mTitles = new ArrayList<String>();
        mTitles.add("e租宝出大事啦，快来围观");
        mTitles.add("春运，火车票一票难求");
        mTitles.add("下载网易新闻客户端，最有态度的客户端");
        mTitles.add("喵喵喵， 我是萌宠 阿呆");
        mTitles.add("春晚彩排激烈进行中，姚晨无缘新年春晚");

        mImages = new ArrayList<Integer>();
        mImages.add(R.drawable.lunbo_1);
        mImages.add(R.drawable.lunbo_2);
        mImages.add(R.drawable.lunbo_3);
        mImages.add(R.drawable.lunbo_4);
        mImages.add(R.drawable.lunbo_5);
    }

    //初始化界面
    private void initView() {
        views = new ArrayList<View>();
        iv_dots = new ArrayList<ImageView>();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.fragment_list_header,this,true);
        vp_listHeader = (ViewPager) view.findViewById(R.id.vp_listHeader);
        ll_dot = (LinearLayout) view.findViewById(R.id.ll_dot);
        ll_dot.removeAllViews();

        //设置小圆点
        for(int i = 0; i<mImages.size(); i++){
            ImageView dot = new ImageView(mContext);
            dot.setImageResource(R.drawable.dot_blur);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            //添加到LinearLayout
            ll_dot.addView(dot,params);

            //添加到数组
            iv_dots.add(dot);
        }


        //设置viewPager里面的图片
        for(int i=0; i<mImages.size(); i++){
            LayoutInflater pagerInflater = LayoutInflater.from(mContext);
            View pagerView = pagerInflater.inflate(R.layout.viewpager_content, null);

            ImageView img_viewpager_content = (ImageView) pagerView.findViewById(R.id.img_viewpager_content);
            TextView  tv_viewpager_content  = (TextView) pagerView.findViewById(R.id.tv_viewpager_content);
            img_viewpager_content.setScaleType(ImageView.ScaleType.CENTER_CROP);

            //img_viewpager_content.setImageResource(mImages.get(i));
            mImageloader.displayImage("drawable://"+mImages.get(i),img_viewpager_content,options);
            tv_viewpager_content.setText(mTitles.get(i));

            img_viewpager_content.setOnClickListener(this);
            views.add(pagerView);
        }

        vp_listHeader.setAdapter(new LunboViewPagerAdapter());
        vp_listHeader.setFocusable(true);
        vp_listHeader.setCurrentItem(0);
        currentItem = 0;
        showDot(currentItem);
        vp_listHeader.addOnPageChangeListener(this);

        //自动轮播
        starPlay();


    }

    /**
     *  自动轮播
     *  %是求余运算，即2%10=2，10%2=0，10%3=1。
        /是普通的除号，即10/2=5
     */
    private void starPlay() {
        autoPlay = true;
        handler.postDelayed(task,4000);
    }

    Runnable task = new Runnable() {
        @Override
        public void run() {
            if(autoPlay){

                if(currentItem == 0){
                    vp_listHeader.setCurrentItem(currentItem,true);
                    currentItem = currentItem + 1;
                    handler.postDelayed(task,3000);
                }else
                {
                    if(currentItem == (mImages.size())){
                        currentItem = 0;
                    }
                    vp_listHeader.setCurrentItem(currentItem, true);
                    currentItem = currentItem + 1;
                    handler.postDelayed(task,3000);
                }
            }
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    //控制圆点的显示
    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        showDot(currentItem);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void  showDot(int position){
        for(int i=0; i< iv_dots.size(); i++){
            if(i == position){
                iv_dots.get(i).setImageResource(R.drawable.dot_focus);
            }else{
                iv_dots.get(i).setImageResource(R.drawable.dot_blur);
            }
        }
    }

    //viewpager中图片的点击事件
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case  R.id.img_viewpager_content:
                Toast.makeText(getContext(),"用户点击了第 "+(currentItem+1)+" 张图片",Toast.LENGTH_SHORT).show();
                break;
        }


    }

    //viewPager适配器
    class LunboViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}