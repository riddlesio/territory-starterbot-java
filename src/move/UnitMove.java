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

package move;

import java.awt.Point;

/**
 * move.UnitMove - Created on 28-3-17
 *
 * Stores the move of units from one cell to a direction
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class UnitMove {

    private Point coordinate;
    private MoveType direction;
    private int amount;

    public UnitMove(Point coordinate, MoveType direction, int amount) {
        this.coordinate = coordinate;
        this.direction = direction;
        this.amount = amount;
    }

    public String toString() {
        String coordinateString = String.format("%d,%d", this.coordinate.x, this.coordinate.y);

        return String.format("%s;%s;%d", coordinateString, this.direction, this.amount);
    }
}
