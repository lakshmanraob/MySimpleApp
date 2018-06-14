package pro.com.my.mysimpleapp.models.event;

public class ScreenChangeEvent {
    int operatoionIndex;
    int fragmentIndex;

    public ScreenChangeEvent() {
    }

    public ScreenChangeEvent(int operatoionIndex, int fragmentIndex) {
        this.operatoionIndex = operatoionIndex;
        this.fragmentIndex = fragmentIndex;
    }

    public int getOperatoionIndex() {
        return operatoionIndex;
    }

    public void setOperatoionIndex(int operatoionIndex) {
        this.operatoionIndex = operatoionIndex;
    }

    public int getFragmentIndex() {
        return fragmentIndex;
    }

    public void setFragmentIndex(int fragmentIndex) {
        this.fragmentIndex = fragmentIndex;
    }
}
