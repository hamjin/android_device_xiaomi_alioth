/*
 * Copyright (C) 2018 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.display;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreference;

import org.lineageos.settings.R;
import org.lineageos.settings.utils.FileUtils;

import vendor.xiaomi.hardware.displayfeature.V1_0.IDisplayFeature;

public class DcDimmingSettingsFragment extends PreferenceFragment implements
        OnPreferenceChangeListener {

    private SwitchPreference mDcDimmingPreference;
    private static final String DC_DIMMING_ENABLE_KEY = "dc_dimming_enable";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.dcdimming_settings);
        mDcDimmingPreference = (SwitchPreference) findPreference(DC_DIMMING_ENABLE_KEY);
        mDcDimmingPreference.setEnabled(true);
        mDcDimmingPreference.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (DC_DIMMING_ENABLE_KEY.equals(preference.getKey())) {
            IDisplayFeature mDisplayFeature = IDisplayFeature.getService();
            mDisplayFeature.setFeature(0, 20, (Boolean) newValue ? 1 : 0, 255);
        }
        return true;
    }
}
