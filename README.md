# totalitea


# Running Docker Container
Run from /totaliteaShop directory\
`docker compose up -d --build`

Check for health:\
`docker compose ps`

Verify app is running:\
Expected --> status up\
`curl -fsS http://localhost:8081/actuator/health`
Protected endpoint WITHOUT creds (401)\
`curl -i http://localhost:8081/actuator/env`
Protected endpoint WITH creds (200)\
`curl -i -u admin:admin http://localhost:8081/actuator/env`
Expected --> connected via admin\
`docker exec -it totalitea-db psql -U admin -d totalitea -c '\conninfo'`

Test Mailhog:
Expected --> acess mailhog ui via link
http://localhost:8025

