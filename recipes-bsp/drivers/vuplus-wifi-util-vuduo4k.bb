require vuplus-wifi-util.inc

COMPATIBLE_MACHINE = "^(vuduo4k)$"

PV="18.1"
SRCDATE = "20210428"
SRCDATE_PR = "r0"
PR_append = ".0"

SRC_URI[md5sum] = "3b13453ac6fa0d0812b747836ea50b7d"
SRC_URI[sha256sum] = "ff109e28acc4df39fcd9173d0816c899b2de6a875992d94f9097853764fd46af"

inherit update-rc.d

INITSCRIPT_PARAMS = "start 60 S ."
INITSCRIPT_NAME = "vuplus-wifi-init.sh"

do_install_append() {
	install -d ${D}${INIT_D_DIR}
	install -m 0755 ${S}/${INITSCRIPT_NAME} ${D}${INIT_D_DIR}/${INITSCRIPT_NAME}
}
