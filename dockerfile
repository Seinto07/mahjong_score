FROM openjdk:8

RUN apt -y update && apt -y install git
RUN apt -y install diffutils patch make doxygen

WORKDIR /mnt
