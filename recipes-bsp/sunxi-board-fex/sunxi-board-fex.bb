# Copyright (C) 2013 Tomas Novotny <novotny@rehivetech.com>
# Released under BSD-2-Clause or MIT license
DESCRIPTION = "Handler for Allwinner's FEX files"
LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=0ceb3372c9595f0a8067e55da801e4a1"
DEPENDS = "sunxi-tools-native"
PV = "1.0"
PR = "r0"

SRC_URI = "file://sys_config/a20/CB2-43RGB.fex \
	   file://sys_config/a20/CB2-7LVDS.fex"

# Increase PV with SRCREV change
#SRCREV = "5e63e3da42254d3c23eb6436a03ed1d32fb11e98"

S = "${WORKDIR}"
SUNXI_FEX_FILE_openvario-43rgb = "sys_config/a20/CB2-43RGB.fex"
SUNXI_FEX_FILE_openvario-7lvds = "sys_config/a20/CB2-7LVDS.fex"

SUNXI_FEX_BIN_IMAGE = "fex-${MACHINE}-${PV}-${PR}.bin"
SUNXI_FEX_BIN_IMAGE_SYMLINK = "fex-${MACHINE}.bin"
SUNXI_FEX_BIN_IMAGE_SYMLINK_SIMPLE = "fex.bin"

inherit deploy

do_compile() {
    fex2bin "${S}/${SUNXI_FEX_FILE}" > "${B}/${SUNXI_FEX_BIN_IMAGE}"
}

do_deploy() {
    install -m 0644 ${B}/${SUNXI_FEX_BIN_IMAGE} ${DEPLOYDIR}/
    cd ${DEPLOYDIR}
    ln -sf ${SUNXI_FEX_BIN_IMAGE} ${SUNXI_FEX_BIN_IMAGE_SYMLINK}
    ln -sf ${SUNXI_FEX_BIN_IMAGE} ${SUNXI_FEX_BIN_IMAGE_SYMLINK_SIMPLE}
}
addtask deploy before do_build after do_compile

PACKAGES = ""

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install[noexec] = "1"
do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_populate_sysroot[noexec] = "1"

COMPATIBLE_MACHINE = "(openvario-43rgb|openvario-7lvds)"