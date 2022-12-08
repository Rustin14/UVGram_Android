package com.example.uvgram.Fragments;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uvgram.Activities.PublicationDetailActivity;
import com.example.uvgram.Adapters.PostAdapter;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.HomepageViewModel;
import com.example.uvgram.ViewModel.HomepageViewModelFactory;

public class CardListViewFragment extends Fragment {

    ListView listView;
    String username;
    private HomepageViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity fragmentActivity = getActivity();
        Intent intent =  fragmentActivity.getIntent();
        username = (String) intent.getSerializableExtra("USERNAME");

        RecyclerView recyclerView = getView().findViewById(R.id.cardsListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        PostAdapter adapter = new PostAdapter();

        adapter.setOnItemClickListener(post -> {
            Intent myIntent = new Intent(getContext(), PublicationDetailActivity.class);
            myIntent.putExtra("POST", post);
            startActivity(myIntent);
        });

        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this,
                new HomepageViewModelFactory(getActivity().getApplication()))
                .get(HomepageViewModel.class);
        viewModel.getPostsList().observe(getViewLifecycleOwner(),
                posts -> adapter.setPostList(posts));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_list_view, container, false);
    }
}