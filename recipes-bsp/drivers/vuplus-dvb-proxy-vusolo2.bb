require vuplus-dvb-proxy.inc

COMPATIBLE_MACHINE = "^(vusolo2)$"

SRCDATE = "20170522"
SRCDATE_PR = "r0"

pkg_postinst_${PN}_append () {
	if [ ! -f $D/lib/modules/${KERNEL_VERSION}/extra/dvb-bcm.ko ]; then
		ln -s /lib/modules/${KERNEL_VERSION}/extra/dvb-bcm7356.ko $D/lib/modules/${KERNEL_VERSION}/extra/dvb-bcm.ko
	fi
}

SRC_URI[md5sum] = "4961784bac9f1c7b8a8bccac0646ff68"
SRC_URI[sha256sum] = "8562f7cfef3b017cb4becbef47e260ddf857f84af3d1a6021555dc2a9a736136"
