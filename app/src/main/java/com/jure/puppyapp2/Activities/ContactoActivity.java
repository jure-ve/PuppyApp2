package com.jure.puppyapp2.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jure.puppyapp2.R;
import com.jure.puppyapp2.Classes.SendMail;

public class ContactoActivity extends AppCompatActivity {

    private EditText editNombre;
    private EditText editCorreo;
    private EditText editComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editComentario = (EditText) findViewById(R.id.editComentario);

        Button buttonEnviar = (Button) findViewById(R.id.buttonEnviar);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {

        String email = editCorreo.getText().toString().trim();
        String subject = "Comentario de: " + editNombre.getText().toString().trim();
        String message = editComentario.getText().toString().trim();

        SendMail sendmail = new SendMail(ContactoActivity.this, email, subject, message);

        sendmail.execute();
    }
}
