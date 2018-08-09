package pro.com.my.mysimpleapp.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

import pro.com.my.mysimpleapp.OutLetAdapter;
import pro.com.my.mysimpleapp.R;
import pro.com.my.mysimpleapp.models.event.BackBtnEvent;
import pro.com.my.mysimpleapp.utils.BaseFragment;

public class OutletMapFragment extends BaseFragment implements View.OnClickListener {

    public static OutletMapFragment fragment;
    private OutletView outletView;
    private OutLetAdapter outLetAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.outlet_fragment;
    }

    public static OutletMapFragment getInstance() {
        if (fragment == null) {
            fragment = new OutletMapFragment();
        }
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        outletView = new OutletView(view);
        initViews();
    }

    private void initViews() {
        outletView.backBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.outlet_back:
                BackBtnEvent event = new BackBtnEvent();
                EventBus.getDefault().post(event);
                break;
        }
    }

    public class OutletView {

        private final ImageView backBtn;
        private final RecyclerView outLetListView;

        public OutletView(View view) {
            backBtn = view.findViewById(R.id.outlet_back);
            outLetListView = view.findViewById(R.id.outlet_list);
        }
    }
}
