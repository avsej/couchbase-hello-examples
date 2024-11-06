PLATFORM := $(shell uname -s)
ARCH := $(shell uname -m)
PHP_VERSION := $(shell php -r "echo PHP_VERSION;")
PHP_MAJOR_MINOR := $(shell echo $(PHP_VERSION) | sed -E 's/^([0-9]+)\.([0-9]+)\..*/\1.\2/')
PHP_VERSION_FORMATTED := php$(PHP_MAJOR_MINOR)
PHP_THREADSAFETY := $(shell php -r 'echo (PHP_ZTS ? "zts" : "nts");')
PLATFORM_NORMALIZED := $(shell echo $(PLATFORM) | sed 's/Darwin/macos/' | tr '[:upper:]' '[:lower:]')

DIST := couchbase-4.2.4-$(PHP_VERSION_FORMATTED)-$(PHP_THREADSAFETY)-$(PLATFORM_NORMALIZED)-$(ARCH)
TARBALL := $(DIST).tgz
URL := https://packages.couchbase.com/clients/php/$(TARBALL)

run:
	curl -L -o $(TARBALL) $(URL)
	tar xf $(TARBALL)
	ln -sf $(DIST) couchbase
	php -d extension=$(shell pwd)/$(shell ls -1 couchbase/couchbase.*) app.php
