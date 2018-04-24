PACK_NAME=xkobel02_xhavli47_xkarpi06_xvavro05
PACK=pack
TMP_DIR := $(shell mktemp -d)

.PHONY: install

install:
	sudo apt-get install ant
	sudo apt-get install default-jre
	sudo apt-get install default-jdk
	sudo apt-get install
	cd /usr/local/lib && wget https://www.antlr.org/download/antlr-4.7.1-complete.jar
	cd /usr/local/lib && export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"
	cd /usr/local/lib && alias antlr4='java -jar /usr/local/lib/antlr-4.7.1-complete.jar'
	cd /usr/local/lib && alias grun='java org.antlr.v4.gui.TestRig'
	sudo apt-get install junit

clean:
	cd ../doxygen && rm -rf *
	cd ../out && rm -rf *
	cd ../ && rm -rf pack

compile:
	cd .. && ant all

doc: doxygen

doxygen:
	cd ../doxygen && rm -rf *
	cd ../ && ant doc

# Go to top && run ant
all: clean compile


# Pack project
pack: clean doxygen
	rm -rf ../$(PACK)
	rm -rf ../$(TMP_DIR)/my_pack
	cd ../ && mkdir -p $(PACK)
	mkdir -p $(TMP_DIR)/my_pack
	cd $(TMP_DIR)/my_pack && mkdir -p doc install repo
	cd ../ && cp -r . $(TMP_DIR)/my_pack/repo
	mv $(TMP_DIR)/my_pack/repo/doxygen/* $(TMP_DIR)/my_pack/doc
	cp -r $(TMP_DIR)/my_pack/repo/install/* $(TMP_DIR)/my_pack/install
	cd $(TMP_DIR)/my_pack && zip -r $(PACK_NAME).zip .
	cp $(TMP_DIR)/my_pack/$(PACK_NAME).zip ../pack/$(PACK_NAME).zip
	rm -rf $(TMP_DIR)/my_pack

run: all
	cd ../out/artifacts/JavaFXApp && java -classpath antlr-4.7.1-complete.jar:Calculator.jar app.Main
test: all
	cd ../out/artifacts/JavaFXApp && java -classpath antlr-4.7.1-complete.jar:Calculator.jar:junit-4.12.jar:hamcrest-core-1.3.jar tests.Main 2>/dev/null
doc:
	doxygen Doxyfile

help:
	echo "Před spuštěním a překladem programu je třeba nainstalovat javu, ant a aplikaci přeložit. Instalaci můžete provést za pomocí cíle install, nebo manuálně."

