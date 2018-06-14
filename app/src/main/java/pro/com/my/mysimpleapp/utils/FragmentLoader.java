package pro.com.my.mysimpleapp.utils;


/**
 * Created by labattula on 12/5/17.
 */

public interface FragmentLoader {

    String getTag();

    void addFragment(BaseFragment fragment);

    void replaceFragment(BaseFragment fragment);

    void popLastFragment();

}
