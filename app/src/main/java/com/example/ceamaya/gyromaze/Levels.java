package com.example.ceamaya.gyromaze;

public final class Levels {
    public static final Wall[] level1Walls = new Wall[]{
            new Wall(4,12,4),
            new Wall(6,13,3)
    };
    public static final Hole[] level1Holes = new Hole[]{
            new Hole(1,1),
            new Hole(2,3)
    };
    public static final Level level1 = new Level(1, level1Walls, level1Holes);
    public static final Level level2 = new Level(2, level1Walls, level1Holes);
    public static final Level level3 = new Level(3, level1Walls, level1Holes);
    public static final Level[] levels = new Level[]{level1, level2, level3};

    public static Level getLevelByNumber(int levelNumber) {
        for(Level level : levels) {
            if(level.levelNumber == levelNumber) {
                return level;
            }
        }
        return level1;
    }
}

class Level {
    public int levelNumber;
    public Wall[] walls;
    public Hole[] holes;

    public Level(int levelNumber, Wall[] walls, Hole[] holes) {
        this.levelNumber = levelNumber;
        this.walls = walls;
        this.holes = holes;
    }
}

class Wall{
    int leftScale;
    int topScale;
    int size;
    public Wall(int leftScale, int topScale, int size) {
        this.leftScale = leftScale;
        this.topScale = topScale;
        this.size = size;
    }
}

class Hole {
    int leftScale;
    int topScale;
    public Hole(int leftScale, int topScale) {
        this.leftScale = leftScale;
        this.topScale = topScale;
    }
}