require linux-vuplus-3.9.6.inc

MACHINE_KERNEL_PR_append = "${PR_INC}.5"

SRC_URI += "\
	file://linux-sata_bcm.patch \
	"

COMPATIBLE_MACHINE = "^(vuduo)$"
