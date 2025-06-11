package org.example.RadioDevice;
import java.time.LocalTime;

public class RadioDevice {
    boolean power;
    boolean cardInserted;


    Mode mode = Mode.HOME;

    public RadioDevice() {
        this.n = 0;
    }

    Radio radio = new Radio();
    Player player;

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
                return RadioMode.DAY;
            }
            if (getTime().isAfter(LocalTime.of(22,0)) || getTime().isBefore(LocalTime.of(6,0))) {
                return RadioMode.NIGHT;
            }
            return null;
        }
    }

    public class Player {
        int track = 1;

        void nextTrack() {
            if (track < 3) {
                track = track + 1;
            }
            printTrackNo();
        }

        void previousTrack() {
            if (track > 1) {
                track = track - 1;
            }
            printTrackNo();
        }

        void printTrackNo() {
            System.out.println("Track: " + track);
        }
    }

    public enum Mode {
        HOME,
        DAILY_PODCAST,
        RADIO,
    }

    public enum TimeOfDay {
        DAY,
        NIGHT
    }



    void powerButton() {
        power = !power;
        System.out.println(power ? "POWER ON" : "POWER OFF");
        if (power) {
            if(cardInserted) {
                player.printTrackNo();
            } else {
                System.out.println(mode);
                if (mode == Mode.RADIO) {
                    radio.getRadioMode();
                }
            }
        }
    };

    void rightButton() {
        if (power) {
            if(cardInserted) {
                player.nextTrack();
            } else {
                cycleMode();

                if (mode == Mode.RADIO) {
                    System.out.println(mode.toString() + " " + radio.getRadioMode());
                } else {
                    System.out.println(mode);
                }
            }
        }
    };

    void leftButton() {
        if (power) {
            if(cardInserted) {
                player.previousTrack();
            }
        }
    };

    void insertCard() {
        cardInserted = true;
        player = new Player();
        if (power) {
            player.printTrackNo();
        }
    };

    void removeCard() {
        cardInserted = false;
        player = null;
        if (power) {
            System.out.println(mode);
        }
    };

    static LocalTime getTime() {
        return LocalTime.now();
    };

    Mode[] listOfModes = new Mode[]{Mode.HOME, Mode.DAILY_PODCAST, Mode.RADIO};
    int n;
    void cycleMode() {
        if (n == 2) {
            n = 0;
        } else {
            n = n + 1;
        }
        mode = listOfModes[n];
    }
}
