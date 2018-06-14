package pro.com.my.mysimpleapp.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import pro.com.my.mysimpleapp.R;

/**
 * Created by labattula on 12/5/17.
 */

public abstract class BaseFragment extends Fragment {

    private String TAG = BaseFragment.class.getCanonicalName();

    @LayoutRes
    protected abstract int getLayoutResource();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
        getActivity().setTitle(getString(getTitle()));

    }

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroyView();

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResum: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        hideKeyboard();
        Log.i(TAG, "onPause: ");
    }

    public boolean isBackPressedHandled() {
        return false;
    }

    public void post(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    @StringRes
    protected int getTitle() {
        return R.string.app_name;
    }

    /**
     * Util method to dismiss keyboard if visible
     */
    protected void hideKeyboard() {
        final View view = getView();
        if (view != null) {
            final InputMethodManager imm =
                    (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void showToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    /**
     * Checking the permission for the Action
     *
     * @param permissionString
     * @return
     */
    public boolean isPermissionGiven(String[] permissionString) {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(getActivity(), permissionString[0]);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    /**
     * Requesting the permission for the action
     *
     * @param permissionString
     * @param permissionCode
     */
    public void requestStoragePermissions(String[] permissionString, int permissionCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissionString[0])) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
//            showToast("Storage Permission is mandatory");
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(), permissionString, permissionCode);

    }

}
