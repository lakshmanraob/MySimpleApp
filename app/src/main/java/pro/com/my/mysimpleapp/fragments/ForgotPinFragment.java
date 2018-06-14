package pro.com.my.mysimpleapp.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import pro.com.my.mysimpleapp.R;
import pro.com.my.mysimpleapp.models.event.BackBtnEvent;
import pro.com.my.mysimpleapp.utils.BaseFragment;

public class ForgotPinFragment extends BaseFragment implements View.OnClickListener {

    public static ForgotPinFragment forgotPinFragment = null;

    private ForgotPinView forgotPinView;

    @Override
    protected int getLayoutResource() {
        return R.layout.forgot_fragment;
    }

    public static ForgotPinFragment getInstance() {
        if (forgotPinFragment == null) {
            forgotPinFragment = new ForgotPinFragment();
        }
        return forgotPinFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        forgotPinView = new ForgotPinView(view);
        initViews();
    }

    private void initViews() {
        forgotPinView.backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgot_back:
                BackBtnEvent backBtnEvent = new BackBtnEvent();
                EventBus.getDefault().post(backBtnEvent);
                break;
        }
    }

    public class ForgotPinView {

        public final ImageView backBtn;

        public ForgotPinView(View view) {
            backBtn = view.findViewById(R.id.forgot_back);
        }
    }
}
