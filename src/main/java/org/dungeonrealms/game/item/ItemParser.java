package org.dungeonrealms.game.item;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Dr. Nick Doran on 5/20/2016.
 */
public class ItemParser {

    private ItemStack itemStack;

    private long existence;
    private String name;

    //TODO: Stats

    private long durability;
    private long maxDurability;

    private JSONObject itemData;

    /**
     * @param itemStack The ItemStack you're parsing.
     */
    public ItemParser(ItemStack itemStack) {
        this.itemStack = itemStack;

        CraftItemStack craftStack = CraftItemStack.asCraftCopy(getItemStack());
        NbtCompound compound = NbtFactory.asCompound(NbtFactory.fromItemTag(craftStack));

        if (compound.containsKey("data")) {
            try {
                this.itemData = (JSONObject) new JSONParser().parse(compound.getString("data"));
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }
            this.existence = Long.valueOf(getItemData().get("existence").toString());
            this.name = getItemData().get("name").toString();

            JSONObject statObject = (JSONObject) getItemData().get("stats");

            //TODO: stats

            this.durability = Integer.valueOf(getItemData().get("durability").toString());
            this.maxDurability = Integer.valueOf(getItemData().get("maxDurability").toString());
        }
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public long getExistence() {
        return existence;
    }

    public String getName() {
        return name;
    }

    public long getDurability() {
        return durability;
    }

    public long getMaxDurability() {
        return maxDurability;
    }

    public JSONObject getItemData() {
        return itemData;
    }
}
