FROM buildpack-deps:jessie-scm
RUN apt-get update && \
    apt-get install -y unzip zip && \
    apt-get autoremove -y && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /tmp/*
ENV SDKMAN_DIR=/opt/sdkman
RUN curl -s get.sdkman.io | bash
RUN set -x \
    && echo "sdkman_auto_answer=true" > $SDKMAN_DIR/etc/config \
    && echo "sdkman_auto_selfupdate=false" >> $SDKMAN_DIR/etc/config \
    && echo "sdkman_insecure_ssl=false" >> $SDKMAN_DIR/etc/config
RUN [ "/bin/bash", "-c", "source /opt/sdkman/bin/sdkman-init.sh && sdk install java 8u111"]
RUN [ "/bin/bash", "-c", "source /opt/sdkman/bin/sdkman-init.sh && sdk install groovy 2.4.10"]
RUN [ "/bin/bash", "-c", "source /opt/sdkman/bin/sdkman-init.sh && sdk install gradle 3.5"]
RUN [ "/bin/bash", "-c", "source /opt/sdkman/bin/sdkman-init.sh && sdk install grails 3.2.8"]
