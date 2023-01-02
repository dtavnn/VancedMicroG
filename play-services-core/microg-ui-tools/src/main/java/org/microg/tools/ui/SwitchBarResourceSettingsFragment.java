/*
 * Copyright (C) 2017 microG Project Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.microg.tools.ui;

import android.os.Bundle;

import com.google.android.material.switchmaterial.SwitchMaterial;

public abstract class SwitchBarResourceSettingsFragment extends ResourceSettingsFragment implements SwitchBar.OnSwitchChangeListener {
    private final SwitchMaterial switchMaterial;
    private boolean listenerSetup = false;

    protected SwitchBarResourceSettingsFragment(SwitchMaterial switchMaterial) {
        this.switchMaterial = switchMaterial;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!listenerSetup) {
            listenerSetup = true;
        }
    }

    @Override
    public void onPause() {
        if (listenerSetup) {
            listenerSetup = false;
        }
        super.onPause();
    }

    @Override
    public void onSwitchChanged(SwitchMaterial switchView, boolean isChecked) {
        if (switchView == switchMaterial) {
            onSwitchBarChanged(isChecked);
        }
    }

    public abstract void onSwitchBarChanged(boolean isChecked);
}
