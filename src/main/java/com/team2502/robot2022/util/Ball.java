package com.team2502.robot2022.util;

/**
 * class for balls in hopper
 * */
public class Ball {
    /** color of ball */
    public BallColor color;
    /** feed belt position when detected */
    public double detectedPos;

    /** Ball colors */
    public enum BallColor {
        RED,
        BLUE,
        UNKNOWN
    };

    public Ball(BallColor color, double startPos) {
        this.color = color;
        this.detectedPos = startPos;
    }
}
