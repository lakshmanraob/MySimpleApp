package pro.com.my.mysimpleapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class OutLetAdapter extends RecyclerView.Adapter<OutLetAdapter.ViewHolderItem> {

    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        public ViewHolderItem(View view) {
            super(view);

        }
    }
}
