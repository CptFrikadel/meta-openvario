# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Logbook application for Openvario"
LICENSE = "MIT"

SECTION = "base/app"

DEPENDS = "\
	ncurses \
"
SRC_URI = "git://github.com/CptFrikadel/igc-loginator.git;protocol=git \
"

do_compile() {
	echo "Making.."
	echo '${WORKDIR}'
	cd $WORKDIR/git
	make
}

do_install() {
	install -d ${D}/opt/bin
	install -m 0755 ${S}/logbook ${D}/opt/bin
}
