package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Player;
import org.Group34.model.items.Item;
import org.Group34.model.Time;
import org.Group34.model.items.crafting.ProcessorCraft;

import java.util.HashMap;

public class ArtisanController {
    private final HashMap<ProcessorCraft, Time> timeToResult = new HashMap<>();
    private final HashMap<ProcessorCraft, Item> result = new HashMap<>();

    public Result useArtisan(ProcessorCraft machine, Player player, Time time, Item input_1, Item input_2){
        Item food;

        if (timeToResult.containsKey(machine)) {
            if (time.compareTo(timeToResult.get(machine)) >= 0)
                return new Result(false, "Machine is full. Take Item inside it with get Artisan command.");
            return new Result(false, "This Machine is already in process.");
        }

        if (input_2 == null) food = machine.process(player, input_1);
        else food = machine.process(player, input_1, input_2);


        if (food == null)
            return new Result(false, "You imported wrong inputs.");
        else if (food.equals(input_1))
            return new Result(false, "You don't have enough amount of " + food.getName() + " in your inventory.");

        Time readyTime = time.copy();
        readyTime.addDays(machine.getDaysToComplete());
        readyTime.addHours(machine.getHoursToComplete());
        timeToResult.put(machine, readyTime);
        result.put(machine, food);


        if (time.equals(readyTime)){
            return new Result(true, "Your Order is ready. Use get Artisan command.");
        }
        return new Result(true, "Your order will be completed at " + readyTime.toString());
    }

    public Result getArtisan(ProcessorCraft machine, Player player, Time time){
        if (!timeToResult.containsKey(time))
            new Result(false, "Your machine is not in process.");
        if (time.compareTo(timeToResult.get(machine)) < 0)
            return new Result(false, "Your order is still on process get back at "
                    + timeToResult.get(machine).toString());

        player.addToInventory(result.get(machine), 1);
        Item item = result.remove(machine);
        timeToResult.remove(machine);

        return new Result(true, "Item " + item.getName() + " added to your inventory" );
    }
}
