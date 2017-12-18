package com.kxky.demo.ui.tabtwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.kxky.demo.R;
import com.kxky.demo.bean.Title;
import com.kxky.demo.ui.BaseFragment;
import com.kxky.demo.ui.tabtwo.adapters.TestListAdapter;
import com.kxky.demo.utils.NetworkUtil;
import com.kxky.demo.utils.view.OnItemClickListener;


/**
 * Created by kxky on 2017/12/13.
 */

public class TabTwoFragment extends BaseFragment implements OnItemClickListener {
    private RecyclerView recyclerView;
    private SwipeToLoadLayout swipeToLoadLayout;
    private int pageNum = 1;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_tab_two;
    }

    @Override
    public void onViewInitialized(Bundle savedInstanceState) {
        initView();
        //开始加载，进入就刷新
        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        });
    }

    private void initView() {
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout) getActivity().findViewById(R.id.swipeToLoadLayout);

        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "OnRefreshListener!", Toast.LENGTH_SHORT);
                refresh();
            }
        });
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                showCommonProcessDialog("加载数据中...");
                Toast.makeText(getActivity(), "OnLoadMoreListener!", Toast.LENGTH_SHORT);
                loadMore();
            }
        });
    }

    /**
     * 刷新
     */
    private void refresh() {
        if (NetworkUtil.isNetworkAvailable(getActivity().getApplicationContext())) {
            pageNum = 1;
            refreshData();
        } else {
            swipeLayoutComplete();
        }
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        if (NetworkUtil.isNetworkAvailable(getActivity().getApplicationContext())) {
            loadMoreData();
        } else {
            swipeLayoutComplete();
        }
    }

    private void swipeLayoutComplete() {
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
    }

    private void refreshData() {
    /*    ACApi.getDeptReportList(pageNum, AppConfig.PAGE_SIZE, getSearchParams(fromOrgId, deptName, patientName, startDate, endDate))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Page<ReportMongoDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Page<ReportMongoDTO> reportMongoDTOPage) {
                        mList.clear();
                        page = reportMongoDTOPage;
                        if (page != null && page.getList() != null) {
                            mList.addAll(page.getList());
                        }
                        if (mList.isEmpty()) {
                            swipeToLoadLayout.setVisibility(View.GONE);
                            emptyView.setVisibility(View.VISIBLE);
                        } else {
                            swipeToLoadLayout.setVisibility(View.VISIBLE);
                            emptyView.setVisibility(View.GONE);
                        }
                        setTotalReportCount(page.getTotal());
                        adapter.setList(mList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeLayoutComplete();
                        ToastUtil.showMessage(getApplicationContext(), "数据查询失败，请检查网络");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        swipeLayoutComplete();
                    }
                });*/
    }

    private void loadMoreData() {
   /*     if (page != null && page.isHasNextPage()) {
            ACApi.getDeptReportList(pageNum + 1, AppConfig.PAGE_SIZE, getSearchParams(fromOrgId, deptName, null, startDate, endDate))
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Page<ReportMongoDTO>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Page<ReportMongoDTO> reportMongoDTOPage) {
                            pageNum++;
                            page = reportMongoDTOPage;
                            if (page.getList() != null) {
                                mList.addAll(page.getList());
                            }
                            setTotalReportCount(page.getTotal());
                            adapter.setList(mList);
                        }

                        @Override
                        public void onError(Throwable e) {
                            swipeLayoutComplete();
                            ToastUtil.showMessage(getApplicationContext(), "数据查询失败，请检查网络");
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            swipeLayoutComplete();
                        }
                    });
        } else {
            ToastUtil.showMessage(getApplicationContext(), "没有更多数据");
            swipeLayoutComplete();
        }*/
    }

    @Override
    public void onItemClick(View view, int position) {
   /*      Title title = listData.get(position);
           if (title != null) {
           Intent intent = new Intent(this, EmergencyActivity.class);
           intent.putExtra("taskcode", aidInfo.getTaskcode());
           startActivity(intent);*/
    }

}