package com.gyromaze;

import static com.gyromaze.Levels.WARP_DOWN;
import static com.gyromaze.Levels.WARP_LEFT;
import static com.gyromaze.Levels.WARP_RIGHT;
import static com.gyromaze.Levels.WARP_UP;

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

public class GameView extends View {

  public static final int THEME_GAME = 1;
  public static final int THEME_GOLF = 2;
  public static final int THEME_OLD_SCHOOL = 3;
  private static final int MOVING_LEFT = 1;
  private static final int MOVING_RIGHT = 2;
  private static final int MOVING_UP = 3;
  private static final int MOVING_DOWN = 4;
  public static int MOVE_SCALE;
  public static int THEME;
  public static int DEFAULT_SCALE = 7;
  private final Context context;
  private final Bitmap bWall;
  private final Bitmap bHole;
  private final Bitmap bBall;
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
    switch (THEME) {
      case THEME_GAME:
        bBall = BitmapFactory.decodeResource(context.getResources(), R.mipmap.pokeball);
        bWall = BitmapFactory.decodeResource(context.getResources(), R.mipmap.wall);
        bHole = BitmapFactory.decodeResource(context.getResources(), R.mipmap.lava);
        bFinish = BitmapFactory.decodeResource(context.getResources(), R.mipmap.finishline);
        oPortal = BitmapFactory.decodeResource(context.getResources(), R.mipmap.oportal);
        bPortal = BitmapFactory.decodeResource(context.getResources(), R.mipmap.bportal);
        setBackgroundResource(R.mipmap.background2);
        break;
      case THEME_GOLF:
        bBall = BitmapFactory.decodeResource(context.getResources(), R.mipmap.golfball);
        bWall = BitmapFactory.decodeResource(context.getResources(), R.mipmap.wood);
        bHole = BitmapFactory.decodeResource(context.getResources(), R.mipmap.water);
        bFinish = BitmapFactory.decodeResource(context.getResources(), R.mipmap.finishline);
        oPortal = BitmapFactory.decodeResource(context.getResources(), R.mipmap.golftele);
        bPortal = oPortal;
        setBackgroundResource(R.mipmap.grassbackground);
        break;
      default:
        bBall = BitmapFactory.decodeResource(context.getResources(), R.mipmap.marble);
        bWall = BitmapFactory.decodeResource(context.getResources(), R.mipmap.wall);
        bHole = BitmapFactory.decodeResource(context.getResources(), R.mipmap.oshole2);
        bFinish = BitmapFactory.decodeResource(context.getResources(), R.mipmap.finishline);
        oPortal = BitmapFactory.decodeResource(context.getResources(), R.mipmap.osportal);
        bPortal = oPortal;
        setBackgroundResource(R.mipmap.wood);
        break;
    }
    initPaints();
  }

  private void initPaints() {
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

    for (int i = 0; i < pads.size(); i++) {
      if (i % 2 == 0) {
        canvas.drawBitmap(bPortal, null, pads.get(i), null);
      } else {
        canvas.drawBitmap(oPortal, null, pads.get(i), null);
      }
    }

    canvas.drawRect(finishBox, YELLOW_PAINT);
    canvas.drawBitmap(bFinish, null, finishBox, null);
    canvas.drawBitmap(bBall, null, getBall(), null);
  }

  private void setUpDimensions() {
    SCREEN_HEIGHT = getHeight();
    SCREEN_WIDTH = getWidth();
    VERTICAL_WALL_WIDTH = (int) Math.round((double) SCREEN_WIDTH / 135.0);
    SPACE_BETWEEN_VERTICAL_WALLS = (int) Math.round((double) SCREEN_WIDTH / 135.0 * 13.88);
    HORIZONTAL_WALL_HEIGHT = (int) Math.round((double) SCREEN_HEIGHT / 216.375);
    SPACE_BETWEEN_HORIZONTAL_WALLS = (int) Math.round((double) SCREEN_HEIGHT / 216.375 * 12.461);
    HOLE_HEIGHT = SPACE_BETWEEN_HORIZONTAL_WALLS;
    HOLE_WIDTH = SPACE_BETWEEN_VERTICAL_WALLS;
    PAD_HEIGHT = SPACE_BETWEEN_HORIZONTAL_WALLS;
    PAD_WIDTH = SPACE_BETWEEN_VERTICAL_WALLS;
    BALL_SIZE = SCREEN_HEIGHT / 43;
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
      createHole(hole.leftCord, hole.topCord, hole.horizontalSize, hole.verticalSize);
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
      if (i % 2 != 0) {
        level.pads[i].destPad = level.pads[i - 1];
        level.pads[i - 1].destPad = level.pads[i];
      }
      i++;
    }
  }

  private void createHole(int leftCord, int topCord, int horizontalSize, int verticalSize) {
    int top = getObjectTopCord(topCord);
    int bottom = top + HOLE_HEIGHT * verticalSize + (verticalSize - 1) * HORIZONTAL_WALL_HEIGHT;
    int holeHeight = (bottom - top) / verticalSize;
    int left = getObjectLeftCord(leftCord);
    int right = left + HOLE_WIDTH * horizontalSize + (horizontalSize - 1) * VERTICAL_WALL_WIDTH;
    int holeWidth = (right - left) / horizontalSize;
    for (int i = 0; i < horizontalSize; i++) {
      int topTemp = top;
      for (int j = 0; j < verticalSize; j++) {
        Rect wall = new Rect(left, topTemp, left + holeWidth, topTemp + holeHeight);
        topTemp += holeHeight;
        holes.add(wall);
      }
      left += holeWidth;
    }
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

  @SuppressWarnings("unused")
  private void createGrid(Canvas canvas) {
    Paint blackPaint = new Paint();
    blackPaint.setColor(Color.BLACK);
    blackPaint.setTextSize(40);
    Paint redPaint = new Paint();
    blackPaint.setColor(Color.RED);
    redPaint.setStyle(Paint.Style.FILL_AND_STROKE);

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
      canvas.drawRect(wall, redPaint);
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
      bottom = top + SPACE_BETWEEN_HORIZONTAL_WALLS * size + HORIZONTAL_WALL_HEIGHT * (1 + size);
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
          boolean isMovingUp = moveUpAmount > 0;
          boolean isMovingRight = moveRightAmount > 0;
          boolean isMovingDown = moveDownAmount > 0;
          boolean isMovingLeft = moveLeftAmount > 0;
          if (isMovingUp && isMovingRight) {
            if (moveUpAmount > moveRightAmount) {
              warp(MOVING_UP, MOVING_RIGHT);
            } else {
              warp(MOVING_RIGHT, MOVING_UP);
            }
          } else if (isMovingUp && isMovingLeft) {
            if (moveUpAmount > moveLeftAmount) {
              warp(MOVING_UP, MOVING_LEFT);
            } else {
              warp(MOVING_LEFT, MOVING_UP);
            }
          } else if (isMovingDown && isMovingRight) {
            if (moveDownAmount > moveRightAmount) {
              warp(MOVING_DOWN, MOVING_RIGHT);
            } else {
              warp(MOVING_RIGHT, MOVING_DOWN);
            }
          } else if (isMovingDown && isMovingLeft) {
            if (moveDownAmount > moveLeftAmount) {
              warp(MOVING_DOWN, MOVING_LEFT);
            } else {
              warp(MOVING_LEFT, MOVING_DOWN);
            }
          } else if (isMovingUp) {
            warp(MOVING_UP);
          } else if (isMovingDown) {
            warp(MOVING_DOWN);
          } else if (isMovingRight) {
            warp(MOVING_RIGHT);
          } else if (isMovingLeft) {
            warp(MOVING_LEFT);
          }
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
    destPad = null;
    for (Rect pad : pads) {
      if (Rect.intersects(pad, ball)) {
        destPad = level.pads[pads.indexOf(pad)].destPad;
        return true;
      }
    }
    return false;
  }

  private void warp(int movingDirection) {
    int top = getTopCord(destPad.topCord);
    int left = getLeftCord(destPad.leftCord);
    boolean canWarpUp = (destPad.warpDirection & WARP_UP) == WARP_UP;
    boolean canWarpRight = (destPad.warpDirection & WARP_RIGHT) == WARP_RIGHT;
    boolean canWarpDown = (destPad.warpDirection & WARP_DOWN) == WARP_DOWN;
    boolean canWarpLeft = (destPad.warpDirection & WARP_LEFT) == WARP_LEFT;
    if (movingDirection == MOVING_UP && canWarpUp) {
      warpUp(top, left);
    } else if (movingDirection == MOVING_RIGHT && canWarpRight) {
      warpRight(top, left);
    } else if (movingDirection == MOVING_DOWN && canWarpDown) {
      warpDown(top, left);
    } else if (movingDirection == MOVING_LEFT && canWarpLeft) {
      warpLeft(top, left);
    } else if (canWarpUp) {
      warpUp(top, left);
    } else if (canWarpRight) {
      warpRight(top, left);
    } else if (canWarpDown) {
      warpDown(top, left);
    } else {
      warpLeft(top, left);
    }
  }

  private void warpUp(int top, int left) {
    top -= BALL_SIZE + HORIZONTAL_WALL_HEIGHT;
    left += PAD_WIDTH / 2;
    BALL_LEFT = left;
    BALL_TOP = top;
  }

  private void warpRight(int top, int left) {
    top += PAD_HEIGHT / 2;
    left += PAD_WIDTH + VERTICAL_WALL_WIDTH;
    BALL_LEFT = left;
    BALL_TOP = top;
  }

  private void warpDown(int top, int left) {
    top += PAD_HEIGHT + HORIZONTAL_WALL_HEIGHT;
    left += PAD_WIDTH / 2;
    BALL_LEFT = left;
    BALL_TOP = top;
  }

  private void warpLeft(int top, int left) {
    top += PAD_HEIGHT / 2;
    left -= BALL_SIZE + VERTICAL_WALL_WIDTH;
    BALL_LEFT = left;
    BALL_TOP = top;
  }

  private void warp(int movingDirectionFast, int movingDirectionSlow) {
    int top = getTopCord(destPad.topCord);
    int left = getLeftCord(destPad.leftCord);
    boolean canWarpUp = (destPad.warpDirection & WARP_UP) == WARP_UP;
    boolean canWarpRight = (destPad.warpDirection & WARP_RIGHT) == WARP_RIGHT;
    boolean canWarpDown = (destPad.warpDirection & WARP_DOWN) == WARP_DOWN;
    boolean canWarpLeft = (destPad.warpDirection & WARP_LEFT) == WARP_LEFT;
    if (movingDirectionFast == MOVING_UP && canWarpUp) {
      warpUp(top, left);
    } else if (movingDirectionFast == MOVING_RIGHT && canWarpRight) {
      warpRight(top, left);
    } else if (movingDirectionFast == MOVING_DOWN && canWarpDown) {
      warpDown(top, left);
    } else if (movingDirectionFast == MOVING_LEFT && canWarpLeft) {
      warpLeft(top, left);
    } else if (movingDirectionSlow == MOVING_UP && canWarpUp) {
      warpUp(top, left);
    } else if (movingDirectionSlow == MOVING_RIGHT && canWarpRight) {
      warpRight(top, left);
    } else if (movingDirectionSlow == MOVING_DOWN && canWarpDown) {
      warpDown(top, left);
    } else if (movingDirectionSlow == MOVING_LEFT && canWarpLeft) {
      warpLeft(top, left);
    } else if (canWarpUp) {
      warpUp(top, left);
    } else if (canWarpRight) {
      warpRight(top, left);
    } else if (canWarpDown) {
      warpDown(top, left);
    } else {
      warpLeft(top, left);
    }
  }

  @NonNull
  private Rect getBall() {
    return new Rect(BALL_LEFT, BALL_TOP, BALL_LEFT + BALL_SIZE, BALL_TOP + BALL_SIZE);
  }

  private void resetBall() {
    BALL_LEFT = SCREEN_WIDTH / 2 - BALL_SIZE / 2;
    BALL_TOP = SCREEN_HEIGHT - HORIZONTAL_WALL_HEIGHT - BALL_SIZE;
  }
}