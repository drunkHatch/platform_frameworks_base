/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.android.systemui.utils;

import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.support.test.InstrumentationRegistry;

import com.android.systemui.SysuiTestCase;

public class ViewUtils {

    public static void attachView(View view) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
                LayoutParams.TYPE_SYSTEM_ALERT,
                0, PixelFormat.TRANSLUCENT);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> InstrumentationRegistry.getContext()
                .getSystemService(WindowManager.class).addView(view, lp));
        SysuiTestCase.waitForIdleSync(handler);
    }

    public static void detachView(View view) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> InstrumentationRegistry.getContext()
                .getSystemService(WindowManager.class).removeView(view));
        SysuiTestCase.waitForIdleSync(handler);
    }
}
