package com.bankmtk.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CoatOfArmsFragment extends Fragment {
    public static final String PARCEL = "parcel";

    public static CoatOfArmsFragment create(Parcel parcel){
        CoatOfArmsFragment f = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARCEL, parcel);
        f.setArguments(args);
        return f;
    }
    public Parcel getParcel(){
        Parcel parcel  =(Parcel)getArguments().getSerializable(PARCEL);
        return parcel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View layout = inflater.inflate(R.layout.fr)
        ImageView coatOfArms = new ImageView(getActivity());
        TypedArray imgs =
                getResources().obtainTypedArray(R.array.coatofarms_imgs);
        coatOfArms.setImageResource(imgs.getResourceId(getIndex(),-1));
        return coatOfArms;
    }
}
