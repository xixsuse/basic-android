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

package com.kevalpatel2106.baseapplication.testUtils;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.kevalpatel2106.baseapplication.R;
import com.kevalpatel2106.baseapplication.TestActivity;
import com.kevalpatel2106.baseapplication.base.BaseFragment;

import org.junit.Assert;

/**
 * Created by Keval on 21-Jul-17.
 */

public class FragmentTestRule<F extends BaseFragment> extends ActivityTestRule<TestActivity> {

    private final Class<F> mFragmentClass;
    private F mFragment;

    public FragmentTestRule(final Class<F> fragmentClass) {
        super(TestActivity.class, true, false);
        mFragmentClass = fragmentClass;
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();

        try {
            //Instantiate and insert the fragment into the container layout
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            mFragment = mFragmentClass.newInstance();
            transaction.replace(R.id.container, mFragment);
            transaction.commit();
        } catch (InstantiationException | IllegalAccessException e) {
            Assert.fail(String.format("%s: Could not insert %s into TestActivity: %s",
                    getClass().getSimpleName(),
                    mFragmentClass.getSimpleName(),
                    e.getMessage()));
        }
    }

    public F getFragment() {
        return mFragment;
    }
}
