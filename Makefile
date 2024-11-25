build:
	@docker compose build

up:
	@docker compose up -d

down:
	@docker compose down

logs:
	@docker compose logs -f

clean:
	@docker compose down --volumes --remove-orphans
