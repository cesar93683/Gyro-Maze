package com.example.ceamaya.gyromaze;

import static com.example.ceamaya.gyromaze.Levels.WARP_ANY;

final class Levels {
    static final int WARP_ANY = 0;
    static final int WARP_ONLY_UP = 1;
    static final int WARP_ONLY_RIGHT = 2;
    static final int WARP_ONLY_DOWN = 3;
    private static final int WARP_ONLY_LEFT = 4;

    //Level 1
    private static final Wall[] level1VerticalWalls = new Wall[]{
            //Side Walls
            new Wall(0, 0, 16),
            new Wall(9, 0, 16),

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
    };
    private static final Wall[] level1HorizontalWalls = new Wall[]{
            //Top/Bot Walls
            new Wall(0, 0, 9),
            new Wall(0, 16, 9),

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
    private static final Pad[] level1Pads = new Pad[]{};
    private static final FinishBox level1FinishBox = new FinishBox(4, 0, 2);
    private static final StarTimes level1StarTimes = new StarTimes(20000, 10000);
    private static final Level level1 = new Level(1, level1VerticalWalls, level1HorizontalWalls,
            level1Holes, level1Pads, level1FinishBox, level1StarTimes);

    //
    //  LEVEL 2
    //
    private static final Wall[] level2VerticalWalls = new Wall[]{
            //Side Walls
            new Wall(0, 0, 16),
            new Wall(9, 0, 16),

            new Wall(2, 0, 16),
            new Wall(3, 2, 1),
            new Wall(3, 6, 1),
            new Wall(3, 9, 1),
            new Wall(4, 4, 3),
            new Wall(4, 14, 1),
            new Wall(5, 6, 3),
            new Wall(5, 10, 4),
            new Wall(6, 4, 1),
            new Wall(6, 8, 2),
            new Wall(6, 11, 1),
            new Wall(6, 13, 2),
            new Wall(7, 11, 1),
            new Wall(7, 13, 2),
            new Wall(8, 1, 3),
            new Wall(8, 5, 4),
            new Wall(8, 10, 2),
    };
    private static final Wall[] level2HorizontalWalls = new Wall[]{
            //Top/Bot Walls
            new Wall(0, 0, 9),
            new Wall(0, 16, 9),

            new Wall(3, 1, 5),
            new Wall(3, 2, 1),
            new Wall(3, 7, 1),
            new Wall(3, 9, 2),
            new Wall(3, 10, 2),
            new Wall(4, 4, 1),
            new Wall(4, 14, 1),
            new Wall(4, 15, 2),
            new Wall(4, 4, 3),
            new Wall(5, 7, 1),
            new Wall(6, 4, 3),
            new Wall(6, 10, 2),
            new Wall(6, 11, 1),
            new Wall(6, 12, 1),
            new Wall(6, 13, 1),
            new Wall(7, 5, 2),
            new Wall(8, 12, 1),
    };
    private static final Hole[] level2Holes = new Hole[]{
            new Hole(0, 7),
            new Hole(0, 9),
            new Hole(0, 11),
            new Hole(1, 7),
            new Hole(1, 9),
            new Hole(1, 11),
            new Hole(3, 11),
            new Hole(3, 12),
            new Hole(7, 3),
            new Hole(8, 15),
    };
    private static final Pad[] level2Pads = new Pad[]{
            new Pad(8, 4, WARP_ONLY_LEFT),
            new Pad(0, 15, WARP_ONLY_RIGHT),
            new Pad(1, 12),
            new Pad(0, 6),
    };
    private static final FinishBox level2FinishBox = new FinishBox(0, 0, 2);
    private static final StarTimes level2StarTimes = new StarTimes(20001, 10001);
    private static final Level level2 = new Level(2, level2VerticalWalls, level2HorizontalWalls,
            level2Holes, level2Pads, level2FinishBox, level2StarTimes);

    //
    //  LEVEL 3
    //
    private static final Wall[] level3VerticalWalls = new Wall[]{
            //Side Walls
            new Wall(0, 0, 16),
            new Wall(9, 0, 16),

            new Wall(1, 2, 13),
            new Wall(2, 1, 1),
            new Wall(2, 4, 5),
            new Wall(2, 11, 3),
            new Wall(3, 1, 1),
            new Wall(3, 6, 2),
            new Wall(3, 12, 1),
            new Wall(7, 5, 2),
            new Wall(7, 9, 1),
            new Wall(7, 12, 2),
            new Wall(8, 4, 5),
            new Wall(8, 11, 4),
    };
    private static final Wall[] level3HorizontalWalls = new Wall[]{
            //Top/Bot Walls
            new Wall(0, 0, 9),
            new Wall(0, 16, 9),

            new Wall(1, 2, 1),
            new Wall(1, 3, 6),
            new Wall(1, 10, 8),
            new Wall(1, 15, 6),
            new Wall(2, 1, 1),
            new Wall(2, 4, 1),
            new Wall(2, 9, 5),
            new Wall(2, 14, 5),
            new Wall(3, 2, 2),
            new Wall(3, 5, 4),
            new Wall(3, 8, 4),
            new Wall(3, 11, 5),
            new Wall(3, 12, 4),
            new Wall(3, 13, 3),
            new Wall(4, 4, 4),
            new Wall(8, 1, 1),
            new Wall(8, 3, 1),
    };
    private static final Hole[] level3Holes = new Hole[]{
            new Hole(1, 1),
            new Hole(3, 0),
            new Hole(4, 1),
            new Hole(6, 1),
    };
    private static final Pad[] level3Pads = new Pad[]{
            new Pad(3, 12),
            new Pad(8, 0),
    };
    private static final FinishBox level3FinishBox = new FinishBox(4, 4, 2);
    private static final StarTimes level3StarTimes = new StarTimes(20000, 10000);
    private static final Level level3 = new Level(3, level3VerticalWalls, level3HorizontalWalls,
            level3Holes, level3Pads, level3FinishBox, level3StarTimes);

    //
    //  LEVEL 4
    //
    private static final Wall[] level4VerticalWalls = new Wall[]{
            //Side Walls
            new Wall(0, 0, 16),
            new Wall(9, 0, 16),

            new Wall(1, 2, 13),
            new Wall(2, 3, 8),
            new Wall(3, 3, 4),
            new Wall(3, 11, 4),
            new Wall(4, 2, 5),
            new Wall(5, 1, 1),
            new Wall(5, 8, 2),
            new Wall(6, 3, 7),
            new Wall(7, 1, 2),
            new Wall(7, 8, 7),
            new Wall(8, 5, 2),
            new Wall(8, 8, 2),
    };
    private static final Wall[] level4HorizontalWalls = new Wall[]{
            //Top/Bot Walls
            new Wall(0, 0, 9),
            new Wall(0, 16, 9),

            new Wall(0, 2, 4),
            new Wall(1, 1, 7),
            new Wall(2, 11, 1),
            new Wall(3, 7, 1),
            new Wall(3, 15, 4),
            new Wall(4, 8, 1),
            new Wall(5, 10, 1),
            new Wall(6, 3, 1),
            new Wall(6, 6, 2),
            new Wall(8, 4, 1),
            new Wall(8, 10, 1),
    };
    private static final Hole[] level4Holes = new Hole[]{
            new Hole(5, 1),
            new Hole(6, 1),
            new Hole(4, 12),
            new Hole(4, 13),
            new Hole(4, 14),
            new Hole(5, 12),
            new Hole(5, 13),
            new Hole(5, 14),
            new Hole(6, 12),
            new Hole(6, 13),
            new Hole(6, 14),
            new Hole(3, 12),
            new Hole(3, 13),
            new Hole(3, 14),
    };
    private static final Pad[] level4Pads = new Pad[]{};
    private static final FinishBox level4FinishBox = new FinishBox(6, 0, 2);
    private static final StarTimes level4StarTimes = new StarTimes(20000, 10000);
    private static final Level level4 = new Level(4, level4VerticalWalls, level4HorizontalWalls,
            level4Holes, level4Pads, level4FinishBox, level4StarTimes);

    // LEVEL 5
    private static final Wall[] level5VerticalWalls = new Wall[]{
            //Side Walls
            new Wall(0, 0, 16),
            new Wall(9, 0, 16),

            new Wall(1, 1, 1),
            new Wall(1, 4, 1),
            new Wall(1, 6, 1),
            new Wall(1, 10, 2),
            new Wall(1, 14, 1),
            new Wall(2, 2, 1),
            new Wall(3, 3, 1),
            new Wall(3, 5, 1),
            new Wall(3, 13, 1),
            new Wall(4, 5, 1),
            new Wall(4, 9, 1),
            new Wall(5, 5, 1),
            new Wall(5, 9, 1),
            new Wall(6, 3, 1),
            new Wall(6, 5, 1),
            new Wall(6, 13, 1),
            new Wall(7, 2, 1),
            new Wall(8, 1, 1),
            new Wall(8, 4, 1),
            new Wall(8, 6, 1),
            new Wall(8, 10, 2),
            new Wall(8, 14, 1),
    };
    private static final Wall[] level5HorizontalWalls = new Wall[]{
            //Top/Bot Walls
            new Wall(0, 0, 9),
            new Wall(0, 16, 9),

            new Wall(0, 4, 1),
            new Wall(0, 14, 1),
            new Wall(1, 1, 1),
            new Wall(1, 6, 1),
            new Wall(1, 10, 2),
            new Wall(2, 2, 1),
            new Wall(2, 4, 1),
            new Wall(2, 13, 1),
            new Wall(3, 5, 1),
            new Wall(3, 9, 1),
            new Wall(3, 12, 3),
            new Wall(4, 4, 1),
            new Wall(5, 5, 1),
            new Wall(5, 9, 1),
            new Wall(6, 2, 1),
            new Wall(6, 4, 1),
            new Wall(6, 10, 2),
            new Wall(6, 13, 1),
            new Wall(7, 1, 1),
            new Wall(7, 6, 1),
            new Wall(8, 4, 1),
            new Wall(8, 14, 1),
    };
    private static final Hole[] level5Holes = new Hole[]{
            new Hole(0, 8),
            new Hole(4, 7),
            new Hole(8, 8),
    };
    private static final Pad[] level5Pads = new Pad[]{};
    private static final FinishBox level5FinishBox = new FinishBox(3, 0, 2);
    private static final StarTimes level5StarTimes = new StarTimes(20000, 10000);
    private static final Level level5 = new Level(5, level5VerticalWalls, level5HorizontalWalls,
            level5Holes, level5Pads, level5FinishBox, level5StarTimes);

    //
    //  LEVEL 6
    //
    private static final Wall[] level6VerticalWalls = new Wall[]{
            //Side Walls
            new Wall(0, 0, 16),
            new Wall(9, 0, 16),

            new Wall(1, 2, 10),
            new Wall(2, 12, 4),
            new Wall(3, 0, 5),
            new Wall(5, 1, 2),
            new Wall(5, 6, 6),
            new Wall(6, 7, 4),
            new Wall(6, 14, 2),
            new Wall(7, 0, 2),
            new Wall(7, 6, 4),
            new Wall(8, 6, 1),
            new Wall(8, 8, 3),
    };
    private static final Wall[] level6HorizontalWalls = new Wall[]{
            //Top/Bot Walls
            new Wall(0, 0, 9),
            new Wall(0, 16, 9),

            new Wall(0, 1, 1),
            new Wall(1, 2, 2),
            new Wall(1, 12, 1),
            new Wall(2, 6, 6),
            new Wall(2, 14, 2),
            new Wall(3, 5, 5),
            new Wall(5, 3, 4),
            new Wall(5, 12, 3),
            new Wall(5, 14, 1),
            new Wall(6, 11, 2),
            new Wall(8, 8, 1),
            new Wall(8, 1, 1),
    };
    private static final Hole[] level6Holes = new Hole[]{
            new Hole(2, 7),
            new Hole(2, 8),
            new Hole(2, 9),
            new Hole(2, 10),
            new Hole(3, 7),
            new Hole(3, 8),
            new Hole(3, 9),
            new Hole(3, 10),

            new Hole(6, 14),
            new Hole(6, 15),
            new Hole(7, 14),
            new Hole(7, 15),
            new Hole(8, 14),
            new Hole(8, 15),
    };
    private static final Pad[] level6Pads = new Pad[]{
            new Pad(8, 0, WARP_ONLY_LEFT),
            new Pad(0, 0, WARP_ONLY_RIGHT),
    };
    private static final FinishBox level6FinishBox = new FinishBox(0, 15, 2);
    private static final StarTimes level6StarTimes = new StarTimes(20000, 10000);
    private static final Level level6 = new Level(6, level6VerticalWalls, level6HorizontalWalls,
            level6Holes, level6Pads, level6FinishBox, level6StarTimes);

    //
    //  LEVEL 7
    //
    private static final Wall[] level7VerticalWalls = new Wall[]{
            //Side Walls
            new Wall(0, 0, 16),
            new Wall(9, 0, 16),

            new Wall(1, 8, 3),
            new Wall(2, 8, 2),
            new Wall(2, 12, 2),
            new Wall(3, 9, 3),
            new Wall(3, 14, 1),
            new Wall(4, 8, 3),
            new Wall(5, 7, 1),
            new Wall(6, 9, 3),
            new Wall(7, 7, 7),
    };
    private static final Wall[] level7HorizontalWalls = new Wall[]{
            //Top/Bot Walls
            new Wall(0, 0, 9),
            new Wall(0, 16, 9),

            new Wall(0, 14, 2),
            new Wall(1, 8, 4),
            new Wall(1, 15, 2),
            new Wall(2, 12, 4),
            new Wall(3, 14, 4),
            new Wall(5, 7, 3),
    };
    private static final Hole[] level7Holes = new Hole[]{
            new Hole(4, 0),
            new Hole(5, 0),
            new Hole(6, 0),
            new Hole(7, 0),
            new Hole(8, 0),

            new Hole(2, 1),
            new Hole(2, 2),
            new Hole(3, 2),
            new Hole(4, 2),
            new Hole(5, 2),
            new Hole(6, 2),
            new Hole(7, 2),

            new Hole(1, 3),
            new Hole(1, 4),
            new Hole(1, 5),
            new Hole(1, 6),
            new Hole(1, 7),

            new Hole(3, 6),
            new Hole(3, 5),
            new Hole(3, 4),
            new Hole(4, 4),
            new Hole(5, 4),
            new Hole(6, 4),
            new Hole(7, 4),
            new Hole(8, 4),
    };
    private static final Pad[] level7Pads = new Pad[]{};
    private static final FinishBox level7FinishBox = new FinishBox(0, 0, 2);
    private static final StarTimes level7StarTimes = new StarTimes(20000, 10000);
    private static final Level level7 = new Level(7, level7VerticalWalls, level7HorizontalWalls,
            level7Holes, level7Pads, level7FinishBox, level7StarTimes);

    //
    //  LEVEL 8
    //
    private static final Wall[] level8VerticalWalls = new Wall[]{
            //Side Walls
            new Wall(0, 0, 16),
            new Wall(9, 0, 16),

            new Wall(3, 1, 3),
            new Wall(3, 6, 2),
            new Wall(3, 13, 1),
            new Wall(4, 5, 2),
            new Wall(5, 4, 6),
            new Wall(6, 2, 14),
            new Wall(7, 1, 10),
    };
    private static final Wall[] level8HorizontalWalls = new Wall[]{
            //Top/Bot Walls
            new Wall(0, 0, 9),
            new Wall(0, 16, 9),

            new Wall(1, 5, 3),
            new Wall(1, 10, 3),
            new Wall(1, 14, 2),
            new Wall(2, 1, 1),
            new Wall(2, 12, 4),
            new Wall(3, 4, 2),
            new Wall(3, 8, 2),
            new Wall(3, 13, 2),
            new Wall(4, 2, 2),
            new Wall(4, 14, 2),
            new Wall(7, 11, 2),
    };
    private static final Hole[] level8Holes = new Hole[]{
            new Hole(0, 0),
            new Hole(0, 1),
            new Hole(0, 2),
            new Hole(0, 3),
            new Hole(0, 4),
            new Hole(0, 5),
            new Hole(0, 6),
            new Hole(0, 7),
            new Hole(0, 8),
            new Hole(0, 9),
            new Hole(0, 10),
            new Hole(0, 11),
            new Hole(0, 12),
            new Hole(0, 13),
            new Hole(0, 14),
            new Hole(0, 15),

            new Hole(3, 2),
            new Hole(3, 3),

            new Hole(4, 0),
            new Hole(5, 0),
            new Hole(6, 0),
            new Hole(7, 0),
            new Hole(8, 0),
    };
    private static final Pad[] level8Pads = new Pad[]{
            new Pad(4, 3),
            new Pad(8, 2),
    };
    private static final FinishBox level8FinishBox = new FinishBox(7, 15, 2);
    private static final StarTimes level8StarTimes = new StarTimes(20000, 10000);
    private static final Level level8 = new Level(8, level8VerticalWalls, level8HorizontalWalls,
            level8Holes, level8Pads, level8FinishBox, level8StarTimes);

    //
    //  LEVEL 9
    //
    private static final Wall[] level9VerticalWalls = new Wall[]{
            //Side Walls
            new Wall(0, 0, 16),
            new Wall(9, 0, 16),

            new Wall(1, 10, 2),
            new Wall(3, 10, 2),
            new Wall(6, 8, 4),
            new Wall(6, 13, 1),
            new Wall(8, 8, 4),

            new Wall(5, 8, 1),
    };
    private static final Wall[] level9HorizontalWalls = new Wall[]{
            //Top/Bot Walls
            new Wall(0, 0, 9),
            new Wall(0, 16, 9),

            new Wall(1, 10, 2),
            new Wall(1, 12, 7),
            new Wall(1, 14, 5),
            new Wall(2, 9, 3),
            new Wall(6, 8, 2),
            new Wall(6, 10, 2),
            new Wall(6, 13, 2),

            new Wall(2, 15, 1),
            new Wall(6, 15, 1),
            new Wall(2, 11, 1),
            new Wall(4, 8, 1),
            new Wall(5, 13, 1),
    };
    private static final Hole[] level9Holes = new Hole[]{
            new Hole(0, 15),
            new Hole(0, 14),
            new Hole(0, 13),
            new Hole(0, 12),
            new Hole(0, 11),
            new Hole(0, 10),
            new Hole(0, 9),
            new Hole(0, 8),
            new Hole(0, 7),
            new Hole(0, 6),
            new Hole(0, 5),
            new Hole(0, 4),
            new Hole(0, 3),
            new Hole(0, 2),
            new Hole(0, 1),
            new Hole(0, 0),

            new Hole(1, 0),
            new Hole(2, 0),
            new Hole(3, 0),
            new Hole(4, 0),
            new Hole(5, 0),
            new Hole(6, 0),
            new Hole(7, 0),

            new Hole(1, 5),
            new Hole(2, 5),
            new Hole(3, 5),
            new Hole(4, 5),
            new Hole(5, 5),
            new Hole(6, 5),
            new Hole(7, 5),

            new Hole(8, 15),
            new Hole(8, 14),
            new Hole(8, 13),
            new Hole(8, 12),
            new Hole(8, 11),
            new Hole(8, 10),
            new Hole(8, 9),
            new Hole(8, 8),
            new Hole(8, 7),
            new Hole(8, 6),
            new Hole(8, 5),
            new Hole(8, 4),
            new Hole(8, 3),
            new Hole(8, 2),
            new Hole(8, 1),
            new Hole(8, 0),
    };
    private static final Pad[] level9Pads = new Pad[]{
            new Pad(2, 15),
            new Pad(6, 15),

            new Pad(7, 13),
            new Pad(1, 13),

            new Pad(5, 13),
            new Pad(6, 11),

            new Pad(7, 12),
            new Pad(6, 9),

            new Pad(6, 10),
            new Pad(4, 8),

            new Pad(6, 8),
            new Pad(2, 11),

            new Pad(4, 10),
            new Pad(5, 3),
    };
    private static final FinishBox level9FinishBox = new FinishBox(3, 2, 2);
    private static final StarTimes level9StarTimes = new StarTimes(20000, 10000);
    private static final Level level9 = new Level(9, level9VerticalWalls, level9HorizontalWalls,
            level9Holes, level9Pads, level9FinishBox, level9StarTimes);

    //
    //  LEVEL 10
    //
    private static final Wall[] level10VerticalWalls = new Wall[]{
            //Side Walls
            new Wall(0, 0, 16),
            new Wall(9, 0, 16),

            new Wall(1, 4, 2),
            new Wall(2, 3, 1),
            new Wall(2, 7, 4),
            new Wall(3, 1, 4),
            new Wall(3, 6, 1),
            new Wall(4, 10, 5),
            new Wall(5, 1, 5),
            new Wall(5, 10, 4),
            new Wall(6, 1, 2),
            new Wall(6, 7, 2),
            new Wall(6, 11, 3),
            new Wall(7, 2, 2),
            new Wall(7, 5, 4),
            new Wall(7, 11, 3),
            new Wall(8, 1, 1),
            new Wall(8, 7, 3),
            new Wall(8, 12, 3),
    };
    private static final Wall[] level10HorizontalWalls = new Wall[]{
            //Top/Bot Walls
            new Wall(0, 0, 9),
            new Wall(0, 16, 9),

            new Wall(0, 2, 3),
            new Wall(0, 3, 2),
            new Wall(0, 7, 2),
            new Wall(0, 9, 1),
            new Wall(0, 11, 1),
            new Wall(0, 13, 3),
            new Wall(1, 4, 1),
            new Wall(1, 6, 2),
            new Wall(1, 10, 2),
            new Wall(1, 12, 3),
            new Wall(1, 14, 3),
            new Wall(1, 15, 7),
            new Wall(2, 9, 3),
            new Wall(3, 1, 1),
            new Wall(3, 5, 1),
            new Wall(3, 7, 3),
            new Wall(3, 11, 1),
            new Wall(4, 6, 2),
            new Wall(5, 1, 1),
            new Wall(5, 4, 2),
            new Wall(5, 10, 3),
            new Wall(6, 11, 3),
            new Wall(7, 1, 1),
            new Wall(7, 2, 1),
            new Wall(7, 5, 2),

            new Wall(0, 1, 1),
    };
    private static final Hole[] level10Holes = new Hole[]{
            new Hole(0, 8),
            new Hole(1, 8),

            new Hole(8, 8),
    };
    private static final Pad[] level10Pads = new Pad[]{
            new Pad(8, 7, WARP_ONLY_UP),
            new Pad(1, 7, WARP_ONLY_LEFT),

            new Pad(1, 3, WARP_ONLY_LEFT),
            new Pad(0, 0, WARP_ONLY_RIGHT),
    };
    private static final FinishBox level10FinishBox = new FinishBox(4, 0, 2);
    private static final StarTimes level10StarTimes = new StarTimes(20000, 10000);
    private static final Level level10 = new Level(10, level10VerticalWalls,
            level10HorizontalWalls, level10Holes, level10Pads,
            level10FinishBox, level10StarTimes);

    private static final Level[] levels = new Level[]{level1, level2, level3, level4, level5,
            level6, level7, level8, level9, level10};

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
    public final Pad[] pads;
    public final FinishBox finishBox;
    private final StarTimes starTimes;

    Level(int levelNumber, Wall[] verticalWalls, Wall[] horizontalWalls, Hole[] holes, Pad[]
            pads, FinishBox finishBox, StarTimes starTimes) {
        this.levelNumber = levelNumber;
        this.verticalWalls = verticalWalls;
        this.horizontalWalls = horizontalWalls;
        this.holes = holes;
        this.pads = pads;
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

class Pad {
    final int leftCord;
    final int topCord;
    Pad destPad;
    final int warpOnlyDirection;

    Pad(int leftCord, int topCord) {
        this.leftCord = leftCord;
        this.topCord = topCord;
        this.destPad = null;
        this.warpOnlyDirection = WARP_ANY;
    }

    Pad(int leftCord, int topCord, int warpOnlyDirection) {
        this.leftCord = leftCord;
        this.topCord = topCord;
        this.destPad = null;
        this.warpOnlyDirection = warpOnlyDirection;
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
    final int twoStarTime;
    final int threeStarTime;

    StarTimes(int twoStarTime, int threeStarTime) {
        this.twoStarTime = twoStarTime;
        this.threeStarTime = threeStarTime;
    }
}
