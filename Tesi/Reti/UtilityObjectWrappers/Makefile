# Makefile

.SUFFIXES: .java .class .idl .module

.java.class:
	vbjc $<

default: all

clean: 
	rm -f *.class *.tmp *.module *~

SRCS = \
	TimingUntypedObjectWrapper.java \
	TimingUntypedObjectWrapperFactory.java \
	TracingUntypedObjectWrapper.java \
	TracingUntypedObjectWrapperFactory.java \
	Init.java 

CLASSES = $(SRCS:.java=.class) 

#all:	$(CLASSES) 
all:
	vbjc *.java


