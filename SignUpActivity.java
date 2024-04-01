package com.example.appbyadaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    EditText etMail, etPassword, etName;

    public void createNewUser(View v) {
        etMail = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPassword);
        etName = findViewById(R.id.etUsername);
        Log.d("Tag", etMail.getText().toString());
        Log.d("Tag", etPassword.getText().toString());
        firebaseAuth.createUserWithEmailAndPassword(etMail.getText().toString(), etPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Log.d("Tag", "User created!");
                    addUserInfo();
                    toGameScreen();
                }
                else
                {
                    buildDialog(task.getException().getLocalizedMessage());
                }
            }
        });
    }
    public void buildDialog(String cause)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("");
        builder.setMessage(cause);
        builder.setCancelable(true);
        builder.setNegativeButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void addUserInfo(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        User user = new User(etMail.getText().toString(),etPassword.getText().toString(),etName.getText().toString());
        firebaseDatabase.getReference("Users").child(FirebaseAuth.getInstance().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Log.d("tag", "saved");
                }
            }
        });
    }
    public void toGameScreen(){
        startActivity(new Intent(this, SettingBeforeGame.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }
}