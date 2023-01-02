package org.microg.tools.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public abstract class AbstractSettingsActivity extends AppCompatActivity {
    protected boolean showHomeAsUp = false;
    protected int preferencesResource = 0;
    protected int customBarLayout = 0;
    protected SwitchBar switchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (showHomeAsUp) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

        switchBar = (SwitchBar) findViewById(R.id.switch_bar);

        ViewGroup customBarContainer = (ViewGroup) findViewById(R.id.custom_bar);
        if (customBarLayout != 0) {
            customBarContainer.addView(getLayoutInflater().inflate(customBarLayout, customBarContainer, false));
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_wrapper, getFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected Fragment getFragment() {
        if (preferencesResource == 0) {
            throw new IllegalStateException("Neither preferencesResource given, nor overriden getFragment()");
        }
        ResourceSettingsFragment fragment = new ResourceSettingsFragment();
        Bundle b = new Bundle();
        b.putInt(ResourceSettingsFragment.EXTRA_PREFERENCE_RESOURCE, preferencesResource);
        fragment.setArguments(b);
        return fragment;
    }
}
