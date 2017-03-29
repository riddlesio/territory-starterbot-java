/*
 *  Copyright 2017 riddles.io (developers@riddles.io)
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 *
 *      For the full copyright and license information, please view the LICENSE
 *      file that was distributed with this source code.
 */

package board;

import java.awt.Point;
import java.util.ArrayList;

import move.MoveType;

/**
 * board.Board - Created on 28-3-17
 *
 * Parses and stores all information about the game board.
 * Calculations concerning the board are also performed here.
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class Board {

    private int MAX_STRENGTH;

    private String myId;
    private String opponentId;
    private int width;
    private int height;
    private Cell[][] cells;
    private ArrayList<Cell> myUnitCells;

    /**
     * Parses the input string given by the engine
     * @param input String representation of the board
     */
    public void parseFromString(String input) {
        this.cells = new Cell[this.width][this.height];
        this.myUnitCells = new ArrayList<>();
        int x = 0;
        int y = 0;

        for (String cellString : input.split(",")) {
            cellString = cellString.trim();
            Cell cell = Cell.fromString(cellString);
            cell.setCoordinate(new Point(x, y));

            this.cells[x][y] = cell;

            if (cell.getUnitPlayerId().equals(this.myId)) {
                this.myUnitCells.add(cell);
            }

            if (++x == this.width) {
                x = 0;
                y++;
            }
        }
    }

    public ArrayList<MoveType> getValidDirections(Cell cell) {
        Point coordinate = cell.getCoordinate();
        ArrayList<MoveType> validDirections = new ArrayList<>();

        for (MoveType moveType : MoveType.values()) {
            Point dir = moveType.getDirection();

            if (isInBounds(new Point(coordinate.x + dir.x, coordinate.y + dir.y))) {
                validDirections.add(moveType);
            }
        }

        return validDirections;
    }

    public void setMyId(int id) {
        this.myId = id + "";
    }

    public void setOpponentId(int id) {
        this.opponentId = id + "";
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMaxStrength(int maxStrength) {
        this.MAX_STRENGTH = maxStrength;
    }

    public String getMyId() {
        return this.myId;
    }

    public String getOpponentId() {
        return this.opponentId;
    }

    public ArrayList<Cell> getMyUnitCells() {
        return this.myUnitCells;
    }

    private boolean isInBounds(Point p) {
        return p.x >= 0 && p.y >= 0 && p.x < this.width && p.y < this.height;
    }
}
