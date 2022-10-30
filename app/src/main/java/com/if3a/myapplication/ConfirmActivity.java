package com.if3a.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ConfirmActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    TextView tvNama, tvJk, tvWhatsapp, tvAlamat, tvTanggal;
    Button btnTanggal, btnKonfirmasi;

    String nama, jk, noWhatsapp, alamat, chosenDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        tvNama = findViewById(R.id.tv_nama);
        tvJk = findViewById(R.id.tv_jk);
        tvWhatsapp = findViewById(R.id.tv_no_whatsapp);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvTanggal = findViewById(R.id.tv_tanggal);

        btnTanggal = findViewById(R.id.btn_tanggal);
        btnKonfirmasi = findViewById(R.id.btn_konfirmasi);

        Intent terima = getIntent();
        nama = terima.getStringExtra("varNama");
        jk = terima.getStringExtra("varJenisKelamin");
        noWhatsapp = terima.getStringExtra("varNoWhatsapp");
        alamat = terima.getStringExtra("varAlamat");

        tvNama.setText(nama);
        tvJk.setText(jk);
        tvWhatsapp.setText(noWhatsapp);
        tvAlamat.setText(alamat);

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalender = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(ConfirmActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String tahun = Integer.toString(year);
                        String bulan = Integer.toString(month + 1);
                        String tanggal = Integer.toString(day);
                        if(day < 10){
                            tanggal = "0" + tanggal;
                        }
                        if(month < 9){
                            bulan = "0" + bulan;
                        }
                        chosenDate = tanggal + "/" + bulan + "/" + tahun;
                        tvTanggal.setText(chosenDate);
                    }
                }, newCalender.get(Calendar.YEAR), newCalender.get(Calendar.MONTH),
                        newCalender.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ConfirmActivity.this);
                dialog.setTitle("Perhatian");
                dialog.setMessage("Apakah Data Anda Sudah Benar ?");
                dialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ConfirmActivity.this, "Pendaftaran Anda Berhasil.",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                dialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ConfirmActivity.this, "Pastikan Data Yang Diisi Sudah Benar",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
    }
}