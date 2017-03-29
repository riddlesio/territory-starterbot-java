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

import java.util.Arrays;
import java.util.Scanner;

import move.Move;
import player.Player;

/**
 * bot.BotParser - Created on 28-3-17
 *
 * Class that will keep reading output from the engine.
 * Will either update the bot state or get actions.
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class BotParser {

    private Scanner scan;
    private BotStarter bot;
    private BotState currentState;

    BotParser(BotStarter bot) {
        this.scan = new Scanner(System.in);
        this.bot = bot;
        this.currentState = new BotState();
    }

    /**
     * Keeps consuming all input over the stdin channel
     */
    void run() {
        while (this.scan.hasNextLine()) {
            String line = this.scan.nextLine();

            if (line.length() == 0) continue;

            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "settings":
                    parseSettings(parts[1], parts[2]);
                    break;
                case "update":
                    if (parts[1].equals("game")) {
                        parseGameData(parts[2], parts[3]);
                    } else {
                        parsePlayerData(parts[1], parts[2], parts[3]);
                    }
                    break;
                case "action":
                    if (parts[1].equals("move")) {
                        this.currentState.setTimebank(Integer.parseInt(parts[2]));
                        Move move = this.bot.doMove(this.currentState);

                        if (move != null) {
                            System.out.println(move.toString());
                        } else {
                            System.out.println("pass");
                        }
                    }
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    /**
     * Parses all the game settings given by the engine
     * @param key Type of setting given
     * @param value Value
     */
    private void parseSettings(String key, String value) {
        try {
            switch (key) {
                case "timebank":
                    int time = Integer.parseInt(value);
                    this.currentState.setMaxTimebank(time);
                    this.currentState.setTimebank(time);
                    break;
                case "time_per_move":
                    this.currentState.setTimePerMove(Integer.parseInt(value));
                    break;
                case "player_names":
                    Arrays.stream(value.split(",")).
                            forEach(name -> {
                                Player player = new Player(name);
                                this.currentState.getPlayers().put(name, player);
                            });
                    break;
                case "your_bot":
                    this.currentState.setMyName(value);
                    break;
                case "your_botid":
                    int myId = Integer.parseInt(value);
                    int opponentId = 2 - (myId + 1);
                    this.currentState.getBoard().setMyId(myId);
                    this.currentState.getBoard().setOpponentId(opponentId);
                    break;
                case "board_width":
                    this.currentState.getBoard().setWidth(Integer.parseInt(value));
                    break;
                case "board_height":
                    this.currentState.getBoard().setHeight(Integer.parseInt(value));
                    break;
                case "max_rounds":
                    this.currentState.setMaxRounds(Integer.parseInt(value));
                    break;
                case "max_strength":
                    this.currentState.getBoard().setMaxStrength(Integer.parseInt(value));
                    break;
                default:
                    System.err.println(String.format(
                            "Cannot parse settings input with key '%s'", key));
            }
        } catch (Exception e) {
            System.err.println(String.format(
                    "Cannot parse settings value '%s' for key '%s'", value, key));
            e.printStackTrace();
        }
    }

    /**
     * Parse data about the game given by the engine
     * @param key Type of game data given
     * @param value Value
     */
    private void parseGameData(String key, String value) {
        try {
            switch (key) {
                case "round":
                    this.currentState.setRoundNumber(Integer.parseInt(value));
                    break;
                case "board":
                    this.currentState.getBoard().parseFromString(value);
                    break;
                default:
                    System.err.println(String.format(
                            "Cannot parse game data input with key '%s'", key));
            }
        } catch (Exception e) {
            System.err.println(String.format(
                    "Cannot parse game data value '%s' for key '%s'", value, key));
            e.printStackTrace();
        }
    }

    /**
     * Parse data about given player that the engine has sent
     * @param playerName Player name that this data is about
     * @param key Type of player data given
     * @param value Value
     */
    private void parsePlayerData(String playerName, String key, String value) {
        Player player = this.currentState.getPlayers().get(playerName);

        if (player == null) {
            System.err.println(String.format("Could not find player with name %s", playerName));
            return;
        }

        try {
            switch(key) {
                case "unit_count":
                    player.setUnitCount(Integer.parseInt(value));
                    break;
                case "territory_count":
                    player.setTerritoryCount(Integer.parseInt(value));
                    break;
                case "territory_strength":
                    player.setTerritoryStrength(Integer.parseInt(value));
                    break;
                default:
                    System.err.println(String.format(
                            "Cannot parse %s data input with key '%s'", playerName, key));
            }
        } catch (Exception e) {
            System.err.println(String.format(
                    "Cannot parse %s data value '%s' for key '%s'", playerName, value, key));
            e.printStackTrace();
        }
    }
}
