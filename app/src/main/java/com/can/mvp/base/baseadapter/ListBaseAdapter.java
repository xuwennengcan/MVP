package com.can.mvp.base.baseadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.can.mvp.R;
import com.can.mvp.bean.MyEntity;
import com.can.mvp.util.NetWorkUtil;
import com.can.mvp.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by can on 2017/12/4.
 */


public abstract class ListBaseAdapter<T extends MyEntity> extends BaseAdapter {
    public static final int STATE_EMPTY_ITEM = 0;
    public static final int STATE_LOAD_MORE = 1;
    public static final int STATE_NO_MORE = 2;
    public static final int STATE_NO_DATA = 3;
    public static final int STATE_LESS_ONE_PAGE = 4;
    public static final int STATE_NETWORK_ERROR = 5;
    protected int state = 4;
    protected int _loadmoreText;
    protected int _loadFinishText;
    protected int _noDateText;
    protected int mScreenWidth;
    private LayoutInflater mInflater;
    protected Context mContext;
    protected ArrayList<T> mDatas = new ArrayList();
    private LinearLayout mFooterView;

    public ListBaseAdapter(Context context) {
        this.mContext = context;
    }

    protected LayoutInflater getLayoutInflater(Context context) {
        if(this.mInflater == null) {
            this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        }

        return this.mInflater;
    }

    public void setScreenWidth(int width) {
        this.mScreenWidth = width;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public ListBaseAdapter() {
        this._loadmoreText = R.string.loading;
        this._loadFinishText = R.string.loading_no_more;
        this._noDateText = R.string.error_view_no_data;
    }

    public int getCount() {
        switch(this.getState()) {
            case 0:
                return this.getDataSizePlus1();
            case 1:
            case 5:
                return this.getDataSizePlus1();
            case 2:
                return this.getDataSizePlus1();
            case 3:
                return 1;
            case 4:
                return this.getDataSize();
            default:
                return this.getDataSize();
        }
    }

    public int getDataSizePlus1() {
        return this.hasFooterView()?this.getDataSize() + 1:this.getDataSize();
    }

    public int getDataSize() {
        return this.mDatas.size();
    }

    public T getItem(int arg0) {
        return this.mDatas.size() > arg0? (T) (MyEntity) this.mDatas.get(arg0) :null;
    }

    public long getItemId(int arg0) {
        return (long)arg0;
    }

    public void setData(ArrayList<T> data) {
        this.mDatas = data;
        this.notifyDataSetChanged();
    }

    public ArrayList<T> getData() {
        return this.mDatas == null?(this.mDatas = new ArrayList()):this.mDatas;
    }

    public void addData(List<T> data) {
        if(this.mDatas != null && data != null && !data.isEmpty()) {
            this.mDatas.addAll(data);
        }

        this.notifyDataSetChanged();
    }

    public void addItem(T obj) {
        if(this.mDatas != null) {
            this.mDatas.add(obj);
        }

        this.notifyDataSetChanged();
    }

    public void addItem(int pos, T obj) {
        if(this.mDatas != null) {
            this.mDatas.add(pos, obj);
        }

        this.notifyDataSetChanged();
    }

    public void removeItem(Object obj) {
        this.mDatas.remove(obj);
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.mDatas.clear();
        this.notifyDataSetChanged();
    }

    public void setLoadmoreText(int loadmoreText) {
        this._loadmoreText = loadmoreText;
    }

    public void setLoadFinishText(int loadFinishText) {
        this._loadFinishText = loadFinishText;
    }

    public void setNoDataText(int noDataText) {
        this._noDateText = noDataText;
    }

    protected boolean loadMoreHasBg() {
        return true;
    }

    public abstract void convert(BaseViewHolder var1, T var2, int var3);

    public abstract int getItemLayoutId();

    @SuppressLint({"InflateParams"})
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position != this.getCount() - 1 || !this.hasFooterView() || this.getState() != 1 && this.getState() != 2 && this.state != 0 && this.getState() != 5) {
            if(position < 0) {
                position = 0;
            }

            BaseViewHolder viewHolder1 = BaseViewHolder.get(this.mContext, convertView, parent, this.getItemLayoutId(), position);
            this.convert(viewHolder1, this.getItem(position), position);
            return viewHolder1.getConvertView();
        } else {
            this.mFooterView = (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.base_footer, (ViewGroup)null);
            if(!this.loadMoreHasBg()) {
                this.mFooterView.setBackgroundDrawable((Drawable)null);
            }

            ProgressBar viewHolder = (ProgressBar)this.mFooterView.findViewById(R.id.progressbar);
            TextView text = (TextView)this.mFooterView.findViewById(R.id.text);
            switch(this.getState()) {
                case 0:
                    viewHolder.setVisibility(8);
                    this.mFooterView.setVisibility(0);
                    text.setText(this._noDateText);
                    break;
                case 1:
                    this.setFooterViewLoading();
                    break;
                case 2:
                    this.mFooterView.setVisibility(0);
                    viewHolder.setVisibility(8);
                    text.setVisibility(0);
                    text.setText(this._loadFinishText);
                    break;
                case 3:
                case 4:
                default:
                    viewHolder.setVisibility(8);
                    this.mFooterView.setVisibility(8);
                    break;
                case 5:
                    this.mFooterView.setVisibility(0);
                    viewHolder.setVisibility(8);
                    text.setVisibility(0);
                    if(NetWorkUtil.hasInternetConnected(this.mContext)) {
                        text.setText("加载出错了");
                    } else {
                        text.setText("没有可用的网络");
                    }
            }

            return this.mFooterView;
        }
    }

    protected boolean hasFooterView() {
        return true;
    }

    public View getFooterView() {
        return this.mFooterView;
    }

    public void setFooterViewLoading(String loadMsg) {
        ProgressBar progress = (ProgressBar)this.mFooterView.findViewById(R.id.progressbar);
        TextView text = (TextView)this.mFooterView.findViewById(R.id.text);
        this.mFooterView.setVisibility(0);
        progress.setVisibility(0);
        text.setVisibility(0);
        if(StringUtils.isEmpty(loadMsg)) {
            text.setText(this._loadmoreText);
        } else {
            text.setText(loadMsg);
        }

    }

    public void setFooterViewLoading() {
        this.setFooterViewLoading("");
    }

    public void setFooterViewText(String msg) {
        ProgressBar progress = (ProgressBar)this.mFooterView.findViewById(R.id.progressbar);
        TextView text = (TextView)this.mFooterView.findViewById(R.id.text);
        this.mFooterView.setVisibility(0);
        progress.setVisibility(8);
        text.setVisibility(0);
        text.setText(msg);
    }

    protected void setText(TextView textView, String text, boolean needGone) {
        if(text != null && !TextUtils.isEmpty(text)) {
            textView.setText(text);
        } else if(needGone) {
            textView.setVisibility(8);
        }

    }

    protected void setImageRes(ImageView imageRes, @DrawableRes int resId) {
        imageRes.setImageResource(resId);
    }

    protected void setText(TextView textView, String text) {
        this.setText(textView, text, false);
    }
}
