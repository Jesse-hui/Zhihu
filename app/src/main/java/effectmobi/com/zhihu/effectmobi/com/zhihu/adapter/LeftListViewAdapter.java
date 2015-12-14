package effectmobi.com.zhihu.effectmobi.com.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import effectmobi.com.zhihu.R;

/**
 * User: Jesse Pinkman
 * Date: 2015-12-10
 * Time: 13:21
 */

public class LeftListViewAdapter extends BaseAdapter {

    List<String> mData;
    Context mContext;

    public LeftListViewAdapter(List<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = View.inflate(mContext, R.layout.leftlistview,null);

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img_left);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_left);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(R.drawable.item);
        viewHolder.textView.setText(mData.get(position));

        //设置监听
        return convertView;
    }

    private static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}