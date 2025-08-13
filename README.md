# Totalitea :coffee: :tea:

An online shop designed from scratch, for a fictional coffee shop.

## Description :page_with_curl:

Totalitea is a small chain of coffee shops that want to reach a wider market through an online presence. This website has a front end and back end. 
The front end is composed of a sign-in page, product catalogue and search engine, shopping cart, order page and order confirmed page. You will be able to see information about the brand/supplier, the type, the amount (grams), cost and a description of the product;

To use this web app, you will need to sign in, select products to add to your shopping cart, a place an order.

> [!WARNING]
> No real bank information is required to use this app. Do not add real bank information.


## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing program, ex. Windows 10
To run this web app, you will need:


### Installing ⬇️

* To download this programme, git close this project, the pull your clone onto your choice of JDK, DB software and 
* You will need to modify 

### Executing program :computer:

# Running Docker Container
Run from /totaliteaShop directory\
`docker compose up -d --build`

Check for health:\
`docker compose ps`

Verify app is running:\
Expected --> status up\
`curl -fsS http://localhost:8081/actuator/health`
Expected --> connected via admin\
`docker exec -it totalitea-db psql -U admin -d totalitea -c '\conninfo'`

Test Mailhog:
Expected --> acess mailhog ui via link
http://localhost:8025

## Help :raising_hand:

Any advise for common problems or issues.
```
command to run if program contains helper info
```

## Authors :pen:

Contributors names and contact info:

Emily Phillips [github](https://github.com/emilyphillips02)\
Christabelle Jacques [github](https://github.com/christabellejacques)\
Abbeygayle Potts [github](https://github.com/AbbeygayleP)

## Version History :books:

* 0.nothing yet

## License 

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details

## Acknowledgments
[Emoji Cheat Sheet](https://gist.github.com/roachhd/1f029bd4b50b8a524f3c) by roachhd\
[Basic writing and formatting syntax](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#footnotes) by GitHub Docs\
[Licensing a repository](https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/licensing-a-repository) by GitHub Docs


\
\
> [!NOTE]
> Useful information that users should know, even when skimming content.

> [!TIP]
> Helpful advice for doing things better or more easily.

> [!IMPORTANT]
> Key information users need to know to achieve their goal.

> [!WARNING]
> Urgent info that needs immediate user attention to avoid problems.

> [!CAUTION]
> Advises about risks or negative outcomes of certain actions.
