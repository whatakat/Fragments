package com.bankmtk.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class CitiesFragment extends ListFragment {
    boolean isExistCoatofarms;
    int currentPosition = 0;

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
            currentPosition = savedInstanceState.getInt("CurrentCity", 0);
        }
        if (isExistCoatofarms){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showCoatOfArms();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CurrentCity",currentPosition);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        currentPosition = position;
        showCoatOfArms();
    }
    private void showCoatOfArms(){
        if (isExistCoatofarms){
            getListView().setItemChecked(currentPosition,true);
            CoatOfArmsFragment detail = (CoatOfArmsFragment)
                    getFragmentManager().findFragmentById(R.id.coat_of_arms);
            if (detail == null || detail.getIndex() != currentPosition){
                detail = CoatOfArmsFragment.create(currentPosition);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.coat_of_arms, detail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }
        else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), CoatOfArmsActivity.class);
            intent.putExtra("index",currentPosition);
            startActivity(intent);
        }

    }
}
