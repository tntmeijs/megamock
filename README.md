# MegaMock
MegaMock is the ultimate mocking solution for your projects!

New projects or microservices often rely upon external integrations and APIs. Waiting for external APIs to become
available is super annoying and could really ruin your team's productivity. 😥

MegaMock is a super easy to use application that can act as a mock interface for all your API calls. Simply run the
application, configure your mock data, and you are all set!

# Planned features
- Mock *any* endpoint
- Security mocking
- "Chaos monkey"
- Configurable endpoint response time
- Insights and statistics

# Installation
TL;DR:
1. `docker compose up -d --build`
2. Navigate to `<container url>:19980`
3. ???
4. Profit

MegaMock relies on a database. Since this database is only used to store mocks, the decision has been made to simply
include a MongoDB Docker container in the `docker-compose.yaml` file. Feel free to update the Docker files so it better
suits your project's needs. 😊

To run MegaMock, simply fire up a terminal instance and navigate to the project's root directory. Once in the root
directory, run `docker compose up -d --build` to start MegaMock and its accompanying MongoDB instance. By default, MegaMock will
be exposed on port `19980`. This means that you will be able to interact with the application by navigating to
`<container url>:19980`.

Happy mocking!
