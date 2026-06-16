CREATE TABLE IF NOT EXISTS monitored_target (
    id              VARCHAR(36) PRIMARY KEY,
    type            VARCHAR(30)  NOT NULL,
    display_name    VARCHAR(255) NOT NULL,
    path            VARCHAR(1024) NOT NULL UNIQUE,
    display_order   INTEGER      NOT NULL,
    enabled         BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_monitored_target_display_order
    ON monitored_target(display_order);

CREATE TABLE IF NOT EXISTS scan_run (
    id              VARCHAR(36) PRIMARY KEY,
    started_at      TIMESTAMP    NOT NULL,
    finished_at     TIMESTAMP    NULL,
    status          VARCHAR(20)  NOT NULL,
    error_message   VARCHAR(2000) NULL
);

CREATE INDEX IF NOT EXISTS idx_scan_run_started_at
    ON scan_run(started_at);

CREATE TABLE IF NOT EXISTS target_snapshot (
    id                  VARCHAR(36) PRIMARY KEY,
    target_id           VARCHAR(36) NOT NULL,
    scan_run_id         VARCHAR(36) NOT NULL,
    scanned_at          TIMESTAMP   NOT NULL,

    total_size_bytes    BIGINT      NOT NULL,
    file_count          BIGINT      NULL,
    directory_count     BIGINT      NULL,
    message_count       BIGINT      NULL,

    CONSTRAINT fk_snapshot_target
        FOREIGN KEY (target_id) REFERENCES monitored_target(id),

    CONSTRAINT fk_snapshot_scan_run
        FOREIGN KEY (scan_run_id) REFERENCES scan_run(id)
);

CREATE INDEX IF NOT EXISTS idx_target_snapshot_target_id
    ON target_snapshot(target_id);

CREATE INDEX IF NOT EXISTS idx_target_snapshot_scanned_at
    ON target_snapshot(scanned_at);
