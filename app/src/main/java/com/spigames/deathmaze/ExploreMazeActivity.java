package com.spigames.deathmaze;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class ExploreMazeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_maze);

        GridLayout mazeLayout = (GridLayout) findViewById(R.id.mazeGridLayout);
        // Width should be the same 1:1 ratio in layout
        // may not need this since can access in MazeGUI...?
        // int gridSize = mazeLayout.getHeight();

        Maze maze = new Maze();
        MazeGUI mazeGUI = new MazeGUI();

        mazeGUI.drawMazeGrid(this, mazeLayout, 5, maze, 1, 1);




    }
}
