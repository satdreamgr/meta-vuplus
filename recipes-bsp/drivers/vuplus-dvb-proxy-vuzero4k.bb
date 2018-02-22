require vuplus-dvb-proxy.inc

KV = "4.1.20"

SRCDATE = "20180220"
SRCDATE_PR = "r0"

pkg_postinst_${PN}_append () {
	if [ ! -f $D/lib/modules/${KERNEL_VERSION}/extra/dvb-bcm.ko ]; then
		ln -s /lib/modules/${KERNEL_VERSION}/extra/dvb-bcm72604.ko $D/lib/modules/${KERNEL_VERSION}/extra/dvb-bcm.ko
	fi
}

SRC_URI[md5sum] = "ae4d39a97ae22a71732694578d3e5dfe"
SRC_URI[sha256sum] = "aaf897b834e971c118d3552be2fe3abef52da584b7aca07a7a2a82c6282f8e7c"
