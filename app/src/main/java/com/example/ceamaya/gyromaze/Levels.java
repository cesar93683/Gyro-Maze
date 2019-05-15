package com.example.ceamaya.gyromaze;

public final class Levels {
    private static final Wall[] level1VerticalWalls = new Wall[]{
            new Wall(1, 2, 2),
            new Wall(1, 3, 1),
            new Wall(1, 6, 4),
            new Wall(2, 1, 2),
            new Wall(2, 10, 5),
            new Wall(2, 6, 3),
            new Wall(3, 10, 1),
            new Wall(3, 12, 3),
            new Wall(3, 3, 6),
            new Wall(4, 0, 1),
            new Wall(4, 12, 4),
            new Wall(4, 9, 1),
            new Wall(5, 10, 2),
            new Wall(5, 2, 2),
            new Wall(5, 4, 2),
            new Wall(5, 7, 1),
            new Wall(6, 0, 1),
            new Wall(6, 13, 3),
            new Wall(6, 9, 1),
            new Wall(7, 1, 1),
            new Wall(7, 3, 1),
            new Wall(7, 4, 2),
            new Wall(7, 8, 1),
            new Wall(8, 10, 1),
            new Wall(8, 12, 3),
            new Wall(8, 2, 1),
            new Wall(8, 5, 1),
            new Wall(8, 9, 1),
            new Wall(9, 1, 2),
            new Wall(9, 5, 3),
    };
    private static final Wall[] level1HorizontalWalls = new Wall[]{
            new Wall(0, 11, 1),
            new Wall(0, 13, 1),
            new Wall(0, 15, 1),
            new Wall(0, 5, 3),
            new Wall(1, 10, 1),
            new Wall(1, 12, 1),
            new Wall(1, 14, 1),
            new Wall(1, 2, 7),
            new Wall(1, 4, 1),
            new Wall(2, 10, 2),
            new Wall(2, 15, 1),
            new Wall(3, 11, 1),
            new Wall(3, 3, 1),
            new Wall(3, 7, 5),
            new Wall(4, 12, 4),
            new Wall(4, 4, 2),
            new Wall(4, 5, 2),
            new Wall(4, 9, 2),
            new Wall(6, 10, 4),
            new Wall(6, 3, 1),
            new Wall(6, 8, 3),
            new Wall(7, 4, 3),
    };
    private static final Hole[] level1Holes = new Hole[]{
            new Hole(9, 10),
            new Hole(4, 7),
            new Hole(4, 5),
            new Hole(5, 5),
            new Hole(0, 0),
    };
    private static final FinishBox level1FinishBox = new FinishBox(4, 0, 2);
    public static final Level level1 = new Level(1, level1VerticalWalls, level1HorizontalWalls, level1Holes, level1FinishBox);

    //
    //  LEVEL 2
    //
    private static final Wall[] level2VerticalWalls = new Wall[]{
            new Wall(1, 2, 2),
    };
    private static final Wall[] level2HorizontalWalls = new Wall[]{
            new Wall(0, 11, 1),
    };
    private static final Hole[] level2Holes = new Hole[]{
            new Hole(9, 10),
    };
    private static final FinishBox level2FinishBox = new FinishBox(4, 0, 2);

    public static final Level level2 = new Level(2, level2VerticalWalls, level2HorizontalWalls, level2Holes, level2FinishBox);

    //
    //  LEVEL 3
    //
    private static final Wall[] level3VerticalWalls = new Wall[]{
            new Wall(1, 2, 2),
    };
    private static final Wall[] level3HorizontalWalls = new Wall[]{
            new Wall(0, 11, 1),
    };
    private static final Hole[] level3Holes = new Hole[]{
            new Hole(9, 10),
    };
    private static final FinishBox level3FinishBox = new FinishBox(4, 0, 2);
    public static final Level level3 = new Level(3, level3VerticalWalls, level3HorizontalWalls, level3Holes, level3FinishBox);

    
    private static final Level[] levels = new Level[]{level1, level2, level3};

    public static Level getLevelByNumber(int levelNumber) {
        for (Level level : levels) {
            if (level.levelNumber == levelNumber) {
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
    public FinishBox finishBox;

    Level(int levelNumber, Wall[] verticalWalls, Wall[] horizontalWalls, Hole[] holes, FinishBox finishBox) {
        this.levelNumber = levelNumber;
        this.verticalWalls = verticalWalls;
        this.horizontalWalls = horizontalWalls;
        this.holes = holes;
        this.finishBox = finishBox;
    }
}

class Wall {
    int leftScale;
    int topScale;
    int size;

    Wall(int leftScale, int topScale, int size) {
        this.leftScale = leftScale;
        this.topScale = topScale;
        this.size = size;
    }
}

class Hole {
    int leftScale;
    int topScale;

    Hole(int leftScale, int topScale) {
        this.leftScale = leftScale;
        this.topScale = topScale;
    }
}

class FinishBox {
    int leftScale;
    int topScale;
    int horizontalSize;

    FinishBox(int leftScale, int topScale, int horizontalSize) {
        this.leftScale = leftScale;
        this.topScale = topScale;
        this.horizontalSize = horizontalSize;
    }
}