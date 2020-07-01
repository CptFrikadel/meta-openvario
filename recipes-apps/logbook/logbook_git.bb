# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Logbook application for Openvario"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=0835ade698e0bcf8506ecda2f7b4f302"

SECTION = "base/app"

DEPENDS = "ncurses"

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"

PR = "r0"

S = "${WORKDIR}/git"

SRCREV_pn-logbook = "${AUTOREV}"

SRC_URI = "git://github.com/CptFrikadel/igc-loginator.git;protocol=git "

addtask do_package_write_ipk after do_package after do_install

do_compile() {
	echo "Making.."
	echo '${WORKDIR}'
	cd ${WORKDIR}/git
	make clean
	make
}

do_install() {
	install -d ${D}/opt/bin
	install -m 0755 ${S}/logbook ${D}/opt/bin
}

PACKAGES = "${PN}"
INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
FILES_${PN} = "/opt/bin/logbook"
