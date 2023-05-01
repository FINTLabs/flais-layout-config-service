# FLAIS Layout Config Service

FLAIS Layout Config Service stores configuration for FLAIS Podium Layout services in a micro frontend environment.

The service uses a PG database to store the configuration.

One instance of the service only holds configuration for one layout service. It is single tenant.

## Run it locally

1. Start PG database either by running `docker-compose up` or start it in your IDE, e.g. IntelliJ.
2. Start the application either by running `./gradlew bootRun` or start it in your IDE, e.g. IntelliJ. You need to run
   the `local` profile.
3. Import `insomnia.json` into Insomnia and create a configuration.


