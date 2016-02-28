package com.alse.paideia;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ModelArrayAdapter extends ArrayAdapter<Model>
{
    private ArrayList<Model> allModelItemsArray;
    private Activity context;
    private LayoutInflater inflator;
    private View.OnTouchListener listener;

    public ModelArrayAdapter(Activity context, ArrayList<Model> list,View.OnTouchListener _listener) {
        super(context, R.layout.result_row, list);
        this.listener = _listener;
        this.context = context;
        this.allModelItemsArray = new ArrayList<Model>();

        this.allModelItemsArray.addAll(list);
        inflator = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(position > allModelItemsArray.size())
            return null;
        Model m = allModelItemsArray.get(position);
        final ViewHolder viewHolder = new ViewHolder();
        ViewHolder Holder = null;
        if (convertView == null) {

            view = inflator.inflate(R.layout.result_row, null);

            view.setTag(viewHolder);

            viewHolder.title= (TextView) view.findViewById(R.id.results_title);
            viewHolder.body = (TextView) view.findViewById(R.id.results_body);
            viewHolder.icon = (ImageView) view.findViewById(R.id.preview);
            viewHolder.position = position;

            Holder = viewHolder;
        } else {
            view = convertView;
            Holder = ((ViewHolder) view.getTag());
        }

        if(this.listener != null)
            view.setOnTouchListener(this.listener);

        Holder.model = m;
        Holder.position = position;
        Holder.title.setText(m.getTitle());
        Holder.body.setText(m.getBody());
        Holder.icon.setImageBitmap(m.getIcon());
        return view;
    }
}
