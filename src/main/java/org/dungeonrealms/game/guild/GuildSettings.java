package org.dungeonrealms.game.guild;

/**
 * Created by Dr. Nick Doran on 8/4/2016.
 */
public class GuildSettings {

    //todo: add more features.

    private boolean notifyLogin;
    private boolean notifyLoginSound;

    public GuildSettings(boolean notifyLogin, boolean notifyLoginSound) {
        this.notifyLogin = notifyLogin;
        this.notifyLoginSound = notifyLoginSound;
    }

    public boolean isNotifyLogin() {
        return notifyLogin;
    }

    public boolean isNotifyLoginSound() {
        return notifyLoginSound;
    }
}
