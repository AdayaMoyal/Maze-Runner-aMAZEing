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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference ref;
    EditText etPassword, etUsername;
    String username, password, mail;

    public interface UserCallback {
        void userFound(String mail);

        void userNotFound();
    }

    public void findUser(final UserCallback callback) {
        ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user.getName().equals(username) && user.getPassword().equals(password)) {
                        mail = user.getMail();
                        callback.userFound(mail);
                        return;
                    }
                }
                callback.userNotFound();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void loginUser(View v) {
        etUsername = findViewById(R.id.etUsername2);
        etPassword = findViewById(R.id.etPassword2);
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        findUser(new UserCallback() {
            @Override
            public void userFound(String mail) {
                firebaseAuth.signInWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                toGameScreen();
                            }
                        });
            }
            @Override
            public void userNotFound() {
                buildDialog();
            }
        });
    }

    public void buildDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("");
        builder.setMessage("This user does not exist");
        builder.setCancelable(true);
        builder.setNegativeButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void toGameScreen()
    {
        startActivity(new Intent(this, SettingBeforeGame.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }
}