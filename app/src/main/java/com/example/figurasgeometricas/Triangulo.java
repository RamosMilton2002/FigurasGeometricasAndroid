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

public class Triangulo extends AppCompatActivity {
    TextView txtba ,txtal,Texto;
    Button btnTri,  btnre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_triangulo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtba = findViewById(R.id.txtBma);
        txtal = findViewById(R.id.txtBmen);
        Texto = findViewById(R.id.txtResul);
        btnTri = findViewById(R.id.btnTria);
        btnre = findViewById(R.id.btnre);

        btnTri.setOnClickListener(v -> {
            String base = txtba.getText().toString().trim();
            String altura = txtal.getText().toString().trim();

            if (!base.isEmpty() && !altura.isEmpty()) {
                obtenerServicioWeb("http://10.10.33.47:3001/triangulo/" + base + "/" + altura);
            } else {
                Toast.makeText(getApplicationContext(), "Por favor ingresa base y altura", Toast.LENGTH_SHORT).show();
            }
        });

        btnre.setOnClickListener(v -> {
            Intent intent = new Intent(Triangulo.this, Menu.class);
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