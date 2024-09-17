package by.tms.instaclone.settings;

import java.time.Clock;


public class TimeZoneSettings {
    private static final Clock utcClock = Clock.systemUTC();// настройка на Гринвич по времени

    public static Clock getUtcClock() {
        return utcClock;
    }
}
