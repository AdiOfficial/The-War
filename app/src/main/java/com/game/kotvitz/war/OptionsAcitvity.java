package com.game.kotvitz.war;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.Locale;
import java.util.Objects;

public class OptionsAcitvity extends AppCompatActivity {

    private Button backButton;
    private RadioButton radioPolish;
    private RadioButton radioEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ScreenDesigner.callFullScreenMode(getWindow());
        checkLanguage();
        backButton = findViewById(R.id.backButtonOptions);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.pl_lang:
                if (checked) {
                    Intent intent = new Intent(this, MainMenuActivity.class);
                    LocaleManager.setLocale("pl", intent, getResources());
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK));
                }
                break;
            case R.id.eng_lang:
                if (checked) {
                    Intent intent = new Intent(this, MainMenuActivity.class);
                    LocaleManager.setLocale("en-us", intent, getResources());
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK));
                }
                break;
        }
    }

    public void checkLanguage() {
        Locale currentLocale = getResources().getConfiguration().locale;
        radioEnglish = findViewById(R.id.eng_lang);
        radioPolish = findViewById(R.id.pl_lang);
        if (Objects.equals(currentLocale.getLanguage(), "en-us"))
            radioEnglish.setChecked(true);
        else if (Objects.equals(currentLocale.getLanguage(), "pl"))
            radioPolish.setChecked(true);
    }
}
