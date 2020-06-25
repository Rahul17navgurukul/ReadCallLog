package com.rahulkashyap.calldetailsfromrecentcalllog.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rahulkashyap.calldetailsfromrecentcalllog.CustomPagerAdapter;
import com.rahulkashyap.calldetailsfromrecentcalllog.DialpadFragment;
import com.rahulkashyap.calldetailsfromrecentcalllog.R;
import com.rahulkashyap.calldetailsfromrecentcalllog.adapter.RecyclerViewAdapter;
import com.rahulkashyap.calldetailsfromrecentcalllog.util.ReadCallLog;
import com.rahulkashyap.calldetailsfromrecentcalllog.util.Utilities;
import com.rahulkashyap.calldetailsfromrecentcalllog.util.model.PendingCalls;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //    private Realm realm;
    private ReadCallLog readCallLog;
    PendingCalls pendingCalls;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @BindView(R.id.appbar)
    View mAppBar;
    @BindView(R.id.dialer_fragment)
    View mDialerView;
    //    // Layouts
    @BindView(R.id.root_view)
    CoordinatorLayout mMainLayout;
    //    // Buttons
    @BindView(R.id.fabRight)
    FloatingActionButton mRightButton;
    @BindView(R.id.left_button)
    FloatingActionButton mLeftButton;
    // Other
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    AppBarLayout mAppBarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTextTitle;

    //    FABCoordinator mFABCoordinator;
    FragmentPagerAdapter mAdapterViewPager;

    // Fragments
    DialpadFragment mDialpadFragment;
    BottomSheetBehavior mBottomSheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Rahul");

        expandDialerView();
        checkPermissions(null);


        mRightButton.setImageResource(R.drawable.ic_dialpad_black_24dp);
        mLeftButton.setImageResource(R.drawable.ic_search_black_24dp);

    }



    private void expandDialerView() {
        mBottomSheetBehavior = BottomSheetBehavior.from(mDialerView); // Set the bottom sheet behaviour
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN); // Hide the bottom sheet
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
//                updateButtons(i);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });


        mDialpadFragment = DialpadFragment.newInstance(true);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.dialer_fragment, mDialpadFragment)
                .commit();
    }

    private void syncFABAndFragment() {

        mAdapterViewPager = new CustomPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapterViewPager);

    }

    @OnClick(R.id.fabRight)
    public void onRightclick() {
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            BottomSheetBehavior.from(mDialerView).setState(BottomSheetBehavior.STATE_EXPANDED);

        } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            BottomSheetBehavior.from(mDialerView).setState(BottomSheetBehavior.STATE_HIDDEN);

        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }


    @OnClick(R.id.left_button)
    public void fabLeftClick() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>> ");
//        mFABCoordinator.performLeftClick();
    }


    public void expandDialer(boolean expand) {
        if (expand) {
            BottomSheetBehavior.from(mDialerView).setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            BottomSheetBehavior.from(mDialerView).setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }


    public void updateButtons(int bottomSheetState) {
        if (bottomSheetState == BottomSheetBehavior.STATE_HIDDEN || bottomSheetState == BottomSheetBehavior.STATE_COLLAPSED) {
            showButtons(true);
        } else {
            showButtons(false);
        }
    }

    /**
     * Animate action buttons
     *
     * @param isShow animate to visible/invisible
     */
    public void showButtons(boolean isShow) {
        View[] buttons = {mRightButton, mLeftButton};
        for (View v : buttons) {
            if (isShow && v.isEnabled()) {
                v.animate().scaleX(1).scaleY(1).setDuration(100).start();
                v.setClickable(true);
                v.setFocusable(true);
            } else {
                v.animate().scaleX(0).scaleY(0).setDuration(100).start();
                v.setClickable(false);
                v.setFocusable(false);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Utilities.DEFAULT_DIALER_RC) {
            checkPermissions(null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkPermissions(grantResults);
    }

    private void checkPermissions(@Nullable int[] grantResults) {
        if (
                (grantResults != null && Utilities.checkPermissionsGranted(grantResults)) ||
                        Utilities.checkPermissionsGranted(this, Utilities.MUST_HAVE_PERMISSIONS)) { //If granted
//            checkVersion();
            syncFABAndFragment();
        } else {
            Utilities.askForPermissions(this, Utilities.MUST_HAVE_PERMISSIONS);
        }
    }


}
