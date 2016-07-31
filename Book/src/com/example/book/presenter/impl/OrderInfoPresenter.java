package com.example.book.presenter.impl;

import com.example.book.entity.Address;
import com.example.book.entity.Cart;
import com.example.book.model.IModel;
import com.example.book.model.IOrderModel;
import com.example.book.model.impl.OrderModel;
import com.example.book.presenter.IOrderInfoPresenter;
import com.example.book.view.IOrderInfoView;


public class OrderInfoPresenter implements IOrderInfoPresenter {

	private IOrderInfoView view;
	private IOrderModel model;

	public OrderInfoPresenter(IOrderInfoView view) {
		this.view = view;
		this.model = new OrderModel();
	}

	@Override
	public void submitOrder(int addressId, String cartInfo) {
		model.submitOrder(addressId, cartInfo, new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				view.submitOrderSuccess();
			}
			public void onError(Object error) {
				view.submitOrderFail(error.toString());
			}
		});
	}

	@Override
	public void loadCartInfo() {
		Cart cart = model.loadMyCartInfo();
		view.setCartInfo(cart);
	}

	@Override
	public void loadMyDefaultAddress() {
		model.loadMyDefaultAddress(new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				view.updateAddressInfo((Address) success);
			}
			public void onError(Object error) {
			}
		});
	}
}
