// ILedControlService.aidl
package com.globallogic.procamp.ledcontrolservice;

interface ILedControlService {
    /**
     * Leds
     */
    const int GREEN_LED_1 = 1;
    const int GREEN_LED_2 = 2;
    const int GREEN_LED_3 = 3;
    const int GREEN_LED_4 = 4;

    /**
     * Led state
     */
    const int STATE_OFF = 0;
    const int STATE_ON  = 1;

    /**
     * Control led state
     */
    void setLedState(int led, int state);
}
