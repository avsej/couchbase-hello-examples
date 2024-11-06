run: minimal
	./minimal

minimal: minimal.c
	gcc $(shell pkg-config --libs --cflags libcouchbase) -o minimal minimal.c
