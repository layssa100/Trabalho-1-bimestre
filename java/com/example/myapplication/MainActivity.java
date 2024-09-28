package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edNome;
    private EditText edEmail;
    private EditText edIdade;
    private EditText edDisciplina;
    private Button btEnviar;
    private Button btLimpar;
    private TextView tvResultado;
    private TextView tvErro;
    private EditText edNota1;
    private EditText edNota2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edNome = findViewById(R.id.edNome);
        edEmail = findViewById(R.id.edEmail);
        edIdade = findViewById(R.id.edIdade);
        edDisciplina = findViewById(R.id.edDisciplina);
        btEnviar = findViewById(R.id.btEnviar);
        tvResultado = findViewById(R.id.tvResultado);
        tvErro = findViewById(R.id.tvErro);
        edNota1 = findViewById(R.id.edNota1);
        edNota2 = findViewById(R.id.edNota2);
        btLimpar = findViewById(R.id.btLimpar);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarInformacoes();;
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparInformacoes();
            }
        });
    }

    private void validarInformacoes(){

        String nome = edNome.getText().toString();
        String email = edEmail.getText().toString();
        String idade = edIdade.getText().toString();
        String disciplina = edDisciplina.getText().toString();
        String nota1 = edNota1.getText().toString();
        String nota2 = edNota2.getText().toString();

        if (nome.isEmpty()) {
            tvErro.setText("O campo de nome está vazio");
            tvErro.setVisibility(View.VISIBLE);
            return;
        } else if (email.isEmpty() || !email.contains("@")) {
            tvErro.setText("O e-mail é inválido");
            tvErro.setVisibility(View.VISIBLE);
            return;
        } else if (idade.isEmpty() || Integer.parseInt(idade) <= 0) {
            tvErro.setText("A idade deve ser número positivo");
            tvErro.setVisibility(View.VISIBLE);
            return;
        } else if (nota1.isEmpty() || nota2.isEmpty() || !isNotaValida(nota1) || !isNotaValida(nota2)) {
            tvErro.setText("Notas inválidas");
            tvErro.setVisibility(View.VISIBLE);
            return;
        }
        
        double media = (Double.parseDouble(nota1) + Double.parseDouble(nota2)) / 2;

        tvResultado.setText("Nome: " + nome +
                "\nE-mail: " + email +
                "\nIdade " + idade +
                "\nDisciplina: " + disciplina +
                "\nNota do 1º Bimestre: "+ nota1 +
                "\nNota do 2º Bimestre " + nota2 +
                "\nMédia das notas: " + media +
                "\n" + (media >= 6 ? "Parabéns! você está APROVADO :)" : "Reprovado"));

        tvResultado.setVisibility(View.VISIBLE);
        tvErro.setVisibility(View.GONE);
    }


    private boolean isNotaValida(String nota) {
        try {
            double valor = Double.parseDouble(nota);
            return valor >= 0 && valor <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void limparInformacoes() {
        edNome.setText("");
        edEmail.setText("");
        edIdade.setText("");
        edDisciplina.setText("");
        edNota1.setText("");
        edNota2.setText("");
        tvResultado.setVisibility(View.GONE);
        tvErro.setVisibility(View.GONE);
    }
}


