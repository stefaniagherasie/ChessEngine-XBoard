DIR = $(shell pwd)

default: jar

jar:
	find -name "*.java" | xargs -i javac -d . {}
	jar cvmf $(DIR)/MANIFEST.MF main.jar ./main/*.class ./pieces/*.class ./commands/*.class ./auxiliary/*.class

run: jar
	java -jar main.jar

xboard: jar
	xboard -fd "$(DIR)" -fcp "java -jar main.jar"

clean:
	find -name "*.class" | xargs -i rm {}
