package org.anirudh_noel.services;

import org.anirudh_noel.Start;
import org.anirudh_noel.model.PocketBlock;
import org.anirudh_noel.model.PocketBlockTypes;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PocketBlockService {

    private Start plugin;
    /**
     * List is holding placed pocket blocks
     */
    private List<PocketBlock> placedPocketBlocks;

    public PocketBlockService(Start plugin) {
        this(plugin, new ArrayList<>());
    }

    public PocketBlockService(Start plugin, List<PocketBlock> placedPocketBlocks) {
        this.plugin = plugin;
        this.placedPocketBlocks = placedPocketBlocks;
    }

    public PocketBlock createPocketBlock(PocketBlockTypes type) {
        PocketBlock block = new PocketBlock(type);
        if (block.registerClipboard(this.plugin)) {
            this.placedPocketBlocks.add(block);
            return block;
        }
        return null;
    }

    public void removePocketBlock(PocketBlock pocketBlock) {
        this.placedPocketBlocks.remove(pocketBlock);
        // TODO remove block from map?
    }


    public boolean isPocketBlock(ItemStack item) {

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();

        for (PocketBlockTypes type : PocketBlockTypes.values()) {
            for (int i = 0; i < lore.size(); i++) {
                try {
                    if (!lore.get(i).equals(type.getLore().get(i))) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    return false;
                }
            }
        }
        return true;

    }

    public List<PocketBlock> getPlacedPocketBlocks() {
        return placedPocketBlocks;
    }

    public boolean contains(ItemStack stack) {
        for (PocketBlock block : placedPocketBlocks)
            if (block.getItemStack().isSimilar(stack))
                return true;
        return false;
    }

    public PocketBlock getPocketBlock(ItemStack stack) {
        for (PocketBlock block : placedPocketBlocks)
            if (block.getItemStack().isSimilar(stack))
                return block;
        return null;
    }
}
