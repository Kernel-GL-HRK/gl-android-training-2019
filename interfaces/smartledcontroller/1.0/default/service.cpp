/*
 * Globallogic 2019
 */
#ifdef LOG_TAG
 #undef LOG_TAG
#endif
#define LOG_TAG "vendor::bksnake::smartledcontroller@1.0-hikey960-service"

#include <hidl/HidlSupport.h>
#include <hidl/HidlTransportSupport.h>
#include "LedController.h"

#include <log/log.h>

using ::android::hardware::configureRpcThreadpool;
using ::android::hardware::joinRpcThreadpool;
using ::android::OK;
using ::android::sp;

using namespace vendor::bksnake::smartledcontroller::V1_0;

int main(int /* argc */, char* /* argv */ []) {
    sp<ILedController> ledcontroller = new LedController();
    configureRpcThreadpool(1, true /* will join */);
    if (ledcontroller->registerAsService() != OK) {
        ALOGE("Could not register ledcontroller 1.0 service.");
        return 1;
    } else {
    	ALOGE("ILedController registred.");
    }
    joinRpcThreadpool();

    ALOGE("Service exited!");
    return 1;
}
