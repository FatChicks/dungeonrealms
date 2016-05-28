package org.dungeonrealms.game.world;

import org.dungeonrealms.game.tier.Tier;

/**
 * Created by Dr. Nick Doran on 5/20/2016.
 */
public enum Settlements {

    TUTORIAL_ISLAND(-1, "Tutorial Island", "tutorial_island", new String[]{
            "The Dungeon Realms Tutorial Island is where all new players go",
            "when they first join the Dungeon Realms experience.",
            "It is an island that is off the coast of Cyrennica's waters and contains",
            "many different NPC's which explain the mechanics of Dungeon Realms.",
    }, new Area(969.5, 32, -179.5), new Tier.TierType[]{}, PrimaryZone.SAFEZONE, new Inhabitants[]{}),

    CYRENNICA(0, "Cyrennica", "cityofcyrennica", new String[]{
            "Around 120 AMi is when the first human city in Andalucia was built by the second generation of Andalaucians.",
            "It was built by the first crowned Leader of the tribes, Thelogian. He named the city after the name of his wife, Cyrennica.",
            "Cyrennica was built with the Limestone and Sand in the nearby cliffs and mountains, they had also developed great craftsmanship and artistry with their interactions with the wisps.",
            "The Andalucians, fast and eager learners, were able to create incredible structures in Cyrennica that stand till this day.",
            "Sunstrider, a member of the Navu tribe, was to be the next leader of Cyrennica if not for Gorgath plumaging the world into war.",
            "The events folding past his attack are yet to be told, however it is hinted that the battle ended in defeat as King Bulwar now sits on the throne."
    }, new Area(-365, 83, 376), new Tier.TierType[]{Tier.TierType.TIER_0}, PrimaryZone.SAFEZONE, new Inhabitants[]{Inhabitants.HUMAN_TRIBES}),

    HARRISONS_FIELD(1, "Harrisons Field", "", new String[]{
            "Harrisons Field is a small broken and ruined town that remains after the Battle over Harrisons that was once won by the Heroes of Andalucia."
    }, new Area(-1, -1), new Tier.TierType[]{Tier.TierType.TIER_1}, PrimaryZone.MIXED, new Inhabitants[]{Inhabitants.BANDIT}),

    TRIPOLI(2, "Tripoli", "cityofcyrennica", new String[]{
            "Tripoli is the main city of Al Sahra, not including the guild territories."
    }, new Area(-1, -1), new Tier.TierType[]{Tier.TierType.TIER_1, Tier.TierType.TIER_2, Tier.TierType.TIER_3}, PrimaryZone.SAFEZONE, new Inhabitants[]{Inhabitants.LIZARDMEN, Inhabitants.TRIPOLI_SOLDIER}),

    GLOOMY_VILLAGE(3, "Gloomy Village", "", new String[]{}, new Area(-1, -1), null, null, null),

    CREST_GUARD(4, "Crest Guard", "", new String[]{
            "During a time of peace and prosperity, tribes roamed throughout Crestguard and made it their home.",
            "In addition, they constructed the Avalon Keep.",
            "When the Ember War broke out, the tribe retreated into the keep and sealed themselves away.",
            "The tribe was wrong when they thought that the Daemon forces would be unable to breach the lost city and enter it.",
            "The forces of Gorgath managed to break through the gate and effectively purge the entire of the city, bar those that were able to escape.",
            "They then took it over, and for a long time none dared venture there in fear of what might be lurking there.",
            "Most of the lost city is entirely overrun but the monsters that invaded it do not leave due to the defeat of their masters at Maltai.",
    }, new Area(-1573, 95, -497), new Tier.TierType[]{Tier.TierType.TIER_3, Tier.TierType.TIER_4}, PrimaryZone.SAFEZONE, new Inhabitants[]{/* TODO: Inhabitants */}),

    DARKOAT_TAVERN(5, "Darkoat Tavern", "", new String[]{
            "The Dark Oak Tavern is located on the eastern border of the Darkoak Forest.",
            "It is the last stop before entering Jagged Rocks, which is full of tougher enemies than you will find in Dark Oak Forest.",
            "There is a bank chest, a merchant, a skill trainer, and an anvil here.",
            "This is a common place to find players entering Jagged Rocks.",
    }, new Area(280, 1130), new Tier.TierType[]{}, PrimaryZone.SAFEZONE, new Inhabitants[]{
            /*
            There are no mobs inside of Darkoak Tavern, however Darkoak Forest which surrounds the tavern is a hotspot for tier 2 farming.
            Many new players come to the forest to farm because of its many low tier mobs and chest routes. Be careful though,
             it is easy to become overwhelmed by the sheer number of mobs in the forest.
             */
    }),

    TROLLSBANE_TAVERN(6, "Trollsbane Tavern", "", new String[]{
            "Trollsbane Tavern or The Lazy Skeleton Tavern is a small Safe-Zone deep inside Jagged Rocks.",
            "It is very near the Crystalpeak Tower, Castle, and Trollingor, which are all common places for players to farm/raid.",
            "The teleport scroll \"Trollsbane Tavern\" teleports here.",
            "The tavern has no bank or anvil, but does have a merchant.",
            "Players looking to raid the tower or castle often meet up here.",
            "The tavern has many skulls and fires, and appears to have seen many battles.",
            "There are fires all over the tavern, and a big hole from being hit by a catapult.",
            "",
            "",
    }, new Area(-1, -1), null, PrimaryZone.SAFEZONE, new Inhabitants[]{Inhabitants.WORKERS}),

    FISHING_VILLAGE(7, "Fishing Village", "", new String[]{
            "The fishing village was a peaceful village that made a living off of marketing fish.",
            "It soon meet its fate when trolls started to come from the inner depths of the forest to over take the village.",
            "No one knows what happened to the residents of the village.",
            "Whether they fled or fought only the bank teller may know.",
    }, new Area(-1, -1), new Tier.TierType[]{Tier.TierType.TIER_1, Tier.TierType.TIER_2}, null, new Inhabitants[]{Inhabitants.TROLL});

    private int id;
    private String name;
    private String regionName;
    private String[] desc;
    private Area area;
    private Tier.TierType[] tier;
    private PrimaryZone zone;
    private Inhabitants[] inhabitants;

    /**
     * @param id          Our id, for referential points.
     * @param name        General name.
     * @param regionName  The region's name.
     * @param desc        Description
     * @param area        Primary Area Location.
     * @param tier        Type of tier mobs.
     * @param zone        Zones
     * @param inhabitants Inhabitants of the area.
     */
    Settlements(int id, String name, String regionName, String[] desc, Area area, Tier.TierType[] tier, PrimaryZone zone, Inhabitants[] inhabitants) {
        this.id = id;
        this.name = name;
        this.regionName = regionName;
        this.desc = desc;
        this.area = area;
        this.tier = tier;
        this.zone = zone;
        this.inhabitants = inhabitants;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegionName() {
        return regionName;
    }

    public String[] getDesc() {
        return desc;
    }

    public Area getArea() {
        return area;
    }

    public Tier.TierType[] getTier() {
        return tier;
    }

    public PrimaryZone getZone() {
        return zone;
    }

    public Inhabitants[] getInhabitants() {
        return inhabitants;
    }

    /**
     * @param regionName The name of the region.
     * @return The associated Settlement object for that region.
     */
    public static Settlements getSettlementByRegion(String regionName) {
        for (Settlements sm : values()) {
            if (sm.getRegionName().equalsIgnoreCase(regionName)) {
                return sm;
            }
        }
        return null;
    }

    /**
     * @param name The name of the Settlement.
     * @return The associated Settlement object for that region.
     */
    public static Settlements getSettlementByName(String name) {
        for (Settlements sm : values()) {
            if (sm.getName().equalsIgnoreCase(name)) {
                return sm;
            }
        }
        return null;
    }
}
