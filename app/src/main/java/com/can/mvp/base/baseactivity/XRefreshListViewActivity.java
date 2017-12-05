package com.can.mvp.base.baseactivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.can.mvp.R;
import com.can.mvp.base.baseview.EmptyLayout;
import com.can.mvp.base.baseadapter.ListBaseAdapter;
import com.can.mvp.bean.MyEntity;
import com.can.mvp.http.MyResponseHandler;
import com.can.mvp.util.Config;
import com.can.mvp.util.LogUtil;
import com.can.mvp.util.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by can on 2017/12/4.
 */


public class XRefreshListViewActivity<T extends MyEntity> extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;
    public static int mState = 0;
    private XRefreshListViewActivity<T>.ParserTask mParserTask;
    protected int mCurrentPage = 0;
    public ListBaseAdapter mAdapter;
    public List<T> mData = new ArrayList();
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    public EmptyLayout mErrorLayout;
    public ListView mListView;
    protected int mStoreEmptyState = -1;
    public MyResponseHandler responseHandler = new MyResponseHandler() {
        public void dataSuccess(String res) {
            LogUtil.i(LogUtil.CP, XRefreshListViewActivity.class + "请求来的数据 " + res);
            XRefreshListViewActivity.this.executeParserTask(res);
        }

        public void dataFinish() {
        }

        public void dataFailuer(int errorNo, String strMsg) {
            LogUtil.i(LogUtil.CP, XRefreshListViewActivity.class + "请求列表数据的接口出错了  " + errorNo + ",," + strMsg);
        }
    };

    public XRefreshListViewActivity() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setRootView() {
        this.setContentView(R.layout.activity_listview_refresh);
    }

    public void initView() {
        super.initView();
        this.mListView = (ListView)this.findViewById(R.id.listview);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swiperefreshlayout);
        this.mErrorLayout = (EmptyLayout)this.findViewById(R.id.error_layout);
        this.addHeadView();
        this.addFooterView();
        LogUtil.i(LogUtil.CP, XRefreshListViewActivity.class + " 为空吗？" + this.mSwipeRefreshLayout);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mSwipeRefreshLayout.setColorSchemeResources(new int[]{R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4});
        this.mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                XRefreshListViewActivity.this.mCurrentPage = 0;
                XRefreshListViewActivity.mState = 1;
                XRefreshListViewActivity.this.mErrorLayout.setErrorType(2);
                XRefreshListViewActivity.this.sendRequestData(true);
            }
        });
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnScrollListener(this);
        LogUtil.i(LogUtil.CP, XRefreshListViewActivity.class + " 为空吗？ mErrorLayout" + this.mErrorLayout);
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

    public void onDestroy() {
        this.mStoreEmptyState = this.mErrorLayout.getErrorState();
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
        if(NetWorkUtil.hasInternetConnected(this)) {
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

    private void executeParserTask(String data) {
        this.cancelParserTask();
        this.mParserTask = new XRefreshListViewActivity.ParserTask(data);
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

    protected int getPageSize() {
        return Config.PAGE_SIXE;
    }

    protected List<T> parseList(String is) {
        return null;
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

    protected boolean needShowEmptyNoData() {
        return true;
    }

    class ParserTask extends AsyncTask<Void, Void, String> {
        private final String reponseData;
        private boolean parserError;
        private List<T> currentList = new ArrayList();

        public ParserTask(String data) {
            this.reponseData = data;
        }

        protected String doInBackground(Void... params) {
            try {
                this.currentList = XRefreshListViewActivity.this.parseList(this.reponseData);
                LogUtil.i(LogUtil.CP, XRefreshListViewActivity.class + "解析 出来的数据 的，值 ，，" + this.currentList);
            } catch (Exception var3) {
                var3.printStackTrace();
                this.parserError = true;
            }

            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(!this.parserError) {
                XRefreshListViewActivity.this.executeOnLoadDataSuccess(this.currentList);
                XRefreshListViewActivity.this.executeOnLoadFinish();
            }

        }
    }
}

