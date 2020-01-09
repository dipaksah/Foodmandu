package com.dipak.foodmandu.ui.basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dipak.foodmandu.R;

public class basketFragment extends Fragment {

    private basketViewModel basketViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        basketViewModel =
                ViewModelProviders.of(this).get(basketViewModel.class);
        View root = inflater.inflate(R.layout.activity_login, container, false);

        return root;
    }
}