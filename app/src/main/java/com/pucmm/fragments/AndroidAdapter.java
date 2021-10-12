package com.pucmm.fragments;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.pucmm.fragments.Classes.PlaceholderContent;

import java.util.List;

public class AndroidAdapter extends ArrayAdapter<PlaceholderContent> {
    private Context context;
    private List<PlaceholderContent.PlaceholderVersion> placeholderContentList = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public AndroidAdapter(Context context, List <PlaceholderContent.PlaceholderVersion> androidList) {
        super(context, -1);
        this.placeholderContentList = androidList;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PlaceholderContent.PlaceholderVersion android = placeholderContentList.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_android_list, null);

        TextView androidTitle = (TextView) view.findViewById(R.id.Android_title);
        androidTitle.setText(android.getName());

        TextView androidVersion = (TextView) view.findViewById(R.id.Android_version);
        androidVersion.setText(android.getVersionNumber());

        return view;
    }
}
