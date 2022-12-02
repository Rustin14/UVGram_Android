package com.example.uvgram;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.uvgram.Adapters.CardListViewAdapter;

public class CardListViewFragment extends Fragment {

    ListView listView;
    String username;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getView().findViewById(R.id.cardsListView);
        FragmentActivity fragmentActivity = getActivity();
        Intent intent =  fragmentActivity.getIntent();
        username = (String) intent.getSerializableExtra("USERNAME");
        listView.setAdapter(new CardListViewAdapter(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_list_view, container, false);
    }


}