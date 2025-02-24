/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.server.config;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.utils.Utils;

import static org.apache.kafka.common.config.ConfigDef.Importance.MEDIUM;
import static org.apache.kafka.common.config.ConfigDef.Range.atLeast;
import static org.apache.kafka.common.config.ConfigDef.Range.between;
import static org.apache.kafka.common.config.ConfigDef.Type.BOOLEAN;
import static org.apache.kafka.common.config.ConfigDef.Type.INT;
import static org.apache.kafka.common.config.ConfigDef.Type.SHORT;

public class ShareGroupConfig {
    /** Share Group Configurations **/

    // Internal configuration used by integration and system tests.
    public static final String SHARE_GROUP_ENABLE_CONFIG = "group.share.enable";
    public static final boolean SHARE_GROUP_ENABLE_DEFAULT = false;
    public static final String SHARE_GROUP_ENABLE_DOC = "Enable share groups on the broker.";

    public static final String SHARE_GROUP_PARTITION_MAX_RECORD_LOCKS_CONFIG = "group.share.partition.max.record.locks";
    public static final int SHARE_GROUP_PARTITION_MAX_RECORD_LOCKS_DEFAULT = 200;
    public static final String SHARE_GROUP_PARTITION_MAX_RECORD_LOCKS_DOC = "Share-group record lock limit per share-partition.";

    public static final String SHARE_GROUP_DELIVERY_COUNT_LIMIT_CONFIG = "group.share.delivery.count.limit";
    public static final int SHARE_GROUP_DELIVERY_COUNT_LIMIT_DEFAULT = 5;
    public static final String SHARE_GROUP_DELIVERY_COUNT_LIMIT_DOC = "The maximum number of delivery attempts for a record delivered to a share group.";

    public static final String SHARE_GROUP_MAX_GROUPS_CONFIG = "group.share.max.groups";
    public static final short SHARE_GROUP_MAX_GROUPS_DEFAULT = 10;
    public static final String SHARE_GROUP_MAX_GROUPS_DOC = "The maximum number of share groups.";

    public static final String SHARE_GROUP_MAX_SIZE_CONFIG = "group.share.max.size";
    public static final short SHARE_GROUP_MAX_SIZE_DEFAULT = 200;
    public static final String SHARE_GROUP_MAX_SIZE_DOC = "The maximum number of consumers that a single share group can accommodate.";

    public static final String SHARE_GROUP_SESSION_TIMEOUT_MS_CONFIG = "group.share.session.timeout.ms";
    public static final int SHARE_GROUP_SESSION_TIMEOUT_MS_DEFAULT = 45000;
    public static final String SHARE_GROUP_SESSION_TIMEOUT_MS_DOC = "The timeout to detect client failures when using the share group protocol.";

    public static final String SHARE_GROUP_MIN_SESSION_TIMEOUT_MS_CONFIG = "group.share.min.session.timeout.ms";
    public static final int SHARE_GROUP_MIN_SESSION_TIMEOUT_MS_DEFAULT = 45000;
    public static final String SHARE_GROUP_MIN_SESSION_TIMEOUT_MS_DOC = "The minimum allowed session timeout for share group members.";

    public static final String SHARE_GROUP_MAX_SESSION_TIMEOUT_MS_CONFIG = "group.share.max.session.timeout.ms";
    public static final int SHARE_GROUP_MAX_SESSION_TIMEOUT_MS_DEFAULT = 60000;
    public static final String SHARE_GROUP_MAX_SESSION_TIMEOUT_MS_DOC = "The maximum allowed session timeout for share group members.";

    public static final String SHARE_GROUP_HEARTBEAT_INTERVAL_MS_CONFIG = "group.share.heartbeat.interval.ms";
    public static final int SHARE_GROUP_HEARTBEAT_INTERVAL_MS_DEFAULT = 5000;
    public static final String SHARE_GROUP_HEARTBEAT_INTERVAL_MS_DOC = "The heartbeat interval given to the members of a share group.";

    public static final String SHARE_GROUP_MIN_HEARTBEAT_INTERVAL_MS_CONFIG = "group.share.min.heartbeat.interval.ms";
    public static final int SHARE_GROUP_MIN_HEARTBEAT_INTERVAL_MS_DEFAULT = 5000;
    public static final String SHARE_GROUP_MIN_HEARTBEAT_INTERVAL_MS_DOC = "The minimum heartbeat interval for share group members.";

    public static final String SHARE_GROUP_MAX_HEARTBEAT_INTERVAL_MS_CONFIG = "group.share.max.heartbeat.interval.ms";
    public static final int SHARE_GROUP_MAX_HEARTBEAT_INTERVAL_MS_DEFAULT = 15000;
    public static final String SHARE_GROUP_MAX_HEARTBEAT_INTERVAL_MS_DOC = "The maximum heartbeat interval for share group members.";

    public static final String SHARE_GROUP_RECORD_LOCK_DURATION_MS_CONFIG = "group.share.record.lock.duration.ms";
    public static final int SHARE_GROUP_RECORD_LOCK_DURATION_MS_DEFAULT = 30000;
    public static final String SHARE_GROUP_RECORD_LOCK_DURATION_MS_DOC = "The record acquisition lock duration in milliseconds for share groups.";

    public static final String SHARE_GROUP_MIN_RECORD_LOCK_DURATION_MS_CONFIG = "group.share.min.record.lock.duration.ms";
    public static final int SHARE_GROUP_MIN_RECORD_LOCK_DURATION_MS_DEFAULT = 15000;
    public static final String SHARE_GROUP_MIN_RECORD_LOCK_DURATION_MS_DOC = "The record acquisition lock minimum duration in milliseconds for share groups.";

    public static final String SHARE_GROUP_MAX_RECORD_LOCK_DURATION_MS_CONFIG = "group.share.max.record.lock.duration.ms";
    public static final int SHARE_GROUP_MAX_RECORD_LOCK_DURATION_MS_DEFAULT = 60000;
    public static final String SHARE_GROUP_MAX_RECORD_LOCK_DURATION_MS_DOC = "The record acquisition lock maximum duration in milliseconds for share groups.";

    public static final ConfigDef CONFIG_DEF =  new ConfigDef()
            .defineInternal(SHARE_GROUP_ENABLE_CONFIG, BOOLEAN, SHARE_GROUP_ENABLE_DEFAULT, null, MEDIUM, SHARE_GROUP_ENABLE_DOC)
            .define(SHARE_GROUP_DELIVERY_COUNT_LIMIT_CONFIG, INT, SHARE_GROUP_DELIVERY_COUNT_LIMIT_DEFAULT, between(2, 10), MEDIUM, SHARE_GROUP_DELIVERY_COUNT_LIMIT_DOC)
            .define(SHARE_GROUP_RECORD_LOCK_DURATION_MS_CONFIG, INT, SHARE_GROUP_RECORD_LOCK_DURATION_MS_DEFAULT, between(1000, 60000), MEDIUM, SHARE_GROUP_RECORD_LOCK_DURATION_MS_DOC)
            .define(SHARE_GROUP_MIN_RECORD_LOCK_DURATION_MS_CONFIG, INT, SHARE_GROUP_MIN_RECORD_LOCK_DURATION_MS_DEFAULT, between(1000, 30000), MEDIUM, SHARE_GROUP_MIN_RECORD_LOCK_DURATION_MS_DOC)
            .define(SHARE_GROUP_MAX_RECORD_LOCK_DURATION_MS_CONFIG, INT, SHARE_GROUP_MAX_RECORD_LOCK_DURATION_MS_DEFAULT, between(30000, 3600000), MEDIUM, SHARE_GROUP_MAX_RECORD_LOCK_DURATION_MS_DOC)
            .define(SHARE_GROUP_PARTITION_MAX_RECORD_LOCKS_CONFIG, INT, SHARE_GROUP_PARTITION_MAX_RECORD_LOCKS_DEFAULT, between(100, 10000), MEDIUM, SHARE_GROUP_PARTITION_MAX_RECORD_LOCKS_DOC)
            .define(SHARE_GROUP_SESSION_TIMEOUT_MS_CONFIG, INT, SHARE_GROUP_SESSION_TIMEOUT_MS_DEFAULT, atLeast(1), MEDIUM, SHARE_GROUP_SESSION_TIMEOUT_MS_DOC)
            .define(SHARE_GROUP_MIN_SESSION_TIMEOUT_MS_CONFIG, INT, SHARE_GROUP_MIN_SESSION_TIMEOUT_MS_DEFAULT, atLeast(1), MEDIUM, SHARE_GROUP_MIN_SESSION_TIMEOUT_MS_DOC)
            .define(SHARE_GROUP_MAX_SESSION_TIMEOUT_MS_CONFIG, INT, SHARE_GROUP_MAX_SESSION_TIMEOUT_MS_DEFAULT, atLeast(1), MEDIUM, SHARE_GROUP_MAX_SESSION_TIMEOUT_MS_DOC)
            .define(SHARE_GROUP_HEARTBEAT_INTERVAL_MS_CONFIG, INT, SHARE_GROUP_HEARTBEAT_INTERVAL_MS_DEFAULT, atLeast(1), MEDIUM, SHARE_GROUP_HEARTBEAT_INTERVAL_MS_DOC)
            .define(SHARE_GROUP_MIN_HEARTBEAT_INTERVAL_MS_CONFIG, INT, SHARE_GROUP_MIN_HEARTBEAT_INTERVAL_MS_DEFAULT, atLeast(1), MEDIUM, SHARE_GROUP_MIN_HEARTBEAT_INTERVAL_MS_DOC)
            .define(SHARE_GROUP_MAX_HEARTBEAT_INTERVAL_MS_CONFIG, INT, SHARE_GROUP_MAX_HEARTBEAT_INTERVAL_MS_DEFAULT, atLeast(1), MEDIUM, SHARE_GROUP_MAX_HEARTBEAT_INTERVAL_MS_DOC)
            .define(SHARE_GROUP_MAX_GROUPS_CONFIG, SHORT, SHARE_GROUP_MAX_GROUPS_DEFAULT, between(1, 100), MEDIUM, SHARE_GROUP_MAX_GROUPS_DOC)
            .define(SHARE_GROUP_MAX_SIZE_CONFIG, SHORT, SHARE_GROUP_MAX_SIZE_DEFAULT, between(10, 1000), MEDIUM, SHARE_GROUP_MAX_SIZE_DOC);

    private final boolean isShareGroupEnabled;
    private final int shareGroupPartitionMaxRecordLocks;
    private final int shareGroupDeliveryCountLimit;
    private final short shareGroupMaxGroups;
    private final short shareGroupMaxSize;
    private final int shareGroupSessionTimeoutMs;
    private final int shareGroupMinSessionTimeoutMs;
    private final int shareGroupMaxSessionTimeoutMs;
    private final int shareGroupHeartbeatIntervalMs;
    private final int shareGroupMinHeartbeatIntervalMs;
    private final int shareGroupMaxHeartbeatIntervalMs;
    private final int shareGroupRecordLockDurationMs;
    private final int shareGroupMaxRecordLockDurationMs;
    private final int shareGroupMinRecordLockDurationMs;

    public ShareGroupConfig(AbstractConfig config) {
        isShareGroupEnabled = config.getBoolean(ShareGroupConfig.SHARE_GROUP_ENABLE_CONFIG);
        shareGroupPartitionMaxRecordLocks = config.getInt(ShareGroupConfig.SHARE_GROUP_PARTITION_MAX_RECORD_LOCKS_CONFIG);
        shareGroupDeliveryCountLimit = config.getInt(ShareGroupConfig.SHARE_GROUP_DELIVERY_COUNT_LIMIT_CONFIG);
        shareGroupMaxGroups = config.getShort(ShareGroupConfig.SHARE_GROUP_MAX_GROUPS_CONFIG);
        shareGroupSessionTimeoutMs = config.getInt(ShareGroupConfig.SHARE_GROUP_SESSION_TIMEOUT_MS_CONFIG);
        shareGroupMinSessionTimeoutMs = config.getInt(ShareGroupConfig.SHARE_GROUP_MIN_SESSION_TIMEOUT_MS_CONFIG);
        shareGroupMaxSessionTimeoutMs = config.getInt(ShareGroupConfig.SHARE_GROUP_MAX_SESSION_TIMEOUT_MS_CONFIG);
        shareGroupHeartbeatIntervalMs = config.getInt(ShareGroupConfig.SHARE_GROUP_HEARTBEAT_INTERVAL_MS_CONFIG);
        shareGroupMinHeartbeatIntervalMs = config.getInt(ShareGroupConfig.SHARE_GROUP_MIN_HEARTBEAT_INTERVAL_MS_CONFIG);
        shareGroupMaxHeartbeatIntervalMs = config.getInt(ShareGroupConfig.SHARE_GROUP_MAX_HEARTBEAT_INTERVAL_MS_CONFIG);
        shareGroupRecordLockDurationMs = config.getInt(ShareGroupConfig.SHARE_GROUP_RECORD_LOCK_DURATION_MS_CONFIG);
        shareGroupMaxRecordLockDurationMs = config.getInt(ShareGroupConfig.SHARE_GROUP_MAX_RECORD_LOCK_DURATION_MS_CONFIG);
        shareGroupMinRecordLockDurationMs = config.getInt(ShareGroupConfig.SHARE_GROUP_MIN_RECORD_LOCK_DURATION_MS_CONFIG);
        shareGroupMaxSize = config.getShort(ShareGroupConfig.SHARE_GROUP_MAX_SIZE_CONFIG);
        validate();
    }

    /** Share group configuration **/
    public boolean isShareGroupEnabled() {
        return isShareGroupEnabled;
    }

    public int shareGroupPartitionMaxRecordLocks() {
        return shareGroupPartitionMaxRecordLocks;
    }

    public int shareGroupDeliveryCountLimit() {
        return shareGroupDeliveryCountLimit;
    }

    public short shareGroupMaxGroups() {
        return shareGroupMaxGroups;
    }

    public short shareGroupMaxSize() {
        return shareGroupMaxSize;
    }

    public int shareGroupSessionTimeoutMs() {
        return shareGroupSessionTimeoutMs;
    }

    public int shareGroupMinSessionTimeoutMs() {
        return shareGroupMinSessionTimeoutMs;
    }

    public int shareGroupMaxSessionTimeoutMs() {
        return shareGroupMaxSessionTimeoutMs;
    }

    public int shareGroupHeartbeatIntervalMs() {
        return shareGroupHeartbeatIntervalMs;
    }

    public int shareGroupMinHeartbeatIntervalMs() {
        return shareGroupMinHeartbeatIntervalMs;
    }

    public int shareGroupMaxHeartbeatIntervalMs() {
        return shareGroupMaxHeartbeatIntervalMs;
    }

    public int shareGroupRecordLockDurationMs() {
        return shareGroupRecordLockDurationMs;
    }

    public int shareGroupMaxRecordLockDurationMs() {
        return shareGroupMaxRecordLockDurationMs;
    }

    public int shareGroupMinRecordLockDurationMs() {
        return shareGroupMinRecordLockDurationMs;
    }

    private void validate() {
        Utils.require(shareGroupMaxHeartbeatIntervalMs >= shareGroupMinHeartbeatIntervalMs,
                String.format("%s must be greater than or equals to %s",
                        SHARE_GROUP_MAX_HEARTBEAT_INTERVAL_MS_CONFIG, SHARE_GROUP_MIN_HEARTBEAT_INTERVAL_MS_CONFIG));
        Utils.require(shareGroupHeartbeatIntervalMs >= shareGroupMinHeartbeatIntervalMs,
                String.format("%s must be greater than or equals to %s",
                        SHARE_GROUP_HEARTBEAT_INTERVAL_MS_CONFIG, SHARE_GROUP_MIN_HEARTBEAT_INTERVAL_MS_CONFIG));
        Utils.require(shareGroupHeartbeatIntervalMs <= shareGroupMaxHeartbeatIntervalMs,
                String.format("%s must be less than or equals to %s",
                        SHARE_GROUP_HEARTBEAT_INTERVAL_MS_CONFIG, SHARE_GROUP_MAX_HEARTBEAT_INTERVAL_MS_CONFIG));

        Utils.require(shareGroupMaxSessionTimeoutMs >= shareGroupMinSessionTimeoutMs,
                String.format("%s must be greater than or equals to %s",
                        SHARE_GROUP_MAX_SESSION_TIMEOUT_MS_CONFIG, SHARE_GROUP_MIN_SESSION_TIMEOUT_MS_CONFIG));
        Utils.require(shareGroupSessionTimeoutMs >= shareGroupMinSessionTimeoutMs,
                String.format("%s must be greater than or equals to %s",
                        SHARE_GROUP_SESSION_TIMEOUT_MS_CONFIG, SHARE_GROUP_MIN_SESSION_TIMEOUT_MS_CONFIG));
        Utils.require(shareGroupSessionTimeoutMs <= shareGroupMaxSessionTimeoutMs,
                String.format("%s must be less than or equals to %s",
                        SHARE_GROUP_SESSION_TIMEOUT_MS_CONFIG, SHARE_GROUP_MAX_SESSION_TIMEOUT_MS_CONFIG));

        Utils.require(shareGroupRecordLockDurationMs >= shareGroupMinRecordLockDurationMs,
                String.format("%s must be greater than or equals to %s",
                        SHARE_GROUP_RECORD_LOCK_DURATION_MS_CONFIG, SHARE_GROUP_MIN_RECORD_LOCK_DURATION_MS_CONFIG));
        Utils.require(shareGroupMaxRecordLockDurationMs >= shareGroupRecordLockDurationMs,
                String.format("%s must be greater than or equals to %s",
                        SHARE_GROUP_MAX_RECORD_LOCK_DURATION_MS_CONFIG, SHARE_GROUP_RECORD_LOCK_DURATION_MS_CONFIG));

    }
}
