package com.example.book.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.book.MyApplication;
import com.example.book.activity.OrderInfoActivity;
import com.example.book.adapter.CartItemAdapter;
import com.example.book.entity.CartItem;
import com.example.book.presenter.ICartPresenter;
import com.example.book.presenter.impl.CartPresenter;
import com.example.book.view.ICartView;
import com.example.book.R;


public class CartFragment extends Fragment implements ICartView{

	@ViewInject(R.id.lvCart)
	private ListView lvCart;
	@ViewInject(R.id.tvEmptyCart)
	private TextView tvEmptyCart;
	@ViewInject(R.id.btnEdit)
	private Button btnEdit;
	@ViewInject(R.id.tvTotalPrice)
	private TextView tvTotalPrice;
	@ViewInject(R.id.btnSubmit)
	private Button btnSubmit;
	private List<CartItem> items;
	private CartItemAdapter adapter;
	private ICartPresenter cartPresenter;

	@Override
	public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cart, null);
		cartPresenter = new CartPresenter(this);
		//初始化控件
		x.view().inject(this, view);
		items=MyApplication.getContext().getCart().getItems();
		//设置监听器
		setListener();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		//设置适配器
		setAdapter();
	}

	/**
	 * 设置监听器
	 */
	private void setListener() {
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(), OrderInfoActivity.class);
				startActivity(intent);
			}
		});

		btnEdit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				adapter.deleteToggle();
			}
		});
	}

	/**
	 * 设置适配器
	 */
	private void setAdapter() {
		adapter = new CartItemAdapter(getActivity(), items, lvCart);
		adapter.setPresenter(cartPresenter);
		lvCart.setAdapter(adapter);
		//计算合计
		cartPresenter.loadTotalPrice();
	}

	@Override
	public void updateTotalPrice(double price) {
		tvTotalPrice.setText("￥"+price);
	}
}
