package com.example.book.presenter.impl;

import java.util.List;

import com.example.book.entity.Address;
import com.example.book.model.IAddressModel;
import com.example.book.model.IModel;
import com.example.book.model.impl.AddressModel;
import com.example.book.presenter.IAddressPresenter;
import com.example.book.view.IAddressView;


public class AddressPresenter implements IAddressPresenter {
	private IAddressView view;
	private IAddressModel model;

	public AddressPresenter(IAddressView view) {
		this.view = view;
		this.model = new AddressModel();
	}

	@Override
	public void saveAddress(Address address) {
		model.saveAddress(address, new IModel.AsyncCallback() {
			@Override
			public void onSuccess(Object success) {
				view.dismissSaveAddressDialog();
			}
			public void onError(Object error) {
			}
		});
	}

	@Override
	public void listAddress() {
		model.listAddress(new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				List<Address> addresses = (List<Address>) success;
				view.setAddresss(addresses);
				view.setAdapter();
			}
			public void onError(Object error) {
			}
		});
	}

	@Override
	public void setDefault(int id) {
		model.setDefault(id, new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				listAddress();
			}
			public void onError(Object error) {
			}
		});
	}
}
