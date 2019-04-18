package com.example.ceamaya.gyromaze;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class BallView extends View {
    private static final float SENSITIVITY_THRESHOLD = 0.5f;
    private static final int MOVE_SCALE = 10;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int BALL_LEFT;
    private int BALL_TOP;
    private int BALL_SIZE;
    private int SPACE_BETWEEN_VERTICAL_WALLS;
    private int VERTICAL_WALL_WIDTH;
    private int SPACE_BETWEEN_HORIZONTAL_WALLS;
    private int HORIZONTAL_WALL_HEIGHT;
    private Paint RED_PAINT;
    private Paint BLACK_PAINT;
    private boolean IS_FIRST_RUN = true;
    private ArrayList<Rect> walls;
    private final String TAG = "BallView";


    public BallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpPaints();
        walls = new ArrayList<>();
    }

    private void setUpPaints() {
        RED_PAINT = new Paint();
        RED_PAINT.setARGB(255, 255, 0, 0);
        RED_PAINT.setStyle(Paint.Style.FILL_AND_STROKE);
        BLACK_PAINT = new Paint();
        BLACK_PAINT.setARGB(255, 0, 0, 0);
        BLACK_PAINT.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (IS_FIRST_RUN) {
            IS_FIRST_RUN = false;
            setUpDimensions();
            setUpMaze();
        }
        for (Rect wall : walls) {
            canvas.drawRect(wall, BLACK_PAINT);
        }
        canvas.drawRect(BALL_LEFT, BALL_TOP, BALL_LEFT + BALL_SIZE,
                BALL_TOP + BALL_SIZE, RED_PAINT);
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
    }

    private void createVerticalWall(int leftScale, int topScale, int size) {
        if (leftScale < 1) {
            throw new RuntimeException("ERROR: leftScale must be greater than 0");
        }
        int top = 0;
        if (topScale > 0) {
            top = SPACE_BETWEEN_HORIZONTAL_WALLS * topScale + (topScale - 1) * HORIZONTAL_WALL_HEIGHT;
        }
        int left = leftScale * SPACE_BETWEEN_VERTICAL_WALLS + (leftScale - 1) * VERTICAL_WALL_WIDTH;
        int right = left + VERTICAL_WALL_WIDTH;
        int bottom = top + SPACE_BETWEEN_HORIZONTAL_WALLS * size + HORIZONTAL_WALL_HEIGHT * size;
        Rect wall = new Rect(left, top, right, bottom);
        walls.add(wall);
    }

    private void createHorizontalWall(int leftScale, int topScale, int size) {
        if (topScale < 1) {
            throw new RuntimeException("ERROR: topScale must be greater than 0");
        }
        int top = SPACE_BETWEEN_HORIZONTAL_WALLS * topScale + (topScale - 1) * HORIZONTAL_WALL_HEIGHT;
        int left = 0;
        if (leftScale > 0) {
            left = leftScale * SPACE_BETWEEN_VERTICAL_WALLS + (leftScale - 1) * VERTICAL_WALL_WIDTH;
        }
        int right = left + SPACE_BETWEEN_VERTICAL_WALLS * size + VERTICAL_WALL_WIDTH * size;
        int bottom = top + HORIZONTAL_WALL_HEIGHT;
        Rect wall = new Rect(left, top, right, bottom);
        walls.add(wall);
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

    private void setUpMaze() {
        createVerticalWall(4,13,3);
        createVerticalWall(6,14,2);
        createVerticalWall(7,13,2);
        createVerticalWall(8,10,2);
        createVerticalWall(8,13,2);
        createHorizontalWall(4,13,3);
        createHorizontalWall(5,11,2);
        createHorizontalWall(8,12,2);
        createHorizontalWall(8,13,1);
    }

    @NonNull
    private Rect getBall() {
        return new Rect(BALL_LEFT, BALL_TOP, BALL_LEFT + BALL_SIZE,
                BALL_TOP + BALL_SIZE);
    }

    public void moveLeft(int moveAmount) {
        if (moveAmount <= VERTICAL_WALL_WIDTH) {
            BALL_LEFT -= moveAmount;
            Rect intersectingWall = findIntersectingWall();
            if (intersectingWall != null) {
                while (Rect.intersects(getBall(), intersectingWall)) {
                    BALL_LEFT++;
                }
            }
        } else {
            int timesToCheck = moveAmount / VERTICAL_WALL_WIDTH;
            int leftOverToMove = moveAmount - VERTICAL_WALL_WIDTH * timesToCheck;
            boolean didIntersect = false;
            for (int i = 1; i <= timesToCheck; i++) {
                BALL_LEFT -= VERTICAL_WALL_WIDTH;
                Rect intersectingWall = findIntersectingWall();
                if (intersectingWall != null) {
                    didIntersect = true;
                    while (Rect.intersects(getBall(), intersectingWall)) {
                        BALL_LEFT++;
                    }
                    break;
                }
            }
            if (!didIntersect) {
                BALL_LEFT -= leftOverToMove;
            }
        }
        if (BALL_LEFT < 0) {
            BALL_LEFT = 0;
        }
        postInvalidate();
    }

    public void moveRight(int moveAmount) {
        if (moveAmount <= VERTICAL_WALL_WIDTH) {
            BALL_LEFT += moveAmount;
            Rect intersectingWall = findIntersectingWall();
            if (intersectingWall != null) {
                while (Rect.intersects(getBall(), intersectingWall)) {
                    BALL_LEFT--;
                }
            }
        } else {
            int timesToCheck = moveAmount / VERTICAL_WALL_WIDTH;
            int leftOverToMove = moveAmount - VERTICAL_WALL_WIDTH * timesToCheck;
            boolean didCollide = false;
            for (int i = 1; i <= timesToCheck; i++) {
                BALL_LEFT += VERTICAL_WALL_WIDTH;
                Rect intersectingWall = findIntersectingWall();
                if (intersectingWall != null) {
                    didCollide = true;
                    while (Rect.intersects(getBall(), intersectingWall)) {
                        BALL_LEFT--;
                    }
                    break;
                }
            }
            if (!didCollide) {
                BALL_LEFT += leftOverToMove;
            }
        }
        if (BALL_LEFT + BALL_SIZE > SCREEN_WIDTH) {
            BALL_LEFT = SCREEN_WIDTH - BALL_SIZE;
        }
        postInvalidate();
    }

    public void moveUp(int moveAmount) {
        if (moveAmount <= HORIZONTAL_WALL_HEIGHT) {
            BALL_TOP -= moveAmount;
            Rect intersectingWall = findIntersectingWall();
            if (intersectingWall != null) {
                while (Rect.intersects(getBall(), intersectingWall)) {
                    BALL_TOP++;
                }
            }
        } else {
            int timesToCheck = moveAmount / HORIZONTAL_WALL_HEIGHT;
            int leftOverToMove = moveAmount - HORIZONTAL_WALL_HEIGHT * timesToCheck;
            boolean didCollide = false;
            for (int i = 1; i <= timesToCheck; i++) {
                BALL_TOP -= HORIZONTAL_WALL_HEIGHT;
                Rect intersectingWall = findIntersectingWall();
                if (intersectingWall != null) {
                    didCollide = true;
                    while (Rect.intersects(getBall(), intersectingWall)) {
                        BALL_TOP++;
                    }
                    break;
                }
            }
            if (!didCollide) {
                BALL_TOP -= leftOverToMove;
            }
        }
        if (BALL_TOP < 0) {
            BALL_TOP = 0;
        }
        postInvalidate();
    }

    public void moveDown(int moveAmount) {
        if(moveAmount <= HORIZONTAL_WALL_HEIGHT) {
            BALL_TOP += moveAmount;
            Rect intersectingWall = findIntersectingWall();
            if (intersectingWall != null) {
                while (Rect.intersects(getBall(), intersectingWall)) {
                    BALL_TOP--;
                }
            }
        }  else {
            int timesToCheck = moveAmount / HORIZONTAL_WALL_HEIGHT;
            int leftOverToMove = moveAmount - HORIZONTAL_WALL_HEIGHT * timesToCheck;
            boolean didCollide = false;
            for (int i = 1; i <= timesToCheck; i++) {
                BALL_TOP += HORIZONTAL_WALL_HEIGHT;
                Rect intersectingWall = findIntersectingWall();
                if (intersectingWall != null) {
                    didCollide = true;
                    while (Rect.intersects(getBall(), intersectingWall)) {
                        BALL_TOP--;
                    }
                    break;
                }
            }
            if (!didCollide) {
                BALL_TOP += leftOverToMove;
            }
        }
        if (BALL_TOP + BALL_SIZE > SCREEN_HEIGHT) {
            BALL_TOP = SCREEN_HEIGHT - BALL_SIZE;
        }
        postInvalidate();
    }

    public void move(float[] values) {
        if (SCREEN_HEIGHT > 0 && SCREEN_WIDTH > 0) {
            float x = values[0];
            float y = values[1];
            if (x > SENSITIVITY_THRESHOLD) {
                int moveAmount = (int) (x * MOVE_SCALE);
                moveLeft(moveAmount);
            }
            if (x * -1 > SENSITIVITY_THRESHOLD) {
                int moveAmount = (int) (-1 * x * MOVE_SCALE);
                moveRight(moveAmount);
            }
            if (y > SENSITIVITY_THRESHOLD) {
                int moveAmount = (int) (y * MOVE_SCALE);
                moveDown(moveAmount);
            }
            if (y * -1 > SENSITIVITY_THRESHOLD) {
                int moveAmount = (int) (-1 * y * MOVE_SCALE);
                moveUp(moveAmount);
            }
        }
    }
}