package com.example.book.model.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.book.MyApplication;
import com.example.book.entity.Book;
import com.example.book.model.IStoreModel;
import com.example.book.util.GlobalConsts;
import com.example.book.util.JSONParser;


public class StoreModel implements IStoreModel {
	private RequestQueue queue;

	public StoreModel() {
		queue = Volley.newRequestQueue(MyApplication.getContext());
	}

	@Override
	public void getRecommendList(final AsyncCallback callback) {
		String url= GlobalConsts.URL_LOAD_RECOMMEND_BOOK_LIST;
		StringRequest request=new StringRequest(StringRequest.Method.GET,url,new Response.Listener<String>() {
			public void onResponse(String response) {
				try {
					JSONObject object=new JSONObject(response);
					int code=object.getInt("code");
					if(code==GlobalConsts.RESPONSE_CODE_SUCCESS) {
						JSONArray array = object.getJSONArray("data");
						List<Book> books = JSONParser.parseBookList(array);
						callback.onSuccess(books);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		},new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				Log.e("error", "response:"+error.getMessage());
			}
		});
		queue.add(request);
	}

	@Override
	public void getHotList(final AsyncCallback callback) {
		String url= GlobalConsts.URL_LOAD_HOT_BOOK_LIST;
		StringRequest request=new StringRequest(StringRequest.Method.GET,url,new Response.Listener<String>() {
			public void onResponse(String response) {
				try {
					JSONObject object=new JSONObject(response);
					int code=object.getInt("code");
					if(code==GlobalConsts.RESPONSE_CODE_SUCCESS) {
						JSONArray array = object.getJSONArray("data");
						List<Book> books = JSONParser.parseBookList(array);
						callback.onSuccess(books);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		},new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				Log.e("error", "response:"+error.getMessage());
			}
		});
		queue.add(request);
	}

	@Override
	public void getNewList(final AsyncCallback callback) {
		String url= GlobalConsts.URL_LOAD_NEW_BOOK_LIST;
		StringRequest request=new StringRequest(StringRequest.Method.GET,url,new Response.Listener<String>() {
			public void onResponse(String response) {
				try {
					JSONObject object=new JSONObject(response);
					int code=object.getInt("code");
					if(code==GlobalConsts.RESPONSE_CODE_SUCCESS) {
						JSONArray array = object.getJSONArray("data");
						List<Book> books = JSONParser.parseBookList(array);
						callback.onSuccess(books);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		},new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				Log.e("error", "response:"+error.getMessage());
			}
		});
		queue.add(request);
	}
}
