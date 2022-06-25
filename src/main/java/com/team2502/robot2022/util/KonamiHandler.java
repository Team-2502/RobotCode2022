package com.team2502.robot2022.util;

public class KonamiHandler {
    public enum BUTTONS {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        B,
        A,
    }

    private BUTTONS[] code = {
        BUTTONS.UP,
        BUTTONS.UP,
        BUTTONS.DOWN,
        BUTTONS.DOWN,
        BUTTONS.LEFT,
        BUTTONS.RIGHT,
        BUTTONS.LEFT,
        BUTTONS.RIGHT,
        BUTTONS.B,
        BUTTONS.A,
    };
    private int index;
    private boolean finished;

    public KonamiHandler() {
        index = 0;
        finished = false;
    }

    public boolean isFinished() {
        return finished;
    }

    public void reset() {
        finished = false;
    }

    public void handle(BUTTONS button) {
        if (index == code.length) {
            finished = true;
            return;
        }

        if (button == code[index]) {
            index += 1;
        } else {
            index = 0;
        }

    }
}
