# svitesttool
SVI Railway Testing tool
# SVIRAIL Spring Boot conversion foundation

## Run

Requires Java 21 and Maven. From this directory run `mvn spring-boot:run`.

## APIs

- `POST /api/routes/validate` validates a legacy configuration text body.
- `POST /api/routes/runs` creates a safe simulated execution plan. It never controls WESTCAD or GSIM.

Example body:

```text
101A(201-OV1)-CHANGE=101C101-Menu.png,MN_RT_Set^D201_G.png#D201-Menu.png,EXIT_with_OV1^WAITSEC,10
```

## Next conversion step

Implement a `DesktopAutomationGateway` in a dedicated Windows runner service. The runner must accept an authenticated job, execute only approved test configurations, collect screenshots/timing, and return results. Do not add desktop-control code to the web server process.

