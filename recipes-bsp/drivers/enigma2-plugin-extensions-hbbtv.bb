DESCRIPTION = "VU+ HBBTV plugin"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
BRANCH = "vuplus_experimental"
S = "${WORKDIR}/git"
SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH} \
        file://move-youtube-menu-entry.patch \
"

inherit gitpkgv
SRCREV = "9e71a84b987082e466bc6c9c3b58255748bfe338"
PR = "r1"
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

RDEPENDS_${PN}  = "vuplus-opera-browser vuplus-hbbtv-dumpait"
FILES_${PN}     = "/usr/lib/enigma2/python/Plugins/Extensions/HbbTV/* \
        /usr/lib/enigma2/python/Components/Sources/* \
        /usr/lib/enigma2/python/Components/Converter/* \
"

do_install() {
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	install -m 0644 ${S}/lib/python/Plugins/Extensions/HbbTV/*.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	install -m 0644 ${S}/lib/python/Plugins/Extensions/HbbTV/keymap.xml ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV

	install -d ${D}/usr/lib/enigma2/python/Components/Sources
	install -m 0644 ${S}/lib/python/Components/Sources/HbbtvApplication.py ${D}/usr/lib/enigma2/python/Components/Sources
	install -d ${D}/usr/lib/enigma2/python/Components/Converter
	install -m 0644 ${S}/lib/python/Components/Converter/HbbtvApplicationInfo.py ${D}/usr/lib/enigma2/python/Components/Converter

	install -m 0755 -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV/locale
	cp -av ${S}/lib/python/Plugins/Extensions/HbbTV/locale/*.po ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV/locale

	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}

do_install_append() {
	rm -rf ${D}/usr/src
}
