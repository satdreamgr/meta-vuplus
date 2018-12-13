require vuplus-dvb-proxy.inc

KV = "4.1.20"

SRCDATE = "20181204"
SRCDATE_PR = "r0"

pkg_postinst_${PN}_append () {
	if [ ! -f $D/lib/modules/${KERNEL_VERSION}/extra/dvb-bcm.ko ]; then
		ln -s /lib/modules/${KERNEL_VERSION}/extra/dvb-bcm7252sse.ko $D/lib/modules/${KERNEL_VERSION}/extra/dvb-bcm.ko
	fi
}

SRC_URI[md5sum] = "15c4a79a217c0a5c77267d232626ccb3"
SRC_URI[sha256sum] = "fadeefb886392ba9c2b5e7d2c6c096b1590a6e9b7d49b4a0c1393bc70003cc49"
