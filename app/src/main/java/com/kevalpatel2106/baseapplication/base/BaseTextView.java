/*
 * Copyright 2017 Keval Patel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kevalpatel2106.baseapplication.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import java.util.Locale;

/**
 * Created by Keval Patel on 04/03/17.
 * This base class is to extend the functionality of {@link AppCompatTextView}. Use this class instead
 * of {@link android.widget.TextView} through out the application.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public final class BaseTextView extends AppCompatTextView {
    public BaseTextView(Context context) {
        super(context);
        init(context);
    }

    public BaseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //set type face
        AssetManager am = context.getApplicationContext().getAssets();
        setTypeface(Typeface.createFromAsset(am, getFont(getTypeface())));
    }

    /**
     * Get the font based on the text style.
     *
     * @return font file name.
     */
    String getFont(Typeface typeface) {
        switch (typeface != null ? typeface.getStyle() : Typeface.NORMAL) {
            case Typeface.BOLD_ITALIC:
                return String.format(Locale.US, "fonts/%s", "OpenSans-BoldItalic.ttf");
            case Typeface.ITALIC:
                return String.format(Locale.US, "fonts/%s", "OpenSans-Italic.ttf");
            case Typeface.BOLD:
                return String.format(Locale.US, "fonts/%s", "OpenSans-Bold.ttf");
            case Typeface.NORMAL:
            default:
                return String.format(Locale.US, "fonts/%s", "OpenSans-Regular.ttf");
        }
    }

    /**
     * @return Trimmed text.
     */
    @NonNull
    public String getTrimmedText() {
        return getText().toString().trim();
    }
}
