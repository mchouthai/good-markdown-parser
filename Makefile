sourcefiles = $(wildcard *.java)
testers = $(wildcard *Test.java)
objects = $(patsubst %.java,%.class,$(sourcefiles))
testclasses = $(patsubst %.java,%,$(testers))

all: test

test: build 
	java -cp lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore $(testclasses) 

$(objects): $(sourcefiles)
	javac -cp lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:. -g $(sourcefiles) 

run: MarkdownParse.class
	java MarkdownParse 

build: $(objects) 

clean:
	-rm *.class

