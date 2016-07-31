package com.example.book.adapter;

import java.util.List;

import org.xutils.x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.book.entity.CartItem;
import com.example.book.util.GlobalConsts;
import com.example.book.R;


public class CartInfoAdapter extends BaseAdapter {
	private List<CartItem> items;
	private Context context;
	private LayoutInflater inflater;

	public CartInfoAdapter(List<CartItem> items, Context context) {
		this.items = items;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public CartItem getItem(int i) {
		return items.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHolder holder = null;
		if(view == null){
			view = inflater.inflate(R.layout.item_cartinfo_lv_cartitem, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) view.findViewById(R.id.ivBookPic);
			holder.tvBookName = (TextView) view.findViewById(R.id.tvBookName);
			holder.tvPrice = (TextView) view.findViewById(R.id.tvPrice);
			holder.tvTotalPrice = (TextView) view.findViewById(R.id.tvTotalPrice);
			holder.tvCount = (TextView) view.findViewById(R.id.tvCount);
			view.setTag(holder);
		}
		holder = (ViewHolder) view.getTag();
		CartItem item=getItem(i);
		x.image().bind(holder.imageView, GlobalConsts.BASEURL+"productImages/"+item.getBook().getProduct_pic());
		holder.tvBookName.setText(item.getBook().getProductName());
		holder.tvCount.setText("x"+item.getCount());
		holder.tvPrice.setText(item.getBook().getDangPrice()+"￥");
		holder.tvTotalPrice.setText("￥"+(item.getCount()*item.getBook().getDangPrice()));
		return view;
	}

	class ViewHolder{
		ImageView imageView;
		TextView tvBookName;
		TextView tvPrice;
		TextView tvTotalPrice;
		TextView tvCount;
	}

}
