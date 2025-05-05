package org.Group34.model.map;

import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;

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
                // Can move if empty or it's the target cell
                if (entities[nx][ny] != null && !(nx == tx && ny == ty)) continue;

                visited[nx][ny] = true;
                q.add(new Node(nx, ny, cur.steps + 1));
            }
        }

        // No path found
        return 0;
    }

    // TODO how player enters the house menu
    public void movePlayer(Player player, Integer targetX, Integer targetY) {
        int playerX = player.getLocation()[0];
        int playerY = player.getLocation()[1];
        Entity[][] entities;

        if (player.getCurrentSpace().equals(NPCVillage) && targetX == VILLAGE_EXIT_X &&
            targetY == VILLAGE_EXIT_Y){
            player.setCurrentSpace(playerFarms.get(player));
            entities = player.getCurrentSpace().entities();
            entities[FARM_EXIT_X][FARM_EXIT_Y] = player;
            player.setLocation(new int[]{FARM_EXIT_X, FARM_EXIT_Y});
        }
        else if (targetX == FARM_EXIT_X && targetY == FARM_EXIT_Y){
            player.setCurrentSpace(NPCVillage);
            entities = player.getCurrentSpace().entities();
            entities[VILLAGE_EXIT_X][VILLAGE_EXIT_Y] = player;
            player.setLocation(new int[]{VILLAGE_EXIT_X, VILLAGE_EXIT_Y});
        }
        else {
            entities = player.getCurrentSpace().entities();
            entities[playerX][playerY] = null;
            entities[targetX][targetY] = player;
            player.setLocation(new int[]{targetX, targetY});
        }
    }
}
