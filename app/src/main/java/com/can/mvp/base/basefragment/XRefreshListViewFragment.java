package com.can.mvp.base.basefragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.can.mvp.R;
import com.can.mvp.base.baseadapter.ListBaseAdapter;
import com.can.mvp.base.baseview.EmptyLayout;
import com.can.mvp.bean.MyEntity;
import com.can.mvp.http.MyResponseHandler;
import com.can.mvp.util.Config;
import com.can.mvp.util.LogUtil;
import com.can.mvp.util.NetWorkUtil;
import com.can.mvp.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by can on 2017/12/4.
 */

public class XRefreshListViewFragment<T extends MyEntity> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;
    public static int mState = 0;
    private XRefreshListViewFragment<T>.ParserTask mParserTask;
    protected int mCurrentPage = 0;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    public ListView mListView;
    public ListBaseAdapter mAdapter;
    public List<T> mData = new ArrayList();
    public EmptyLayout mErrorLayout;
    protected int mStoreEmptyState = -1;
    public String myCachePath = "";
    public MyResponseHandler responseHandler = new MyResponseHandler() {
        public void dataSuccess(String res) {
            LogUtil.i(LogUtil.CP, XRefreshListViewFragment.class + "请求来的数据 " + res);
            if(!StringUtils.isEmpty(XRefreshListViewFragment.this.myCachePath)) {
            }

            XRefreshListViewFragment.this.executeParserTask(res);
        }

        public void dataFinish() {
        }

        public void dataFailuer(int errorNo, String strMsg) {
            LogUtil.i(LogUtil.CP, XRefreshListViewFragment.class + "请求列表数据 的时候，出异常了 ,代码：" + errorNo + "， 描述：" + strMsg);
        }
    };

    public XRefreshListViewFragment() {
    }


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.activity_listview_refresh, container, false);
    }

    public void onDestroyView() {
        this.mStoreEmptyState = this.mErrorLayout.getErrorState();
        super.onDestroyView();
    }

    public void onDestroy() {
        this.cancelParserTask();
        super.onDestroy();
    }

    protected ListBaseAdapter getmAdapter() {
        return null;
    }

    public void onRefresh() {
        if(mState != 1) {
            this.mListView.setSelection(0);
            this.setSwipeRefreshLoadingState();
            this.mCurrentPage = 0;
            mState = 1;
            this.sendRequestData(true);
        }
    }

    protected void sendRequestData(boolean refresh) {
        LogUtil.i(LogUtil.CP, XRefreshListViewFragment.class + "  设置缓存的ke   " + this.myCachePath);
        String cacheStr = "";
        if(!StringUtils.isEmpty(this.myCachePath)) {
            //cacheStr = (String)MyCache.getMyCache(this.getActivity()).readObject(this.myCachePath + this.mCurrentPage);
        }

        if(refresh) {
            if(NetWorkUtil.hasInternetConnected(this.getActivity())) {
                LogUtil.i(LogUtil.CP, XRefreshListViewFragment.class + "  来刷新了 ");
                this.requestData();
            } else {
                this.mErrorLayout.setErrorType(1);
            }
        } else if(!StringUtils.isEmpty(cacheStr)) {
            LogUtil.i(LogUtil.CP, XRefreshListViewFragment.class + " 缓存中取出，  列表 数据  " + cacheStr);
            this.executeParserTask(cacheStr);
        } else if(NetWorkUtil.hasInternetConnected(this.getActivity())) {
            this.requestData();
        } else {
            this.mErrorLayout.setErrorType(1);
        }

    }

    protected void requestData() {
    }

    protected boolean requestDataIfViewCreated() {
        return true;
    }

    protected void executeOnLoadFinish() {
        this.setSwipeRefreshLoadedState();
        mState = 0;
    }

    protected void setSwipeRefreshLoadingState() {
        if(this.mSwipeRefreshLayout != null) {
            this.mSwipeRefreshLayout.setRefreshing(true);
            this.mSwipeRefreshLayout.setEnabled(false);
        }

    }

    protected void setSwipeRefreshLoadedState() {
        if(this.mSwipeRefreshLayout != null) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            this.mSwipeRefreshLayout.setEnabled(true);
        }

    }

    public void setCachePath(String cachePath) {
        this.myCachePath = cachePath;
    }

    private void executeParserTask(String data) {
        this.cancelParserTask();
        this.mParserTask = new XRefreshListViewFragment.ParserTask(data);
        this.mParserTask.execute(new Void[0]);
    }

    private void cancelParserTask() {
        if(this.mParserTask != null) {
            this.mParserTask.cancel(true);
            this.mParserTask = null;
        }

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(this.mAdapter != null && this.mAdapter.getCount() != 0) {
            if(mState != 2 && mState != 1) {
                boolean scrollEnd = false;

                try {
                    if(view.getPositionForView(this.mAdapter.getFooterView()) == view.getLastVisiblePosition()) {
                        scrollEnd = true;
                    }
                } catch (Exception var5) {
                    scrollEnd = false;
                }

                if(mState == 0 && scrollEnd && (this.mAdapter.getState() == 1 || this.mAdapter.getState() == 5)) {
                    ++this.mCurrentPage;
                    mState = 2;
                    this.sendRequestData(false);
                    this.mAdapter.setFooterViewLoading();
                }

            }
        }
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    protected void executeOnLoadDataSuccess(List<T> data) {
        if(data == null) {
            data = new ArrayList();
        }

        this.mErrorLayout.setErrorType(4);
        if(this.mCurrentPage == 0) {
            this.mAdapter.clear();
        }

        boolean adapterState = false;
        byte adapterState1;
        if(this.mAdapter.getCount() + ((List)data).size() == 0) {
            adapterState1 = 0;
        } else if(((List)data).size() != 0 && (((List)data).size() >= this.getPageSize() || this.mCurrentPage != 0)) {
            adapterState1 = 1;
        } else {
            adapterState1 = 2;
            this.mAdapter.notifyDataSetChanged();
        }

        this.mAdapter.setState(adapterState1);
        this.mAdapter.addData((List)data);
        if(this.mAdapter.getCount() == 1) {
            if(this.needShowEmptyNoData()) {
                this.mErrorLayout.setErrorType(3);
            } else {
                this.mAdapter.setState(0);
                this.mAdapter.notifyDataSetChanged();
            }
        }

    }

    protected int getPageSize() {
        return Config.PAGE_SIXE;
    }

    protected List<T> parseList(String is) {
        return null;
    }

    protected boolean needShowEmptyNoData() {
        return true;
    }

    private void addHeadView() {
        if(this.getHeadView() != null) {
            this.mListView.addHeaderView(this.getHeadView());
        }

    }

    private void addFooterView() {
        if(this.getFooterView() != null) {
            this.mListView.addFooterView(this.getFooterView());
        }

    }

    public View getHeadView() {
        return null;
    }

    public View getFooterView() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView(View view) {
        this.mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swiperefreshlayout);
        this.mListView = (ListView)view.findViewById(R.id.listview);
        this.mErrorLayout = (EmptyLayout)view.findViewById(R.id.error_layout);
        this.addHeadView();
        this.addFooterView();
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mSwipeRefreshLayout.setColorSchemeResources(new int[]{R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4});
        this.mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                XRefreshListViewFragment.this.mCurrentPage = 0;
                XRefreshListViewFragment.mState = 1;
                XRefreshListViewFragment.this.mErrorLayout.setErrorType(2);
                XRefreshListViewFragment.this.sendRequestData(true);
            }
        });
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnScrollListener(this);
        if(this.mAdapter != null) {
            this.mListView.setAdapter(this.mAdapter);
            this.mErrorLayout.setErrorType(4);
        } else {
            this.mAdapter = this.getmAdapter();
            this.mListView.setAdapter(this.mAdapter);
            if(this.requestDataIfViewCreated()) {
                this.mErrorLayout.setErrorType(2);
                mState = 0;
                this.sendRequestData(false);
            } else {
                this.mErrorLayout.setErrorType(4);
            }
        }

        if(this.mStoreEmptyState != -1) {
            this.mErrorLayout.setErrorType(this.mStoreEmptyState);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    class ParserTask extends AsyncTask<Void, Void, String> {
        private final String reponseData;
        private boolean parserError;

        public ParserTask(String data) {
            this.reponseData = data;
        }

        protected String doInBackground(Void... params) {
            try {
                XRefreshListViewFragment.this.mData = XRefreshListViewFragment.this.parseList(this.reponseData);
                LogUtil.i(LogUtil.CP, XRefreshListViewFragment.class + "解析 出来的数据 的，值 ，，" + XRefreshListViewFragment.this.mData);
            } catch (Exception var3) {
                var3.printStackTrace();
                this.parserError = true;
            }

            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(!this.parserError) {
                XRefreshListViewFragment.this.executeOnLoadDataSuccess(XRefreshListViewFragment.this.mData);
                XRefreshListViewFragment.this.executeOnLoadFinish();
            }

        }
    }
}

