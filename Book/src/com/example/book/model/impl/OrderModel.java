package com.example.book.model.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.book.MyApplication;
import com.example.book.entity.Address;
import com.example.book.entity.Cart;
import com.example.book.model.IOrderModel;
import com.example.book.util.CommonRequest;
import com.example.book.util.GlobalConsts;


public class OrderModel implements IOrderModel {
	private RequestQueue queue;

	public OrderModel() {
		queue = Volley.newRequestQueue(MyApplication.getContext());
	}

	@Override
	public void submitOrder(final int addressId, final String cartInfo, final AsyncCallback callback) {
		String url = GlobalConsts.URL_SUBMIT_ORDER;
		CommonRequest req = new CommonRequest(Request.Method.POST, url, new Response.Listener<String>() {
			public void onResponse(String respJson) {
				try {
					JSONObject obj = new JSONObject(respJson);
					if (obj.getInt("code") == GlobalConsts.RESPONSE_CODE_SUCCESS) {
						callback.onSuccess(null);
					}else{
						callback.onError("下订单出错啦，重试下~");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
			}
		}) {
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String,String> params = new HashMap<String, String>();
				params.put("addressId", addressId+"");
				params.put("cartInfo", cartInfo);
				return params;
			}
		};
		queue.add(req);
	}

	@Override
	public Cart loadMyCartInfo() {
		Cart cart = MyApplication.getContext().getCart();
		return cart;
	}

	@Override
	public void loadMyDefaultAddress(final AsyncCallback callback) {
		String url = GlobalConsts.URL_LOAD_DEFAULT_ADDRESS;
		
		CommonRequest req = new CommonRequest(url, new Response.Listener<String>() {
			public void onResponse(String str) {
				try {
					JSONObject obj = new JSONObject(str);
					if (obj.getInt("code") == GlobalConsts.RESPONSE_CODE_SUCCESS) {
						obj = obj.getJSONObject("data");
						Address address = new Address(obj.getInt("id"),
										obj.getString("receiveName"),
										obj.getString("full_address"),
										obj.getString("postalCode"),
										obj.getString("mobile"),
										obj.getString("phone"));
						callback.onSuccess(address);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				callback.onError("默认地址加载失败");
			}
		});
		queue.add(req);
	}

}








