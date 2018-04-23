.PHONY: install-ant-junit install-ant all pack run clean test doc
install-ant:
	@if ! yum -q list intalled ant > /dev/null ; then\
		sudo yum install ant;\
	fi
install-ant-junit:
	@if ! yum -q list intalled ant > /dev/null ; then\
		sudo yum install ant-junit;\
	fi

all: install-ant
	ant all

pack:
	ant pack
run:
	ant compile
	ant run
test: install-ant-junit
	ant unit-tests
doc:
	doxygen Doxyfile
clean:
	ant clean
