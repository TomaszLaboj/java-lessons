package org.example.RadioDevice;

import java.sql.Time;
import java.time.LocalTime;

public class RadioDevice {

    boolean power;
    boolean cardInserted;

    Track track;

    public class Radio {
        RadioMode radioMode = dayOrNight();
        public enum RadioMode {
            DAY,
            NIGHT
        }

        public RadioMode getRadioMode() {
            return radioMode;
        }

        RadioMode dayOrNight() {
            if (getTime().isAfter(LocalTime.of(6,0)) && getTime().isBefore(LocalTime.of(22,0))) {
                return RadioMode.NIGHT;
            }
            if (getTime().isAfter(LocalTime.of(22,0)) || getTime().isBefore(LocalTime.of(6,0))) {
                return RadioMode.DAY;
            }
            return null;
        }
    }

    public class Mode {
        Screen screen;

        void cycleScreen() {

        }

        public enum Screen {
            HOME,
            DAILY_PODCAST,
            RADIO
        }
    }

    public enum TimeOfDay {
        DAY,
        NIGHT
    }

    public enum Track {
        TRACK_1,
        TRACK_2,
        TRACK_3
    }

    void powerButton() {
        power = !power;
        System.out.println(power ? "POWER ON" : "POWER OFF");
    };

    void rightButton() {};

    void leftButton() {};

    void insertCard() {
        cardInserted = true;
        if (power) {
            track = Track.TRACK_1;
        }
    };

    void removeCard() {
        cardInserted = false;
        track = null;
    };

    static LocalTime getTime() {
        return LocalTime.now();
    };
}
