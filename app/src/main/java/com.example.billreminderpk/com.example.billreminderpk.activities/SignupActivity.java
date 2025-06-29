package com.example.billreminderpk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.billreminderpk.R;
import com.example.billreminderpk.utils.SharedPrefsHelper;

public class SignupActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
    private CheckBox humanVerificationCheckBox;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        humanVerificationCheckBox = findViewById(R.id.humanVerificationCheckBox);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(v -> attemptSignup());
    }

    private void attemptSignup() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (!humanVerificationCheckBox.isChecked()) {
            Toast.makeText(this, R.string.human_verification_required, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError(getString(R.string.error_email));
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordEditText.setError(getString(R.string.error_password));
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError(getString(R.string.error_password_mismatch));
            return;
        }

        // Save user credentials
        SharedPrefsHelper.registerUser(this, email, password);
        SharedPrefsHelper.setLoggedIn(this, true, email);
        
        Toast.makeText(this, R.string.signup_success, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}