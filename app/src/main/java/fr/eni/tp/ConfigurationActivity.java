package fr.eni.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import fr.eni.tp.databinding.ActivityConfigurationBinding;

public class ConfigurationActivity extends AppCompatActivity {
    ActivityConfigurationBinding layout;
    public final String DEFAULT_PRICE = "DEFAULT_PRICE";
    public final String SORT_PRICE = "SORT_PRICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ActivityConfigurationBinding.inflate(getLayoutInflater());
        setContentView(layout.getRoot());
        SharedPreferences configuration = getSharedPreferences("CONFIG", MODE_PRIVATE);
        layout.defaultPriceET.setText(configuration.getString(DEFAULT_PRICE, ""));
        layout.sortPriceSC.setChecked(configuration.getBoolean(SORT_PRICE, false));
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences configuration = getSharedPreferences("CONFIG", MODE_PRIVATE);
        SharedPreferences.Editor editor = configuration.edit();
        editor.putString(DEFAULT_PRICE, layout.defaultPriceET.getText().toString());
        editor.putBoolean(SORT_PRICE, layout.sortPriceSC.isChecked());
        editor.apply();
    }
}