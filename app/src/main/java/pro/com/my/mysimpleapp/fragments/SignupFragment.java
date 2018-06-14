package pro.com.my.mysimpleapp.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

import pro.com.my.mysimpleapp.R;
import pro.com.my.mysimpleapp.models.event.BackBtnEvent;
import pro.com.my.mysimpleapp.utils.BaseFragment;

public class SignupFragment extends BaseFragment implements View.OnClickListener {

    public static SignupFragment signupFragment = null;

    private SignupView signupView;

    @Override
    protected int getLayoutResource() {
        return R.layout.signup_fragment;
    }

    public static SignupFragment getInstance() {
        if (signupFragment == null) {
            signupFragment = new SignupFragment();
        }
        return signupFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signupView = new SignupView(view);
        initViews();
    }

    private void initViews() {
        signupView.backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signip_back:
                BackBtnEvent event = new BackBtnEvent();
                EventBus.getDefault().post(event);
                break;
        }
    }

    public class SignupView {

        private final ImageView backBtn;

        public SignupView(View view) {
            backBtn = view.findViewById(R.id.signip_back);
        }
    }
}
