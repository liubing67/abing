package com.liu.abing;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demievil.library.RefreshLayout;
import com.liu.extend.adapters.NewsAdapter;
import com.liu.extend.entity.Constants;
import com.liu.extend.entity.NewsEntity;
import com.liu.extend.constant.Urls;
import com.tools.Tools;
import com.tools.http.CallServer;
import com.tools.http.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsFragment extends Fragment {
	private ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
	private ListView mListView;
	private NewsAdapter mAdapter;
	private final static int SET_NEWSLIST = 0;
	private View view;
	private RefreshLayout mRefreshLayout;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initData();
		super.onCreate(savedInstanceState);
	}

	/** 此方法意思为fragment是否可见 ,可见时候加载数据 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		if (isVisibleToUser) {
			//fragment可见时加载数据
			if(newsList !=null && newsList.size() !=0){
				handler.obtainMessage(SET_NEWSLIST).sendToTarget();
			}else{
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handler.obtainMessage(SET_NEWSLIST).sendToTarget();
					}
				}).start();
			}
		}else{
			//fragment不可见时不执行操作
		}
		super.setUserVisibleHint(isVisibleToUser);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news, null);
		initView();

		return view;
	}

	private void initData() {
		newsList = Constants.getNewsList();
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
				case SET_NEWSLIST:
					mAdapter = new NewsAdapter(getActivity(), newsList);
					mListView.setAdapter(mAdapter);
					break;
				default:
					break;
			}
			super.handleMessage(msg);
		}
	};

	private void initView()
	{
		mListView = (ListView) view.findViewById(R.id.mListView);
		mRefreshLayout = (RefreshLayout) view.findViewById(R.id.swipe_container);
		bindData();
		refreshListener();



		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//				doLogin();
				startActivity(new Intent(getActivity(),NewsDetailActivity.class));
			}
		});
	}
	private void bindData() {
		mRefreshLayout.setChildView(mListView);
		mRefreshLayout.setColorSchemeResources(R.color.user_bottom_text,
				R.color.google_green,
				R.color.light_green,
				R.color.red);
	}
	private void refreshListener()
	{
		//下拉刷新
		mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				mRefreshLayout.setRefreshing(false);
			}
		});

		//上拉加载
		mRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
			@Override
			public void onLoad() {
				mRefreshLayout.setLoading(false);
				Log.e("11111111","11111111");
			}
		});
	}
	private void doLogin() {
		Request request = NoHttp.createStringRequest(Urls.LOGIN, RequestMethod.POST);
		request.setConnectTimeout(30000);
		request.add("username", "13225337945");
		request.add("password", "l123456789");
		request.add("sign", Tools.getMD5("13515859857"+"2042386"));
		request(1, request, httpListener, true, true);
	}

	/**
	 * 接受响应
	 */
	private HttpListener<String> httpListener = new HttpListener<String>() {
		@Override
		public void onSucceed(int what, Response<String> response) {
			// 拿到请求结果
			String result = response.get();
			Log.d("1111111111", "loginresult = " + result);
			try {
				JSONObject jsonObject = new JSONObject(result);
				int rtState = jsonObject.optInt("rtState");
				String rtMsrg = jsonObject.optString("rtMsrg");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
//			Log.d(TAG, exception.getMessage());
		}

	};
	public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
		request.setCancelSign(this);
		CallServer.getRequestInstance().add(getActivity(), what, request, callback, canCancel, isLoading);
	}
}
