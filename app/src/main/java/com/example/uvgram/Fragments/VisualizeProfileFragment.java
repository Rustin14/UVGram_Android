package com.example.uvgram.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.uvgram.Adapters.ViewPagerAdapter;
import com.example.uvgram.R;
import com.google.android.material.tabs.TabLayout;

public class VisualizeProfileFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;
    SharedPreferences sharedPreferences;
    Context context;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String signedInUsername = sharedPreferences.getString("USERNAME", null);

        // TODO: Agregar condición para mostrar botón de Editar Perfil o Seguir/Bloquear

        tabLayout = getView().findViewById(R.id.tabLayout);
        viewPager2 = getView().findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager2.setAdapter(viewPagerAdapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visualize_profile, container, false);
    }
}