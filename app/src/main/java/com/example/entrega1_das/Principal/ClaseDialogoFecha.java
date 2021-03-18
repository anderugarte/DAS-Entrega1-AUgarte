package com.example.entrega1_das.Principal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.entrega1_das.RegistroInicioSesion.Registro;

import java.util.Calendar;

public class ClaseDialogoFecha extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText cumple;

    public ClaseDialogoFecha (EditText et) {
        cumple = et;
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
    public void onDateSet(DatePicker datePicker, int anyo, int mes, int dia) {
        final String selectedDate = twoDigits(dia) + " / " + twoDigits(mes+1) + " / " + anyo;
        cumple.setText(selectedDate);
    }

    private String twoDigits(int x) {return (x<10) ? ("0"+x) : String.valueOf(x);}

}
