package com.pucmm.fragments.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.ListFragment;

import com.pucmm.fragments.AndroidAdapter;
import com.pucmm.fragments.BodyActivity;
import com.pucmm.fragments.Classes.Concept;
import com.pucmm.fragments.Classes.PlaceholderContent;
import com.pucmm.fragments.R;

import java.util.List;

public class TitlesFragment extends ListFragment {
    OnHeadlineSelectedListener mCallback;

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layout = android.R.layout.simple_list_item_activated_1;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), layout, PlaceholderContent.Android);
        //ArrayAdapter<PlaceholderContent> adapter = new ArrayAdapter<>(getActivity(), layout, PlaceholderContent.ITEMS);

        setListAdapter(adapter);
    }


//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.title_fragment,container,false);
//
//        ListView androidList = view.findViewById(R.id.list);
//
//        AndroidAdapter androidAdapter = new AndroidAdapter(getActivity(), PlaceholderContent.ITEMS);
//        androidList.setAdapter(androidAdapter);
//
//        return view;
//    }

    @Override
    public void onStart() {
        super.onStart();

        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement headline selector listener");
        }
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent i = new Intent(getActivity().getApplicationContext(), BodyActivity.class);
            i.putExtra("position", position);
            startActivity(i);
            return;
        }
        mCallback.onArticleSelected(position);

        //set item as checked so its highlighted
        getListView().setItemChecked(position, true);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }
}
