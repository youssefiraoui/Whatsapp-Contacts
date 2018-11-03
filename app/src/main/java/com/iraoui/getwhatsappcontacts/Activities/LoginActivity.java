package com.iraoui.getwhatsappcontacts.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.iraoui.getwhatsappcontacts.Database.DatabaseHelper;
import com.iraoui.getwhatsappcontacts.R;
import com.iraoui.getwhatsappcontacts.entities.User;

/**
 * Created by IRAOUI on 02/11/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private Button btSignIn;
    private Button btSignUp;
    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btSignIn = findViewById(R.id.btSignIn);
        btSignUp = findViewById(R.id.btSignUp);

        edtEmail = findViewById(R.id.emailinput);
        edtPassword = findViewById(R.id.passwordinput);

        final DatabaseHelper dbHelper = new DatabaseHelper(this);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationDesInputs();
                dbHelper.addUser(new User(edtEmail.getText().toString(), edtPassword.getText().toString()));
                Toast.makeText(LoginActivity.this, "Added User", Toast.LENGTH_SHORT).show();
                viderInputs();
            }
        });
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationDesInputs();

                User user = dbHelper.queryUser(edtEmail.getText().toString(), edtPassword.getText().toString());

                if (user != null)
                {
                    Bundle mBundle = new Bundle();
                    mBundle.putString("user", user.getEmail());
                    Intent intent = new Intent(LoginActivity.this, CardListActivity.class);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    viderInputs();
                }
                else
                    {
                    Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    edtPassword.setText("");
                }
            }
        });


    }

    public void verificationDesInputs()
    {
        final String email = edtEmail.getText().toString();
        final String pwd = edtPassword.getText().toString().trim();
        if(email.isEmpty()){
            edtEmail.setError("Email is required");
            edtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("please enter a valid email");
            edtEmail.requestFocus();
            return;
        }

        if(pwd.isEmpty()){
            edtPassword.setError("password is required");
            edtPassword.requestFocus();
            return;
        }
        if(pwd.length()<6){
            edtPassword.setError("minimum legth of password should be 6 ");
            edtPassword.requestFocus();
            return;
        }
        //progressBar.setVisibility(View.VISIBLE);

    }

    protected  void viderInputs()
    {
        edtEmail.setText("");
        edtPassword.setText("");
    }

}
