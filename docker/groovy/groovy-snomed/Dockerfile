FROM groovy-sdkman:1.0
ENV JAVA_HOME "/opt/sdkman/candidates/java/current"
ENV PATH="/opt/sdkman/candidates/java/current/bin:/opt/sdkman/candidates/groovy/current/bin:/opt/sdkman/candidates/grails/current/bin:/opt/sdkman/candidates/gradle/current/bin:$PATH"
ADD grape-install.sh /
RUN chmod +x /grape-install.sh
RUN ["/bin/bash", "/grape-install.sh"]
WORKDIR /snomed/git/snomed-groovy
RUN git clone https://github.com/pradeepvemulakonda/snomed-groovy.git /snomed/git/snomed-groovy
CMD gradle run
