package pro.com.my.mysimpleapp.models;

public class User {

    private String phone_number;
    private String pin;

    public User(String phone_number, String pin) {
        this.phone_number = phone_number;
        this.pin = pin;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
