package org.Group34.model.map;

import org.Group34.model.Result;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.WalkAble;
import org.Group34.model.entities.buildings.Building;
import org.Group34.model.enums.Color;
import org.Group34.model.enums.FarmType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Each Map has to remember each Farm is for what player
 * Map has to handle switching between its spaces
 * There is no universal location for entities in this game
 */

public record Map(HashMap<Player, Space> playerFarms, Space NPCVillage) {

    private static final int[][] DIRS = {
            {-1,-1},{-1,0},{-1,1},
            { 0,-1},       { 0,1},
            { 1,-1},{ 1,0},{ 1,1}
    };
    private static final int FARM_EXIT_X = 50;
    private static final int FARM_EXIT_Y = 0;
    private static final int VILLAGE_EXIT_X = 0;
    private static final int VILLAGE_EXIT_Y = 0;



    /**
     * Finds the minimum number of moves from the player's current location to (tx,ty).
     * @param player  the Player, with player.x and player.y set
     * @param tx      target x
     * @param ty      target y
     * @return        minimum moves to reach (tx,ty), or 0 if no path exists
     */
    public int findPath(Player player, Integer tx, Integer ty) {
        int rows = player.getCurrentSpace().height();
        int cols = player.getCurrentSpace().width();
        int playerX = player.getLocation()[0];
        int playerY = player.getLocation()[1];
        Entity[][] entities = player.getCurrentSpace().entities();
        boolean[][] visited = new boolean[rows][cols];

        // Node holds coordinates + steps from start
        class Node {
            int x, y, steps;
            Node(int x, int y, int steps) {
                this.x = x;
                this.y = y;
                this.steps = steps;
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(playerX, playerY, 0));
        visited[playerX][playerY] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            // If we've reached the target, return the steps taken
            if (cur.x == tx && cur.y == ty) {
                return cur.steps;
            }

            // Explore neighbors
            for (int[] d : DIRS) {
                int nx = cur.x + d[0], ny = cur.y + d[1];
                if (nx < 0 || nx >= rows || ny < 0 || ny >= cols) continue;
                if (visited[nx][ny]) continue;
                if (!canPassThrough(entities[nx][ny], nx, ny, tx, ty)) continue;

                visited[nx][ny] = true;
                q.add(new Node(nx, ny, cur.steps + 1));
            }
        }

        // No path found
        return 0;
    }

    /**
     * Returns true if the player can move onto (x,y).
     * Movement is allowed if:
     *   1) It's the goal cell, regardless of its contents.
     *   2) The cell is empty (null).
     *   3) The cell contains a WalkAble entity.
     */
    private boolean canPassThrough(Entity e, int x, int y, int tx, int ty) {
        // Always allow stepping onto the target cell
        if (x == tx && y == ty) {
            return true;
        }
        // Otherwise only if empty or WalkAble
        return (e == null) || (e instanceof WalkAble);
    }


    // TODO how player enters the house menu
    public Result movePlayer(Player player, Integer targetX, Integer targetY) {
        int playerX = player.getLocation()[0];
        int playerY = player.getLocation()[1];
        Entity[][] entities;

        if (player.getCurrentSpace().equals(NPCVillage) && targetX == VILLAGE_EXIT_X &&
            targetY == VILLAGE_EXIT_Y){
            player.setCurrentSpace(playerFarms.get(player));
            entities = player.getCurrentSpace().entities();
            entities[FARM_EXIT_X][FARM_EXIT_Y] = player;
            player.setLocation(new int[]{FARM_EXIT_X, FARM_EXIT_Y});
            return new Result(true, "You have entered Your farm");
        }
        else if (targetX == FARM_EXIT_X && targetY == FARM_EXIT_Y){
            player.setCurrentSpace(NPCVillage);
            entities = player.getCurrentSpace().entities();
            entities[VILLAGE_EXIT_X][VILLAGE_EXIT_Y] = player;
            player.setLocation(new int[]{VILLAGE_EXIT_X, VILLAGE_EXIT_Y});
            return new Result(true, "You have entered NPC Village");
        }
        else {
            entities = player.getCurrentSpace().entities();
            entities[playerX][playerY] = null;
            entities[targetX][targetY] = player;
            player.setLocation(new int[]{targetX, targetY});
            return new Result(true, "Your character have been moved to: " + "<" + targetX + " ," + targetY + ">");
        }
    }

    public Space getCurrentPlayerFarm(Player currentPlayer) {
        return playerFarms.get(currentPlayer);
    }

    public ArrayList<Space> getSpaces() {
        ArrayList<Space> spaces = new ArrayList<>();
        spaces.addAll(playerFarms.values());
        spaces.add(NPCVillage);
        return spaces;
    }

    public Result printMap(Integer beginX, Integer beginY, Integer size, Entity[][] entities) {
        StringBuilder message = new StringBuilder();

        if (beginX == null || beginY == null || size == null)
            return new Result(false, "size or center location should be number format");

        int endX = Math.min(MapBuilder.SPACE_WIDTH - 1, beginX + size);
        int endY = Math.max(MapBuilder.SPACE_HEIGHT - 1, beginY + size);

        for (int i = beginX; i < endX; i++) {
            for (int j = beginY; j < endY; j++) {
                message.append(entities[i][j]).append(" ");
            }
            message.append("\n");
        }

        return new Result(true, message.toString());
    }

    public Result helpMap() {
        return new Result(true, "player:      P\n" +
                "house: " + Color.BROWN + "       H" + Color.RESET + "\n" +
                "green house: " + Color.YELLOW + "G" + Color.RESET + "\n" +
                "lake: " + Color.CYAN + "        L" + Color.RESET + "\n" +
                "quarry: " + Color.GRAY + "      Q" + Color.RESET + "\n" +
                "foraging: " + Color.RED + "    F" + Color.RESET + "\n" +
                "stone: " + Color.GRAY + "       S" + Color.RESET + "\n" +
                "tree: " + Color.GREEN + "        T" + Color.RESET);
    }
}
