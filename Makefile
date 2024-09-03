compose_up:
	docker-compose -f docker/docker-compose.yml up --force-recreate

compose_delete:
	docker-compose -f docker/docker-compose.yml stop && \
	docker-compose -f docker/docker-compose.yml rm && \
	sudo rm -rf docker/pgdata

potato_image_delete:
	docker image prune -a

potato_container_delete:
	docker container prune

potato_create:
	gradlew.bat build
	copy build/libs/potato-0.0.1.jar docker/potato
	docker build -t potato:jar docker/potato

potato_up:
	docker run -p 8080:8080 --rm --name potato potato:jar

potato_down:
	docker stop potato:jar
