package com.example.figurasgeometricas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class TrinomioCuadradoPerfecto extends AppCompatActivity {
    TextView txt1, txt2, txt3 ,Texto;
    Button btnTri, btnre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trinomio_cuadrado_perfecto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt1 = findViewById(R.id.txtP1);
        txt2 = findViewById(R.id.txtP2);
        txt3 = findViewById(R.id.txtP3);
        btnTri = findViewById(R.id.btnTrin);
        Texto = findViewById(R.id.txtRes);
        btnre = findViewById(R.id.btnRe);

        btnTri.setOnClickListener(v -> {
            String P1 = txt1.getText().toString().trim();
            String P2 = txt2.getText().toString().trim();
            String P3 = txt3.getText().toString().trim();

            if (!P1.isEmpty() && !P2.isEmpty() && !P3.isEmpty()) {
                obtenerServicioWeb("http://172.23.0.1:3001/trinomio/" + P1 + "/" + P2 + "/" + P3);
            } else {
                Toast.makeText(getApplicationContext(), "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
        btnre.setOnClickListener(v -> {
            Intent intent = new Intent(TrinomioCuadradoPerfecto.this, Menu.class);
            startActivity(intent);
        });
    }

    private void obtenerServicioWeb(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Texto.setText("Resultado: " + response),
                error -> Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show()
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}