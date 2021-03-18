package com.example.entrega1_das.Principal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.entrega1_das.RegistroInicioSesion.Registro;

import java.util.Calendar;

public class ClaseDialogoFecha extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog.OnDateSetListener listener;

    public static ClaseDialogoFecha newInstance(DatePickerDialog.OnDateSetListener listener) {
        ClaseDialogoFecha fragment = new ClaseDialogoFecha();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Calendar calendario = Calendar.getInstance();
        int anyo = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog eldialogo = new DatePickerDialog(getActivity(),this, anyo,mes,dia);
        return eldialogo;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int anyo, int mes, int dia) { }
}
