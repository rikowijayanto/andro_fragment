package com.example.myflexiblefragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailCategoryFragment extends Fragment implements View.OnClickListener {


    TextView tvCategoryName;
    TextView tvCategoryDescription;
    Button btnProfile;
    Button btnShowDialog;


    public static final String EXTRA_NAME = "extra_name";
    public  static final String EXTRA_DESCRIPTION = "extra_description";
    public  String Description;

    public String getDescription() {
        return Description;
    }


    public void setDescription(String description) {
        this.Description = description;

    }
    public DetailCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCategoryName = view.findViewById(R.id.tv_category_name);
        tvCategoryDescription = view.findViewById(R.id.tv_category_description);

        btnProfile = view.findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(this);

        btnShowDialog = view.findViewById(R.id.btn_show_dialog);
        btnShowDialog.setOnClickListener(this);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            String descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION);
            setDescription(descFromBundle);
        }

        if (getArguments() != null) {
            String categoryName = getArguments().getString(EXTRA_NAME);
            tvCategoryName.setText(categoryName);
            tvCategoryDescription.setText(getDescription());

        }
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_profile:
                break;

            case R.id.btn_show_dialog:

                OptionDialogFragment mOptionDialogFragment = new OptionDialogFragment();
                FragmentManager mFragmentManager = getChildFragmentManager();

                //mFragmentManager.beginTransaction().show(mOptionDialogFragment);

                mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment.class.getSimpleName());
                break;

        }
    }


    OptionDialogFragment.OnOptionDialogListener optionDialogListener = new OptionDialogFragment.OnOptionDialogListener() {
        @Override
        public void onOptionChosen(String text) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    };
}
