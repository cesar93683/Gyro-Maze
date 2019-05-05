package com.example.ceamaya.gyromaze;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class GameView extends View {
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int BALL_LEFT;
    private int BALL_TOP;
    private int BALL_SIZE;
    private int SPACE_BETWEEN_VERTICAL_WALLS;
    private int VERTICAL_WALL_WIDTH;
    private int SPACE_BETWEEN_HORIZONTAL_WALLS;
    private int HORIZONTAL_WALL_HEIGHT;
    private int HOLE_HEIGHT;
    private int HOLE_WIDTH;
    private Paint RED_PAINT;
    private Paint BLACK_PAINT;
    private Paint GREEN_PAINT;
    private Paint YELLOW_PAINT;
    private boolean IS_FIRST_RUN = false;
    private ArrayList<Rect> walls;
    private ArrayList<Rect> holes;
    private final String TAG = GameView.class.getSimpleName();
    private Level level;
    private Rect finishBox;
    Context context;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setUpPaints();
    }

    public void setLevel(int levelNumber) {
        level = Levels.getLevelByNumber(levelNumber);
        IS_FIRST_RUN = true;
        walls = new ArrayList<>(level.verticalWalls.length + level.horizontalWalls.length);
        holes = new ArrayList<>(level.holes.length);
    }

    private void setUpPaints() {
        RED_PAINT = new Paint();
        RED_PAINT.setARGB(255, 255, 0, 0);
        RED_PAINT.setStyle(Paint.Style.FILL_AND_STROKE);
        BLACK_PAINT = new Paint();
        BLACK_PAINT.setARGB(255, 0, 0, 0);
        BLACK_PAINT.setStyle(Paint.Style.FILL_AND_STROKE);
        GREEN_PAINT = new Paint();
        GREEN_PAINT.setARGB(255, 0, 255, 0);
        GREEN_PAINT.setStyle(Paint.Style.FILL_AND_STROKE);
        YELLOW_PAINT = new Paint();
        YELLOW_PAINT.setARGB(255, 255, 255, 0);
        YELLOW_PAINT.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (IS_FIRST_RUN) {
            IS_FIRST_RUN = false;
            setUpDimensions();
            setUpWalls();
            setUpHoles();
            setUpFinishBox();
        }

        // for easier maze creation
        //createGrid(canvas);

        for (Rect wall : walls) {
            canvas.drawRect(wall, BLACK_PAINT);
        }

        for (Rect hole : holes) {
            canvas.drawRect(hole, GREEN_PAINT);
        }

        canvas.drawRect(finishBox, YELLOW_PAINT);
        canvas.drawRect(BALL_LEFT, BALL_TOP, BALL_LEFT + BALL_SIZE,
                BALL_TOP + BALL_SIZE, RED_PAINT);
    }

    private void setUpFinishBox() {
        int top = getTopCord(level.finishBox.topScale);
        int left = getLeftCord(level.finishBox.leftScale) + VERTICAL_WALL_WIDTH;
        int right = left + level.finishBox.horizontalSize * SPACE_BETWEEN_VERTICAL_WALLS
                + VERTICAL_WALL_WIDTH;
        int bottom = SPACE_BETWEEN_HORIZONTAL_WALLS + HORIZONTAL_WALL_HEIGHT;
        finishBox = new Rect(left,top,right,bottom);
    }

    private void createGrid(Canvas canvas) {
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setTextSize(40);

        ArrayList<Rect> grid = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            grid.add(createHorizontalWall(0,i,10));
            int topScale = getTopCord(i);
            canvas.drawText("" + i, 10, topScale, blackPaint);
        }

        for(int i = 1; i < 10; i++) {
            grid.add(createVerticalWall(i,0,16));
            int leftScale = getLeftCord(i);
            canvas.drawText("" + i, leftScale + 5, 40, blackPaint);
        }

        for (Rect wall : grid) {
            canvas.drawRect(wall, RED_PAINT);
        }
    }

    private void setUpDimensions() {
        SCREEN_HEIGHT = getHeight();
        SCREEN_WIDTH = getWidth();
        BALL_SIZE = SCREEN_HEIGHT / 86;
        BALL_LEFT = SCREEN_WIDTH / 2 - BALL_SIZE / 2;
        BALL_TOP = SCREEN_HEIGHT - BALL_SIZE;
        VERTICAL_WALL_WIDTH = (int) Math.round((double) SCREEN_WIDTH / 120.0);
        SPACE_BETWEEN_VERTICAL_WALLS = (int) Math.round((double) VERTICAL_WALL_WIDTH * 11.11);
        HORIZONTAL_WALL_HEIGHT = (int) Math.round((double) SCREEN_HEIGHT / 192.33);
        SPACE_BETWEEN_HORIZONTAL_WALLS = (int) Math.round((double) HORIZONTAL_WALL_HEIGHT * 11.0625);
        HOLE_HEIGHT = SPACE_BETWEEN_HORIZONTAL_WALLS;
        HOLE_WIDTH = SPACE_BETWEEN_VERTICAL_WALLS;
    }

    private void setUpHoles() {
        for(Hole hole : level.holes) {
            createHole(hole.leftScale, hole.topScale);
        }
    }

    private void createHole(int leftScale, int topScale) {
        int top;
        if(topScale == 0) {
            top = 0;
        } else {
            top = getTopCord(topScale) + HORIZONTAL_WALL_HEIGHT;
        }
        int left;
        if(leftScale == 0) {
            left = 0;
        } else {
            left = getLeftCord(leftScale) + VERTICAL_WALL_WIDTH;
        }
        int right = left + HOLE_HEIGHT;
        int bottom = top + HOLE_WIDTH;
        Rect wall = new Rect(left, top, right, bottom);
        holes.add(wall);
    }

    private int getLeftCord(int leftScale) {
        int left = 0;
        if (leftScale > 0) {
            left = leftScale * SPACE_BETWEEN_VERTICAL_WALLS + (leftScale - 1) * VERTICAL_WALL_WIDTH;
        }
        return left;
    }

    private int getTopCord(int topScale) {
        int top = 0;
        if (topScale > 0) {
            top = SPACE_BETWEEN_HORIZONTAL_WALLS * topScale + (topScale - 1) * HORIZONTAL_WALL_HEIGHT;
        }
        return top;
    }

    private void setUpWalls() {
        for(Wall wall : level.verticalWalls) {
            Rect newWall = createVerticalWall(wall.leftScale, wall.topScale, wall.size);
            walls.add(newWall);
        }
        for(Wall wall : level.horizontalWalls) {
            Rect newWall = createHorizontalWall(wall.leftScale, wall.topScale, wall.size);
            walls.add(newWall);
        }
    }

    private Rect createVerticalWall(int leftScale, int topScale, int size) {
        if (leftScale < 1) {
            throw new RuntimeException("ERROR: leftScale must be greater than 0");
        }
        int top = getTopCord(topScale);
        int left = getLeftCord(leftScale);
        int right = left + VERTICAL_WALL_WIDTH;
        int bottom;
        if(top == 0) {
            bottom = top + SPACE_BETWEEN_HORIZONTAL_WALLS * size + HORIZONTAL_WALL_HEIGHT * size;
        } else {
            bottom = top + SPACE_BETWEEN_HORIZONTAL_WALLS * size + HORIZONTAL_WALL_HEIGHT * (1 + size);
        }
        return new Rect(left, top, right, bottom);
    }

    private Rect createHorizontalWall(int leftScale, int topScale, int size) {
        if (topScale < 1) {
            throw new RuntimeException("ERROR: topScale must be greater than 0");
        }
        int top = getTopCord(topScale);
        int left = getLeftCord(leftScale);
        int right;
        if(left == 0) {
            right = left + SPACE_BETWEEN_VERTICAL_WALLS * size + VERTICAL_WALL_WIDTH * size;
        } else {
            right = left + SPACE_BETWEEN_VERTICAL_WALLS * size + VERTICAL_WALL_WIDTH * (1 + size);
        }
        int bottom = top + HORIZONTAL_WALL_HEIGHT;
        return new Rect(left, top, right, bottom);
    }

    public void move(float[] values) {
        int MOVE_SCALE = 7;
        float SENSITIVITY_THRESHOLD = 0.25f;
        if (SCREEN_HEIGHT > 0 && SCREEN_WIDTH > 0) {
            float x = values[0];
            float y = values[1];
            boolean didMove = false;
            if (x > SENSITIVITY_THRESHOLD) {
                int moveAmount = (int) (x * MOVE_SCALE);
                moveLeft(moveAmount);
                didMove = true;
            }
            if (x * -1 > SENSITIVITY_THRESHOLD) {
                int moveAmount = (int) (-1 * x * MOVE_SCALE);
                moveRight(moveAmount);
                didMove = true;
            }
            if (y > SENSITIVITY_THRESHOLD) {
                int moveAmount = (int) (y * MOVE_SCALE);
                moveDown(moveAmount);
                didMove = true;
            }
            if (y * -1 > SENSITIVITY_THRESHOLD) {
                int moveAmount = (int) (-1 * y * MOVE_SCALE);
                moveUp(moveAmount);
                didMove = true;
            }
            if (didMove) {
                postInvalidate();
            }
        }
    }

    public void moveLeft(int moveAmount) {
        if (moveAmount >= VERTICAL_WALL_WIDTH) {
            BALL_LEFT -= VERTICAL_WALL_WIDTH;
        } else {
            BALL_LEFT -= moveAmount;
        }
        Rect intersectingWall = findIntersectingWall();
        if (intersectsHole()) {
            resetBall();
        } else if (intersectingWall != null) {
            BALL_LEFT = intersectingWall.right;
        } else if (BALL_LEFT < 0) {
            BALL_LEFT = 0;
        } else if (moveAmount >= VERTICAL_WALL_WIDTH) {
            moveLeft(moveAmount - VERTICAL_WALL_WIDTH);
        } else if (intersectsFinish()) {
            userWins();
        }
    }

    public void moveRight(int moveAmount) {
        if (moveAmount >= VERTICAL_WALL_WIDTH) {
            BALL_LEFT += VERTICAL_WALL_WIDTH;
        } else {
            BALL_LEFT += moveAmount;
        }
        Rect intersectingWall = findIntersectingWall();
        if (intersectsHole()) {
            resetBall();
        } else if (intersectingWall != null) {
            BALL_LEFT = intersectingWall.left - BALL_SIZE;
        } else if (BALL_LEFT + BALL_SIZE > SCREEN_WIDTH) {
            BALL_LEFT = SCREEN_WIDTH - BALL_SIZE;
        } else if (moveAmount >= VERTICAL_WALL_WIDTH) {
            moveRight(moveAmount - VERTICAL_WALL_WIDTH);
        } else if (intersectsFinish()) {
            userWins();
        }
    }

    public void moveUp(int moveAmount) {
        if (moveAmount >= HORIZONTAL_WALL_HEIGHT) {
            BALL_TOP -= HORIZONTAL_WALL_HEIGHT;
        } else {
            BALL_TOP -= moveAmount;
        }
        Rect intersectingWall = findIntersectingWall();
        if (intersectsHole()) {
            resetBall();
        } else if (intersectingWall != null) {
            BALL_TOP = intersectingWall.bottom;
        } else if (BALL_TOP < 0) {
            BALL_TOP = 0;
        } else if (moveAmount >= HORIZONTAL_WALL_HEIGHT) {
            moveUp(moveAmount - HORIZONTAL_WALL_HEIGHT);
        } else if (intersectsFinish()) {
            userWins();
        }
    }

    public void moveDown(int moveAmount) {
        if (moveAmount >= HORIZONTAL_WALL_HEIGHT) {
            BALL_TOP += HORIZONTAL_WALL_HEIGHT;
        } else {
            BALL_TOP += moveAmount;
        }
        Rect intersectingWall = findIntersectingWall();
        if (intersectsHole()) {
            resetBall();
        } else if (intersectingWall != null) {
            BALL_TOP = intersectingWall.top - BALL_SIZE;
        } else if (BALL_TOP + BALL_SIZE > SCREEN_HEIGHT) {
            BALL_TOP = SCREEN_HEIGHT - BALL_SIZE;
        } else if (moveAmount >= HORIZONTAL_WALL_HEIGHT) {
            moveDown(moveAmount - HORIZONTAL_WALL_HEIGHT);
        } else if (intersectsFinish()) {
            userWins();
        }
    }

    private boolean intersectsFinish(){
        return Rect.intersects(getBall(), finishBox);
    }

    private void userWins() {
        Toast.makeText(context, "You win", Toast.LENGTH_LONG).show();
    }

    private boolean intersectsHole() {
        Rect ball = getBall();
        for (Rect hole : holes) {
            if (Rect.intersects(hole, ball)) {
                return true;
            }
        }
        return false;
    }

    private void resetBall() {
        BALL_LEFT = SCREEN_WIDTH / 2 - BALL_SIZE / 2;
        BALL_TOP = SCREEN_HEIGHT - BALL_SIZE;
    }

    private Rect findIntersectingWall() {
        Rect ball = getBall();
        for (Rect wall : walls) {
            if (Rect.intersects(ball, wall)) {
                return wall;
            }
        }
        return null;
    }

    @NonNull
    private Rect getBall() {
        return new Rect(BALL_LEFT, BALL_TOP, BALL_LEFT + BALL_SIZE,
                BALL_TOP + BALL_SIZE);
    }
}