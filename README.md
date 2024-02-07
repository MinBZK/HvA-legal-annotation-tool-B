# wetanalyse



## Running

Run `docker compose up -d` in root dir project


## Known issues

### Permission denied
In the event `/mvnw: Permission denied` please run `chmod +x mvnw` in the Backend dir.

### Backend container fails with error
```
[INFO] ------------------------------------------------------------------------
2024-02-07 12:26:13 [INFO] BUILD FAILURE
2024-02-07 12:26:13 [INFO] ------------------------------------------------------------------------
2024-02-07 12:26:13 [INFO] Total time:  3.562 s
2024-02-07 12:26:13 [INFO] Finished at: 2024-02-07T11:26:13Z
2024-02-07 12:26:13 [INFO] ------------------------------------------------------------------------
2024-02-07 12:26:13 [ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:3.0.5:run (default-cli) on project demo: Process terminated with exit code: 1 -> [Help 1]
```

to do...