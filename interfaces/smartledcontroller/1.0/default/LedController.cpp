#define LOG_TAG "LedController"

#include <vendor/bksnake/smartledcontroller/1.0/types.h>
#include <log/log.h>

#include "LedController.h"

namespace vendor {
namespace bksnake {
namespace smartledcontroller {
namespace V1_0 {

LedController::LedController() {
	ALOGD("->LedController::LedController()");
}

Return<int32_t> LedController::initilizeLedController()
{
	ALOGD("->LedController::initilizeLedController");
	return 0;
}

Return<int32_t> LedController::terminateLedController() 
{
	ALOGD("->LedController::terminateLedController");
	return 0;
}


Return<int32_t> LedController::setLedState(Leds /*led*/, LedState /*state*/) 
{
	ALOGD("->LedController::setLedState");
    return 0;
}

} // namespace V1_0
} // namespace smartledcontroller
} // namespace bksnake
} // namespace vendor
