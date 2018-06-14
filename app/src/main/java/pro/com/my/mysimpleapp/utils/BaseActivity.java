package pro.com.my.mysimpleapp.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.Subscribe;

import pro.com.my.mysimpleapp.R;
import pro.com.my.mysimpleapp.models.event.BackBtnEvent;


/**
 * Created by labattula on 12/5/17.
 */

public class BaseActivity extends AppCompatActivity implements FragmentLoader {

    private FrameLayout progressBar;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    final Handler handler = new Handler();
    private static final long WAIT_TIME = 4000;

    @LayoutRes
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());

        UiUtils.setSystemBarTheme(this, true,
                getResources().getColor(R.color.colorPrimary));

        progressBar = (FrameLayout) findViewById(R.id.progressBar);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        hideKeyboard();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Tag to identify a class by its name
     *
     * @return tag
     */
    @Override
    public String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void addFragment(BaseFragment fragment) {
        progressBar.setVisibility(View.GONE);
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void replaceFragment(BaseFragment fragment) {
        progressBar.setVisibility(View.GONE);
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void popLastFragment() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * Util method to dismiss keyboard if visible
     */
    protected void hideKeyboard() {
        final View view = this.getCurrentFocus();
        if (view != null) {
            final InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showProgressBar(boolean isShown) {
        if (isShown) {
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(200);
            progressBar.setAnimation(inAnimation);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            outAnimation = new AlphaAnimation(1f, 0f);
            outAnimation.setDuration(200);
            progressBar.setAnimation(outAnimation);
            progressBar.setVisibility(View.GONE);
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            showProgressBar(true);
        }
    };

    public void startProgressBarHandler() {
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, WAIT_TIME);
    }

    public void stopProgressBarHandler() {
        handler.removeCallbacks(runnable);
        showProgressBar(false);
    }

    @Override
    public void onBackPressed() {
        popFragments();
    }

    private void popFragments() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

}
