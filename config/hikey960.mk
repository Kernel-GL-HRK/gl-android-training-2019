# Balaind 2019

PRODUCT_PACKAGES +=\
	vendor.bksnake.smartledcontroller@1.0-service.hikey960\
	LedControllerService\
	LedController\

DEVICE_MANIFEST_FILE += \
    vendor/bksnake/config/manifest.xml\

DEVICE_MATRIX_FILE += \
    vendor/bksnake/config/compatibility_matrix.xml\

BOARD_SEPOLICY_DIRS += \
    vendor/bksnake/sepolicy\

TARGET_FS_CONFIG_GEN +=\
	vendor/bksnake/config/config.fs
