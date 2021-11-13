### OpenTelemetry Examples
This repository includes examples for **manual** instrumentation in [OpenTelemetry](https://opentelemetry.io/) using [Lightstep](https://lightstep.com/).

1. Simple Trace
2. Error Handling
3. WithSpan annotation
4. Spans in Reactive Streams
5. Context Propagation with external service
6. Context Propagation in SAGA based services (using Kafka)

### Auto Instrumentation
1. Build the code so that you have a .jar file with you. 
2. Get the latest version of the distro and run it with your code
    ```shell
    curl -L -O https://github.com/lightstep/otel-launcher-java/releases/latest/download/lightstep-opentelemetry-javaagent.jar
    ```
3. Run it with your target/*.jar
    ```shell
    java -javaagent:lightstep-opentelemetry-javaagent.jar -jar target/*.jar
    ```

If you have multiple environments for java and use [jenv](https://github.com/jenv/jenv) change the env to the runtime on your shell before running the above command.
```shell
Exception in thread "main" java.lang.UnsupportedClassVersionError: com/chaks/opentelemetry/examples/ExamplesApplication has been compiled by a more recent version of the Java Runtime (class file version 55.0), this version of the Java Runtime only recognizes class file versions up to 52.0
```

The output for the `simple/okay` controller would look like this.
![image info](./docs/images/auto-instrument-view.png)
