package com.example.ceamaya.gyromaze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import static com.example.ceamaya.gyromaze.Levels.WARP_ANY;
import static com.example.ceamaya.gyromaze.Levels.WARP_ONLY_DOWN;
import static com.example.ceamaya.gyromaze.Levels.WARP_ONLY_RIGHT;
import static com.example.ceamaya.gyromaze.Levels.WARP_ONLY_UP;

public class GameView extends View {
    private static final int MOVING_LEFT = 1;
    private static final int MOVING_RIGHT = 2;
    private static final int MOVING_UP = 3;
    private static final int MOVING_DOWN = 4;
    public static int MOVE_SCALE;
    private final Context context;
    private final Bitmap bWall;
    private final Bitmap bHole;
    private final Bitmap bPortal;
    private final Bitmap oPortal;
    private final Bitmap bFinish;
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
    private int PAD_HEIGHT;
    private int PAD_WIDTH;
    private Paint RED_PAINT;
    private Paint YELLOW_PAINT;
    private boolean IS_FIRST_RUN = false;
    private ArrayList<Rect> walls;
    private ArrayList<Rect> holes;
    private ArrayList<Rect> pads;
    private Rect finishBox;
    private Level level;
    private Pad destPad;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        switch (MainActivity.theme) {
            case 1:
                bWall = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall);
                bHole = BitmapFactory.decodeResource(context.getResources(), R.drawable.lava);
                bFinish = BitmapFactory.decodeResource(context.getResources(), R.drawable
                        .finishline);
                oPortal = BitmapFactory.decodeResource(context.getResources(), R.drawable.oportal);
                bPortal = BitmapFactory.decodeResource(context.getResources(), R.drawable.bportal);
                setBackgroundResource(R.drawable.background);
                break;
            case 2:
                bWall = BitmapFactory.decodeResource(context.getResources(), R.drawable.wood);
                bHole = BitmapFactory.decodeResource(context.getResources(), R.drawable.water);
                bFinish = BitmapFactory.decodeResource(context.getResources(), R.drawable
                        .finishline);
                oPortal = BitmapFactory.decodeResource(context.getResources(), R.drawable.golftele);
                bPortal = oPortal;
                setBackgroundResource(R.drawable.grassbackground);
                break;
            default:
                bWall = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall);
                bHole = BitmapFactory.decodeResource(context.getResources(), R.drawable.golfhole);
                bFinish = BitmapFactory.decodeResource(context.getResources(), R.drawable
                        .finishline);
                oPortal = BitmapFactory.decodeResource(context.getResources(), R.drawable.portal);
                bPortal = oPortal;
                setBackgroundResource(R.drawable.wood);
                break;
        }
        setUpPaints();
    }

    private void setUpPaints() {
        RED_PAINT = new Paint();
        RED_PAINT.setARGB(255, 255, 0, 0);
        RED_PAINT.setStyle(Paint.Style.FILL_AND_STROKE);
        YELLOW_PAINT = new Paint();
        YELLOW_PAINT.setARGB(255, 255, 255, 0);
        YELLOW_PAINT.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void setLevel(int levelNumber) {
        level = Levels.getLevelByNumber(levelNumber);
        IS_FIRST_RUN = true;
        walls = new ArrayList<>(level.verticalWalls.length + level.horizontalWalls.length);
        holes = new ArrayList<>(level.holes.length);
        pads = new ArrayList<>(level.pads.length);
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
            setUpPads();
        }

        // for easier maze creation
        //createGrid(canvas);

        for (Rect wall : walls) {
            canvas.drawBitmap(bWall, null, wall, null);
        }

        for (Rect hole : holes) {
            canvas.drawBitmap(bHole, null, hole, null);
        }
        //evens blue odds orange
        for (int i = 0; i < level.pads.length; i++) {
            if (i % 2 == 0) {
                canvas.drawBitmap(bPortal, null, pads.get(i), null);
            } else {
                canvas.drawBitmap(oPortal, null, pads.get(i), null);
            }
        }

        canvas.drawRect(finishBox, YELLOW_PAINT);
        canvas.drawBitmap(bFinish, null, finishBox, null);
        canvas.drawRect(BALL_LEFT, BALL_TOP, BALL_LEFT + BALL_SIZE,
                BALL_TOP + BALL_SIZE, RED_PAINT);
    }

    private void setUpDimensions() {
        SCREEN_HEIGHT = getHeight();
        SCREEN_WIDTH = getWidth();
        VERTICAL_WALL_WIDTH = (int) Math.round((double) SCREEN_WIDTH / 135.0);
        SPACE_BETWEEN_VERTICAL_WALLS = (int) Math.round((double) SCREEN_WIDTH / 135.0 * 13.88);
        HORIZONTAL_WALL_HEIGHT = (int) Math.round((double) SCREEN_HEIGHT / 216.375);
        SPACE_BETWEEN_HORIZONTAL_WALLS =
                (int) Math.round((double) SCREEN_HEIGHT / 216.375 * 12.461);
        HOLE_HEIGHT = SPACE_BETWEEN_HORIZONTAL_WALLS;
        HOLE_WIDTH = SPACE_BETWEEN_VERTICAL_WALLS;
        PAD_HEIGHT = SPACE_BETWEEN_HORIZONTAL_WALLS;
        PAD_WIDTH = SPACE_BETWEEN_VERTICAL_WALLS;
        BALL_SIZE = SCREEN_HEIGHT / 86;
        BALL_LEFT = SCREEN_WIDTH / 2 - BALL_SIZE / 2;
        BALL_TOP = SCREEN_HEIGHT - HORIZONTAL_WALL_HEIGHT - BALL_SIZE;
    }

    private void setUpWalls() {
        for (Wall wall : level.verticalWalls) {
            Rect newWall = createVerticalWall(wall.leftCord, wall.topCord, wall.size);
            walls.add(newWall);
        }
        for (Wall wall : level.horizontalWalls) {
            Rect newWall = createHorizontalWall(wall.leftCord, wall.topCord, wall.size);
            walls.add(newWall);
        }
    }

    private void setUpHoles() {
        for (Hole hole : level.holes) {
            createHole(hole.leftCord, hole.topCord);
        }
    }

    private void setUpFinishBox() {
        int size = level.finishBox.horizontalSize;
        int top = getObjectTopCord(level.finishBox.topCord);
        int bottom = top + SPACE_BETWEEN_HORIZONTAL_WALLS;
        int left = getObjectLeftCord(level.finishBox.leftCord);
        int right = left + size * SPACE_BETWEEN_VERTICAL_WALLS + (size - 1) * VERTICAL_WALL_WIDTH;
        finishBox = new Rect(left, top, right, bottom);
    }

    private void setUpPads() {
        int i = 0;
        for (Pad pad : level.pads) {
            createPad(pad.leftCord, pad.topCord);
            if (i > 0 && i % 2 != 0) {
                level.pads[i].destPad = level.pads[i - 1];
                level.pads[i - 1].destPad = level.pads[i];
            }
            i++;
        }
    }

    private void createHole(int leftCord, int topCord) {
        int top = getObjectTopCord(topCord);
        int bottom = top + HOLE_HEIGHT;
        int left = getObjectLeftCord(leftCord);
        int right = left + HOLE_WIDTH;
        Rect wall = new Rect(left, top, right, bottom);
        holes.add(wall);
    }

    private int getObjectTopCord(int topCord) {
        return getTopCord(topCord) + HORIZONTAL_WALL_HEIGHT;
    }

    private void createPad(int leftCord, int topCord) {
        int top = getObjectTopCord(topCord);
        int bottom = top + PAD_HEIGHT;
        int left = getObjectLeftCord(leftCord);
        int right = left + PAD_WIDTH;
        pads.add(new Rect(left, top, right, bottom));
    }

    private int getObjectLeftCord(int leftCord) {
        return getLeftCord(leftCord) + VERTICAL_WALL_WIDTH;
    }

    private void createGrid(Canvas canvas) {
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setTextSize(40);

        ArrayList<Rect> grid = new ArrayList<>();
        for (int i = 0; i <= 16; i++) {
            grid.add(createHorizontalWall(0, i, 10));
            int topCord = getTopCord(i);
            canvas.drawText("" + i, 10, topCord, blackPaint);
        }

        for (int i = 0; i <= 9; i++) {
            grid.add(createVerticalWall(i, 0, 16));
            int leftCord = getLeftCord(i);
            canvas.drawText("" + i, leftCord + 5, 40, blackPaint);
        }

        for (Rect wall : grid) {
            canvas.drawRect(wall, RED_PAINT);
        }
    }

    private Rect createHorizontalWall(int leftCord, int topCord, int size) {
        int top = getTopCord(topCord);
        int left = getLeftCord(leftCord);
        int right;
        if (left == 0) {
            right = SPACE_BETWEEN_VERTICAL_WALLS * size + VERTICAL_WALL_WIDTH * size;
        } else {
            right = left + SPACE_BETWEEN_VERTICAL_WALLS * size + VERTICAL_WALL_WIDTH * (1 + size);
        }
        int bottom = top + HORIZONTAL_WALL_HEIGHT;
        return new Rect(left, top, right, bottom);
    }

    private int getTopCord(int topCord) {
        return topCord * SPACE_BETWEEN_HORIZONTAL_WALLS + topCord * HORIZONTAL_WALL_HEIGHT;
    }

    private Rect createVerticalWall(int leftCord, int topCord, int size) {
        int top = getTopCord(topCord);
        int left = getLeftCord(leftCord);
        int right = left + VERTICAL_WALL_WIDTH;
        int bottom;
        if (top == 0) {
            bottom = SPACE_BETWEEN_HORIZONTAL_WALLS * size + HORIZONTAL_WALL_HEIGHT * size;
        } else {
            bottom =
                    top + SPACE_BETWEEN_HORIZONTAL_WALLS * size + HORIZONTAL_WALL_HEIGHT * (1 +
                            size);
        }
        return new Rect(left, top, right, bottom);
    }

    private int getLeftCord(int leftCord) {
        return leftCord * SPACE_BETWEEN_VERTICAL_WALLS + leftCord * VERTICAL_WALL_WIDTH;
    }

    public void move(float[] values) {
        float SENSITIVITY_THRESHOLD = 0.25f;
        if (SCREEN_HEIGHT > 0 && SCREEN_WIDTH > 0) {
            float x = values[0];
            float y = values[1];
            boolean didMove = false;
            if (intersectsFinish()) {
                userWins();
                return;
            }
            int moveUpAmount = 0;
            int moveRightAmount = 0;
            int moveDownAmount = 0;
            int moveLeftAmount = 0;
            if (x > SENSITIVITY_THRESHOLD) {
                moveLeftAmount = (int) (x * MOVE_SCALE);
                moveLeft(moveLeftAmount);
                didMove = true;
            }
            if (x * -1 > SENSITIVITY_THRESHOLD) {
                moveRightAmount = (int) (-1 * x * MOVE_SCALE);
                moveRight(moveRightAmount);
                didMove = true;
            }
            if (y > SENSITIVITY_THRESHOLD) {
                moveDownAmount = (int) (y * MOVE_SCALE);
                moveDown(moveDownAmount);
                didMove = true;
            }
            if (y * -1 > SENSITIVITY_THRESHOLD) {
                moveUpAmount = (int) (-1 * y * MOVE_SCALE);
                moveUp(moveUpAmount);
                didMove = true;
            }
            if (didMove) {
                if (intersectsTeleporter()) {
                    int movingDirection = MOVING_LEFT;
                    if (moveRightAmount > moveDownAmount && moveRightAmount > moveUpAmount &&
                            moveRightAmount > moveLeftAmount) {
                        movingDirection = MOVING_RIGHT;
                    } else if (moveDownAmount > moveRightAmount && moveDownAmount > moveUpAmount &&
                            moveDownAmount > moveLeftAmount) {
                        movingDirection = MOVING_DOWN;
                    } else if (moveUpAmount > moveRightAmount && moveUpAmount > moveDownAmount &&
                            moveUpAmount > moveLeftAmount) {
                        movingDirection = MOVING_UP;
                    }
                    warp(destPad, movingDirection);
                }
                postInvalidate();
            }
        }
    }

    private void moveLeft(int moveAmount) {
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
        }
    }

    private void moveRight(int moveAmount) {
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
        }
    }

    private void moveUp(int moveAmount) {
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
        }
    }

    private void moveDown(int moveAmount) {
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
        }
    }

    private void userWins() {
        ((GameActivity) context).userWins();
    }

    private boolean intersectsFinish() {
        return Rect.intersects(getBall(), finishBox);
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

    private Rect findIntersectingWall() {
        Rect ball = getBall();
        for (Rect wall : walls) {
            if (Rect.intersects(ball, wall)) {
                return wall;
            }
        }
        return null;
    }

    private boolean intersectsTeleporter() {
        Rect ball = getBall();
        for (Rect pad : pads) {
            if (Rect.intersects(pad, ball)) {
                destPad = level.pads[pads.indexOf(pad)].destPad;
                return true;
            } else
                destPad = null;
        }
        return false;
    }

    private void warp(final Pad destPad, final int movingDirection) {
        int top = getTopCord(destPad.topCord);
        int left = getLeftCord(destPad.leftCord);
        switch (destPad.warpOnlyDirection) {
            case WARP_ANY:
                switch (movingDirection) {
                    case MOVING_LEFT:
                        top += PAD_HEIGHT / 2;
                        left -= BALL_SIZE + VERTICAL_WALL_WIDTH;
                        break;
                    case MOVING_RIGHT:
                        top += PAD_HEIGHT / 2;
                        left += PAD_WIDTH + VERTICAL_WALL_WIDTH;
                        break;
                    case MOVING_DOWN:
                        top += PAD_HEIGHT + HORIZONTAL_WALL_HEIGHT;
                        left += PAD_WIDTH / 2;
                        break;
                    default:
                        top -= BALL_SIZE + HORIZONTAL_WALL_HEIGHT;
                        left += PAD_WIDTH / 2;
                        break;
                }
                break;
            case WARP_ONLY_UP:
                top -= BALL_SIZE + HORIZONTAL_WALL_HEIGHT;
                left += PAD_WIDTH / 2;
                break;
            case WARP_ONLY_RIGHT:
                top += PAD_HEIGHT / 2;
                left += PAD_WIDTH + VERTICAL_WALL_WIDTH;
                break;

            case WARP_ONLY_DOWN:
                top += PAD_HEIGHT + HORIZONTAL_WALL_HEIGHT;
                left += PAD_WIDTH / 2;
                break;
            default:
                top += PAD_HEIGHT / 2;
                left -= BALL_SIZE + VERTICAL_WALL_WIDTH;
                break;
        }
        BALL_LEFT = left;
        BALL_TOP = top;
    }

    @NonNull
    private Rect getBall() {
        return new Rect(BALL_LEFT, BALL_TOP, BALL_LEFT + BALL_SIZE,
                BALL_TOP + BALL_SIZE);
    }

    private void resetBall() {
        BALL_LEFT = SCREEN_WIDTH / 2 - BALL_SIZE / 2;
        BALL_TOP = SCREEN_HEIGHT - HORIZONTAL_WALL_HEIGHT - BALL_SIZE;
    }
}