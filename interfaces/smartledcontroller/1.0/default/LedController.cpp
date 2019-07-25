#define LOG_TAG "LedController_HAL"

#include <vendor/bksnake/smartledcontroller/1.0/types.h>
#include <android/log.h>
#include <fstream>
#include <sstream>

#include "LedController.h"

namespace vendor {
namespace bksnake {
namespace smartledcontroller {
namespace V1_0 {

LedController::LedController() {
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG, "-> LedController()");
}

Return<int32_t> LedController::initilizeLedController()
{
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG, "-> initilizeLedController()");

	writeLedTrigger(Leds::LED_GREEN_1, "none");
	writeLedBright(Leds::LED_GREEN_1, LedState::LED_STATE_OFF);

	writeLedTrigger(Leds::LED_GREEN_2, "none");
	writeLedBright(Leds::LED_GREEN_2, LedState::LED_STATE_OFF);

	writeLedTrigger(Leds::LED_GREEN_3, "none");
	writeLedBright(Leds::LED_GREEN_3, LedState::LED_STATE_OFF);

	writeLedTrigger(Leds::LED_GREEN_4, "none");
	writeLedBright(Leds::LED_GREEN_4, LedState::LED_STATE_OFF);
	return 0;
}

Return<int32_t> LedController::terminateLedController() 
{
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG, "-> terminateLedController()");

	writeLedTrigger(Leds::LED_GREEN_1, "heartbeat");
	writeLedBright(Leds::LED_GREEN_1, LedState::LED_STATE_ON);

	writeLedTrigger(Leds::LED_GREEN_2, "heartbeat");
	writeLedBright(Leds::LED_GREEN_2, LedState::LED_STATE_ON);

	writeLedTrigger(Leds::LED_GREEN_3, "heartbeat");
	writeLedBright(Leds::LED_GREEN_3, LedState::LED_STATE_ON);

	writeLedTrigger(Leds::LED_GREEN_4, "heartbeat");
	writeLedBright(Leds::LED_GREEN_4, LedState::LED_STATE_ON);

	return 0;
}


Return<int32_t> LedController::setLedState(Leds led, LedState state) 
{
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG, "-> setLedState()");

	writeLedBright(led, state);

    return 0;
}

bool LedController::writeLedTrigger(Leds led, const std::string& trigger)
{
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG, "-> writeLedTrigger()");
	
	std::fstream fs;
   	fs.open(ledPathGenerator(led, "/trigger"), std::fstream::out);
	
	if (!fs.is_open()) {
		__android_log_print(ANDROID_LOG_ERROR, LOG_TAG, "-> writeLedTrigger() file /trigger is not open");
		return false;
	}

   	fs << trigger;
   	fs.close();

	return true;
}

bool LedController::writeLedBright(Leds led, LedState state)
{
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG, "-> writeLedBright()");
	std::fstream fs;
	fs.open(ledPathGenerator(led, "/brightness"), std::fstream::out);

	if (!fs.is_open()) {
		__android_log_print(ANDROID_LOG_ERROR, LOG_TAG, "-> writeLedBright() file /brightness is not open");
		return false;
	}

   	fs << (int)state;
   	fs.close();

	return true;
}

std::string LedController::ledPathGenerator(Leds led, std::string&& file_name) {
	// /sys/class/leds/user_led%u/brightness
	std::stringstream path;
	path << "/sys/class/leds/user_led" << (int)led << file_name;
	return path.str();
}

} // namespace V1_0
} // namespace smartledcontroller
} // namespace bksnake
} // namespace vendor
