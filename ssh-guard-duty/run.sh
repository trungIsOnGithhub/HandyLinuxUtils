#!/bin/bash

# Configuration Env Vars
AUTH_LOG_LOCATION="/var/log/auth.log"
ALERT_LOG_LOCATION=
FAILED_THRESHOLD=3
KNOWN_IPS_LOCATION="/var/log/known_ssh_ips.log"
HOUR_AGO=$(date -d '1 hour ago' '+%Y-%m-%d %H:%M:%S')
DAY_AGO=$(date -d '1 day ago' '+%Y-%m-%d %H:%M:%S')
INCOMING_WEBHOOK_URL=
DATE_STR=$(date +"%Y-%m-%d_%H:%M:%S")

log_alert() {
        DATE_STR="$(date '+%Y-%m-%d__%H:%M:%S') - $1"
        echo "$DATE_STR" | tee -a "$ALERT_LOG"
    }

    send_alert() {
        curl -X POST -H 'Content-type: application/json' --data '{"text":"$"}' $INCOMING_WEBHOOK_URL

    if [ ! -f "$KNOWN_IPS_LOCATION" ]; then
        touch "$KNOWN_IPS_LOCATION"
    fi

    FAILED_ATTEMPTS=$(grep "$HOUR_AGO" "$AUTH_LOG" | grep -i "failed password\|invalid user" | awk '{print $11}' | sort | uniq -c | sort -nr)

    echo "$FAILED_ATTEMPTS" | while read count ip; do
        if [ "$count" -gt "$FAILED_THRESHOLD" ] && [[ "$ip" =~ ^[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
            log_alert "Exccedded failed login: $count attempts from IP $ip"
        fi
    done


    # Handling for unrecognized sucessful login within 1 day
    SUCCESSFUL_LOGINS=$(grep "$DAY_AGO" "$AUTH_LOG" | grep -i "accepted password\|accepted publickey" | awk '{print $11}' | sort | uniq)

    while read ip; do
        if ! grep -q "^$ip$" "$KNOWN_IPS_FILE"; then
            log_alert "New successful SSH login from unknown IP: $ip"
            echo "$ip" >> "$KNOWN_IPS_FILE"
        fi
    done <<< "$SUCCESSFUL_LOGINS"

    if [ -f "$KNOWN_IPS_FILE" ]; then
        awk -v date="$DAY_AGO" '$0 > date {print}' "$KNOWN_IPS_FILE" > "${KNOWN_IPS_FILE}.tmp" && mv "${KNOWN_IPS_FILE}.tmp" "$KNOWN_IPS_FILE"
    fi
}
