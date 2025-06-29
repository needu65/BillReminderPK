package com.example.billreminderpk.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import com.example.billreminderpk.R;
import com.example.billreminderpk.utils.AppConstants;
import com.example.billreminderpk.utils.SharedPrefsHelper;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup languageRadioGroup;
    private RadioButton englishRadio, urduRadio;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize views
        languageRadioGroup = findViewById(R.id.languageRadioGroup);
        englishRadio = findViewById(R.id.englishRadio);
        urduRadio = findViewById(R.id.urduRadio);
        saveButton = findViewById(R.id.saveSettingsButton);

        // Load current language setting
        String currentLang = SharedPrefsHelper.getLanguage(this);
        if (currentLang.equals(AppConstants.LANG_URDU)) {
            urduRadio.setChecked(true);
        } else {
            englishRadio.setChecked(true);
        }

        // Save button click listener
        saveButton.setOnClickListener(v -> saveSettings());
    }

    private void saveSettings() {
        int selectedId = languageRadioGroup.getCheckedRadioButtonId();
        String lang = (selectedId == R.id.urduRadio) ? 
                     AppConstants.LANG_URDU : AppConstants.LANG_ENGLISH;

        SharedPrefsHelper.setLanguage(this, lang);
        
        // Restart app to apply language changes
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finishAffinity();
    }
}