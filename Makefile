up:
	@docker compose up -d

build:
	@docker compose build

down:
	@docker compose down

restart: down up

logs:
	@docker compose logs -f

clean:
	@docker compose down --volumes --remove-orphans
	@docker image prune -a -f
	@docker volume prune -f

