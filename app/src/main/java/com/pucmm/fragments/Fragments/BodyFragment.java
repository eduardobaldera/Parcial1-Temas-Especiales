package com.pucmm.fragments.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.pucmm.fragments.Classes.Concept;
import com.pucmm.fragments.Classes.PlaceholderContent;
import com.pucmm.fragments.R;

import java.time.format.DateTimeFormatter;

public class BodyFragment extends Fragment {
    public final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        return inflater.inflate(R.layout.body_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        boolean test2 = args != null;
        if (args != null) {
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateArticleView(mCurrentPosition);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateArticleView(int position) {
        TextView title = getActivity().findViewById(R.id.txt_title);
        TextView body = getActivity().findViewById(R.id.txt_body);
        Button link = getActivity().findViewById(R.id.button);
        TextView fecha = getActivity().findViewById(R.id.releasedate);
        Button checkbox = getActivity().findViewById(R.id.checkBox);

        title.setText(PlaceholderContent.ITEMS.get(position).getName());
        body.setText(PlaceholderContent.ITEMS.get(position).getDetails());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        checkbox.setPressed(PlaceholderContent.ITEMS.get(position).isSupported());
        fecha.setText(PlaceholderContent.ITEMS.get(position).getReleaseDate().format(formatter));

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = PlaceholderContent.ITEMS.get(position).getLink();

                Intent openBrowser = new Intent(Intent.ACTION_VIEW);
                openBrowser.setData(Uri.parse(url));
                startActivity(openBrowser);
            }
        });

        mCurrentPosition = position;


    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}
