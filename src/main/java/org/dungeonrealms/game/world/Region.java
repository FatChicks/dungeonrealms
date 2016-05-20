package org.dungeonrealms.game.world;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public enum Region {

    PLAINS_OF_CYRENE(0, "Plains of Cyrene", new AreaDifficulty[]{AreaDifficulty.LOW}, PrimaryZone.WILDERNESS, new Area(-765, 778), Inhabitants.BANDIT),
    DARKOAT_FOREST(1, "Darkoat Forest", new AreaDifficulty[]{AreaDifficulty.MEDIUM}, PrimaryZone.WILDERNESS, new Area(-523, 874), Inhabitants.TROLL, Inhabitants.GOBLIN),
    DEAD_PEAKS(2, "Deadpeaks", new AreaDifficulty[]{AreaDifficulty.MEDIUM, AreaDifficulty.HIGH}, PrimaryZone.CHAOTIC, new Area(-1560, 784), Inhabitants.BANDIT, Inhabitants.DAEMON),
    JAGGED_ROCKS(3, "Jagged Rocks", new AreaDifficulty[]{AreaDifficulty.MEDIUM}, PrimaryZone.CHAOTIC, new Area(390, 1134), Inhabitants.GOBLIN, Inhabitants.TROLL),
    AL_SAHRA(4, "Al Sahra", new AreaDifficulty[]{AreaDifficulty.LOW, AreaDifficulty.MEDIUM}, PrimaryZone.CHAOTIC, new Area(-1318, 520), Inhabitants.ZOMBIE, Inhabitants.SKELETON),
    THE_DREADWOOD(5, "The Dreadwood", new AreaDifficulty[]{AreaDifficulty.LOW, AreaDifficulty.MEDIUM}, PrimaryZone.WILDERNESS, new Area(-591, -4), Inhabitants.NAGA),
    AVALON_PEAKS(6, "Avalon Peaks", new AreaDifficulty[]{AreaDifficulty.MEDIUM, AreaDifficulty.HIGH}, PrimaryZone.WILDERNESS, new Area(-841, -621), Inhabitants.DAEMON),

    /**
     * Peaceful Settlements:
     * Despite much of Andalucia being dangerous in nature, there still remain a number of safe havens in which adventurers may gain rest and respite.
     * Whilst few and far between, these 'safezones' often contain banks, merchants, and other useful aspects that make them invaluable to the player.
     */

    CYRENNICA(10, "Cyrennica", new AreaDifficulty[]{}, PrimaryZone.SAFEZONE, new Area(-365, 83, 376), Inhabitants.HUMAN_TRIBES),
    HARRISONS_FIELD(11, "Harrisons Field", new AreaDifficulty[]{}, PrimaryZone.SAFEZONE, new Area(-1, -1), Inhabitants.BANDIT),;

    private int id;
    private String name;
    private AreaDifficulty[] difficulty;
    private PrimaryZone zone;
    private Inhabitants[] inhabitants;
    private Area area;

    /**
     * @param id          Our id, for referential points.
     * @param name        Name of Region.
     * @param difficulty  General difficulty of region.
     * @param zone        The zone type.
     * @param area        The area object of primary spawn location.
     * @param inhabitants Creatures in area.
     */
    Region(int id, String name, AreaDifficulty[] difficulty, PrimaryZone zone, Area area, Inhabitants... inhabitants) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.zone = zone;
        this.inhabitants = inhabitants;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AreaDifficulty[] getDifficulty() {
        return difficulty;
    }

    public PrimaryZone getZone() {
        return zone;
    }

    public Inhabitants[] getInhabitants() {
        return inhabitants;
    }

    public Area getArea() {
        return area;
    }
}