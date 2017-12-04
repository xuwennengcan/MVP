package com.can.mvp.base.baseadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by can on 2017/12/4.
 */


public class BaseViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    public BaseViewHolder(Context context, View itemView, ViewGroup parent, int position) {
        super(itemView);
        this.mContext = context;
        this.mConvertView = itemView;
        this.mPosition = position;
        this.mViews = new SparseArray();
        this.mConvertView.setTag(this);
    }

    public void updatePosition(int position) {
        this.mPosition = position;
    }

    public static BaseViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        BaseViewHolder holder;
        if(convertView != null && convertView.getTag() != null) {
            holder = (BaseViewHolder)convertView.getTag();
            holder.mPosition = position;
            return holder;
        } else {
            convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            holder = new BaseViewHolder(context, convertView, parent, position);
            convertView.setTag(holder);
            return holder;
        }
    }

    public View getConvertView() {
        return this.mConvertView;
    }

    public <T extends View> T getView(int viewId) {
        View view = this.mViews.get(viewId);
        if(view == null) {
            view = this.mConvertView.findViewById(viewId);
            this.mViews.put(viewId, view);
        }

        return (T) view;
    }

    public BaseViewHolder setText(int viewId, String text) {
        TextView view = (TextView)this.getView(viewId);
        view.setText(text);
        return this;
    }

    public BaseViewHolder setHint(int viewId, String text) {
        TextView view = (TextView)this.getView(viewId);
        view.setHint(text);
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = (ImageView)this.getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    public BaseViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = (ImageView)this.getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    public BaseViewHolder setImageByUrl(int viewId, String url) {
        //ImageLoader.getInstance().displayImage(url, (ImageView)this.getView(viewId));
        return this;
    }

    public BaseViewHolder setVisible(int viewId) {
        View view = this.getView(viewId);
        view.setVisibility(0);
        return this;
    }

    public BaseViewHolder setGone(int viewId) {
        View view = this.getView(viewId);
        view.setVisibility(8);
        return this;
    }
}
