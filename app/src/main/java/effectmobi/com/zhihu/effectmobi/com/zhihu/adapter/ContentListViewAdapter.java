package effectmobi.com.zhihu.effectmobi.com.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import effectmobi.com.zhihu.R;
import effectmobi.com.zhihu.effectmobi.com.zhihu.bean.MainItem;

/**
 * User: Jesse Pinkman
 * Date: 2015-12-11
 * Time: 14:50
 */

public class ContentListViewAdapter extends BaseAdapter {

    Context mContext;
    List<MainItem> mainItems;

    private ImageLoader mImageloader;
    private DisplayImageOptions options;

    public ContentListViewAdapter(Context mContext, List<MainItem> mainItems) {
        mImageloader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .build();

        this.mContext = mContext;
        this.mainItems = mainItems;
    }

    @Override
    public int getCount() {
        return mainItems == null?0:mainItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mainItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = View.inflate(mContext, R.layout.contentlistview,null);

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img_content_pic);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_content_title);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        viewHolder.imageView.setImageResource(mainItems.get(position).getResId());
//        采用imageloader加载图片
        mImageloader.displayImage("drawable://"+mainItems.get(position).getResId(),viewHolder.imageView,options);

        viewHolder.textView.setText(mainItems.get(position).getTitle());


        return convertView;
    }

    static  class  ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}