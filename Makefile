up_compose:
	docker-compose -f docker/docker-compose.yml up --force-recreate

delete_compose:
	docker-compose stop && \
	docker-compose rm &&  \
	sudo rm -rf pgdata

potato_image_delete:
	docker image prune -a

potato_container_delete:
	docker container prune

potato_create:
	./gradlew clean build
	cp build/libs/potato-0.0.1.jar docker/potato
	docker build -t potato:jar docker/potato

potato_up:
	docker run -p 8080:8080 --rm --name potato potato:jar
