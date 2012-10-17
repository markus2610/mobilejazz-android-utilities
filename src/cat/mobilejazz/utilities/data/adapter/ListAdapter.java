package cat.mobilejazz.utilities.data.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class ListAdapter<T> extends BaseAdapter {

	private List<T> data;
	private int resourceId;
	private LayoutInflater inflater;

	public ListAdapter(List<T> data, int resourceId, Context context) {
		this.data = data;
		this.resourceId = resourceId;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	protected abstract int getObjectId(T obj);

	protected abstract void updateView(View v, T obj);

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getObjectId(data.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null)
			view = inflater.inflate(resourceId, null);
		updateView(view, data.get(position));
		return view;
	}

}
