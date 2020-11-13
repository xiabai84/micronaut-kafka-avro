#!/usr/bin/env bash

cat $1 | xargs -Ix \
kafka-topics --bootstrap-server "${BOOTSTRAP_SERVER:-localhost:9092}" \
--create --replication-factor 1 --partitions 1 \
--config retention.ms=-1 --config cleanup.policy=compact \
--topic x