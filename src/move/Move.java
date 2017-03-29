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

import java.util.ArrayList;

/**
 * move.Move - Created on 28-3-17
 *
 * Contains all unit moves and a toString method.
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class Move {

    private ArrayList<UnitMove> unitMoves;

    public Move(ArrayList<UnitMove> unitMoves) {
        this.unitMoves = unitMoves;
    }

    public String toString() {
        if (this.unitMoves.size() <= 0) {
            return "pass";
        }

        StringBuilder builder = new StringBuilder();

        for (UnitMove unitMove : this.unitMoves) {
            builder.append(unitMove.toString()).append(" ");
        }

        return builder.toString().trim();
    }
}
