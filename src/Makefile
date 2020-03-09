DIR = $(shell pwd)

default: jar

jar:
	find -name "*.java" | xargs -i javac -d . {}
	find -name "*.class" | xargs -i jar cvmf MANIFEST.MF main.jar {}

run: jar
	java -jar main.jar

xboard: jar
	xboard -fd "$(DIR)" -fcp "java -jar main.jar"

clean:
	rm -rf *.class
