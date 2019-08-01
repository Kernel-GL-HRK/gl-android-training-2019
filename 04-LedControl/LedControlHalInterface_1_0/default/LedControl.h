#ifndef VENDOR_GL_HARDWARE_LEDCONTROL_V1_0_LEDCONTROL_H
#define VENDOR_GL_HARDWARE_LEDCONTROL_V1_0_LEDCONTROL_H

#include <vendor/gl/hardware/ledcontrol/1.0/ILedControl.h>

namespace vendor {
namespace gl {
namespace hardware {
namespace ledcontrol {
namespace V1_0 {
namespace implementation {

using ::android::hardware::Return;

/**
 * Led control implementation
 */
struct LedControl : public ILedControl {
    LedControl();

    Return<Result> setLedState(Led led, State state)  override;

private:
    ~LedControl();
};

}  // namespace implementation
}  // namespace V1_0
}  // namespace ledcontrol
}  // namespace hardware
}  // namespace gl
}  // namespace vendor

#endif  // VENDOR_GL_HARDWARE_LEDCONTROL_V1_0_LEDCONTROL_H
