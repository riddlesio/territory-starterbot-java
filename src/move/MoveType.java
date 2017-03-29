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
 * move.MoveType - Created on 28-3-17
 *
 * All possible moveTypes, with their direction
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public enum MoveType {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public Point getDirection() {
        switch (this) {
            case UP:
                return new Point(0, -1);
            case DOWN:
                return new Point(0, 1);
            case LEFT:
                return new Point(-1, 0);
            case RIGHT:
                return new Point(1, 0);
            default:
                return new Point(0, 0);
        }
    }
}
