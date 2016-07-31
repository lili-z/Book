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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.example.book.activity.BookDetailActivity;
import com.example.book.adapter.StoreBookListAdapter;
import com.example.book.entity.Book;
import com.example.book.presenter.IStorePresenter;
import com.example.book.presenter.impl.StorePresenter;
import com.example.book.view.IStoreView;
import com.example.book.R;


public class StoreFragment extends Fragment implements IStoreView{

	@ViewInject(R.id.gvRecommend)
	private GridView gvRecommend;
	@ViewInject(R.id.etSearch)
	private EditText etSearch;
	@ViewInject(R.id.gvNew)
	private GridView gvNew;
	@ViewInject(R.id.gvHot)
	private GridView gvHot;

	private IStorePresenter storePresenter;

	private List<Book> reCommendBooks;
	private StoreBookListAdapter recommendAdapter;

	private List<Book> hotBooks;
	private StoreBookListAdapter hotAdapter;

	private List<Book> newBooks;
	private StoreBookListAdapter newAdapter;


	public StoreFragment() {
		storePresenter = new StorePresenter(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_store, null);
		x.view().inject(this, view);
		//清除自动聚焦
		etSearch.clearFocus();
		//加载数据
		storePresenter.loadRecommendBooks();
		storePresenter.loadNewBooks();
		storePresenter.loadHotBooks();
		//给gridView添加监听
		setListeners();
		return view;
	}

	/**
	 * 设置监听
	 */
	private void setListeners() {
		gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Book book = reCommendBooks.get(position);
				Intent intent=new Intent(getActivity(), BookDetailActivity.class);
				intent.putExtra("book",book);
				startActivity(intent);
			}
		});

		gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Book book = hotBooks.get(position);
				Intent intent=new Intent(getActivity(), BookDetailActivity.class);
				intent.putExtra("book",book);
				startActivity(intent);
			}
		});

		gvNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Book book = newBooks.get(position);
				Intent intent=new Intent(getActivity(), BookDetailActivity.class);
				intent.putExtra("book",book);
				startActivity(intent);
			}
		});


	}

	@Override
	public void updateRecommendList(List<Book> books) {
		this.reCommendBooks = books;
		recommendAdapter = new StoreBookListAdapter(getActivity(), this.reCommendBooks);
		gvRecommend.setAdapter(recommendAdapter);
	}

	@Override
	public void updateHotList(List<Book> books) {
		this.hotBooks = books;
		hotAdapter = new StoreBookListAdapter(getActivity(), this.hotBooks);
		gvHot.setAdapter(hotAdapter);
	}

	@Override
	public void updateNewList(List<Book> books) {
		this.newBooks = books;
		newAdapter = new StoreBookListAdapter(getActivity(), this.newBooks);
		gvNew.setAdapter(newAdapter);
	}
}
