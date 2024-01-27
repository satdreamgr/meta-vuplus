SUMMARY = "vuplus-kexec"
PRIORITY = "required"
LICENSE = "CLOSED"

PV = "1.0"

FILESEXTRAPATHS_prepend := "${THISDIR}/vuplus-kexec-${MACHINE}:"

SRC_URI = " \
	file://kernel_auto.bin \
	file://STARTUP_cpio.bin \
"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/kernel_auto.bin ${D}${bindir}/kernel_auto.bin
	install -m 0755 ${WORKDIR}/STARTUP_cpio.bin ${D}${bindir}/STARTUP.cpio.gz
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN} = "already-stripped ldflags"

COMPATIBLE_MACHINE = "^(vuduo4k|vuduo4kse|vusolo4k|vuultimo4k|vuuno4k|vuuno4kse|vuzero4k)$"
