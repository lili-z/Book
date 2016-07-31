package com.example.book.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.book.MyApplication;
import com.example.book.entity.Address;
import com.example.book.model.IAddressModel;
import com.example.book.util.CommonRequest;
import com.example.book.util.GlobalConsts;
import com.example.book.util.JSONParser;


public class AddressModel implements IAddressModel{
	private RequestQueue queue;

	public AddressModel() {
		queue = Volley.newRequestQueue(MyApplication.getContext());
	}

	@Override
	public void saveAddress(final Address address, final AsyncCallback callback) {
		String url= GlobalConsts.URL_SAVE_ADDRESS;
		CommonRequest request = new CommonRequest(Request.Method.POST,url,new Response.Listener<String>() {
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					if(obj.getInt("code")==GlobalConsts.RESPONSE_CODE_SUCCESS){
						callback.onSuccess(null);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		},new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("address.receiveName",address.getReceiveName());
				resultMap.put("address.full_address",address.getFull_address());
				resultMap.put("address.postalCode",address.getPostalCode());
				resultMap.put("address.mobile",address.getMobile());
				resultMap.put("address.phone",address.getPhone());
				return resultMap;
			}
		};
		queue.add(request);
	}

	@Override
	public void listAddress(final AsyncCallback callback) {
		String url=GlobalConsts.URL_LOAD_USER_ADDRESS;
		CommonRequest request = new CommonRequest(url, new Response.Listener<String>() {
			public void onResponse(String response) {
				try {
					JSONObject jsonObject = new JSONObject(response);
					if(jsonObject.getInt("code")==GlobalConsts.RESPONSE_CODE_SUCCESS){
						JSONArray ary = jsonObject.getJSONArray("data");
						List<Address> adds=JSONParser.parseAddress(ary);
						callback.onSuccess(adds);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {

			}
		});
		queue.add(request);
	}

	@Override
	public void setDefault(int id, final AsyncCallback callback) {
		String url=GlobalConsts.URL_SET_ADDRESS_DEFAULT+"?id="+id;
		CommonRequest request = new CommonRequest(url, new Response.Listener<String>() {
			public void onResponse(String response) {
				try {
					JSONObject jsonObject = new JSONObject(response);
					if(jsonObject.getInt("code")==GlobalConsts.RESPONSE_CODE_SUCCESS){
						callback.onSuccess(null);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {

			}
		});
		queue.add(request);
	}
}
