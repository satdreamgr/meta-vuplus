require vuplus-dvb-proxy.inc

COMPATIBLE_MACHINE = "^(vusolose)$"

SRCDATE = "20170522"
SRCDATE_PR = "r0"

pkg_postinst_${PN}_append () {
	if [ ! -f $D/lib/modules/${KERNEL_VERSION}/extra/dvb-bcm.ko ]; then
		ln -s /lib/modules/${KERNEL_VERSION}/extra/dvb-bcm7241.ko $D/lib/modules/${KERNEL_VERSION}/extra/dvb-bcm.ko
	fi
}

SRC_URI[md5sum] = "bf87932893aff25d4194e12b49824285"
SRC_URI[sha256sum] = "8b24c9172ff5ad69202859a4030272d0e8fa5dcca6635ae0d200ac50c76f8226"
