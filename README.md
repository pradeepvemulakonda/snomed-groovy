# snomed-groovy
Groovy script to load snomed data into a neo4j container

docker network create snomed-net
docker run --detach --name neo4j --net snomed-net neo4j
docker run  --name snomed-groovy --net snomed-net snomed
docker run -it --net snomed-net snomed bash

//— with pwd
docker run --env NEO4J_AUTH=neo4j/sample --name neo4j --net snomed-net neo4j


docker network create \
  --driver=bridge \
  --subnet=172.28.0.0/16 \
  --ip-range=172.28.5.0/30 \
  --gateway=172.28.5.254 \
  snomed-net


iptables -t nat -A DOCKER -p tcp --dport 7474 -j DNAT --to-destination 172.28.5.0:7474


docker run \
    --publish=7474:7474 --publish=7687:7687 \
    --volume=$HOME/neo4j/data:/data \
    --volume=$HOME/neo4j/logs:/logs \
    --detach --name neo4j --net snomed-net \
    --env NEO4J_AUTH=neo4j/sample \
    neo4j


build

docker build -t snomed .

grape install org.neo4j neo4j-jdbc 3.0.1
grape install org.neo4j neo4j-jdbc-driver 3.0.1     
grape install org.neo4j.driver neo4j-java-driver 1.1.2 


TO delete unused images


docker rm $(docker ps -qa)
docker rmi $(docker images -f "dangling=true" -q)

docker exec -it foo /bin/bash


