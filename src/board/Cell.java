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

/**
 * board.Cell - Created on 28-3-17
 *
 * Stores all information about a cell on the field, also contains
 * a parsing function.
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class Cell {

    private String playerId;
    private String unitPlayerId;
    private int strength;
    private int unitCount;
    private Point coordinate;

    public Cell(String playerId, int strength, String unitPlayerId, int unitCount) {
        this.strength = strength;
        this.playerId = playerId;
        this.unitPlayerId = unitPlayerId;
        this.unitCount = unitCount;
    }

    public static Cell fromString(String input) {
        try {
            String[] split = input.split(";");
            String[] strength = split[0].split(":");
            String[] units = split[1].split(":");

            return new Cell(
                    strength[0],
                    Integer.parseInt(strength[1]),
                    units[0],
                    Integer.parseInt(units[1]));
        } catch (Exception ex) {
            throw new RuntimeException("Failed to parse cell:" + ex.getMessage());
        }
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public String getPlayerId() {
        return this.playerId;
    }

    public String getUnitPlayerId() {
        return this.unitPlayerId;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getUnitCount() {
        return this.unitCount;
    }

    public Point getCoordinate() {
        return this.coordinate;
    }
}
