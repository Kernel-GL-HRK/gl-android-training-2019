#define LOG_TAG "LedControl"

#include <vendor/gl/hardware/ledcontrol/1.0/types.h>
#include <log/log.h>
#include <stdio.h>

#include "LedControl.h"

namespace vendor {
namespace gl {
namespace hardware {
namespace ledcontrol {
namespace V1_0 {
namespace implementation {

LedControl::LedControl() {
    ALOGD("->LedControl::LedControl()");

    for (int i = 1; i <= 4; ++i) {
        char ledPath[64] = {};
        if (snprintf(ledPath, sizeof(ledPath), "/sys/class/leds/user_led%d/trigger", i) <= 0) {
            continue;
        }

        FILE* f = fopen(ledPath, "w");
        if (!f) {
            continue;
        }
        fprintf(f, "none\n");
        fclose(f);
    }
}

LedControl::~LedControl() {
    ALOGD("->LedControl::~LedControl()");
}

Return<Result> LedControl::setLedState(Led led, State state) {
    ALOGD("->LedControl::setLedState(%s, %s)", toString(led).c_str(), toString(state).c_str());
    char ledPath[64] = {};
    if (snprintf(ledPath, sizeof(ledPath), "/sys/class/leds/user_led%u/brightness", led) <= 0) {
        return Result::ERR_FAULT;
    }

    FILE* f = fopen(ledPath, "w");
    if (!f) {
        return Result::ERR_FAULT;
    }
    fprintf(f, "%u\n", state);
    fclose(f);

    return Result::ERR_OK;
}

}  // namespace implementation
}  // namespace V1_0
}  // namespace ledcontrol
}  // namespace hardware
}  // namespace gl
}  // namespace vendor
