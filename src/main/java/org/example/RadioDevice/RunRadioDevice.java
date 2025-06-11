package org.example.RadioDevice;

public class RunRadioDevice {
    static RadioDevice radioDevice = new RadioDevice();
    public static void main(String[] args) {
        radioDevice.powerButton();
        radioDevice.rightButton();
        radioDevice.rightButton();
        radioDevice.rightButton();
        radioDevice.rightButton();
        radioDevice.leftButton();
        radioDevice.insertCard();
        radioDevice.rightButton();
        radioDevice.rightButton();
        radioDevice.rightButton();
        radioDevice.leftButton();
        radioDevice.removeCard();
        radioDevice.leftButton();
        radioDevice.rightButton();
        radioDevice.insertCard();
        radioDevice.powerButton();
        radioDevice.leftButton();
    }
}
