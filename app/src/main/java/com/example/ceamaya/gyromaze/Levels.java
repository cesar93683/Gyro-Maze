package com.example.ceamaya.gyromaze;

public final class Levels {
    public static final Wall[] level1VerticalWalls = new Wall[]{
            new Wall(4,12,4),
            new Wall(6,13,3),
            new Wall(8,12,3)
    };
    public static final Wall[] level1HorizontalWalls = new Wall[]{
            new Wall(4,12,4),
            new Wall(6,10,4)
    };
    public static final Hole[] level1Holes = new Hole[]{
            new Hole(9,10)
    };
    public static final Level level1 = new Level(1, level1VerticalWalls, level1HorizontalWalls, level1Holes);
    public static final Level level2 = new Level(2, level1VerticalWalls, level1HorizontalWalls, level1Holes);
    public static final Level level3 = new Level(3, level1VerticalWalls, level1HorizontalWalls, level1Holes);
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
    public Wall[] verticalWalls;
    public Wall[] horizontalWalls;
    public Hole[] holes;

    public Level(int levelNumber, Wall[] verticalWalls, Wall[] horizontalWalls, Hole[] holes) {
        this.levelNumber = levelNumber;
        this.verticalWalls = verticalWalls;
        this.horizontalWalls = horizontalWalls;
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