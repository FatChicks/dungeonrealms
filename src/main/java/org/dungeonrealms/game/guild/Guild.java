package org.dungeonrealms.game.guild;

import org.bukkit.inventory.ItemStack;
import org.dungeonrealms.game.player.FakePlayer;

import java.util.List;

/**
 * Created by Dr. Nick Doran on 8/4/2016.
 */
public class Guild {

    private int id;
    private String tag;

    private FakePlayer owner;
    private List<FakePlayer> moderators;
    private List<FakePlayer> members;

    private List<ItemStack> vault;
    private int gems;

    private GuildSettings guildSettings;

    public Guild() {
    }

    /**
     * @param id         The guild's id.
     * @param tag        The guild's prefix tag.
     * @param owner      The owner of the guild.
     * @param moderators The moderators of the guild.
     * @param members    The members of the guild.
     * @param vault      The guild's vault.
     * @param gems       The guild's shared gem collection.
     */
    public Guild(int id, String tag, FakePlayer owner, List<FakePlayer> moderators, List<FakePlayer> members, List<ItemStack> vault, int gems, GuildSettings settings) {
        this.id = id;
        this.tag = tag;
        this.owner = owner;
        this.moderators = moderators;
        this.members = members;
        this.vault = vault;
        this.gems = gems;
        this.guildSettings = settings;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public FakePlayer getOwner() {
        return owner;
    }

    public List<FakePlayer> getModerators() {
        return moderators;
    }

    public List<FakePlayer> getMembers() {
        return members;
    }

    public List<ItemStack> getVault() {
        return vault;
    }

    public int getGems() {
        return gems;
    }

    public GuildSettings getGuildSettings() {
        return guildSettings;
    }

    public void addModerator(FakePlayer fakePlayer) {
        this.moderators.add(fakePlayer);
    }

    public void addMember(FakePlayer fakePlayer) {
        this.members.add(fakePlayer);
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setOwner(FakePlayer owner) {
        this.owner = owner;
    }

    public void setModerators(List<FakePlayer> moderators) {
        this.moderators = moderators;
    }

    public void setMembers(List<FakePlayer> members) {
        this.members = members;
    }

    public void setVault(List<ItemStack> vault) {
        this.vault = vault;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public void setGuildSettings(GuildSettings guildSettings) {
        this.guildSettings = guildSettings;
    }
}
