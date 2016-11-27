cd micro-parent-mvn/; mvn clean install; cd -;
cd micro-discovery-server/; mvn clean package docker:build; cd -
cd micro-config-server/; mvn clean package docker:build; cd -
cd micro-parameter-service/; mvn clean package docker:build; cd -
