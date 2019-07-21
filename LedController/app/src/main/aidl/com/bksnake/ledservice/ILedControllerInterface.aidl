// ILedControllerInterface.aidl
package com.bksnake.ledservice;

// Declare any non-default types here with import statements

interface ILedControllerInterface {
    /**
    * Set led state
    * @(params) led - the led number. It can be from 1 to 4.
    * @(params) state - if 1 - turn on, 0 - turn off.
    */
    void setLedState(int led, int state);

    /**
    * Get led state
    * @(params) led - the led number. It can be from 1 to 4.
    */
    int getLedState(int led);
}
