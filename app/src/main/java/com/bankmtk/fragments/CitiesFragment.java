package com.bankmtk.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import static com.bankmtk.fragments.CoatOfArmsFragment.PARCEL;

public class CitiesFragment extends ListFragment {
    boolean isExistCoatofarms;
    Parcel currentParcel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Cities,
                android.R.layout.simple_list_item_activated_1);
        setListAdapter(adapter);
        View detailsFrame = getActivity().findViewById(R.id.coat_of_arms);
        isExistCoatofarms = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        if (savedInstanceState != null){
            currentParcel = (Parcel)savedInstanceState.getSerializable("CurrentCity");
        }
        if (isExistCoatofarms){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showCoatOfArms(currentParcel);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("CurrentCity",currentParcel);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        TextView cityNameView = (TextView) v;
        currentParcel = new Parcel(position,cityNameView.getText().toString());
        showCoatOfArms(currentParcel);
    }
    private void showCoatOfArms(Parcel parcel){
        if (isExistCoatofarms){
            getListView().setItemChecked(parcel.getImageIndex(),true);
            CoatOfArmsFragment detail = (CoatOfArmsFragment)
                    getFragmentManager().findFragmentById(R.id.coat_of_arms);
            if (detail == null || detail.getParcel().getImageIndex() != parcel.getImageIndex()){
                detail = CoatOfArmsFragment.create(parcel);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.coat_of_arms, detail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }
        else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), CoatOfArmsActivity.class);
            intent.putExtra(PARCEL, parcel);
            startActivity(intent);
        }

    }
}
