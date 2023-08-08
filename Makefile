# Makefile

task12-1: main12.o task12-1.o		
	gcc -o task12-1 main12.o task12-1.o
main.o:	mian.c
	gcc -c main.c
task12-1.o:	task12-1.c
	gcc -c task12-1.c

task12-1.c:	task12-1.h
main.c:	task12-1.h