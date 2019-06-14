package com.game.kotvitz.war.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;

import com.game.kotvitz.war.GameMedia;
import com.game.kotvitz.war.LocaleManager;
import com.game.kotvitz.war.R;
import com.game.kotvitz.war.ScreenDesigner;

import java.util.Locale;
import java.util.Objects;

public class OptionsAcitvity extends AppCompatActivity {

    private Button backButton;
    private RadioButton radioPolish;
    private RadioButton radioEnglish;
    private Switch soundsSwitch;
    private AudioManager audio;
    private GameMedia gameMedia = new GameMedia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ScreenDesigner.INSTANCE.callFullScreenMode(getWindow());
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        soundsSwitch = findViewById(R.id.soundsSwitch);
        soundsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playSwitchSound(getBaseContext());
            }
        });
        checkSoundState();
        checkLanguage();
        backButton = findViewById(R.id.backButtonOptions);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(getBaseContext());
                onBackPressed();
            }
        });
        soundsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    audio.setStreamVolume(AudioManager.STREAM_MUSIC, audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
                } else {
                    audio.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        gameMedia.playClickSound(getBaseContext());
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.pl_lang:
                if (checked) {
                    Intent intent = new Intent(this, MainMenuActivity.class);
                    LocaleManager.INSTANCE.setLocale("pl", intent, getResources());
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK));
                }
                break;
            case R.id.eng_lang:
                if (checked) {
                    Intent intent = new Intent(this, MainMenuActivity.class);
                    LocaleManager.INSTANCE.setLocale("en-us", intent, getResources());
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

    public void checkSoundState() {
        int volume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (volume == 0)
            soundsSwitch.setChecked(false);
        else
            soundsSwitch.setChecked(true);
    }
}
