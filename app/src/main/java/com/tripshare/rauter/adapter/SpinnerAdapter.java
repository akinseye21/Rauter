package com.tripshare.rauter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tripshare.rauter.R;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> values;

    public SpinnerAdapter(Context context, List<String> values) {
        super(context, R.layout.spinner_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.spinnerTextView);
        textView.setText(values.get(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.spinnerTextView);
        textView.setText(values.get(position));
        return convertView;
    }
}
