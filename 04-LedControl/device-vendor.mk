PRODUCT_PACKAGES += \
    vendor.gl.hardware.ledcontrol@1.0-service \
    LedControlService \
    LedControl

DEVICE_MANIFEST_FILE += \
    vendor/gl/hardware/interfaces/manifest.xml

DEVICE_MATRIX_FILE += \
    vendor/gl/hardware/interfaces/compatibility_matrix.xml

BOARD_SEPOLICY_DIRS += \
    vendor/gl/sepolicy
