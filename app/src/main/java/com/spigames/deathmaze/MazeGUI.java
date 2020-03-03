package com.spigames.deathmaze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import androidx.gridlayout.widget.GridLayout;

public class MazeGUI {

    private static final double ROOM_TO_SEGMENT_RATIO = 0.7;
    private static final double DOOR_TO_SEGMENT_RATIO = 0.2;

    private static final int MAZE_BACKGROUND_COLOR = Color.rgb(96, 96, 96); // gray
    private static final int MAZE_ROOM_COLOR = Color.rgb(204, 204, 0); // dark yellow
    private static final int MAZE_WALL_COLOR = Color.rgb(0, 102, 0); // dark green
    private static final int MAZE_PEGGED_DOOR_COLOR = Color.rgb(204, 0, 0); // dark red
    private static final int MAZE_CURRENT_LOCATION_COLOR = Color.rgb(0, 204, 204); // dark cyan
    private static final int MAZE_EXIT_COLOR = Color.rgb(178, 102, 255); // light purple

    public void drawMazeGrid(Context context, GridLayout grid, int dimension,
                             Maze maze, int middleX, int middleY) {
        // dimension is the dimension of the grid; e.g. 3 means 3x3 grid

        if(dimension % 2 == 0) {
            throw new InvalidParameterException("Grid dimension must be odd.");
        }

        // Set size each maze segment
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        int segmentSize = Math.min(display.heightPixels, display.widthPixels) / dimension;
        // only need one dimension since segment is a square
        // int segmentHeight = display.heightPixels / dimension;
        // int segmentWidth = display.widthPixels / dimension;

        // Set maze coordinates of upper left corner of grid
        int topLeftX = middleX - (dimension - 1) / 2;
        int topLeftY = middleY - (dimension - 1) / 2;

        // Prepare grid for display
        grid.removeAllViewsInLayout();
        grid.setColumnCount(dimension);
        grid.setRowCount(dimension);
        grid.setOrientation(GridLayout.HORIZONTAL);

        // Populate the grid with segment ImageViews
        for (int y = topLeftY; y < topLeftY + dimension; y++) {
            for (int x = topLeftX; x < topLeftX + dimension; x++) {
                ImageView segmentImageView;
                Segment segment = maze.getSegmentAtPosition(x, y);
                if (segment != null) {
                    int roomColor = MAZE_ROOM_COLOR;
                    if (x == middleX & y == middleY) roomColor = MAZE_CURRENT_LOCATION_COLOR;
                    // exit color beats current location color if current location is exit
                    if (x == 0 & y == 0) roomColor = MAZE_EXIT_COLOR;
                    segmentImageView = getSegmentImageView(context, segment,
                            segmentSize, roomColor);
                } else {
                    segmentImageView = getNoSegmentImageView(context, segmentSize);
                }
                grid.addView(segmentImageView);
            }
        }
    }

    private ImageView getNoSegmentImageView(Context context, int size) {
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(MAZE_BACKGROUND_COLOR);
        ImageView iv = new ImageView(context);
        iv.setImageBitmap(bitmap);
        return iv;
    }

    private ImageView getSegmentImageView(Context context, Segment segment,
                                          int size, int roomColor) {
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(MAZE_WALL_COLOR);
        Paint paint = new Paint();

        // Draw room in center
        int roomSize = (int) (size * MazeGUI.ROOM_TO_SEGMENT_RATIO);
        int wallSize = (size - roomSize) / 2;
        Rect room = new Rect(wallSize, wallSize, wallSize + roomSize, wallSize + roomSize);
        paint.setColor(roomColor);
        canvas.drawRect(room, paint);

        // Draw edge on each side, default is wall
        int doorSize = (int) (size * MazeGUI.DOOR_TO_SEGMENT_RATIO);
        int doorOffset = (size - doorSize) / 2;
        Chit chit = segment.getChit();
        switch (chit.getNorth()) {
            case CORRIDOR:
                canvas.drawRect(wallSize, 0, wallSize + roomSize, wallSize, paint);
                break;
            case PEGGED_DOOR:
                paint.setColor(MAZE_PEGGED_DOOR_COLOR);
            case DOOR:
                canvas.drawRect(doorOffset, 0, doorOffset + doorSize, wallSize, paint);
                paint.setColor(roomColor);
                break;
        }
        switch (chit.getEast()) {
            case CORRIDOR:
                canvas.drawRect(size - wallSize - 1, wallSize, size, wallSize + roomSize, paint);
                break;
            case PEGGED_DOOR:
                paint.setColor(MAZE_PEGGED_DOOR_COLOR);
            case DOOR:
                canvas.drawRect(size - wallSize - 1, doorOffset, size, doorOffset + doorSize, paint);
                paint.setColor(roomColor);
                break;
        }
        switch (chit.getSouth()) {
            case CORRIDOR:
                canvas.drawRect(wallSize, size - wallSize - 1, wallSize + roomSize, size, paint);
                break;
            case PEGGED_DOOR:
                paint.setColor(MAZE_PEGGED_DOOR_COLOR);
            case DOOR:
                canvas.drawRect(doorOffset, size - wallSize - 1, doorOffset + doorSize, size, paint);
                paint.setColor(roomColor);
                break;
        }
        switch (chit.getWest()) {
            case CORRIDOR:
                canvas.drawRect(0, wallSize, wallSize, wallSize + roomSize, paint);
                break;
            case PEGGED_DOOR:
                paint.setColor(MAZE_PEGGED_DOOR_COLOR);
            case DOOR:
                canvas.drawRect(0, doorOffset, wallSize, doorOffset + doorSize, paint);
                paint.setColor(roomColor);
                break;
        }

        // Draw feature
        // todo: draw feature

        ImageView iv = new ImageView(context);
        iv.setImageBitmap(bitmap);
        return iv;
    }
}
