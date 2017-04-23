FROM ubuntu:16.04 
 RUN apt-get update
 RUN apt-get install nano unzip
 RUN apt-get update && apt-get install -y git
# This is in accordance to : https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-get-on-ubuntu-16-04
 RUN apt-get update && \
	apt-get install -y openjdk-8-jdk && \
        sudo apt-get install groovy && \
	apt-get install -y ant && \
        apt-get install -y nano && \
        apt-get install -y unzip && \
        apt-get install -y git && \
        apt-get install -y sudo && \
        apt-get install -y wget && \
        apt-get autoclean && \
	apt-get clean;
	
# Fix certificate issues, found as of 
# https://bugs.launchpad.net/ubuntu/+source/ca-certificates-java/+bug/983302
 RUN apt-get install ca-certificates-java && \
	apt-get clean && \
	update-ca-certificates -f;

# Setup JAVA_HOME, this is useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

# Create Grapes volume
VOLUME "/home/groovy/.groovy/grapes"
WORKDIR /home/groovy

RUN set -o errexit -o nounset \
	&& echo "Testing Groovy installation" \
	&& groovy --version
