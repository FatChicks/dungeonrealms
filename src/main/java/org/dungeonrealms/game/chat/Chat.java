package org.dungeonrealms.game.chat;

/**
 * Created by Dr. Nick Doran on 8/4/2016.
 */
public class Chat {

    private ChatType chatType;
    private long lastChat;

    public Chat(ChatType chatType) {
        this.chatType = chatType;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public long getLastChat() {
        return lastChat;
    }

    public void setLastChat(long time) {
        this.lastChat = time;
    }
}
