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

package bot;

import java.util.ArrayList;
import java.util.Random;

import board.Board;
import board.Cell;
import move.Move;
import move.MoveType;
import move.UnitMove;

/**
 * bot.BotStarter - Created on 28-3-17
 *
 * Magic happens here. You should edit this file, or more specifically
 * the doMove() method to make your bot do more than random moves.
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class BotStarter {

    private Random random;

    private BotStarter() {
        this.random = new Random();
    }

    /**
     * You should edit this method to make your bot smarter.
     * Currently tries to move a random amount of units on every cell that contains
     * units. If the random amount is 0, no units will be moved from the cell.
     * @param state The current bot state
     * @return The Move object that contains all unit moves
     */
    public Move doMove(BotState state) {
        Board board = state.getBoard();

        ArrayList<UnitMove> unitMoves = new ArrayList<>();
        for (Cell myCell : board.getMyUnitCells()) {
            int amount = this.random.nextInt(myCell.getUnitCount() + 1);

            if (amount <= 0) continue;

            ArrayList<MoveType> validDirections = board.getValidDirections(myCell);
            MoveType direction = validDirections.get(this.random.nextInt(validDirections.size()));

            unitMoves.add(new UnitMove(myCell.getCoordinate(), direction, amount));
        }

        return new Move(unitMoves);
    }

    public static void main(String[] args) {
        BotParser parser = new BotParser(new BotStarter());
        parser.run();
    }
}
