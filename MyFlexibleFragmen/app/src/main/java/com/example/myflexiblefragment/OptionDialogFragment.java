package com.example.myflexiblefragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Timer;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionDialogFragment extends Fragment implements View.OnClickListener {

    Button btnChoose, btnClose;
    RadioGroup rgOptions;
    RadioButton rgSaf, rgDmo, rgLvg, rgJMo;
    OnOptionDialogListener optionDialogListener;
    private Dialog getDialog;


    public OptionDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnChoose = view.findViewById(R.id.btn_choose);
        btnClose = view.findViewById(R.id.btn_close);

        btnChoose.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        rgOptions = view.findViewById(R.id.rg_options);
        rgDmo = view.findViewById(R.id.rb_dmo);
        rgJMo = view.findViewById(R.id.rb_jmo);
        rgLvg = view.findViewById(R.id.rb_lvg);
        rgSaf = view.findViewById(R.id.rb_saf);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();

        if (fragment instanceof DetailCategoryFragment) {
            DetailCategoryFragment detailCategoryFragment = (DetailCategoryFragment) fragment;
            this.optionDialogListener = detailCategoryFragment.optionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.optionDialogListener = null;
    }


    @Override
    public void onClick(View v) {



        switch(v.getId()) {

            case (R.id.btn_close) :
                getDialog.cancel();
                break;

            case R.id.btn_choose:
                int checkedRadioButtonId = rgOptions.getCheckedRadioButtonId();
                if (checkedRadioButtonId == -1) {
                    String coach = null;


                    switch (checkedRadioButtonId) {
                        case R.id.rb_saf:
                            coach = rgSaf.getText().toString().trim();
                            break;

                        case R.id.rb_dmo:
                            coach = rgDmo.getText().toString().trim();
                            break;

                        case R.id.rb_jmo:
                            coach = rgJMo.getText().toString().trim();
                            break;

                        case R.id.rb_lvg:
                            coach = rgLvg.getText().toString().trim();
                            break;

                    }

                    if (optionDialogListener != null) {
                        optionDialogListener.onOptionChosen(coach);
                    }

                    getDialog.dismiss();
                }
                break;


        }

    }


    public interface OnOptionDialogListener {
        void onOptionChosen(String text);
    }
}
