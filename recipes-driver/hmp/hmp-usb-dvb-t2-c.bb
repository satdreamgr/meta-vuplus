SUMMARY = "media tree drivers for hmp-usb-dvb-t2-c"
HOMEPAGE = "http://linuxtv.org/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"

COMPATIBLE_MACHINE = "^vu"

SRC_URI[md5sum] = "8073a7921a6f1e154083d71bc2ef5b46"
SRC_URI[sha256sum] = "b0a32dc6efb5cb62c0572938de10cd3d718d94191fe4648a9722b7fdddcad2d5"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

DEPENDS = "virtual/kernel module-init-tools perl"
KERNEL_VERSION = "${@base_read_file('${STAGING_KERNEL_BUILDDIR}/kernel-abiversion')}"

do_fetch[depends] += "virtual/kernel:do_package_write_ipk"

inherit module

RPROVIDES_${PN} = "kernel-module-dvb-usb-dvbsky-${KERNEL_VERSION} \
                   kernel-module-dvbsky-m88rs6000-${KERNEL_VERSION} \
                   kernel-module-dvbsky-m88ds3103-${KERNEL_VERSION} \
                   kernel-module-sit2fe-${KERNEL_VERSION} \
                   kernel-module-dvb-usb-v2-media-tree-${KERNEL_VERSION} \
                    "
SRCDATE = "141106"
SRCDATE_VER = "${SRCDATE}"

PV = "V${SRCDATE_VER}"
PR = "r0"

PACKAGES = "${PN} \
            ${PN}-dev \
            ${PN}-dbg \
            "
FILES_${PN} = " \
	/lib/firmware/dvb-fe-ds300x.fw \
	/lib/firmware/dvb-fe-ds3103.fw \
	/lib/firmware/dvb-fe-rs6000.fw \
	/lib/modules/*/kernel/drivers/media/dvb-frontends/dvbsky_m88ds3103.ko \
	/lib/modules/*/kernel/drivers/media/dvb-frontends/dvbsky_m88rs6000.ko \
	/lib/modules/*/kernel/drivers/media/dvb-frontends/sit2fe.ko \
	/lib/modules/*/kernel/drivers/media/usb/dvb-usb-v2/dvb_usb_v2_media_tree.ko \
	/lib/modules/*/kernel/drivers/media/usb/dvb-usb-v2/dvb-usb-dvbsky.ko \
	"

FILES_${PN}-dev = " \
	/lib/modules/*/modules.* \
	/lib/firmware/* \
       "

SRC_URI = "http://dvbsky.net/download/linux/media_build-bst-14-${SRCDATE}.tar.gz \
           file://fix-strip.patch;patch=1 \
           file://rename_dvb-usb-v2.patch \
           file://vu_adapter_adjustment.patch \
           file://vu_keep_compatibility.patch \
           file://vu_no_v4l_firmwares_install.patch \
           file://defconfig \
           file://sit2_op.o_${SRCDATE} \
"

S = "${WORKDIR}/media_build-bst-14"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR} OUTDIR=${STAGING_KERNEL_BUILDDIR}"

do_configure_prepend() {
	CUR=`pwd`
	cp ${WORKDIR}/sit2_op.o_${SRCDATE} ${S}/v4l/sit2_op.o
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake DIR=${STAGING_KERNEL_BUILDDIR} allyesconfig
	cd $CUR
}

do_configure() {
	install -m 0644 ${WORKDIR}/defconfig ${S}/v4l/.config
}

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake KDIR="${STAGING_KERNEL_DIR}" DIR="${STAGING_KERNEL_DIR}" ${MAKE_TARGETS}
	mipsel-oe-linux-strip --strip-debug ${S}/v4l/sit2fe.ko
}

do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake DIR="${STAGING_KERNEL_BUILDDIR}" DESTDIR="${D}" install
	install -m 0644 ${S}/v4l/sit2fe.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/media/dvb-frontends/
	install -d 0644 ${D}${base_libdir}/firmware
	install -m 0644 ${S}/dvbsky-firmware/*.fw ${D}${base_libdir}/firmware/
}

pkg_postinst_${PN} () {
	depmod -a
}
