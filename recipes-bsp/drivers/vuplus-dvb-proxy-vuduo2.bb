require vuplus-dvb-proxy.inc

COMPATIBLE_MACHINE = "^(vuduo2)$"

SRCDATE = "20170522"
SRCDATE_PR = "r0"

pkg_postinst_${PN}_append () {
	if [ ! -f $D/lib/modules/${KERNEL_VERSION}/extra/dvb-bcm.ko ]; then
		ln -s /lib/modules/${KERNEL_VERSION}/extra/dvb-bcm7424.ko $D/lib/modules/${KERNEL_VERSION}/extra/dvb-bcm.ko
	fi
}

SRC_URI[md5sum] = "d24185d9fa9afe71668a0d102f380f35"
SRC_URI[sha256sum] = "f241c01e06001c709e54f619171120eecf99bb015d2427aad9a5a0e5e6150eda"
