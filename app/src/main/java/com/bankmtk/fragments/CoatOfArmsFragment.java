package com.bankmtk.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View layout = inflater.inflate(R.layout.fragment_coatofarm,container,false);
        ImageView coatOfArms = (ImageView)layout.findViewById(R.id.imageView);
        TextView cityNameView = (TextView)layout.findViewById(R.id.textView);

        TypedArray imgs =
                getResources().obtainTypedArray(R.array.coatofarms_imgs);
        Parcel parcel = getParcel();
        coatOfArms.setImageResource(imgs.getResourceId(parcel.getImageIndex(),-1));
        cityNameView.setText(parcel.getCityName());
        return layout;
    }
}
