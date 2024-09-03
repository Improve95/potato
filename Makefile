up:
	docker-compose -f docker/docker-compose.yml up --force-recreate

stop:
	docker-compose stop && \
	docker-compose rm &&  \
	sudo rm -rf pgdata