package com.example.figurasgeometricas;

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

public class Traprecio extends AppCompatActivity {
    TextView txtbam ,txtbme,txtal, Texto;
    Button btnTra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_circulo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtbam = findViewById(R.id.txtBma);
        txtbme = findViewById(R.id.txtBmen);
        txtal = findViewById(R.id.txtAl);
        btnTra = findViewById(R.id.btnTra);
        Texto = findViewById(R.id.txtResul);
        btnTra.setOnClickListener(v -> {
            String baseMayor = txtbam.getText().toString().trim();
            String baseMenor = txtbme.getText().toString().trim();
            String altura = txtal.getText().toString().trim();

            if (!baseMayor.isEmpty() && !baseMenor.isEmpty() && !altura.isEmpty()) {
                obtenerServicioWeb("http://192.168.68.104:3001/trapecio/" + baseMayor + "/" + baseMenor + "/" + altura);
            } else {
                Toast.makeText(getApplicationContext(), "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
            }
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
