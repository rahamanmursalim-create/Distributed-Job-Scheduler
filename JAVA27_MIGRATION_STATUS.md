# Java 27 Migration and Feature-Gap Assessment

## 1. Execution Summary — 6 November 2026

The Java 27 and Spring Boot framework migration has been applied and successfully verified on JDK 27.

The project now uses Java 27 as its Maven compiler release and Spring Boot 4.1.2 with Jakarta EE APIs and springdoc OpenAPI 3.

### Build and Test Results

The following verification was completed using:

```bash
mvn clean test -U
```

| Verification | Result |
|---|---:|
| Maven modules | 16/16 successful |
| Tests discovered and executed | 63 |
| Tests passed | 63 |
| Test failures | 0 |
| Test errors | 0 |
| Skipped tests | 0 |
| Elasticsearch tests | 5/5 passed |
| Checkstyle violations | 0 in the modules reported |
| Build status | BUILD SUCCESS |
| Total build time | Approximately 3 minutes 27 seconds |

All five previously disabled Elasticsearch tests were enabled and executed successfully against a local Elasticsearch 7.17.27 instance.

An additional fix was applied to `Elasticsearch7DAOImpl.deleteByLastTime()`. The method now safely skips non-date index names instead of throwing a `NumberFormatException`.

**Verification scope:** These results establish that the project compiles and its discovered automated tests pass on the local JDK 27 environment. They do not constitute complete production certification or validation against every supported external database and distributed deployment configuration.

---

## 2. Project Scope and Migration Objectives

This project is based on the supplied Distributed Job Scheduler repository snapshot:

- Original project: Distributed Job Scheduler
- Original project version: 1.0.8
- Baseline Git commit: `f6516c60`
- Original build system: Maven multi-module
- Java source files in the assessed snapshot: 737

The primary objective was to migrate the existing application to Java 27 while preserving its existing modules, functionality, APIs, database migrations, UI assets, and tests wherever possible.

The migration also includes the framework-level changes necessary to support the newer Java and Spring ecosystem.

### Migration scope

- Upgrade the Maven compiler release to Java 27.
- Update the CI workflow to use the intended JDK version.
- Upgrade Lombok and configure annotation processing explicitly.
- Migrate Spring Boot to version 4.1.2.
- Update framework-facing APIs to Jakarta EE.
- Replace Springfox with springdoc OpenAPI 3.
- Update related persistence, validation, servlet, and framework integrations.
- Enable and execute the Elasticsearch integration tests.
- Resolve compatibility and compilation issues discovered during migration.
- Preserve existing scheduler functionality and module structure.

The migration is intended to retain the original system's capabilities rather than replace the scheduler with an unrelated implementation.

---

## 3. Java 27 Compatibility and Runtime Considerations

Java 27 is a non-LTS feature release. It should not be described as an LTS version.

As of 6 November 2026:

- Java 27 became generally available on 15 September 2026.
- Java 27 is a non-LTS release.
- Java 25 is the current LTS release.
- Java 27 provides the requested modern compiler and runtime target for this project.

For long-term production deployments, Java 25 LTS may be a lower-risk option because of its longer support lifecycle.

Java 27 is retained here as the explicit project target.

### Compiler and static-analysis compatibility

The Maven compiler is configured for Java 27.

The legacy Alibaba P3C rules are based on PMD 6.x. Therefore, the PMD analysis language target remains Java 17 until the legacy rules can be migrated and validated against a newer PMD version.

This is a static-analysis compatibility decision. It does not change the Java 27 compiler release.

### Orika runtime compatibility

The existing Orika 1.5.4 dependency uses reflective access to JDK internals.

The application therefore requires:

```text
--add-opens=java.base/java.lang=ALL-UNNAMED
```

The Maven test configuration and bundled server startup scripts include this JVM option.

Custom launch commands must also provide it until Orika is replaced or the reflective-access requirement is otherwise resolved.

---

## 4. Technology Stack

### Current project technology

| Area | Current implementation |
|---|---|
| Programming language | Java 27 |
| Build system | Apache Maven |
| Project structure | Multi-module Maven architecture |
| Framework | Spring Boot 4.1.2 |
| Enterprise APIs | Jakarta EE |
| API documentation | springdoc OpenAPI 3 |
| Persistence | Spring Data JPA |
| Database migrations | Flyway |
| Supported database migration targets | H2, MySQL, Oracle, PostgreSQL |
| Distributed coordination | Akka cluster |
| Messaging and coordination support | Redis and Akka |
| Worker-side persistence | H2 and LevelDB components |
| Static analysis | Checkstyle and PMD |
| Testing | Maven Surefire and existing automated tests |
| CI | GitHub Actions Maven workflow |

### Pre-migration baseline

The original repository snapshot used:

| Area | Original baseline |
|---|---|
| Java | Java 8 source/target |
| Spring Boot | 2.5.6 |
| Akka | 2.6.19 / Scala 2.13 |
| Gradle metadata | Gradle wrapper metadata 7.4.2 |
| Persistence | Spring Data JPA |
| Coordination | Redis and Akka |
| CI | JDK 8 and 11 |

The Gradle wrapper metadata is retained from the supplied repository. Maven remains the actual build system of record. The supplied archive did not contain a Gradle wrapper JAR or a root Gradle build entry point.

---

## 5. Existing Feature Audit

The following assessment describes functionality found in the original source snapshot. It distinguishes existing implementation from partial implementation and missing capabilities.

| Capability | Status | Assessment |
|---|---|---|
| Job submission and scheduling | Implemented | Admin job APIs and cron, fixed-rate, and one-time scheduling exist in `JobSchedulingService`. |
| Priority queues | Missing | No priority field or priority-based dispatch path was identified in the assessed Java source and entities. |
| Multiple distributed workers | Implemented | Worker registration, heartbeats, application worker lists, dispatch, and task masters exist. |
| Persistent job state | Implemented | JPA job and job-instance entities, repositories, and database migrations exist. |
| Exponential retry | Partial | Retry count and fixed retry intervals exist. Delay retry timing is linear (`interval * retryTimes`), not exponential. |
| General-purpose dead-letter queue | Partial | Delayed-task failure-topic support exists, but a general durable DLQ with inspection and requeue operations was not identified. |
| Worker crash recovery | Partial | Heartbeat/report-time checks and failover redispatch paths exist. Recovery guarantees still require failure-injection testing. |
| Job dependencies and DAG workflows | Incomplete | Workflow IDs and timing-wheel scaffolding exist, but `WorkflowSchedulingService.workflowJob()` is empty in the assessed snapshot. A complete dependency DAG execution engine is not established. |
| Concurrency control | Partial | Job/instance concurrency fields, slots, and task-master pools exist. Cross-node enforcement requires additional testing. |
| Metrics and monitoring | Partial | Admin charts, alerts, and statistics exist. Micrometer/Prometheus instrumentation and a scrape endpoint were not identified. |
| Multi-node coordination | Partial / existing foundation | Akka cluster communication, server membership, slot distribution, and cluster utilities exist. |
| Explicit leader election | Missing / not evidenced | No explicit leader-election algorithm or leader lease/fencing-token implementation was identified. Slot ownership alone does not establish leader election. |
| Kafka integration | Not implemented | Kafka is optional. Redis and Akka are already used; Kafka is not required for the initial migration. |

### Important distinction

The presence of distributed worker management and Akka cluster communication does not automatically mean that every distributed-systems guarantee is implemented.

In particular, leader election, global concurrency guarantees, split-brain prevention, and exactly-once execution must be designed and tested independently.

---

## 6. Spring Boot 4 and Jakarta Migration

The project has been migrated from Spring Boot 2.5.6 to Spring Boot 4.1.2.

The framework migration includes:

- Replacement of Springfox with springdoc OpenAPI 3.
- Migration of framework-facing persistence APIs.
- Migration of validation APIs.
- Migration of servlet APIs.
- Migration of related annotations and framework integrations.
- Updates to application configuration and annotation processing.

The migration compiles successfully on JDK 27, and the existing automated test suite passes.

However, passing the current automated tests does not establish that every third-party integration or external production environment has been validated.

---

## 7. Remaining Validation

The following validation work remains important before considering the application production-ready.

### Database integration

Run integration tests against supported versions of:

- MySQL
- PostgreSQL
- Oracle
- H2

Verify schema migrations, transaction behaviour, entity mappings, queries, and database compatibility.

### External service integration

Validate:

- Redis connectivity and failure handling.
- Elasticsearch 7 integration.
- Akka cluster membership and communication.
- Worker registration and heartbeat behaviour.
- API documentation and packaged server startup.
- Orika runtime compatibility.
- Hibernate JSON mappings.
- Flyway migration behaviour.

### Distributed failure testing

Test:

- Worker crashes during execution.
- Server crashes during job dispatch.
- Redis connectivity loss.
- Network partition and recovery.
- Duplicate job delivery.
- Concurrent execution attempts.
- Scheduler restart and recovery.
- Multi-node coordination failures.

These scenarios are not covered merely by a successful local Maven build.

---

## 8. Recommended Implementation Plan for Missing Features

The following are proposed engineering requirements. They are not claims that these features have already been implemented.

### 8.1 Priority-Based Job Scheduling

Introduce a validated priority field in job definitions and job instances.

Requirements:

- Persist job priority.
- Order due-job claims by priority and scheduled time.
- Support configurable priority levels.
- Prevent starvation using priority aging.
- Update API DTOs, admin UI, repositories, and database migrations.
- Add concurrency and dispatch-order tests.

### 8.2 Exponential Retry with Jitter

Extend the existing retry mechanism rather than replacing it without compatibility consideration.

Requirements:

- Persist execution attempt number.
- Support configurable base delay and maximum delay.
- Calculate capped exponential backoff.
- Add bounded random jitter.
- Persist the next-attempt timestamp.
- Preserve the existing fixed-interval retry policy as a backward-compatible option.
- Add deterministic unit tests for retry calculations.

### 8.3 General-Purpose Dead-Letter Queue

Implement a durable dead-letter mechanism for jobs that exceed their retry policy or reach a terminal failure state.

Requirements:

- Persist the original job payload.
- Store failure reason and execution history.
- Store attempt count and timestamps.
- Maintain an idempotency key.
- Move terminal failures into the DLQ transactionally with the final job state.
- Provide inspection, requeue, and discard operations.
- Maintain an audit trail.

A Redis-only list should not be treated as a durable general-purpose DLQ.

### 8.4 Job Dependencies and DAG Workflows

Complete the existing workflow foundation.

Requirements:

- Persist workflow nodes and edges.
- Validate dependency graphs and reject cycles.
- Implement topological readiness checks.
- Support parent-success conditions.
- Handle dependency failure propagation.
- Support cancellation and restart recovery.
- Add tests for linear workflows, diamond DAGs, cycles, retries, cancellation, and worker failures.

### 8.5 Distributed Concurrency Control

First define the scope of concurrency limits:

- Per job.
- Per application.
- Per worker.
- Global across the cluster.

Then enforce the selected limits using database-backed claims, leases, or another suitable coordination mechanism.

Global concurrency must not rely solely on process-local semaphores.

Use fencing tokens where required to prevent stale workers from acting on expired ownership.

### 8.6 Metrics and Monitoring

Introduce Actuator and Micrometer instrumentation.

Recommended metrics:

- Job submission count.
- Job execution count.
- Successful and failed job count.
- Retry count.
- Dispatch latency.
- Queue depth.
- Running jobs.
- Worker heartbeat status.
- Worker recovery count.
- Dead-letter queue depth.
- Scheduler execution latency.

Expose Prometheus scraping with bounded-cardinality metric labels.

### 8.7 Leader Election and Coordination

Implement explicit leader election only if the scheduler architecture requires a single active coordinator for a particular responsibility.

Recommended design:

- Database-backed leases or a suitable coordination service.
- Lease expiration and renewal.
- Monotonically increasing fencing tokens.
- Safe leadership transfer.
- Stale leader rejection.
- Split-brain prevention.
- Recovery and failover tests.

Akka cluster membership or a Redis lock without fencing should not be presented as proof of safe exclusive leadership.

---

## 9. Exactly-Once Execution Semantics

The system should not promise exactly-once execution for arbitrary background jobs.

Consider a worker that performs an external side effect and crashes before acknowledging completion. The scheduler cannot reliably determine whether that side effect occurred.

A subsequent retry may execute the same operation again.

The recommended default is:

**At-least-once delivery with idempotent job handlers.**

Recommended safeguards:

- Stable idempotency keys.
- Persistent execution attempts.
- Durable job-state transitions.
- Deduplication of externally visible writes.
- Transactional outbox/inbox patterns where database updates and message publishing must be coordinated.
- Explicit documentation of duplicate-execution guarantees.

Exactly-once business effects may be achievable for particular operations through idempotency and transactional design, but should not be claimed as a universal execution guarantee.

---

## 10. Development Roadmap

The recommended implementation order is:

| Phase | Work |
|---|---|
| Phase 1 | Complete Java 27 and framework migration validation |
| Phase 2 | Rename project identity and update repository documentation |
| Phase 3 | Implement priority-based scheduling |
| Phase 4 | Implement exponential retry with jitter |
| Phase 5 | Implement durable dead-letter queue |
| Phase 6 | Complete workflow dependency and DAG execution |
| Phase 7 | Strengthen distributed concurrency control |
| Phase 8 | Add metrics and Prometheus monitoring |
| Phase 9 | Implement explicit leader election if required |
| Phase 10 | Perform multi-node, failure-injection, and long-duration testing |

Each phase should preserve existing functionality and include appropriate automated tests.

Database changes should remain backward-compatible wherever practical.

---

## 11. Final Assessment

The Java 27 migration and Spring Boot framework migration have been applied and successfully verified through the current 16-module Maven build.

The project retains a substantial existing scheduler foundation, including job scheduling, worker management, persistent job state, retry mechanisms, cluster communication, and administrative features.

However, several advanced distributed-systems capabilities remain incomplete or unverified.

The next development stage should focus on strengthening the existing architecture rather than blindly adding technologies or duplicating existing functionality.

The key priorities are reliable priority-based dispatch, exponential retry, a durable dead-letter queue, complete workflow dependencies, distributed concurrency guarantees, observability, and safe coordination.

**Current verified status:** Java 27 compilation and the discovered automated test suite pass successfully.

**Production readiness:** Not yet established. External database integration, distributed failure testing, security review, performance testing, and long-duration stability testing remain necessary.