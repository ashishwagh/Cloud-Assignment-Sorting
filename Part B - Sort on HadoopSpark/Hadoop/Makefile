JFLAGS = -g -classpath $(hadoop classpath) $*.java
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS)

CLASSES = \
        *.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
