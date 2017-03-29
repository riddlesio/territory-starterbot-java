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

package player;

/**
 * player.Player - Created on 28-3-17
 *
 * Stores all information about a player
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class Player {

    private String name;
    private int unitCount;
    private int territoryStrength;
    private int territoryCount;

    public Player(String playerName) {
        this.name = playerName;
    }

    public String getName() {
        return this.name;
    }

    public int getUnitCount() {
        return this.unitCount;
    }

    public void setUnitCount(int livingCells) {
        this.unitCount = livingCells;
    }

    public int getTerritoryStrength() {
        return this.territoryStrength;
    }

    public void setTerritoryStrength(int territoryStrength) {
        this.territoryStrength = territoryStrength;
    }

    public int getTerritoryCount() {
        return this.territoryCount;
    }

    public void setTerritoryCount(int territoryCount) {
        this.territoryCount = territoryCount;
    }
}
