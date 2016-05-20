package org.dungeonrealms.io;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class IOUtils {

    /**
     * @param base64String The base64 String of a ItemStack object.
     * @return The ItemStack un-serialized.
     */
    public static ItemStack itemStackFromBase64(String base64String) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(base64String));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack items = (ItemStack) dataInput.readObject();
            dataInput.close();
            return items;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param item The item you're serializing.
     * @return The serialized base64 string representation of that ItemStack object.
     */
    public static String itemStackToBase64(ItemStack item) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(item);
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

}
