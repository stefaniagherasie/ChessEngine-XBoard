default:
	javac -d . *.java

jar:
	javac -d . *.java
	jar cvmf MANIFEST.MF main.jar *.class

run: jar
	java -jar main.jar

xboard: jar
	xboard -fd "/home/student/proiect_PA/src" -fcp "java -jar main.jar"

clean:
	rm *.class
