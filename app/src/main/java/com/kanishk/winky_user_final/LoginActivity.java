package com.kanishk.winky_user_final;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextMobile;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextMobile = findViewById(R.id.editTextMobile);
        btnContinue = findViewById(R.id.btnContinue);

        setStatusBarColor();
        getUserNumber();
        onContinueClick();
    }

    private void onContinueClick() {
        btnContinue.setOnClickListener(v -> {
            String number = editTextMobile.getText().toString().trim();

            if (number.isEmpty() || number.length() != 10) {
                utils.showToast(LoginActivity.this, "Please enter valid phone number");
            } else {
                Intent intent = new Intent(LoginActivity.this, OTPActivity.class);
                intent.putExtra("number", number);
                startActivity(intent);
            }
        });
    }

    private void getUserNumber() {
        editTextMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence number, int start, int before, int count) {
                if (number.length() == 10) {
                    btnContinue.setBackgroundTintList(ContextCompat.getColorStateList(LoginActivity.this, R.color.text_theme));
                } else {
                    btnContinue.setBackgroundTintList(ContextCompat.getColorStateList(LoginActivity.this, R.color.signin));
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void setStatusBarColor() {
        Window window = getWindow();
        int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;

        int color = (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES)
                ? ContextCompat.getColor(this, R.color.theme)
                : ContextCompat.getColor(this, android.R.color.white);

        window.setStatusBarColor(color);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = window.getDecorView();
            if (nightModeFlags != android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(0);
            }
        }
    }
}

