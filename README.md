# SVAGARAIL Spring Boot conversion foundation

## Run

Requires Java 21 and Maven. From this directory run `mvn spring-boot:run`.

## APIs

- `POST /api/routes/validate` validates a legacy configuration text body.
- `POST /api/routes/runs` validates the submitted routes, runs them through the selected gateway, and persists every result.
- `GET /api/routes/runs` returns the latest 100 persisted results.
- `/` provides a lightweight run and results dashboard.

Example body:

```text
101A(201-OV1)-CHANGE=101C101-Menu.png,MN_RT_Set^D201_G.png#D201-Menu.png,EXIT_with_OV1^WAITSEC,10
```

## Database

The default local database is H2 at `./data/svirail`. For an operational deployment, replace it with PostgreSQL and add authentication/authorization before exposing the service.

## WESTCAD / GSIM desktop bridge

The default gateway is simulation. To enable actual desktop execution, start on the Windows machine that has SikuliX, WESTCAD, GSIM, and the approved test environment:

```text
mvn spring-boot:run -Dspring-boot.run.profiles=desktop-runner -Drunner.enabled=true -Drunner.command="C:\\approved-runner\\run-route.cmd"
```

The configured command receives `SVI_ROUTE_NAME`, `SVI_OPERATION`, and `SVI_LEGACY_CONFIG_LINE` environment variables. `run-route.cmd` must translate them to the existing SikuliX script arguments, write evidence/screenshots, and return `0` only for a successful test. The application waits up to `runner.timeout-seconds` (default 600). Do not enable this profile outside an approved, isolated test desktop.
