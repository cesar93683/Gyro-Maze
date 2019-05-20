package com.example.ceamaya.gyromaze;

final class Levels {
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
    private static final FinishBox level1FinishBox = new FinishBox(4, 13, 2);
    private static final StarTimes level1StarTimes = new StarTimes(20000, 10000);
    private static final Level level1 = new Level(1, level1VerticalWalls, level1HorizontalWalls, level1Holes, level1FinishBox, level1StarTimes);

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
    private static final FinishBox level2FinishBox = new FinishBox(3, 0, 1);
    private static final StarTimes level2StarTimes = new StarTimes(20001, 10001);
    private static final Level level2 = new Level(2, level2VerticalWalls, level2HorizontalWalls, level2Holes, level2FinishBox, level2StarTimes);

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
    private static final StarTimes level3StarTimes = new StarTimes(20000, 10000);
    private static final Level level3 = new Level(3, level3VerticalWalls, level3HorizontalWalls, level3Holes, level3FinishBox, level3StarTimes);


    //
    //  LEVEL 4
    //
    private static final Wall[] level4VerticalWalls = new Wall[]{
            new Wall(1, 2, 2),
    };
    private static final Wall[] level4HorizontalWalls = new Wall[]{
            new Wall(0, 11, 1),
    };
    private static final Hole[] level4Holes = new Hole[]{
            new Hole(9, 10),
    };
    private static final FinishBox level4FinishBox = new FinishBox(4, 0, 2);
    private static final StarTimes level4StarTimes = new StarTimes(20000, 10000);
    private static final Level level4 = new Level(4, level4VerticalWalls, level4HorizontalWalls, level4Holes, level4FinishBox, level4StarTimes);


    //
    //  LEVEL 5
    //
    private static final Wall[] level5VerticalWalls = new Wall[]{
            new Wall(1, 2, 2),
    };
    private static final Wall[] level5HorizontalWalls = new Wall[]{
            new Wall(0, 11, 1),
    };
    private static final Hole[] level5Holes = new Hole[]{
            new Hole(9, 10),
    };
    private static final FinishBox level5FinishBox = new FinishBox(4, 0, 2);
    private static final StarTimes level5StarTimes = new StarTimes(20000, 10000);
    private static final Level level5 = new Level(5, level5VerticalWalls, level5HorizontalWalls, level5Holes, level5FinishBox, level5StarTimes);


    //
    //  LEVEL 6
    //
    private static final Wall[] level6VerticalWalls = new Wall[]{
            new Wall(1, 2, 2),
    };
    private static final Wall[] level6HorizontalWalls = new Wall[]{
            new Wall(0, 11, 1),
    };
    private static final Hole[] level6Holes = new Hole[]{
            new Hole(9, 10),
    };
    private static final FinishBox level6FinishBox = new FinishBox(4, 0, 2);
    private static final StarTimes level6StarTimes = new StarTimes(20000, 10000);
    private static final Level level6 = new Level(6, level6VerticalWalls, level6HorizontalWalls, level6Holes, level6FinishBox, level6StarTimes);


    //
    //  LEVEL 7
    //
    private static final Wall[] level7VerticalWalls = new Wall[]{
            new Wall(1, 2, 2),
    };
    private static final Wall[] level7HorizontalWalls = new Wall[]{
            new Wall(0, 11, 1),
    };
    private static final Hole[] level7Holes = new Hole[]{
            new Hole(9, 10),
    };
    private static final FinishBox level7FinishBox = new FinishBox(4, 0, 2);
    private static final StarTimes level7StarTimes = new StarTimes(20000, 10000);
    private static final Level level7 = new Level(7, level7VerticalWalls, level7HorizontalWalls, level7Holes, level7FinishBox, level7StarTimes);


    //
    //  LEVEL 8
    //
    private static final Wall[] level8VerticalWalls = new Wall[]{
            new Wall(1, 2, 2),
    };
    private static final Wall[] level8HorizontalWalls = new Wall[]{
            new Wall(0, 11, 1),
    };
    private static final Hole[] level8Holes = new Hole[]{
            new Hole(9, 10),
    };
    private static final FinishBox level8FinishBox = new FinishBox(4, 0, 2);
    private static final StarTimes level8StarTimes = new StarTimes(20000, 10000);
    private static final Level level8 = new Level(8, level8VerticalWalls, level8HorizontalWalls, level8Holes, level8FinishBox, level8StarTimes);


    //
    //  LEVEL 9
    //
    private static final Wall[] level9VerticalWalls = new Wall[]{
            new Wall(1, 2, 2),
    };
    private static final Wall[] level9HorizontalWalls = new Wall[]{
            new Wall(0, 11, 1),
    };
    private static final Hole[] level9Holes = new Hole[]{
            new Hole(9, 10),
    };
    private static final FinishBox level9FinishBox = new FinishBox(4, 0, 2);
    private static final StarTimes level9StarTimes = new StarTimes(20000, 10000);
    private static final Level level9 = new Level(9, level9VerticalWalls, level9HorizontalWalls, level9Holes, level9FinishBox, level9StarTimes);


    //
    //  LEVEL 10
    //
    private static final Wall[] level10VerticalWalls = new Wall[]{
            new Wall(1, 2, 2),
    };
    private static final Wall[] level10HorizontalWalls = new Wall[]{
            new Wall(0, 11, 1),
    };
    private static final Hole[] level10Holes = new Hole[]{
            new Hole(9, 10),
    };
    private static final FinishBox level10FinishBox = new FinishBox(4, 0, 2);
    private static final StarTimes level10StarTimes = new StarTimes(20000, 10000);
    private static final Level level10 = new Level(10, level10VerticalWalls, level10HorizontalWalls, level10Holes, level10FinishBox, level10StarTimes);


    private static final Level[] levels = new Level[]{level1, level2, level3, level4, level5, level6, level7, level8, level9, level10};

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
    public final int levelNumber;
    public final Wall[] verticalWalls;
    public final Wall[] horizontalWalls;
    public final Hole[] holes;
    public final FinishBox finishBox;
    private final StarTimes starTimes;

    Level(int levelNumber, Wall[] verticalWalls, Wall[] horizontalWalls, Hole[] holes, FinishBox finishBox, StarTimes starTimes) {
        this.levelNumber = levelNumber;
        this.verticalWalls = verticalWalls;
        this.horizontalWalls = horizontalWalls;
        this.holes = holes;
        this.finishBox = finishBox;
        this.starTimes = starTimes;
    }

    int getNumStars(int time) {
        if (time < starTimes.threeStarTime) {
            return 3;
        } else if (time < starTimes.twoStarTime) {
            return 2;
        }
        return 1;
    }
}

class Wall {
    final int leftCord;
    final int topCord;
    final int size;

    Wall(int leftCord, int topCord, int size) {
        this.leftCord = leftCord;
        this.topCord = topCord;
        this.size = size;
    }
}

class Hole {
    final int leftCord;
    final int topCord;

    Hole(int leftCord, int topCord) {
        this.leftCord = leftCord;
        this.topCord = topCord;
    }
}

class FinishBox {
    final int leftCord;
    final int topCord;
    final int horizontalSize;

    FinishBox(int leftCord, int topCord, int horizontalSize) {
        this.leftCord = leftCord;
        this.topCord = topCord;
        this.horizontalSize = horizontalSize;
    }
}

class StarTimes {
    @SuppressWarnings("CanBeFinal")
    final
    int twoStarTime;
    final int threeStarTime;

    StarTimes(int twoStarTime, int threeStarTime) {
        this.twoStarTime = twoStarTime;
        this.threeStarTime = threeStarTime;
    }
}
