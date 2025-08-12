# totalitea


# Running Docker Container
Run from /totaliteaShop directory\
`docker compose up -d --build`

Check for health:\
`docker compose ps`

Verify app is running:\
Expected --> status up\
`curl -fsS http://localhost:8080/actuator/health`
Expected --> connected via admin\
`docker exec -it totalitea-db psql -U admin -d totalitea -c '\conninfo'`

Test Mailhog:
Expected --> acess mailhog ui via link
http://localhost:8025

