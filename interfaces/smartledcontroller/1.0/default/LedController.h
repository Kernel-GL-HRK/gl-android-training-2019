#ifndef vendor_bksnake_smartledcontroller_v1_0_LedController_H_
#define vendor_bksnake_smartledcontroller_v1_0_LedController_H_

#include <vendor/bksnake/smartledcontroller/1.0/ILedController.h>
#include <log/log.h>

namespace vendor {
namespace bksnake {
namespace smartledcontroller {
namespace V1_0 {

using namespace android::hardware;

class LedController: public ILedController {
public:
	LedController();
	Return<int32_t> initilizeLedController(void) override;
	Return<int32_t> terminateLedController(void) override;
	Return<int32_t> setLedState(Leds led, LedState state) override;
};

} // namespace V1_0
} // namespace smartledcontroller
} // namespace bksnake
} // namespace vendor

#endif  // vendor_bksnake_smartledcontroller_v1_0_LedController_H_
