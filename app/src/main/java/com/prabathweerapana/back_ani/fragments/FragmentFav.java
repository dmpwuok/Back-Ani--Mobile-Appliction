package com.prabathweerapana.back_ani.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prabathweerapana.back_ani.R;

public class FragmentFav extends Fragment {

    private View v;

    public FragmentFav() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.frag_contacts,container,false);
        return v;
    }
}
