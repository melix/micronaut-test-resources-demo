## Micronaut test resources demo

Run `./gradlew -t run` or `./gradlew test` to see a demo of test resources which will:

- checkout and build the Gradle plugin for test resources demo purposes only (version 3.5 of the plugin will integrate support natively)
- spawn a test container for MySQL when running the app or launching tests using Testcontainers
- run a test which makes use of a custom test resource written in `src/testResources`

### A bit more context

If you run `./gradlew test` or `./gradlew run`, a test container will be spawned, and shutdown at the end of the build.

If you run with `./gradlew -t test`, then the container will only be stopped at the end of the whole build session, which means that you can make changes to your code and reuse containers without paying the startup price.

Last but not least, you can also start the test resources server separately, making it possible to reuse it in independent builds:

- `./gradlew startTestResourcesService` : starts the test resources server
- `./gradlew run`: uses the test resources
- `./gradlew test`: uses the same test resources
- `./gradlew stopTestResourcesService`: stops the test resources server

Please read the [snapshot documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/snapshot/#test-resources) for all the possible configuration options.
